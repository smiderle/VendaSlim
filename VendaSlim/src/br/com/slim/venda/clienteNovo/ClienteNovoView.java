package br.com.slim.venda.clienteNovo;

import org.holoeverywhere.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;


public class ClienteNovoView extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Cliente");
		ClienteVO clienteSelecionado = (ClienteVO) getIntent().getParcelableExtra("CLIENTE_SELECIONADO");

		if(clienteSelecionado == null)
			clienteSelecionado = new ClienteVO();
		
		ClienteNovoTabFragment fragmenttab = new ClienteNovoTabFragment(clienteSelecionado);
		getSupportFragmentManager().beginTransaction()
		.add(R.id.item_detail_container, fragmenttab).commit();
	}	  


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		
		menu.add(R.menu.menu_pedido_salvar);
		return super.onCreateOptionsMenu(menu);

	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:			
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;		
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}


	@Override		
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}

}






























/*
package br.com.slim.venda.clienteNovo;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Spinner;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.slim.venda.R;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.cliente.ClienteDAO;
import br.com.slim.venda.cliente.ClienteModel;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.config.ConfigVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;
import br.com.slim.venda.widget.Alert;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ClienteNovoView extends Activity {

	ClienteVO clienteVO = new ClienteVO();
	ClienteModel clienteModel;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		clienteModel = new ClienteModel(this);

		edicao();
        getSupportActionBar().setTitle("  Detalhes Cliente");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addTab(); 

	}


	private void edicao(){
		ClienteVO clienteSelecionado = (ClienteVO) getIntent().getParcelableExtra("CLIENTE_SELECIONADO");
		if(clienteSelecionado != null){
			this.clienteVO = clienteSelecionado;
		}

	}

	private void teste(){
		getSupportActionBar().setSelectedNavigationItem(0);
		getSupportActionBar().setSelectedNavigationItem(1);
		getSupportActionBar().setSelectedNavigationItem(2);
		getSupportActionBar().setSelectedNavigationItem(3);

	}

	private void addTab(){
		TextView tvPessoais = new TextView(this);
		tvPessoais.setText("Pessoais");
		tvPessoais.setTextColor(Color.WHITE);
		tvPessoais.setTextSize(12);
		tvPessoais.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pessoais	, 0, 0);

		TextView tvEndereco = new TextView(this);
		tvEndereco.setText("Endereco");
		tvEndereco.setTextColor(Color.WHITE);
		tvEndereco.setTextSize(12);
		tvEndereco.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.endereco	, 0, 0);

		TextView tvContato = new TextView(this);
		tvContato.setText("Contato");
		tvContato.setTextColor(Color.WHITE);
		tvContato.setTextSize(12);
		tvContato.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.contato	, 0, 0);

		TextView tvFinanceiro = new TextView(this);
		tvFinanceiro.setText("Financeiro");
		tvFinanceiro.setTextColor(Color.WHITE);
		tvFinanceiro.setTextSize(12);
		tvFinanceiro.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dados2	, 0, 0);

		TextView tvPedidos = new TextView(this);
		tvPedidos.setText("Pedidos");
		tvPedidos.setTextColor(Color.WHITE);
		tvPedidos.setTextSize(12);
		tvPedidos.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pedidos2	, 0, 0);

		TextView tvTitulos = new TextView(this);
		tvTitulos.setText("Titulos");
		tvTitulos.setTextColor(Color.WHITE);
		tvTitulos.setTextSize(12);
		tvTitulos.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.pagamento	, 0, 0);

		ActionBar.Tab pessoais = getSupportActionBar().newTab();
		pessoais.setTabListener(new MyTabListener());		
		pessoais.setCustomView(tvPessoais);

		ActionBar.Tab contato = getSupportActionBar().newTab();
		contato.setTabListener(new MyTabListener());				
		contato.setCustomView(tvEndereco);

		ActionBar.Tab endereco = getSupportActionBar().newTab();
		endereco.setTabListener(new MyTabListener());				
		endereco.setCustomView(tvContato);

		ActionBar.Tab financeiro = getSupportActionBar().newTab();
		financeiro.setTabListener(new MyTabListener());			
		financeiro.setCustomView(tvFinanceiro);

		ActionBar.Tab pedidos = getSupportActionBar().newTab();
		pedidos.setTabListener(new MyTabListener());			
		pedidos.setCustomView(tvPedidos);		

		ActionBar.Tab titulos = getSupportActionBar().newTab();
		titulos.setTabListener(new MyTabListener());			
		titulos.setCustomView(tvTitulos);		

		getSupportActionBar().addTab(pessoais);
		getSupportActionBar().addTab(endereco);
		getSupportActionBar().addTab(contato);
		getSupportActionBar().addTab(financeiro);
		getSupportActionBar().addTab(pedidos);
		getSupportActionBar().addTab(titulos);
	}


	public void getValues(){	
		if(getSupportActionBar().getSelectedNavigationIndex() == 0){
			String nome = ((EditText) findViewById(R.id.edNome)).getText().toString();
			String contato = ((EditText) findViewById(R.id.edContato)).getText().toString();
			int tipo = (int) ((Spinner) findViewById(R.id.spTipo)).getSelectedItemId();
			String cpf = ((EditText) findViewById(R.id.edCpfCnpj)).getText().toString();
			String rg = ((EditText) findViewById(R.id.edRgIe)).getText().toString();
			String dtNascimento = ((EditText) findViewById(R.id.edDtNascimento)).getText().toString();
			String observacao = ((EditText) findViewById(R.id.edObservacao)).getText().toString();

			clienteVO.setNome(nome);
			clienteVO.setContato(contato);
			clienteVO.setTipo(tipo);
			clienteVO.setCpf(cpf);
			clienteVO.setRg(rg);
			if(dtNascimento != null && dtNascimento.length() == 10)
				clienteVO.setDtNascimento(Convert.dateToLong(dtNascimento));

			clienteVO.setObservacao(observacao);
		} else 	if(getSupportActionBar().getSelectedNavigationIndex() == 1){			
			String foner = ((EditText) findViewById(R.id.edFoneR)).getText().toString();
			String fonec = ((EditText) findViewById(R.id.edFoneC)).getText().toString();
			String celular = ((EditText) findViewById(R.id.edCelular)).getText().toString();
			String email = ((EditText) findViewById(R.id.edEmail)).getText().toString();

			clienteVO.setFoneResidencial(foner);
			clienteVO.setFoneComercial(fonec);
			clienteVO.setFoneCelular(celular);
			clienteVO.setEmail(email);
		}  else if(getSupportActionBar().getSelectedNavigationIndex() == 2){
			String cep = ((EditText) findViewById(R.id.edCep)).getText().toString();
			String bairro = ((EditText) findViewById(R.id.edBairro)).getText().toString();
			String numero = ((EditText) findViewById(R.id.edNumero)).getText().toString();
			String rua = ((EditText) findViewById(R.id.edRua)).getText().toString();
			String idCidade = ((EditText) findViewById(R.id.edIdCidade)).getText().toString();

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
			clienteVO.setTabPrecovo((TabelaPrecoVO)((Spinner) findViewById(R.id.spTabPreco)).getSelectedItem());
			clienteVO.setParcelamentoVO((ParcelamentoVO) ((Spinner) findViewById(R.id.spFormaParcel)).getSelectedItem());
			clienteVO.setIdFormaPagamento(((Spinner) findViewById(R.id.spFormaPgto)).getSelectedItemPosition());
			clienteVO.setLimiteCredito(Convert.toDouble(((EditText)findViewById(R.id.edLimiteCredito)).getText().toString()));
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			setResult(RESULT_CANCELED, null);
			this.finish();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
			break;
		case 1:
		//	teste();
			getValues();
			String camposInvalidos = validaDados();
			if(camposInvalidos.equals("")){				
				int inserts = clienteModel.save(clienteVO);
				if(inserts > 0){
					Toast.makeText(ClienteNovoView.this, "Cliente salvo com sucesso!", Toast.LENGTH_LONG).show();
					setResult(RESULT_OK, null);
					this.finish();
					overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
				}
			} else {
				showDialogFildsInvalid(camposInvalidos);
			}
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void showDialogFildsInvalid(String text){
		new Alert().showDialog(this, "Campos invalidos", text, "OK", null);
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
	public boolean onCreateOptionsMenu(Menu menu) {		
		 menu.add(0, 1, 1, "Salvar")
         .setIcon(R.drawable.save)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		 return super.onCreateOptionsMenu(menu);

	}

	private class MyTabListener implements ActionBar.TabListener {
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if(tab.getPosition()==0)			{
				ClienteNovoPessoaisFragment frag = new ClienteNovoPessoaisFragment(clienteVO);
				ft.replace(android.R.id.content, frag);
			}
			else if(tab.getPosition()==1) {
				ClienteNovoContatoFragment frag = new ClienteNovoContatoFragment(clienteVO);
				ft.replace(android.R.id.content, frag);				
			} else if(tab.getPosition()==2){
				ClienteNovoEnderecoFragment frag = new ClienteNovoEnderecoFragment(clienteVO);
				ft.replace(android.R.id.content, frag);
			} else if(tab.getPosition()==3){
				ClienteNovoFinanceiroFragment frag = new ClienteNovoFinanceiroFragment(clienteVO);
				ft.replace(android.R.id.content, frag);
			} else if(tab.getPosition()==4){
				ClienteNovoPedidosFragment frag = new ClienteNovoPedidosFragment(clienteVO);
				ft.replace(android.R.id.content, frag);
			} else if(tab.getPosition() == 5){				
				ClienteNovoTitulosFragment frag = new ClienteNovoTitulosFragment(clienteVO);
				ft.replace(android.R.id.content, frag);
			}
		}
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {

		}
	}

	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {	
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
	}
}
 */