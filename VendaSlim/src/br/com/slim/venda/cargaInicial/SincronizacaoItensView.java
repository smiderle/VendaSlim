package br.com.slim.venda.cargaInicial;

import java.util.ArrayList;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import br.com.slim.venda.R;
import br.com.slim.venda.SincronizacaoFinalizadaView;
import br.com.slim.venda.integration.Integration;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.versao.VersaoBO;


public class SincronizacaoItensView extends Activity {
	
	SincronizacaoItensAdapter sincronizacaoItensAdapter;
	private ArrayList<SincronizacaoItensVO> lsSincronizacaoIntesVO;
	ListView listView;
	Button btnClose;
	private boolean concluido;
	private boolean sincronizacaoBemSucedida;
	
	Animation animBounce;
	private Button btnNotification;
	/*Representante gerado na carga inicial versão demonstração*/
	private RepresentanteVO representanteGerado;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.sincronizacao_item_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("Sincronizando");
		
		listView = (ListView) findViewById(R.id.lvSincronizacao);
		btnClose = (Button) findViewById(R.id.btnFechar);
		btnNotification =(Button) findViewById(R.id.btnNotification);
				
		btnClose.setOnClickListener(new CloseButtonListner());
		btnNotification.setVisibility(View.INVISIBLE);
		
				
		criaItens();
		createListView();
		sincroniza();
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if(concluido){
				finish();
				overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);				
			} else {
				Toast.makeText(this, "Aguarde finalizar a sincronização", Toast.LENGTH_LONG).show();
			}
			
			break;	

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	
	private void sincroniza(){		
		setSupportProgressBarIndeterminateVisibility(true);
		new Thread(){
			public void run(){
				
				try {					
					atualizaListView();
					final Integration it = new Integration(SincronizacaoItensView.this);
					
					if(getIntent().getIntExtra("CARGA_INICIAL", 0) == 1){
						String email = getIntent().getStringExtra("EMAIL");
						representanteGerado = it.gerarRepresentanteDemonstracao(email);
						
						VersaoBO versaoBO = new VersaoBO(getApplicationContext());
						versaoBO.geraVersaoDemo();
						it.enviarVersao();
					}
					
					
					
					final Long dtHrServer = it.getTimeServer();
					
					it.receberDataExpiracao(((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
					
					/*SincronizacaoItensVO sincVO = it.enviarClientes();
					updateItemSucesso(sincVO);
					atualizaListView();*/
										
					/*SincronizacaoItensVO sincVO = it.enviarPedidos();
					updateItemSucesso(sincVO);
					atualizaListView();*/
					
					SincronizacaoItensVO sincVO = it.receberClientes(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberProduto(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberTabelaPreco(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();

					sincVO = it.receberMensagem(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();

					sincVO = it.receberGrupoProduto(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberRepresentante(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberGrupoRepresentante(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberFilialConfig(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();				
					
					sincVO = it.receberGrupoRepresentanteParcelamento(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberGrupoRepresentanteTabPreco(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberParcelamento(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberFilial(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberRepresentanteFilial(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberRepresentanteParcelamento(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					
					sincVO = it.receberRepresentanteTabPreco(dtHrServer);
					updateItemSucesso(sincVO);
					atualizaListView();
					concluido = true;
					
					sincronizacaoBemSucedida = true;
				} catch (Exception e) {
					concluido = true;
					sincronizacaoBemSucedida = false;
					mostrarNotificacao(e.getMessage());
					e.printStackTrace();
				} finally {					
					sincConcluida();					
				}
			}
			
		}.start();			
	}
	
	private void sincConcluida(){
		runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				if(concluido){
					btnClose.setVisibility(View.VISIBLE);
					setSupportProgressBarIndeterminateVisibility(false);
				}
				if(!sincronizacaoBemSucedida)
					btnClose.setText("Tentar Novamente");
				else
					btnClose.setText("Concluir");
			}
		});
	}
	
	/*Atualiza  a listview em um thread separada. runOnUiThread é a mesma coisa que o Handler*/
	public void atualizaListView(){
		runOnUiThread(new Runnable() {			
			@Override
			public void run() {
				sincronizacaoItensAdapter.notifyDataSetChanged();				
			}
		});
	}	
	

	private void mostrarNotificacao(final String msg){
		
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				btnNotification.setText(msg);
				btnNotification.setVisibility(View.VISIBLE);
				animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_in_move_out);
				btnNotification.setAnimation(animBounce);
				updateItemFalha();
				atualizaListView();
			}
		});
	
	}
	
	public void updateItemFalha(){
		for (SincronizacaoItensVO sincronizacao : lsSincronizacaoIntesVO) {
				sincronizacao.setStatusSincronizacao(SincronizacaoItensVO.FALHA);
		}		
	}
	
	public void updateItemSincronizando(){
		for (SincronizacaoItensVO sincronizacao : lsSincronizacaoIntesVO) {
				sincronizacao.setStatusSincronizacao(SincronizacaoItensVO.PENDENTE);
		}		
	}
	
	public void updateItemSucesso(SincronizacaoItensVO sincronizaItensVO){
		for (SincronizacaoItensVO sincronizacao : lsSincronizacaoIntesVO) {
			if(sincronizacao.getItemDescricao().equals(sincronizaItensVO.getItemDescricao())){
				sincronizacao.setCount(sincronizaItensVO.getCount());
				sincronizacao.setStatusSincronizacao(SincronizacaoItensVO.SINCRONIZADO);
			}
		}
	}
	
	
	private void createListView(){
		sincronizacaoItensAdapter = new SincronizacaoItensAdapter(SincronizacaoItensView.this, lsSincronizacaoIntesVO);
		listView.setAdapter(sincronizacaoItensAdapter);
	}
	
	private void criaItens(){
		lsSincronizacaoIntesVO = new ArrayList<SincronizacaoItensVO>();
		
		SincronizacaoItensVO criaRepresentante = new SincronizacaoItensVO("Criando Representante", 0);
		SincronizacaoItensVO enviarClientes = new SincronizacaoItensVO("Enviar Clientes",0);
		SincronizacaoItensVO enviarPedidos = new SincronizacaoItensVO("Enviar Pedidos",0);
		
		SincronizacaoItensVO clientes = new SincronizacaoItensVO("Clientes",0);
		SincronizacaoItensVO filiais = new SincronizacaoItensVO("Filiais",0);
		SincronizacaoItensVO configuracoes = new SincronizacaoItensVO("Configurações",0);
		SincronizacaoItensVO grupReprese = new SincronizacaoItensVO("Grupo Representantes",0);
		SincronizacaoItensVO grupProduto = new SincronizacaoItensVO("Grupo Produtos",0);
		SincronizacaoItensVO grupRepParcela = new SincronizacaoItensVO("Grupo Representantes Parcelamento",0);
		SincronizacaoItensVO grupRepTabPreco = new SincronizacaoItensVO("Grupo Representantes TabPreco",0);
		SincronizacaoItensVO mensagens = new SincronizacaoItensVO("Mensagens",0);
		SincronizacaoItensVO parcelamento = new SincronizacaoItensVO("Parcelamento",0);
		SincronizacaoItensVO tabPreco = new SincronizacaoItensVO("Tabela de Preco",0);
		SincronizacaoItensVO produtos = new SincronizacaoItensVO("Produtos",0);
		SincronizacaoItensVO representantesFilial = new SincronizacaoItensVO("Representantes Filial",0);
		SincronizacaoItensVO representantes = new SincronizacaoItensVO("Representantes",0);
		SincronizacaoItensVO representantesParcelamento = new SincronizacaoItensVO("Representantes Parcelamento",0);
		SincronizacaoItensVO representantesTabPreco = new SincronizacaoItensVO("Representantes TabPreco",0);		

		
		if(getIntent().getIntExtra("CARGA_INICIAL", 0) == 1){
			//lsSincronizacaoIntesVO.add(criaRepresentante);
		}
		//lsSincronizacaoIntesVO.add(enviarClientes);
		//lsSincronizacaoIntesVO.add(enviarPedidos);
		
		lsSincronizacaoIntesVO.add(clientes);
		lsSincronizacaoIntesVO.add(produtos);		
		lsSincronizacaoIntesVO.add(tabPreco);
		
		lsSincronizacaoIntesVO.add(mensagens);
		lsSincronizacaoIntesVO.add(grupProduto);
		lsSincronizacaoIntesVO.add(representantes);
		lsSincronizacaoIntesVO.add(grupReprese);
		lsSincronizacaoIntesVO.add(configuracoes);		
		lsSincronizacaoIntesVO.add(grupRepParcela);
		lsSincronizacaoIntesVO.add(grupRepTabPreco);
		lsSincronizacaoIntesVO.add(parcelamento);
		lsSincronizacaoIntesVO.add(filiais);
		
		lsSincronizacaoIntesVO.add(representantesFilial);
		
		lsSincronizacaoIntesVO.add(representantesParcelamento);
		lsSincronizacaoIntesVO.add(representantesTabPreco);
	}
	
	
	
	@Override
	@SuppressLint("NewApi")
	public void onBackPressed() {
		if(concluido){
			super.onBackPressed();
			overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
		} else {
			Toast.makeText(this, "Aguarde finalizar a sincronização", Toast.LENGTH_LONG).show();
		}
		
	}
	private void close(){
		//this.finish();
	}
	
	private class CloseButtonListner implements View.OnClickListener{

		@Override
		public void onClick(View v) {	
			if(sincronizacaoBemSucedida){				
				if(getIntent().getIntExtra("CARGA_INICIAL", 0) == 1){
					Intent it = new Intent(SincronizacaoItensView.this, SincronizacaoFinalizadaView.class);
					it.putExtra("REPRESENTANTE_GERADO", representanteGerado);
					startActivity(it);
					overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);	
					finish();
				} else {
					finish();
					overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
				}
				
				
				
			} else {
				updateItemSincronizando();
				btnClose.setVisibility(View.INVISIBLE);
				sincroniza();
			}
		}
		
	}
	
	
}

