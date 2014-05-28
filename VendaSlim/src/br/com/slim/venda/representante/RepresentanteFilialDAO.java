package br.com.slim.venda.representante;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.RepresentanteFilialVO.RepresentanteFilial;

public class RepresentanteFilialDAO {
	String CATEGORIA = "REPRESENTANTE_FILIAL_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public RepresentanteFilialDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final RepresentanteFilialVO representanteFilialVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(RepresentanteFilial.TABELA);
				sb.append("(");
					sb.append(RepresentanteFilial.IDEMPRESA);
					sb.append(",");
					sb.append(RepresentanteFilial.IDFILIAL);
					sb.append(",");
					sb.append(RepresentanteFilial.IDREPRESENTANTE);
					sb.append(",");
					sb.append(RepresentanteFilial.IDGRUPO);
					sb.append(",");
					sb.append(RepresentanteFilial.DESCMAX);
					sb.append(",");
					sb.append(RepresentanteFilial.GRUPOSPRODUTO);
					sb.append(",");
					sb.append(RepresentanteFilial.MINVENDA);
					sb.append(",");
					sb.append(RepresentanteFilial.COMICAOVENDA);
					sb.append(",");
					sb.append(RepresentanteFilial.VISUALIZATODOSCLIENTES);
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						representanteFilialVO.getIdEmpresa()+"",
						representanteFilialVO.getIdFilial()+"",
						representanteFilialVO.getIdRepresentante()+"",
						representanteFilialVO.getIdGrupo()+"",
						representanteFilialVO.getDescMax()+"",
						representanteFilialVO.getGruposProdutosStr(),
						representanteFilialVO.getMinVenda()+"",
						representanteFilialVO.getComicaoVenda()+"",
						representanteFilialVO.isVisualizaTodosClientes()+""
				};								

				db.execSQL(sb.toString(), values);
	}
	
	public ArrayList<RepresentanteFilialVO> getAllByRepresentante(RepresentanteVO repVO){
		
		Cursor c =  db.query(RepresentanteFilial.TABELA, RepresentanteFilial.COLUNAS,RepresentanteFilial.IDREPRESENTANTE+ "= ?"  , 
				new String[]{repVO.getIdRepresentante()+""}, null, null, null);
		return getAllByRepresentante(c);
	}
	
	public ArrayList<RepresentanteFilialVO> getAllByRepresentante(Cursor c){
		
		ArrayList<RepresentanteFilialVO> lsRepresentanteFilialVO = new ArrayList<RepresentanteFilialVO>();
		
		int idxIdEmpresa = c.getColumnIndex(RepresentanteFilial.IDEMPRESA);
		int idxIdFilial = c.getColumnIndex(RepresentanteFilial.IDFILIAL);		
		int idxComicaoVenda = c.getColumnIndex(RepresentanteFilial.COMICAOVENDA);
		int idxDescMax = c.getColumnIndex(RepresentanteFilial.DESCMAX);
		int idxGruposProduto = c.getColumnIndex(RepresentanteFilial.GRUPOSPRODUTO);		
		int idxIdGrupo = c.getColumnIndex(RepresentanteFilial.IDGRUPO);
		int idxIdRepresentante = c.getColumnIndex(RepresentanteFilial.IDREPRESENTANTE);
		int idxMinVenda = c.getColumnIndex(RepresentanteFilial.MINVENDA);
		int idxVisualizaTodosClientes = c.getColumnIndex(RepresentanteFilial.VISUALIZATODOSCLIENTES);
		
		
		if(c.moveToFirst()){
			do{
				RepresentanteFilialVO repFilialVO = new RepresentanteFilialVO();
				repFilialVO.setComicaoVenda(c.getDouble(idxComicaoVenda));
				repFilialVO.setIdEmpresa(c.getInt(idxIdEmpresa));				
				repFilialVO.setIdFilial(c.getInt(idxIdFilial));
				repFilialVO.setIdGrupo(c.getInt(idxIdGrupo));
				repFilialVO.setIdRepresentante(c.getInt(idxIdRepresentante));
				repFilialVO.setDescMax(c.getDouble(idxDescMax));
				repFilialVO.setMinVenda(c.getDouble(idxMinVenda));
				repFilialVO.setVisualizaTodosClientes(c.getInt(idxVisualizaTodosClientes) == 1);
				repFilialVO.setGruposProdutosStr(c.getString(idxGruposProduto));
				lsRepresentanteFilialVO.add(repFilialVO);
			}while(c.moveToNext());
		}
		
		return lsRepresentanteFilialVO;
	}
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
			db.close();
		}
	}
}
