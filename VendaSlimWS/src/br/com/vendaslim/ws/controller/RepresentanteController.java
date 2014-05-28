package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.DeviceIntegration;
import br.com.vendaslim.ws.domain.EmpresaIntegration;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.RepresentanteFilialIntegration;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;
import br.com.vendaslim.ws.domain.RepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.domain.RepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.domain.UsuarioIntegration;
import br.com.vendaslim.ws.infra.RepresentanteHibernate;

public class RepresentanteController {
private RepresentanteHibernate representanteHibernate;
	
	public RepresentanteController() {
		representanteHibernate = DAOFactory.criaRepresentanteRepository();
	}
		
	public List<RepresentanteIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa) throws Exception{
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return  representanteHibernate.buscarPorDataAlteracao(alteracao, idEmpresa);
	}
	
	public Integer buscaProximoIdPorEmpresa(Integer idEmpresa){
		return this.representanteHibernate.buscaMariorIdPorEmpresa(idEmpresa) + 1;
	}
	
	public RepresentanteIntegration geraNovoRepresentanteDemonstracao(DeviceIntegration deviceIntegration){
		UsuarioController usuarioController  = new UsuarioController();
		Integer proximoIdUsuario = usuarioController.buscaProximoIdPorEmpresa();
		String sulfixo = proximoIdUsuario * 13+"";
				
		EmpresaIntegration empresa= new EmpresaContoller().geraEmpresa(sulfixo);
		FilialIntegration filial = new FilialController().geraFilial(empresa.getIdEmpresa());
		UsuarioIntegration usuario =  usuarioController.geraUsuario(sulfixo, filial);
		usuarioController.geraUsuarioPermissao(usuario.getIdUsuario());
		
		//Integer idRepresentante = buscaProximoIdPorEmpresa(empresa.getIdEmpresa());
		Integer idRepresentante = 1;
		String representanteNome = "DEMO"+sulfixo;
		
		RepresentanteIntegration representanteIntegration = new RepresentanteIntegration();
		representanteIntegration.setIdRepresentante(idRepresentante);
		representanteIntegration.setIdEmpresa(empresa.getIdEmpresa());
		representanteIntegration.setNome(representanteNome);
		representanteIntegration.setLogin(representanteNome);
		representanteIntegration.setSenha(sulfixo);
		representanteIntegration.setEmail(deviceIntegration.getEmail());
		representanteIntegration.setDtHrAlteracao(new GregorianCalendar());
		representanteIntegration.setInativo(false);
		representanteIntegration.setDeviceIntegration(deviceIntegration);
		
		representanteHibernate.save(representanteIntegration);
		
		RepresentanteFilialIntegration repFilialIntegration = new RepresentanteFilialIntegration();
		
		repFilialIntegration.setDtHrAlteracao(new GregorianCalendar());		
		repFilialIntegration.setIdEmpresa(empresa.getIdEmpresa());
		repFilialIntegration.setIdFilial(filial.getIdFilial());
		repFilialIntegration.setIdRepresentante(idRepresentante);
		repFilialIntegration.setVisualizaTodosClientes(true);
		
		RepresentanteFilialController repFilialController = new RepresentanteFilialController();
		repFilialController.save(repFilialIntegration);
		
		
		/*Parcelamentos*/		
		ParcelamentoController parcelamentoController = new ParcelamentoController();
		parcelamentoController.geraParcelamento(filial);
		/*Tabela de Preço*/
		TabelaPrecoController tabelaController = new TabelaPrecoController();
		tabelaController.geraTabelaPreco(filial);
		
		ArrayList<RepresentanteParcelamentoIntegration> lsRepParcelamento = new ArrayList<>();
		
		RepresentanteParcelamentoIntegration representanteParcelamentoIntegration1 = new RepresentanteParcelamentoIntegration();
		representanteParcelamentoIntegration1.setIdEmpresa(empresa.getIdEmpresa());
		representanteParcelamentoIntegration1.setIdFilial(filial.getIdFilial());
		representanteParcelamentoIntegration1.setIdRepresentante(idRepresentante);		
		representanteParcelamentoIntegration1.setIdParcelamento(1);
		
		RepresentanteParcelamentoIntegration representanteParcelamentoIntegration2 = new RepresentanteParcelamentoIntegration();
		representanteParcelamentoIntegration2.setIdEmpresa(empresa.getIdEmpresa());
		representanteParcelamentoIntegration2.setIdFilial(filial.getIdFilial());
		representanteParcelamentoIntegration2.setIdRepresentante(idRepresentante);
		representanteParcelamentoIntegration2.setIdParcelamento(2);
		
		
		RepresentanteParcelamentoIntegration representanteParcelamentoIntegration3 = new RepresentanteParcelamentoIntegration();
		representanteParcelamentoIntegration3.setIdEmpresa(empresa.getIdEmpresa());
		representanteParcelamentoIntegration3.setIdFilial(filial.getIdFilial());
		representanteParcelamentoIntegration3.setIdRepresentante(idRepresentante);
		representanteParcelamentoIntegration3.setIdParcelamento(3);
		
		lsRepParcelamento.add(representanteParcelamentoIntegration1);
		lsRepParcelamento.add(representanteParcelamentoIntegration2);
		lsRepParcelamento.add(representanteParcelamentoIntegration3);
		
		RepresentanteParcelamentoController repParcelaController = new RepresentanteParcelamentoController();
		repParcelaController.save(lsRepParcelamento);
		
		
		/*Tabela de Preco*/
		
		ArrayList<RepresentanteTabPrecoIntegration> lsRepTabPreco = new ArrayList<>();
		
		RepresentanteTabPrecoIntegration repTabPrecoIntegration1 = new RepresentanteTabPrecoIntegration();
		repTabPrecoIntegration1.setDtHrAlteracao(new GregorianCalendar());
		repTabPrecoIntegration1.setIdEmpresa(empresa.getIdEmpresa());
		repTabPrecoIntegration1.setIdFilial(filial.getIdFilial());
		repTabPrecoIntegration1.setIdRepresentante(idRepresentante);
		repTabPrecoIntegration1.setIdTabPreco(1);
		
		RepresentanteTabPrecoIntegration repTabPrecoIntegration2 = new RepresentanteTabPrecoIntegration();
		repTabPrecoIntegration2.setDtHrAlteracao(new GregorianCalendar());
		repTabPrecoIntegration2.setIdEmpresa(empresa.getIdEmpresa());
		repTabPrecoIntegration2.setIdFilial(filial.getIdFilial());
		repTabPrecoIntegration2.setIdRepresentante(idRepresentante);
		repTabPrecoIntegration2.setIdTabPreco(2);
		
		RepresentanteTabPrecoIntegration repTabPrecoIntegration3 = new RepresentanteTabPrecoIntegration();
		repTabPrecoIntegration3.setDtHrAlteracao(new GregorianCalendar());
		repTabPrecoIntegration3.setIdEmpresa(empresa.getIdEmpresa());
		repTabPrecoIntegration3.setIdFilial(filial.getIdFilial());
		repTabPrecoIntegration3.setIdRepresentante(idRepresentante);
		repTabPrecoIntegration3.setIdTabPreco(3);
		
		lsRepTabPreco.add(repTabPrecoIntegration1);
		lsRepTabPreco.add(repTabPrecoIntegration2);
		lsRepTabPreco.add(repTabPrecoIntegration3);
		
		RepresentanteTabPrecoController repTabController = new RepresentanteTabPrecoController();
		repTabController.save(lsRepTabPreco);
		
		
		GrupoProdutoController grupoProdutoController = new GrupoProdutoController();
		ProdutoController produtoController = new ProdutoController();
		ClienteController clienteController = new ClienteController();
		grupoProdutoController.geraGrupoProduto(filial);
		produtoController.geraProdutos(filial);
		clienteController.geraCliente(filial);
		
		
		return representanteIntegration;
		
		
	}
	
	public RepresentanteIntegration buscaPorSerial(DeviceIntegration deviceIntegration){
		return representanteHibernate.buscarPorSerial(deviceIntegration);
	}
}