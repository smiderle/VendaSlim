package br.com.slim.venda.clienteNovo;

import org.holoeverywhere.app.TabSwipeFragment;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.Spinner;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import br.com.slim.venda.R;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.cliente.ClienteModel;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;
import br.com.slim.venda.widget.Alert;

@SuppressLint("ValidFragment")
public class ClienteNovoTabFragment extends TabSwipeFragment {
	
	
	private ClienteVO clienteVO = null;
	
	public ClienteNovoTabFragment(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}
	
	public ClienteNovoTabFragment() {	
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    super.onViewCreated(view, savedInstanceState);
	    setHasOptionsMenu(true);
	}
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();		
		inflater.inflate(R.menu.menu_pedido_salvar, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {		
		case R.id.menuSave:
		//	teste();
			getValues();
			String camposInvalidos = validaDados();
			if(camposInvalidos.equals("")){				
				int inserts = new ClienteModel(getActivity()).save(clienteVO);
				if(inserts > 0){
					Toast.makeText(getActivity(), "Cliente salvo com sucesso!", Toast.LENGTH_LONG).show();
					getActivity().setResult(getActivity().RESULT_OK, null);
					this.getActivity().finish();
					getActivity().overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
				}
			} else {
				new Alert().showDialog(getActivity(), "Campos invalidos", camposInvalidos, "OK", null);				
			}
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	private String validaDados(){
		String dadosvalidos = "";
		if(ConfigVO.validarCpf){
			if(!Util.isCpfValid(clienteVO.getCpf())){
				dadosvalidos = "CPF Inválido";
			}
		}
		return dadosvalidos;
	}
	

    @Override
    public void onHandleTabs() {
    	Bundle bundle = new Bundle();
    	bundle.putParcelable("CLIENTE", clienteVO);
    	
    	addTab("Contato", ClienteNovoPessoaisFragment.class, bundle);
        addTab("Contato", ClienteNovoContatoFragment.class, bundle);
        addTab("Endereço", ClienteNovoEnderecoFragment.class, bundle);
        addTab("Financeiro", ClienteNovoFinanceiroFragment.class, bundle);
        addTab("Pedidos", ClienteNovoPedidosFragment.class, bundle);
        addTab("Titulos", ClienteNovoTitulosFragment.class, bundle);
        
        
        TextView tvPessoais = new TextView(getActivity());
		tvPessoais.setText("Pessoais");
		tvPessoais.setTextColor(Color.WHITE);
		tvPessoais.setTextSize(12);
		tvPessoais.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pessoais	, 0, 0);
		
		TextView tvEndereco = new TextView(getActivity());
		tvEndereco.setText("Endereco");
		tvEndereco.setTextColor(Color.WHITE);
		tvEndereco.setTextSize(12);
		tvEndereco.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.endereco	, 0, 0);
		
		TextView tvContato = new TextView(getActivity());
		tvContato.setText("Contato");
		tvContato.setTextColor(Color.WHITE);
		tvContato.setTextSize(12);
		tvContato.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contato	, 0, 0);
		
		TextView tvFinanceiro = new TextView(getActivity());
		tvFinanceiro.setText("Financeiro");
		tvFinanceiro.setTextColor(Color.WHITE);
		tvFinanceiro.setTextSize(12);
		tvFinanceiro.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dados2	, 0, 0);
		
		TextView tvPedidos = new TextView(getActivity());
		tvPedidos.setText("Pedidos");
		tvPedidos.setTextColor(Color.WHITE);
		tvPedidos.setTextSize(12);
		tvPedidos.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pedidos2	, 0, 0);
		
		TextView tvTitulos = new TextView(getActivity());
		tvTitulos.setText("Titulos");
		tvTitulos.setTextColor(Color.WHITE);
		tvTitulos.setTextSize(12);
		tvTitulos.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pagamento	, 0, 0);
		
		getSupportActionBar().getTabAt(0).setCustomView(tvPessoais);
		getSupportActionBar().getTabAt(1).setCustomView(tvEndereco);
		getSupportActionBar().getTabAt(2).setCustomView(tvContato);
		getSupportActionBar().getTabAt(3).setCustomView(tvFinanceiro);
		getSupportActionBar().getTabAt(4).setCustomView(tvPedidos);
		getSupportActionBar().getTabAt(5).setCustomView(tvTitulos);
        
    }
    
    public void getValues(){	
		if(getSupportActionBar().getSelectedNavigationIndex() == 0){
			String nome = ((EditText) getActivity().findViewById(R.id.edNome)).getText().toString();
			String contato = ((EditText) getActivity().findViewById(R.id.edContato)).getText().toString();
			int tipo = (int) ((Spinner) getActivity().findViewById(R.id.spTipo)).getSelectedItemId();
			String cpf = ((EditText) getActivity().findViewById(R.id.edCpfCnpj)).getText().toString();
			String rg = ((EditText) getActivity().findViewById(R.id.edRgIe)).getText().toString();
			String dtNascimento = ((EditText) getActivity().findViewById(R.id.edDtNascimento)).getText().toString();
			String observacao = ((EditText) getActivity().findViewById(R.id.edObservacao)).getText().toString();

			clienteVO.setNome(nome);
			clienteVO.setContato(contato);
			clienteVO.setTipo(tipo);
			clienteVO.setCpf(cpf);
			clienteVO.setRg(rg);
			if(dtNascimento != null && dtNascimento.length() == 10)
				clienteVO.setDtNascimento(Convert.dateToLong(dtNascimento));

			clienteVO.setObservacao(observacao);
		} else 	if(getSupportActionBar().getSelectedNavigationIndex() == 1){			
			String foner = ((EditText) getActivity().findViewById(R.id.edFoneR)).getText().toString();
			String fonec = ((EditText) getActivity().findViewById(R.id.edFoneC)).getText().toString();
			String celular = ((EditText) getActivity().findViewById(R.id.edCelular)).getText().toString();
			String email = ((EditText) getActivity().findViewById(R.id.edEmail)).getText().toString();

			clienteVO.setFoneResidencial(foner);
			clienteVO.setFoneComercial(fonec);
			clienteVO.setFoneCelular(celular);
			clienteVO.setEmail(email);
		}  else if(getSupportActionBar().getSelectedNavigationIndex() == 2){
			String cep = ((EditText) getActivity().findViewById(R.id.edCep)).getText().toString();
			String bairro = ((EditText) getActivity().findViewById(R.id.edBairro)).getText().toString();
			String numero = ((EditText) getActivity().findViewById(R.id.edNumero)).getText().toString();
			String rua = ((EditText) getActivity().findViewById(R.id.edRua)).getText().toString();
			String idCidade = ((EditText) getActivity().findViewById(R.id.edIdCidade)).getText().toString();

			clienteVO.setCep(cep);
			clienteVO.setBairro(bairro);
			clienteVO.setNumero(numero);
			clienteVO.setRua(rua);

			if(idCidade != null && !idCidade.trim().equals("")){
				Integer idCidadeInt = Integer.parseInt(idCidade.trim());

				if(idCidadeInt != 0){
					if(clienteVO.getCidadeVO() != null){
						clienteVO.getCidadeVO().setIdCidade(idCidadeInt);
					} else{
						clienteVO.setCidadeVO(new CidadeVO(idCidadeInt));
					}
				}

			}				
		} else if(getSupportActionBar().getSelectedNavigationIndex() == 3){
			clienteVO.setTabPrecovo((TabelaPrecoVO)((Spinner) getActivity().findViewById(R.id.spTabPreco)).getSelectedItem());
			clienteVO.setParcelamentoVO((ParcelamentoVO) ((Spinner) getActivity().findViewById(R.id.spFormaParcel)).getSelectedItem());
			clienteVO.setIdFormaPagamento(((Spinner) getActivity().findViewById(R.id.spFormaPgto)).getSelectedItemPosition());
			clienteVO.setLimiteCredito(Convert.toDouble(((EditText)getActivity().findViewById(R.id.edLimiteCredito)).getText().toString()));
		}
	}
	
}










/*
package br.com.slim.venda.clienteNovo;

import org.holoeverywhere.LayoutInflater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.slim.venda.R;
//import org.holoeverywhere.app.Fragment;

public class ClienteTeste extends Fragment{
	private FragmentTabHost mTabHost;
	
	public ClienteTeste() {
		// TODO Auto-generated constructor stub
	}
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	    }
	
	 @Override
	public View onCreateView(android.view.LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

	        View rootView = inflater.inflate(R.layout.fragment_tabs,container, false);


	        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
	        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

	        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Fragment B"),
	        		TabFragment.class, make(2));
	        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Fragment C"),
	        		TabFragment.class, make(1));
	        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Fragment D"),
	        		TabFragment.class, make(3));


	        return rootView;
	    }
	 
	
	
	
	
	
	
	
	public static class TabFragment extends Fragment {
		
		@Override
		public View onCreateView(android.view.LayoutInflater inflater,
				ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setTextAppearance(getActivity(), R.style.Holo_TextAppearance_Medium);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(getArguments().getInt("color"));
            textView.setText(getArguments().getCharSequence("text"));
            return textView;
        }
		
		
    }

    private static Bundle make(int i) {
        Bundle bundle = new Bundle();
        int color;
        CharSequence text;
        switch (i) {
            case 1:
                color = R.color.holo_blue_dark;
                text = "I'm perfect! Maybe...";
                break;
            case 2:
                color = R.color.holo_green_dark;
                text = "Love and dru... friends. Yea.";
                break;
            case 3:
                color = R.color.holo_red_dark;
                text = "I'm angry!!! Argh!!";
                break;
            default:
                return null;
        }
        bundle.putCharSequence("text", text);
        bundle.putInt("color", color);
        return bundle;
    }
}*/