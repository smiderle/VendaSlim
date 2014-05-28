package br.com.vendaslim.controler;

import java.util.List;

import com.sun.org.apache.regexp.internal.REProgram;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Mensagem;
import br.com.vendaslim.domain.repository.IMensagemRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.util.ContextoUtil;

public class MensagemControler {

	
	private IMensagemRepository repository;
	public MensagemControler() {
		this.repository = DAOFactory.criaMensagemRepository();
	}
	
	public List<Mensagem> listaTodos(){
		Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
		return repository.listaTodas(empresa);
	}
	
	
	public List<Mensagem> listaTodosPorRepresentante(Representante representante){
		Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
		return repository.listaTodasPorRepresentante(empresa, representante);
	}
	
	public void salvar(List<Mensagem> mensagens){
		repository.save(mensagens);
	}
	
	public Integer buscaProximoId(Empresa empresa){
		return repository.buscaMariorId(empresa) +1;
	}
}
