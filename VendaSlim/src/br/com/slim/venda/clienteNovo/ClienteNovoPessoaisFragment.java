package br.com.slim.venda.clienteNovo;



import org.holoeverywhere.app.Fragment;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;
import br.com.slim.venda.widget.PickersDatePickerFragment;
//import android.widget.EditText;
//import android.widget.Spinner;

@SuppressLint("ValidFragment")
public class ClienteNovoPessoaisFragment extends Fragment {

	ClienteVO clienteVO;
	EditText edNome;
	EditText edContato;
	Spinner spTipo;
	EditText edCpf;
	EditText edRg;
	EditText edDtNascimento;
	EditText edObservacao;
		
	public ClienteNovoPessoaisFragment() {
		//this.clienteVO = new ClienteVO();
	}
	
	
	View view;	
	@Override
	public View onCreateView(org.holoeverywhere.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");
		
		view = inflater.inflate(R.layout.cliente_novo_pessoais, container, false);	
		edNome = (EditText)view.findViewById(R.id.edNome);
		edContato = (EditText)view.findViewById(R.id.edContato);
		spTipo = (Spinner)view.findViewById(R.id.spTipo);
		edCpf = (EditText)view.findViewById(R.id.edCpfCnpj);
		edRg = (EditText)view.findViewById(R.id.edRgIe);
		edDtNascimento = (EditText)view.findViewById(R.id.edDtNascimento);		
		edDtNascimento.setOnFocusChangeListener(new DtNascimentoListner());
		edObservacao = (EditText)view.findViewById(R.id.edObservacao);	
		
		valorizaCampos();
		
		
		return view;
	}
	
	
	
	public void valorizaAtributos(){
		clienteVO.setNome(edNome.getText().toString());
		clienteVO.setContato(edContato.getText().toString());		
		clienteVO.setTipo((int)spTipo.getSelectedItemId());		
		clienteVO.setCpf(edCpf.getText().toString());
		clienteVO.setRg(edRg.getText().toString());
		clienteVO.setObservacao(edObservacao.getText().toString());
		if(edDtNascimento.getText().toString() != null && edDtNascimento.getText().toString().length() == 10)
		clienteVO.setDtNascimento(Convert.dateToLong(edDtNascimento.getText().toString()));
	}
	
	public void valorizaCampos(){
		edNome.setText(clienteVO.getNome());
		edContato.setText(clienteVO.getContato());
		spTipo.setSelection(clienteVO.getTipo());	
		if(clienteVO.getDtNascimento() != null && clienteVO.getDtNascimento() != 0)		
			edDtNascimento.setText(Convert.toDateStr(clienteVO.getDtNascimento()));
		edCpf.setText(clienteVO.getCpf());
		edRg.setText(clienteVO.getRg());
		edObservacao.setText(clienteVO.getObservacao());
	}
    
    private class DtNascimentoListner implements View.OnFocusChangeListener{
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus){
				PickersDatePickerFragment p= new PickersDatePickerFragment(edDtNascimento);
				p.show(getActivity().getSupportFragmentManager(), "bbbb");
			}
		}		
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
}
