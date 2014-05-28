package br.com.slim.venda.filial;

import java.util.ArrayList;

import android.content.Context;

public class FilialController {

	Context context;
	FilialDAO filialDAO;
	public FilialController(Context context) {
		this.context = context;
		filialDAO = new FilialDAO(context);
	}
	
	public ArrayList<FilialVO> getAllBasic(){
		return filialDAO.getAllBasic();
	}
	
	public boolean existeFiliais(){
		return getAllBasic() != null;
	}
}