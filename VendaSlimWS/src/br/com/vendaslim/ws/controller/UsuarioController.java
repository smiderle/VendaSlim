package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.UsuarioIntegration;
import br.com.vendaslim.ws.domain.UsuarioPermissao;
import br.com.vendaslim.ws.infra.UsuarioHibernate;
import br.com.vendaslim.ws.infra.UsuarioPermissaoHibernate;

public class UsuarioController {
	private UsuarioHibernate usuarioHibernate;

	public UsuarioController() {
		usuarioHibernate = DAOFactory.criaUsuarioRepository();
	}
	
	
	public void save(UsuarioIntegration usuarioIntegretion){
		usuarioHibernate.save(usuarioIntegretion);
	}
	
	public Integer buscaProximoIdPorEmpresa(){
		return this.usuarioHibernate.buscaMariorId() + 1;
	}
	
	
	public UsuarioIntegration geraUsuario(String sulfixo, FilialIntegration filial){
		UsuarioIntegration usuario = new UsuarioIntegration();
		usuario.setAtivo(true);
		usuario.setEmail("email@usuario.com");
		usuario.setFone("131321321");
		usuario.setIdEmpresa(filial.getIdEmpresa());
		usuario.setIdFilial(filial.getIdFilial());
		usuario.setLogin("DEMO"+sulfixo);
		usuario.setNome("Usuario Demonstração");
		usuario.setSenha(sulfixo);
		save(usuario);
		return usuario;
	}
	
	public void geraUsuarioPermissao(Integer idUsuario){
		UsuarioPermissaoHibernate usuarioPermissaohibernate = DAOFactory.criaUsuarioPermissaoRepository();
		UsuarioPermissao usuarioPermissao = new UsuarioPermissao();
		usuarioPermissao.setIdUsuario(idUsuario);
		usuarioPermissao.setPermissao("ROLE_USER");
		
		usuarioPermissaohibernate.save(usuarioPermissao);
	}

}
