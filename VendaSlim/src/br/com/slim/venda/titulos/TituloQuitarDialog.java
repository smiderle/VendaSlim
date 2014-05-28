package br.com.slim.venda.titulos;

import java.util.Date;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.pedidopgto.PedidoPgtoDAO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;

@SuppressLint("ValidFragment")
public class TituloQuitarDialog extends DialogFragment{

	PedidoPgtoVO pedidoPgtoVO;
	TextView edNrParcela;
	TextView edVencimento;
	TextView edValor;
	TextView edJuros;
	TextView edValorTotal;
	TextView edDtPagamento;
	TextView edDtPagamentoEd;
	Context context;
	TituloExpandAdapter tituloExpAdapter;
	
	
	AlertDialog alertDialog;
	
	public TituloQuitarDialog(Context context, PedidoPgtoVO pedidoPgtoVO, TituloExpandAdapter tituloExpAdapter) {
		this.context = context;
		this.pedidoPgtoVO = pedidoPgtoVO;
		this.tituloExpAdapter = tituloExpAdapter;
	}
	
	@Override
	public Dialog onCreateDialog(
			Bundle savedInstanceState) {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.titulo_quitacao_dialog, null);	
	
		
		
		
		
		
		edNrParcela = (TextView) view.findViewById(R.id.tvParcelaEd);
		edVencimento = (TextView) view.findViewById(R.id.tvVencimentoEd);
		edValor = (TextView) view.findViewById(R.id.tvValorEd);
		edJuros = (TextView) view.findViewById(R.id.tvJurosEd);
		edValorTotal = (TextView) view.findViewById(R.id.tvTotalEd);
		edDtPagamento = (TextView) view.findViewById(R.id.tvDtPagamento);
		edDtPagamentoEd = (TextView) view.findViewById(R.id.tvDtPagamentoEd);
		
			
		if(pedidoPgtoVO.getParcelaPaga() == PedidoPgtoVO.PAGAMENTO_TOTAL){			
			alertDialog = new AlertDialog.Builder(context)
		        .setView(view)
		        .setTitle("Titulo Pago")
		        .setNegativeButton("Voltar", null)		       
		        .create();
			
			edDtPagamentoEd.setText(Convert.toDateStr(pedidoPgtoVO.getDtPagamento()));
		} else if(pedidoPgtoVO.getParcelaPaga() == PedidoPgtoVO.PAGAMENTO_PENDENTE){
			alertDialog = new AlertDialog.Builder(context)
		        .setView(view)
		        .setTitle("Pagar Titulo")
		        .setNegativeButton("Cancelar", null)
		        .setPositiveButton("Pagar",new PagarTitulo())
		        .create();
			
			edDtPagamento.setVisibility(View.INVISIBLE);
			edDtPagamentoEd.setVisibility(View.INVISIBLE);
		}
			
		valorizaCampos();
		
		return alertDialog;
	}
	
	
	private class PagarTitulo implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
			pedidoPgtoVO.setParcelaPaga(PedidoPgtoVO.PAGAMENTO_TOTAL);
			pedidoPgtoVO.setDtPagamento(new Date().getTime());
			pedidoPgtoDAO.updateSetPaga(pedidoPgtoVO);
			tituloExpAdapter.notifyDataSetChanged();			
			dismiss();
		}		
	
	}
	
	
	
	/*public TituloQuitarDialog(Context context, PedidoPgtoVO pedidoPgtoVO, TituloExpandAdapter tituloExpAdapter) {
		super(context);
		this.pedidoPgtoVO = pedidoPgtoVO;
		this.tituloExpAdapter = tituloExpAdapter;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.titulo_quitacao_dialog);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnPagar = (Button) findViewById(R.id.btnPagar);
		
		edNrParcela = (TextView) findViewById(R.id.tvParcelaEd);
		edVencimento = (TextView) findViewById(R.id.tvVencimentoEd);
		edValor = (TextView) findViewById(R.id.tvValorEd);
		edJuros = (TextView) findViewById(R.id.tvJurosEd);
		edValorTotal = (TextView) findViewById(R.id.tvTotalEd);
		edDtPagamento = (TextView) findViewById(R.id.tvDtPagamento);
		edDtPagamentoEd = (TextView) findViewById(R.id.tvDtPagamentoEd);
		btnCancelar.setOnClickListener(new CancelarListener());
		btnPagar.setOnClickListener(new PagarListener());
		
		valorizaCampos();
	}*/
	
	private void valorizaCampos(){
		TituloBO tituloBO = new TituloBO();
		double valorComJuros = tituloBO.calculaJuros(pedidoPgtoVO.getValorParcela(), pedidoPgtoVO.getDtVencimento());
		edNrParcela.setText(pedidoPgtoVO.getIdSequencia()+"");
		edVencimento.setText(Convert.toDateStr(pedidoPgtoVO.getDtVencimento()));
		edValor.setText(pedidoPgtoVO.getValorParcela()+"");
		double juros = Util.arredondaDouble(valorComJuros - pedidoPgtoVO.getValorParcela());
		edJuros.setText(juros < 0 ? "0" : juros +"");
		edValorTotal.setText(valorComJuros+"");
		
	}
	
	/*private class PagarListener implements View.OnClickListener{
		@Override
		public void onClick(View v) {
			PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(getContext());
			pedidoPgtoVO.setParcelaPaga(PedidoPgtoVO.PAGAMENTO_TOTAL);
			pedidoPgtoVO.setDtPagamento(new Date().getTime());
			pedidoPgtoDAO.updateSetPaga(pedidoPgtoVO);
			tituloExpAdapter.notifyDataSetChanged();			
			dismiss();
		}		
	}*/
	
	
	private class CancelarListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dismiss();
		}		
	
	}
}
