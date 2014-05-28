package br.com.slim.venda.clienteNovo;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.util.EmailIntent;

@SuppressLint("ValidFragment")
public class ClienteNovoContatoFragment extends Fragment{
	
	public ClienteNovoContatoFragment() {
		// TODO Auto-generated constructor stub
		this.clienteVO = new ClienteVO();
	}
	
	ClienteVO clienteVO;
	EditText edFoneRe;
	EditText edFoneCo;
	EditText edFoneCel;
	EditText edEmail;
	
	ImageButton btnFoneR;
	ImageButton btnFoneC;
	ImageButton btnCelular;
	ImageButton btnEmail;
	
		
	View view;	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved)
	{
		
		Bundle bundle = this.getArguments();
		this.clienteVO = bundle.getParcelable("CLIENTE");
		
		view = inflater.inflate(R.layout.cliente_novo_contato, group, false);
		bindingFilds();
		setListner();
		return view;
	}
	
	private void bindingFilds(){
		edFoneRe = (EditText) view.findViewById(R.id.edFoneR);
		edFoneCo = (EditText) view.findViewById(R.id.edFoneC);
		edFoneCel = (EditText) view.findViewById(R.id.edCelular);
		edEmail = (EditText) view.findViewById(R.id.edEmail);		
		btnFoneR = (ImageButton) view.findViewById(R.id.btnFoneR);
		btnFoneC = (ImageButton) view.findViewById(R.id.btnFoneC);
		btnCelular = (ImageButton) view.findViewById(R.id.btnCelular);
		btnEmail = (ImageButton) view.findViewById(R.id.btnEmail);
	}
	
	private void setListner(){
		btnFoneC.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!edFoneCo.getText().equals("")){
					Uri uri = Uri.parse("tel:"+edFoneCo.getText().toString());
					Intent it = new Intent(Intent.ACTION_CALL, uri);
					startActivity(it);
				}
			}
		});
		
		
		btnFoneR.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!edFoneRe.getText().equals("")){
					Uri uri = Uri.parse("tel:"+edFoneRe.getText().toString());
					Intent it = new Intent(Intent.ACTION_CALL, uri);
					startActivity(it);
				}
			}
		});
		
		
		btnCelular.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!edFoneCel.getText().equals("")){
					Uri uri = Uri.parse("tel:"+edFoneCel.getText().toString());
					Intent it = new Intent(Intent.ACTION_CALL, uri);
					startActivity(it);
				}
			}
		});
		
		btnEmail.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(!edEmail.getText().toString().equals("")){	
					String[] para = edEmail.getText().toString().split(",");
					EmailIntent email = new EmailIntent(getActivity());
					email.send(para);
				}
			}
		});
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
		clienteVO.setFoneResidencial(edFoneRe.getText().toString());
		clienteVO.setFoneComercial(edFoneCo.getText().toString());
		clienteVO.setFoneCelular(edFoneCel.getText().toString());
		clienteVO.setEmail(edEmail.getText().toString());
	}
	
	public void valorizaCampos(){
		edFoneRe.setText(clienteVO.getFoneResidencial());
		edFoneCel.setText(clienteVO.getFoneCelular());
		edFoneCo.setText(clienteVO.getFoneComercial());
		edEmail.setText(clienteVO.getEmail());
	}
}
