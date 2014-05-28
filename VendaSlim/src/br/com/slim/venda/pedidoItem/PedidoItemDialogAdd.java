package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;
import java.util.List;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.R.color;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;

@SuppressLint("ValidFragment")
public class PedidoItemDialogAdd extends DialogFragment{
	
	ImageButton btnMais;
	ImageButton btnMenos;
	TextView tvItemDescricao;
	TextView tvPreco;
	TextView tvEstoque;	
	TextView tvDescontoReais;
	TextView tvDesconto;
	
	EditText edQtd;
	EditText edPrecoVenda;
	EditText edDescontoPercentual;
	EditText edDescontoReais;
	//final ArrayList<PedidoItemVO> lsPedidoItem;
	final ItemVO itemVO;
	PedidoItemAdapter pedidoItemAdapter;
	ArrayList<Integer> lsItensAdicionados;
	PedidoVO pedidoVO;
	boolean editListnerAbilitado = true; 
	boolean vendaPermitida = true;
	PedidoItemCestaFragment pedidoItenCestaFragment;
	
	Context context;
	public PedidoItemDialogAdd(Context context,final ItemVO itemVO,final PedidoVO pedidoVO,PedidoItemAdapter pedidoItemAdapter, 
			ArrayList<Integer> lsItensAdicionados, PedidoItemCestaFragment pedidoItenCestaFragment) {
		this.pedidoVO = pedidoVO;
		this.itemVO = itemVO;
		this.pedidoItemAdapter = pedidoItemAdapter;
		this.lsItensAdicionados = lsItensAdicionados;
		this.context = context;
		this.pedidoItenCestaFragment = pedidoItenCestaFragment;
	}
	AlertDialog alertDialog;
	
	@Override
	public Dialog onCreateDialog(
			Bundle savedInstanceState) {
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.pedido_item_dialog_add, null);
		
		alertDialog = new AlertDialog.Builder(context)
        .setView(view)
        .setTitle("Adicionar Produtos ao Carrinho")
        .setNegativeButton("Cancelar", new CancelarCarrinho())
        .setPositiveButton("Adicionar ao Carrinho",null)
        .create();
		
		alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
		    @Override
		    public void onShow(DialogInterface dialog) {
		        Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
		        b.setOnClickListener(new AdicionaCarrinho());
		    }
		});		
	
		
		btnMais = (ImageButton) view.findViewById(R.id.btnAdd);
		btnMenos = (ImageButton) view.findViewById(R.id.btnRemove);
		edQtd = (EditText) view.findViewById(R.id.edQtd);
		edPrecoVenda = (EditText) view.findViewById(R.id.edPrecoVenda);
		edDescontoPercentual = (EditText) view.findViewById(R.id.edDescontoPercentual);
		edDescontoReais = (EditText) view.findViewById(R.id.edDescontoEmReais);
		tvItemDescricao = (TextView) view.findViewById(R.id.tvDescricao);
		tvPreco = (TextView) view.findViewById(R.id.tvPrecoEd);
		tvEstoque= (TextView) view.findViewById(R.id.tvEstoqueEd);
		tvDescontoReais = (TextView) view.findViewById(R.id.tvDescontoReais);
		tvDesconto = (TextView) view.findViewById(R.id.tvDesconto);
		
		edQtd.setText("1.0");
		tvItemDescricao.setText(itemVO.getDescricao());
		tvEstoque.setText(String.valueOf(itemVO.getEstoque()));
		tvPreco.setText(String.valueOf(itemVO.getPrecoTabPreco()));
		edPrecoVenda.setText(String.valueOf(itemVO.getPrecoTabPreco()));
		edDescontoPercentual.setText("0.00");
		edDescontoReais.setText("0.00");
		edPrecoVenda.addTextChangedListener(new PrecoListner());
		edDescontoReais.addTextChangedListener(new DescontoReaisListner());
		edDescontoPercentual.addTextChangedListener(new DescontoPercentualListner());
		btnMais.setOnClickListener(new Mais());
		btnMenos.setOnClickListener(new Menos());
		/*builder.setTitle("Adicionar Produto");
		alertDialog = builder.create();*/		
		setCancelable(false);
		return alertDialog;		
	
	}
	
	private class AdicionaCarrinho implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			double precoVenda = Double.parseDouble(edPrecoVenda.getText().toString());
			double desconto = Double.parseDouble(edDescontoReais.getText().toString());
			double quantidade = Double.parseDouble(edQtd.getText().toString());
			if(ownStock(quantidade)){
				PedidoItemVO pedidoItemVO = new PedidoItemVO();
				pedidoItemVO.setItemVO(itemVO);
				pedidoItemVO.setPrecoVenda(precoVenda);
				pedidoItemVO.setDesconto(desconto);
				pedidoItemVO.setQuantidade(quantidade);
				pedidoVO.getPedidoItemVO().add(pedidoItemVO);				
				
				itemVO.setAdicionadoCesta(true);
				lsItensAdicionados.add(itemVO.getIdItem());
				pedidoItemAdapter.notifyDataSetChanged();		        				
				dismiss();
				if(pedidoItenCestaFragment != null)
					pedidoItenCestaFragment.updateFragment();
				
			} else {		        				
				Toast.makeText(context, "Produto não possui estoque suficiente", Toast.LENGTH_LONG).show();
			}			
		}
	}
	
	
	
	//Possui estoque
	private boolean ownStock(double quantidade){
		if(!ConfigVO.permiteVendaEstoqueNegativo){			
			if(quantidade > itemVO.getEstoque()){
				return false;
			}
		}
		return true;
	}

	private class CancelarCarrinho implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			PedidoItemDialogAdd.this.getDialog().cancel();
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
	
	private void calculaDescontoReais(){
		double descontoReais = Convert.toDouble(edDescontoReais.getText().toString());
		edPrecoVenda.setText(Util.arredondaDouble(itemVO.getPrecoTabPreco() - descontoReais)+"");
		edDescontoPercentual.setText(Util.arredondaDouble(descontoReais * 100 / itemVO.getPrecoTabPreco())+"");
	}
	
	private void calculaDescontoPercentual(){
		double percentualReais = itemVO.getPrecoTabPreco() * 
				Convert.toDouble(edDescontoPercentual.getText().toString()) /100;
		edPrecoVenda.setText(Util.arredondaDouble(itemVO.getPrecoTabPreco() - percentualReais)+"");
		edDescontoReais.setText(Util.arredondaDouble(percentualReais)+"");
	}
	
	private void calculaDesconto(){
		double precoVenda = Convert.toDouble(edPrecoVenda.getText().toString());
		double precoFinal = Util.arredondaDouble(((precoVenda * 100 / itemVO.getPrecoTabPreco())-100)*-1);
		if(precoFinal < 0)
			precoFinal = precoFinal * -1;
		edDescontoPercentual.setText(precoFinal+"");
		double descontoReais = Util.arredondaDouble(itemVO.getPrecoTabPreco() - precoVenda);
		if(descontoReais < 0)
			edDescontoReais.setText(descontoReais *-1+"");
		else 
			edDescontoReais.setText(descontoReais+"");
		setTextoDescontoAcrescimo(descontoReais);
	}
	
	private void setTextoDescontoAcrescimo(double valor){
		if(valor < 0){
			tvDesconto.setTextColor(getResources().getColor(R.color.green));
			tvDescontoReais.setTextColor(getResources().getColor(R.color.green));
			tvDesconto.setText("Acrescimo %");
			tvDescontoReais.setText("Acrescimo R$");
		} else  {
			tvDesconto.setTextColor(getResources().getColor(R.color.red));
			tvDescontoReais.setTextColor(getResources().getColor(R.color.red));
			tvDesconto.setText("Desconto %");
			tvDescontoReais.setText("Desconto R$");
		}
	}

	
	/*private class AlertDialogListner implements OnShowListener{
		@Override
		public void onShow(DialogInterface dialog) {
			Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
			btnAdd.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					if(vendaPermitida){
						alertDialog.dismiss();
					}
				}
			});			
		}
	}*/
}

