package br.com.slim.venda.cidade;

import java.util.ArrayList;

import android.content.Context;

public class CidadeModel {
	
	private Context context;
	private CidadeDAO cidadeDAO;
	
	public CidadeModel(Context context) {
		this.context = context;
	}
	
	public CidadeVO findCity(String code) throws CidadeException{
		try {			
			int codigo = Integer.parseInt(code.substring(1, 7));
			return findCity(codigo);
		} catch (NumberFormatException e) {
			throw new CidadeException("Código da cidade não encontrado");
		}
	}
	
	/**
	 * Retorna a cidade com o código informado
	 * @param code
	 * @return CidadeVO
	 */
	public CidadeVO findCity(int code){
		cidadeDAO = CidadeDAO.getInstance(context);
		CidadeVO cidadeVO = cidadeDAO.getCity(code);		
		return cidadeVO;
	}
	
	/**
	 * Retorna as cidades que contenha determinada palavra
	 * @param description
	 * @return ArrayList<CidadeVO>
	 */
	public ArrayList<CidadeVO> findCities(String description){
		cidadeDAO = CidadeDAO.getInstance(context);
		ArrayList<CidadeVO> lsCidades = cidadeDAO.getCities(description);		
		return lsCidades;
	}
}
