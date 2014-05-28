package br.com.slim.venda.parcelamento;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;

public class ParcelamentoDAO {
	String CATEGORIA = "PARCELAMENTO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public ParcelamentoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public Cursor getCursor(){
		try{			
			return db.query(Parcela.TABELA, Parcela.COLUNAS, Parcela.IDPARCELAMENTO +" <> 4", null, null, null, null, null);			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os Parcelamentos: " + e.toString());
			return null;
		}
	}
	
	public ArrayList<ParcelamentoVO> getAll(){
		ArrayList<ParcelamentoVO> lsParcelaVO = new ArrayList<ParcelamentoVO>();
		Cursor c = getCursor();
		if(c.moveToFirst()){
			int idxCarencia = c.getColumnIndex(Parcela.CARENCIA);
			int idxDiasEntreParcela = c.getColumnIndex(Parcela.DIASENTREPARCELA);
			int idxDescricao = c.getColumnIndex(Parcela.DESCRICAO);
			int idxIdEmpresa = c.getColumnIndex(Parcela.IDEMPRESA);
			int idxIdParcel = c.getColumnIndex(Parcela.IDPARCELAMENTO);
			int idxNroParcela = c.getColumnIndex(Parcela.NROPARCELA);
			int idxPercentual = c.getColumnIndex(Parcela.PERCENTUAL);
			do{
				ParcelamentoVO parcelamentoVO = new ParcelamentoVO();
				parcelamentoVO.setCarencia(c.getInt(idxCarencia));
				parcelamentoVO.setDescricao(c.getString(idxDescricao));
				parcelamentoVO.setDiasEntreParcela(c.getInt(idxDiasEntreParcela));
				parcelamentoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				parcelamentoVO.setIdParcelamento(c.getInt(idxIdParcel));
				parcelamentoVO.setNroParcela(c.getInt(idxNroParcela));
				parcelamentoVO.setPercentual(c.getDouble(idxPercentual));
				lsParcelaVO.add(parcelamentoVO);
			} while(c.moveToNext());
		}
		c.close();
		close();
		return lsParcelaVO;
	}
	
	public void insertOrUpdate(final ParcelamentoVO parcelamentoVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Parcela.TABELA);
				sb.append("(");
					sb.append(Parcela.IDEMPRESA);
					sb.append(",");
					sb.append(Parcela.IDFILIAL);
					sb.append(",");
					sb.append(Parcela.IDPARCELAMENTO);
					sb.append(",");
					sb.append(Parcela.CARENCIA);
					sb.append(",");
					sb.append(Parcela.DESCRICAO);
					sb.append(",");
					sb.append(Parcela.DIASENTREPARCELA);
					sb.append(",");
					sb.append(Parcela.INATIVO);
					sb.append(",");
					sb.append(Parcela.NROPARCELA);
					sb.append(",");
					sb.append(Parcela.PERCENTUAL);
																
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						parcelamentoVO.getIdEmpresa()+"",
						parcelamentoVO.getIdFilial()+"",
						parcelamentoVO.getIdParcelamento()+"",
						parcelamentoVO.getCarencia()+"",
						parcelamentoVO.getDescricao()+"",
						parcelamentoVO.getDiasEntreParcela()+"",
						parcelamentoVO.isInativo()+"",
						parcelamentoVO.getNroParcela()+"",
						parcelamentoVO.getPercentual()+""
				};								

				db.execSQL(sb.toString(), values);
	}
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
	}
}
