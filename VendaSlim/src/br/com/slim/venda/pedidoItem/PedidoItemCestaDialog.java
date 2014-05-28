package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import br.com.slim.venda.R;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.pedido.PedidoPgtoFragments;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;

@SuppressLint("ValidFragment")
public class PedidoItemCestaDialog extends DialogFragment{
	
	ImageButton btnMais;
	ImageButton btnMenos;
	TextView tvItemDescricao;
	TextView tvPreco;
	TextView tvEstoque;	
	
	EditText edQtd;
	EditText edPrecoVenda;
	EditText edDescontoReais;	
	EditText edDescontoPercentual;
	TextView tvDescontoReais;
	TextView tvDesconto;
	
	
	//final ArrayList<PedidoItemVO> lsPedidoItem;
	final PedidoItemVO pedidoItemVO;
	final PedidoItemCestaAdapter pedidoItemCestaAdapter;
	ArrayList<Integer> lsItensIdsAdicionados;
	boolean editListnerAbilitado = true; 
	Context context;
	AlertDialog alertDialog;
	final PedidoVO pedidoVO;
	PedidoItemFragment pedidoItemFragment;
	PedidoPgtoFragments pedidoPgtoFragment;
	PedidoItemCestaFragment pedidoItemCestaFragment;
	
	public PedidoItemCestaDialog(Context context,final PedidoItemVO pedidoItemVO,final PedidoVO pedidoVO, 
			PedidoItemCestaAdapter pedidoItemCestaAdapter, ArrayList<Integer> lsItensIdsAdicionados, PedidoItemFragment pedidoItemFragment, PedidoPgtoFragments pedidoPgtoFragment,
			PedidoItemCestaFragment pedidoItemCestaFragment) {
		
		this.pedidoItemVO = pedidoItemVO;
		this.pedidoItemCestaAdapter = pedidoItemCestaAdapter;
		this.lsItensIdsAdicionados = lsItensIdsAdicionados;
		this.context = context;
		this.pedidoVO = pedidoVO;
		this.pedidoItemFragment = pedidoItemFragment;
		this.pedidoPgtoFragment =pedidoPgtoFragment;
		this.pedidoItemCestaFragment = pedidoItemCestaFragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflate  = getLayoutInflater();
		View view = inflate.inflate(R.layout.pedido_item_dialog_add,null);
		
		btnMais = (ImageButton) view.findViewById(R.id.btnAdd);
		btnMenos = (ImageButton) view.findViewById(R.id.btnRemove);
		edQtd = (EditText) view.findViewById(R.id.edQtd);
		edPrecoVenda = (EditText) view.findViewById(R.id.edPrecoVenda);
		edDescontoReais = (EditText) view.findViewById(R.id.edDescontoEmReais);
		edDescontoPercentual = (EditText) view.findViewById(R.id.edDescontoPercentual);		
		tvItemDescricao = (TextView) view.findViewById(R.id.tvDescricao);
		tvPreco = (TextView) view.findViewById(R.id.tvPrecoEd);
		tvEstoque= (TextView) view.findViewById(R.id.tvEstoqueEd);
		tvDescontoReais = (TextView) view.findViewById(R.id.tvDescontoReais);
		tvDesconto = (TextView) view.findViewById(R.id.tvDesconto);

		edPrecoVenda.addTextChangedListener(new PrecoListner());
		edDescontoReais.addTextChangedListener(new DescontoReaisListner());
		edDescontoPercentual.addTextChangedListener(new DescontoPercentualListner());

		edQtd.setText(String.valueOf(pedidoItemVO.getQuantidade()));
		tvItemDescricao.setText(pedidoItemVO.getItemVO().getDescricao());
		tvEstoque.setText(String.valueOf(pedidoItemVO.getItemVO().getEstoque()));
		tvPreco.setText(String.valueOf(pedidoItemVO.getItemVO().getPreco()));
		edPrecoVenda.setText(String.valueOf(pedidoItemVO.getPrecoVenda()));		
		edDescontoReais.setText(String.valueOf(pedidoItemVO.getDesconto()));

		btnMais.setOnClickListener(new Mais());
		btnMenos.setOnClickListener(new Menos());

		alertDialog = new AlertDialog.Builder(context)
		.setView(view)
		.setTitle("Editar/Remover Produtos do Carrinho")
		.setNegativeButton("Remover do carrinho", new RemoveCesta())
		.setPositiveButton("Confirma Edição",null)
		.create();

		alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
				b.setOnClickListener(new AdicionaCarrinho());
			}
		});


		return alertDialog;
	}
	
	private class AdicionaCarrinho implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			double precoVenda = Double.parseDouble(edPrecoVenda.getText().toString());
			double desconto = Double.parseDouble(edDescontoReais.getText().toString());
			double quantidade = Double.parseDouble(edQtd.getText().toString());
			if(ownStock(quantidade)){								
				pedidoItemVO.setPrecoVenda(precoVenda);
				pedidoItemVO.setDesconto(desconto);
				pedidoItemVO.setQuantidade(quantidade);				
				pedidoItemCestaAdapter.notifyDataSetChanged();
				pedidoItemCestaFragment.somaValorTotal();
				dismiss();
			} else {		        				
				Toast.makeText(context, "Este produto não possui estoque suficiente", Toast.LENGTH_LONG).show();
			}			
		}
	}
	

	//Possui estoque
	private boolean ownStock(double quantidade){
		if(!ConfigVO.permiteVendaEstoqueNegativo){
			if(pedidoVO.isEdition()){
				//Valida a quantidade
				if(pedidoItemVO.getQuantidade() + pedidoItemVO.getItemVO().getEstoque() < quantidade){
					return false;
				} else {
					//adiciona a diferença ao estoque
					atualizaEstoqueItem(quantidade);
				}
			} else {
				if(quantidade > pedidoItemVO.getItemVO().getEstoque()){
					return false;
				}
			}
		}
		return true;
	}
	//Atualiza o estoque do item 
	private void atualizaEstoqueItem(double quantidade){
		double estoqueAtualizado = pedidoItemVO.getQuantidade() + pedidoItemVO.getItemVO().getEstoque() - quantidade;
		pedidoItemVO.getItemVO().setEstoque(estoqueAtualizado);
	}

	private class RemoveCesta implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			pedidoVO.getPedidoItemVO().remove(pedidoItemVO);
			lsItensIdsAdicionados.remove((Integer)pedidoItemVO.getItemVO().getIdItem());
			atualizaEstoqueItem(0);
			close();
			//atualiza a lista
			pedidoItemCestaAdapter.notifyDataSetChanged();
			pedidoItemCestaFragment.somaValorTotal();
			if(pedidoItemFragment != null)
				pedidoItemFragment.updateFragment();
			
			if(pedidoPgtoFragment != null)
				pedidoPgtoFragment.updateFragment();
			
			
		}
	}
	
	private class Mais implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			double qtd = getQtd();
			edQtd.setText(String.valueOf(qtd+1));
		}
	}
	
	
	private class Menos implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			double qtd = getQtd();
			edQtd.setText(String.valueOf(qtd-1));
		}
	}
	
	public double getQtd(){
		double qtd = 1;
		try {
			qtd = Double.parseDouble(edQtd.getText().toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return qtd;
	}
	
	public void close(){
		dismiss();
	}
	
	
	private class PrecoListner implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(editListnerAbilitado){
				editListnerAbilitado = false;
				calculaDesconto();
				editListnerAbilitado = true;
			}
		}		
	}
	
	
	private class DescontoReaisListner implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(editListnerAbilitado){
				editListnerAbilitado = false;
				calculaDescontoReais();
				
				editListnerAbilitado = true;
			}
		}
	}
	
	
	private class DescontoPercentualListner implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(editListnerAbilitado){
				editListnerAbilitado = false;
				calculaDescontoPercentual();
				
				editListnerAbilitado = true;
			}
		}		
	}
	
	private void calculaDescontoPercentual(){
		double percentualReais = pedidoItemVO.getItemVO().getPrecoTabPreco() * 
				Convert.toDouble(edDescontoPercentual.getText().toString()) /100;
		edPrecoVenda.setText(Util.arredondaDouble(pedidoItemVO.getItemVO().getPrecoTabPreco() - percentualReais)+"");
		edDescontoReais.setText(Util.arredondaDouble(percentualReais)+"");
	}
	
	private void calculaDescontoReais(){
		double descontoReais = Convert.toDouble(edDescontoReais.getText().toString());
		edPrecoVenda.setText(Util.arredondaDouble(pedidoItemVO.getItemVO().getPrecoTabPreco() - descontoReais)+"");
		edDescontoPercentual.setText(Util.arredondaDouble(descontoReais * 100 / pedidoItemVO.getItemVO().getPrecoTabPreco())+"");
	}
	
	private void calculaDesconto(){
		double precoVenda = Convert.toDouble(edPrecoVenda.getText().toString());
		double precoFinal = Util.arredondaDouble(((precoVenda * 100 / pedidoItemVO.getItemVO().getPrecoTabPreco())-100)*-1);
		edDescontoPercentual.setText(
				precoFinal+"");
		double descontoReais = Util.arredondaDouble(pedidoItemVO.getItemVO().getPrecoTabPreco() - precoVenda);
		edDescontoReais.setText(descontoReais+"");
		setTextoDescontoAcrescimo(descontoReais);
	}
	
	private void setTextoDescontoAcrescimo(double valor){
		if(valor < 0){
			tvDesconto.setText("Acrescimo %");
			tvDescontoReais.setText("Acrescimo R$");
		} else {
			tvDesconto.setText("Desconto %");
			tvDescontoReais.setText("Desconto R$");
		}
	}
}
