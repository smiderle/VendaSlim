package br.com.slim.venda.tabelaPreco;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TableLayout;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;

public class TabelaPrecoDAO {
	String CATEGORIA = "TABPRECO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public TabelaPrecoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}

	public Cursor getCursor(){
		try{			
			return db.query(TabelaPreco.TABELA, TabelaPreco.COLUNAS, null, null, null, null, null, null);			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os TabelaPreco: " + e.toString());
			return null;
		}
	}
	
	public ArrayList<TabelaPrecoVO> getAll(){
		ArrayList<TabelaPrecoVO> lsTabPrecoVO = new ArrayList<TabelaPrecoVO>();
		Cursor c = getCursor();		
		if(c.moveToFirst()){
			int idxDescricao = c.getColumnIndex(TabelaPreco.DESCRICAO);
			int idxIdTabPreco = c.getColumnIndex(TabelaPreco.IDTABPRECO);
			int idxPercentual = c.getColumnIndex(TabelaPreco.PERCENTUAL);
			int idxAcrescimo = c.getColumnIndex(TabelaPreco.ACRESCIMO);
			int idxIdEmpresa = c.getColumnIndex(TabelaPreco.IDEMPRESA);
			int idxIdFilial = c.getColumnIndex(TabelaPreco.IDFILIAL);
			int idxInativo = c.getColumnIndex(TabelaPreco.INATIVO);
			
			do{
				TabelaPrecoVO tabelaPrecoVO = new TabelaPrecoVO();
				tabelaPrecoVO.setDescricao(c.getString(idxDescricao));
				tabelaPrecoVO.setIdTabPreco(c.getInt(idxIdTabPreco));
				tabelaPrecoVO.setPercentual(c.getDouble(idxPercentual));
				tabelaPrecoVO.setAcrescimo(c.getInt(idxAcrescimo) == 1);
				tabelaPrecoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				tabelaPrecoVO.setIdFilial(c.getInt(idxIdFilial));
				tabelaPrecoVO.setInativo(c.getInt(idxInativo) == 1);
				lsTabPrecoVO.add(tabelaPrecoVO);
			}while(c.moveToNext());
		}
		close();
		c.close();
		return lsTabPrecoVO;
	}
	
	
	public void insertOrUpdate(final TabelaPrecoVO tabelaPrecoVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(TabelaPreco.TABELA);
				sb.append("(");
					sb.append(TabelaPreco.IDEMPRESA);
					sb.append(",");
					sb.append(TabelaPreco.IDFILIAL);
					sb.append(",");
					sb.append(TabelaPreco.IDTABPRECO);
					sb.append(",");
					sb.append(TabelaPreco.ACRESCIMO);
					sb.append(",");
					sb.append(TabelaPreco.DESCRICAO);
					sb.append(",");
					sb.append(TabelaPreco.INATIVO);
					sb.append(",");
					sb.append(TabelaPreco.PERCENTUAL);
																
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?)");
				
				String[] values = {
						tabelaPrecoVO.getIdEmpresa()+"",
						tabelaPrecoVO.getIdFilial()+"",
						tabelaPrecoVO.getIdTabPreco()+"",						
						tabelaPrecoVO.isAcrescimo()+"",
						tabelaPrecoVO.getDescricao(),
						tabelaPrecoVO.isInativo()+"",
						tabelaPrecoVO.getPercentual()+""
				};								

				db.execSQL(sb.toString(), values);
	}
		
	
	
	
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
	}
}
