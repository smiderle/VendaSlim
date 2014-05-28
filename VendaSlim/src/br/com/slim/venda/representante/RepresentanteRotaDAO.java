package br.com.slim.venda.representante;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.RepresentanteRotaVO.RepresentanteRota;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;

public class RepresentanteRotaDAO {
	
	String CATEGORIA = "REPRESENTANTE_ROTA_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	

	public RepresentanteRotaDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}	

	public void insertOrUpdate(final RepresentanteRotaVO representanteRotaVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(RepresentanteRota.TABELA);
				sb.append("(");
					sb.append(RepresentanteRota.IDEMPRESA);					
					sb.append(",");
					sb.append(RepresentanteRota.IDREPRESENTANTE);
					sb.append(",");
					sb.append(RepresentanteRota.DTHRROTA);
					sb.append(",");
					sb.append(RepresentanteRota.LATITUDE);
					sb.append(",");
					sb.append(RepresentanteRota.LONGITUDE);
					sb.append(",");
					sb.append(RepresentanteRota.SINCRONIZADO);	
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?)");
				
				String[] values = {
						representanteRotaVO.getIdEmpresa()+"",
						representanteRotaVO.getIdRepresentante()+"",
						representanteRotaVO.getDtHrRotaLong()+"",
						representanteRotaVO.getLatitude() +"",
						representanteRotaVO.getLongitude() +"",
						representanteRotaVO.getSincronizado()+""
				};

				db.execSQL(sb.toString(), values);
	}
	
	
	public ArrayList<RepresentanteRotaVO> getAllNaoSincronizado(){
		Cursor c = db.query(RepresentanteRota.TABELA, RepresentanteRota.COLUNAS, RepresentanteRota.SINCRONIZADO+" = 0 ", null, null, null, null, null);
		
		
		ArrayList<RepresentanteRotaVO> lsRepresentanteRotaVO = null;
				
		if(c.moveToFirst()){
			lsRepresentanteRotaVO = new ArrayList<RepresentanteRotaVO>();
			
			int idxDtHora = c.getColumnIndex(RepresentanteRota.DTHRROTA);
			int idxIdEmpresa = c.getColumnIndex(RepresentanteRota.IDEMPRESA);
			int idxIdRepresentante = c.getColumnIndex(RepresentanteRota.IDREPRESENTANTE);
			int idxLatitude = c.getColumnIndex(RepresentanteRota.LATITUDE);
			int idxLongitude = c.getColumnIndex(RepresentanteRota.LONGITUDE);
			int idxSincronizado = c.getColumnIndex(RepresentanteRota.SINCRONIZADO);			
			
			do{
				RepresentanteRotaVO representanteRotaVO = new RepresentanteRotaVO();
				representanteRotaVO.setDtHrRotaLong(c.getLong(idxDtHora));
				representanteRotaVO.setLatitude(c.getDouble(idxLatitude));
				representanteRotaVO.setLongitude(c.getDouble(idxLongitude));
				
				representanteRotaVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				representanteRotaVO.setIdRepresentante(c.getInt(idxIdRepresentante));
				representanteRotaVO.setSincronizado(c.getInt(idxSincronizado));
				
				lsRepresentanteRotaVO.add(representanteRotaVO);
			}while(c.moveToNext());
		}
		c.close();
		return lsRepresentanteRotaVO;	
	}
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
			db.close();
		}
	}

}
