package br.com.slim.venda.clienteNovo;

import java.util.ArrayList;

import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.AutoCompleteTextView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.slim.venda.R;
import br.com.slim.venda.cidade.CidadeException;
import br.com.slim.venda.cidade.CidadeModel;
import br.com.slim.venda.cidade.CidadeSimpleAdapter;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.util.UtilContext;
import br.com.slim.venda.widget.Alert;
import br.com.slim.venda.widget.ProgressDialogBoxIndeterminade;

//@SuppressLint("ValidFragment")
@SuppressLint("ValidFragment")
public class ClienteNovoEnderecoFragment extends Fragment{
	
	ClienteVO clienteVO;
	CidadeModel cidadeModel;
	
	EditText edCep;
	AutoCompleteTextView edCidade;	
	EditText edBairro;
	EditText edRua;
	EditText edNumero;	
	EditText edIdCidade;
	Button btnBuscaCep;
	ArrayList<CidadeVO> lsCidades = null;
	
	public ClienteNovoEnderecoFragment() {
		super();
	}


	View view;
	
	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");
		
		view = inflater.inflate(R.layout.cliente_novo_endereco, container, false);		
		
		cidadeModel = new CidadeModel(getActivity());
		bindControls();
		//registerListners();
		//autoCompleteCidade();
		return view;
	};
	
	

	
	private void bindControls(){
		edCep = (EditText) view.findViewById(R.id.edCep);
		edCidade = (AutoCompleteTextView) view.findViewById(R.id.acCidade);		
		edBairro = (EditText) view.findViewById(R.id.edBairro);
		edRua = (EditText) view.findViewById(R.id.edRua);
		edNumero = (EditText) view.findViewById(R.id.edNumero);
		btnBuscaCep = (Button) view.findViewById(R.id.btnBuscarCep);
		edIdCidade = (EditText) view.findViewById(R.id.edIdCidade);		
		edCidade.addTextChangedListener(new CidadeTextChangeListner());
		edCidade.setOnItemClickListener(new CidadeClickListner());
	}
	
	private class CidadeClickListner implements OnItemClickListener{
		@Override
		public void onItemClick(android.widget.AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			CidadeVO cidadeVO = lsCidades.get(position);
			clienteVO.setCidadeVO(cidadeVO);
			edIdCidade.setText(cidadeVO.getIdCidade()+"");
		}

		
	}
	
	private class CidadeTextChangeListner implements TextWatcher{
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if(start > 0 && edCidade.getAdapter() == null){
				autoCompleteCidade();
			}
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}
		
		@Override
		public void afterTextChanged(Editable s) {}
	
	}
	
	/*private void registerListners(){
		btnBuscaCep.setOnClickListener(new BuscaCepListner());
		edCidade.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int posicao,
					long arg3) {
				CidadeVO cidadeVO = (CidadeVO) adapterView.getItemAtPosition(posicao);
				clienteVO.setCidadeVO(cidadeVO);
				
				if(clienteVO.getCidadeVO() != null)
					edIdCidade.setText(cidadeVO.getIdCidade());
				
			}
		});
	}*/
	
	
	private void autoCompleteCidade(){
		lsCidades = cidadeModel.findCities("");
		edCidade.setAdapter(new CidadeSimpleAdapter(getActivity(), R.layout.cidade_auto, lsCidades));
	}
	
	@Override
	public void onPause() {
		valorizaAtributos();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		valorizaCampos();
		super.onResume();
	}
	
	public void valorizaAtributos(){		
		clienteVO.setCep(edCep.getText().toString());
		//clienteVO.getCidadeVO().set(edCidade.getText().toString());
		clienteVO.setBairro(edBairro.getText().toString());
		clienteVO.setRua(edRua.getText().toString());
		clienteVO.setNumero(edNumero.getText().toString());
	}
	
	public void valorizaCampos(){
		edCep.setText(clienteVO.getCep());
		edBairro.setText(clienteVO.getBairro());
		edRua.setText(clienteVO.getRua());
		edNumero.setText(clienteVO.getNumero());
		if(clienteVO.getCidadeVO().getIdCidade() != null){
			edCidade.setText(clienteVO.getCidadeVO().getMunicipio());
			edIdCidade.setText(clienteVO.getCidadeVO().getIdCidade()+"");
		}
	}
	
	private void setFieldsFromCep(String endereco) throws CidadeException{		
			String[] enderecos = endereco.split(",");
			if(enderecos.length < 5){
				throw new CidadeException("Cep não encontrado.");
			}
			String rua = enderecos[0].contains("-") ?  
					enderecos[0].substring(0, enderecos[0].indexOf("-")) : enderecos[0];
			edRua.setText(rua);
			edBairro.setText(enderecos[1]);
			edCidade.setText(enderecos[2]);
			String codigoCidade = enderecos[4];
			CidadeVO cidadeVO = cidadeModel.findCity(codigoCidade);
			clienteVO.setCidadeVO(cidadeVO);		
	}
	
	
	private class BuscaCepListner implements OnClickListener{
		ProgressDialogBoxIndeterminade progress = new ProgressDialogBoxIndeterminade("Bscando CEP");
		String endereco ="";
		
		@Override
		public void onClick(View v) {
			progress.setCancelable(false);
			progress.show(getActivity().getSupportFragmentManager(),"aa");
			
			UtilContext utilContext = new UtilContext(getActivity());
			if(!utilContext.haveNetworkConnection()){
				progress.dismiss();
				new Alert().showDialog(getActivity(), "Conexão falhou", "Parece que o você não existe uma conexão com a internet. Por favor, verifique para prosseguir.", "OK", null);
			}
			
			final Handler handler = new Handler();
			 

			/*
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					buscarCep();
					
					a.dismiss();
				}
			});		*/
		}
	}
	
	/*private class DialogsProgressDialogIndeterminateFragment extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        ProgressDialog dialog = new ProgressDialog(getSupportActivity(), getTheme());
	        dialog.setIndeterminate(true);
	        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        dialog.setMessage("Buscando CEP");
	        return dialog;
	    }
	}	*/
}
