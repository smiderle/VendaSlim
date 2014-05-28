package br.com.vendaslim.ws.controller;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.EmpresaIntegration;
import br.com.vendaslim.ws.infra.EmpresaHibernate;

public class EmpresaContoller {
	
private EmpresaHibernate empresaHibernate;
	
	public EmpresaContoller() {
		empresaHibernate = DAOFactory.criaEmpresaRepository();
	}
	
	public Integer buscaProximoId(){
		return this.empresaHibernate.buscaMariorId() + 1;
	}
	
	public void save(EmpresaIntegration empresaIntegration){
		empresaHibernate.save(empresaIntegration);
	}
	
	public EmpresaIntegration geraEmpresa(String prefixoUsuario){
		Integer idEmpresa = buscaProximoId();
		EmpresaIntegration empresaIntegration = new EmpresaIntegration();
		empresaIntegration.setIdEmpresa(idEmpresa);
		empresaIntegration.setNomeFantasia("Empresa Demonstração "+ prefixoUsuario);
		empresaIntegration.setRazaoSocial("Empresa Demonstração "+ prefixoUsuario);
		save(empresaIntegration);
		return empresaIntegration;
	}

}
