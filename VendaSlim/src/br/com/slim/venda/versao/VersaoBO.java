package br.com.slim.venda.versao;

import android.content.Context;
import android.telephony.TelephonyManager;

public class VersaoBO {
	
	Context context;
	public VersaoBO(Context context) {
		this.context = context;  
	}
	
	public void geraVersaoDemo(){
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		VersaoPdaVO versaoPdaVO = new VersaoPdaVO();
		versaoPdaVO.setBuild("2014.03.24");
		versaoPdaVO.setLicenca(1);
		versaoPdaVO.setSerial(tManager.getDeviceId());
		versaoPdaVO.setVersao("0.1");
		versaoPdaVO.setVersaoExpirada(false);
		VersaoDAO versaoDAO = new VersaoDAO(context);
		versaoDAO.insert(versaoPdaVO);
	}
	
	public VersaoPdaVO getVersao(){
		VersaoDAO versaoDAO = new VersaoDAO(context);
		return versaoDAO.getVersaoPda();
	}

	
	public void updateExpirateDate(Long expirationDate, boolean versaoExpirada){
		VersaoDAO versaoDAO = new VersaoDAO(context);
		versaoDAO.updateDataExpiracao(expirationDate, versaoExpirada);
	}
	
	public boolean demonstracaoExpirou(){
		VersaoPdaVO versao = getVersao();
		if(versao != null){
			if(versao.isVersaoExpirada())
				return true;
		}
		return false;
	}
}
