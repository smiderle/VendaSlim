package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.cadastro.UsuarioAcesso;

public interface IUsuarioRepository extends Repository{
	public void save(Usuario usuario);
	public Usuario findByLogin(String login);
	public List<Usuario> list(Empresa empresa);	
	public void registraAcesso(UsuarioAcesso usuarioAcesso);
}
