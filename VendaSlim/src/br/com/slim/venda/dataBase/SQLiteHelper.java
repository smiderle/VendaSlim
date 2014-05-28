package br.com.slim.venda.dataBase;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.TelephonyManager;
import android.util.Log;
import br.com.slim.venda.analitico.EstoqueAnaliticoSQL;
import br.com.slim.venda.cidade.CidadeSQL;
import br.com.slim.venda.cliente.ClienteSQL;
import br.com.slim.venda.filial.FilialMobileConfigSQL;
import br.com.slim.venda.filial.FilialSQL;
import br.com.slim.venda.item.ItemSQL;
import br.com.slim.venda.itemGrupo.GrupoProdutoSQL;
import br.com.slim.venda.mensagem.MensagemSQL;
import br.com.slim.venda.parcelamento.ParcelamentoSQL;
import br.com.slim.venda.pedido.PedidoSQL;
import br.com.slim.venda.pedidoItem.PedidoItemSQL;
import br.com.slim.venda.pedidopgto.PedidoPgtoSQL;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoSQL;
import br.com.slim.venda.representante.GrupoRepresentanteSQL;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoSQL;
import br.com.slim.venda.representante.RepresentanteFilialSQL;
import br.com.slim.venda.representante.RepresentanteParcelamentoSQL;
import br.com.slim.venda.representante.RepresentanteRotaSQL;
import br.com.slim.venda.representante.RepresentanteSQL;
import br.com.slim.venda.representante.RepresentanteTabPrecoDAO;
import br.com.slim.venda.representante.RepresentanteTabPrecoSQL;
import br.com.slim.venda.representante.RepresentanteVO.Representante;
import br.com.slim.venda.sincroniza.SincronizaSQL;
import br.com.slim.venda.tabelaPreco.TabelaPrecoSQL;
import br.com.slim.venda.versao.VersaoBO;
import br.com.slim.venda.versao.VersaoPdaVO;
import br.com.slim.venda.versao.VersaoSQL;

public class SQLiteHelper extends SQLiteOpenHelper{
	
	public static final String CATEGORIA = "DataBase";
	public static final String DATABASENAME = "VENDASLIM";
	public static final Integer VERSAO = 1;
	
	
	public SQLiteHelper(Context context) {
		super(context, DATABASENAME, null, VERSAO);
	
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String[] scripts = scriptCriateDatabase();
		//int sizeScripts = scripts.length;
		
		for (String sql : scripts) {
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
		
		/*for (int i = 0; i < sizeScripts; i++) {
			String sql = scripts[i];
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
			
		}*/
		
		
		List<String> scripts2 = getDadosFicticios();
		for (String sql : scripts2) {
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
		
		
	}

	
	
	@Override
	// Mudou a versão...
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		
	}
	
	
	
	public String[] scriptCriateDatabase(){
		return new String[]{
				FilialMobileConfigSQL.createTable(),
				FilialSQL.createTable(),
				ItemSQL.createTable(),
				ClienteSQL.createTable(),
				TabelaPrecoSQL.createTable(), 
				ParcelamentoSQL.createTable(),
				GrupoProdutoSQL.createTable(),
				PedidoSQL.createTable(), 
				PedidoItemSQL.createTable(),
				PedidoPgtoSQL.createTable(),
				CidadeSQL.createTable(),
				EstoqueAnaliticoSQL.createTable(),
				SincronizaSQL.createTable(),
				GrupoRepresentanteSQL.createTable(),
				GrupoRepresentanteTabPrecoSQL.createTable(),
				GrupoRepresentanteParcelamentoSQL.createTable(),
				MensagemSQL.createTable(),
				RepresentanteFilialSQL.createTable(),
				RepresentanteSQL.createTable(),
				RepresentanteTabPrecoSQL.createTable(),
				RepresentanteParcelamentoSQL.createTable(),
				RepresentanteRotaSQL.createTable(),
				VersaoSQL.createTable()				
		};
	}
	
	public List<String> getDadosFicticios(){
		List<String> sql = new ArrayList<String>();
		String[] cidades = CidadeSQL.queryCidades;
		
		for(String e : cidades){
			sql.add(e);
		}
		
		return sql;
	}
}
