package br.com.slim.venda.login;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import br.com.slim.venda.filial.FilialVO;
import br.com.slim.venda.representante.RepresentanteDAO;
import br.com.slim.venda.representante.RepresentanteFilialDAO;
import br.com.slim.venda.representante.RepresentanteFilialVO;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.usuario.UsuarioVO;

public class LoginController {
	
	private Context context;
	public LoginController(Context context) {
		this.context = context;
	}

	public boolean validarLogin(String login, String senha, FilialVO filialVO){
		if(login == null || login.trim().equals("")){
			throw new RuntimeException("Informe o Usuário!");
		}
		
		if(senha == null || senha.trim().equals("")){
			throw new RuntimeException("Informe  a Senha!");
		}
		
		if(filialVO == null){
			throw new RuntimeException("Informe o a Empresa!");
		}
		
		
		RepresentanteDAO representanteDAO = new RepresentanteDAO(context);
		RepresentanteVO representanteVO = representanteDAO.getRepresentanteByLogin(login, senha);
		
		if(representanteVO == null) {
			throw new RuntimeException("Usuário ou Senha Inválidos!");
		}
		
		RepresentanteFilialDAO repFilialDAO = new RepresentanteFilialDAO(context);
		ArrayList<RepresentanteFilialVO> lsRepFilialVO = repFilialDAO.getAllByRepresentante(representanteVO);
		
		boolean empresaValida = false;
		for (RepresentanteFilialVO representanteFilialVO : lsRepFilialVO) {
			if(representanteFilialVO.getIdFilial().equals(filialVO.getIdFilial())){
				empresaValida = true;
				break;
			}
		}
		
		if(!empresaValida) {
			throw new RuntimeException("Usuário não tem permissão para acessar a empresa selecionada.");
		}
		
		UsuarioVO.idEmpresa = representanteVO.getIdEmpresa();
		UsuarioVO.idFilial = filialVO.getIdFilial();
		UsuarioVO.idUsuairo = representanteVO.getIdRepresentante();
		
		
		salvarUsuario(login);
		return true;
	}
	
	private void salvarUsuario(String usuario){
		SharedPreferences prefs = context.getSharedPreferences("br.com.slim.venda", Context.MODE_PRIVATE);
		prefs.edit().putString("usuario", usuario).commit();
	}
}
