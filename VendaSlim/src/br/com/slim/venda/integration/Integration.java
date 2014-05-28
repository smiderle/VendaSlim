package br.com.slim.venda.integration;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.com.slim.venda.cargaInicial.SincronizacaoItensVO;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.device.Device;
import br.com.slim.venda.device.DeviceController;
import br.com.slim.venda.filial.FilialMobileConfigVO.FilialMobileConfig;
import br.com.slim.venda.filial.FilialVO.Filial;
import br.com.slim.venda.integration.controller.ClienteIntegrationController;
import br.com.slim.venda.integration.controller.FilialIntegrationController;
import br.com.slim.venda.integration.controller.GrupoProdutoIntegrationController;
import br.com.slim.venda.integration.controller.GrupoRepresentanteIntegrationController;
import br.com.slim.venda.integration.controller.PedidoIntegrationController;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.domain.FilialIntegration;
import br.com.slim.venda.integration.domain.FilialMobileConfigIntegration;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.integration.domain.GrupoRepresentanteIntegration;
import br.com.slim.venda.integration.domain.PedidoIntegration;
import br.com.slim.venda.item.ItemModel;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;
import br.com.slim.venda.mensagem.MensagemController;
import br.com.slim.venda.mensagem.MensagemVO;
import br.com.slim.venda.mensagem.MensagemVO.Mensagem;
import br.com.slim.venda.parcelamento.ParcelamentoController;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoController;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO.GrupoRepresentanteParcelamento;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoController;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO.GrupoRepresentanteTabPreco;
import br.com.slim.venda.representante.GrupoRepresentanteVO.GrupoRepresentante;
import br.com.slim.venda.representante.RepresentanteController;
import br.com.slim.venda.representante.RepresentanteFilialVO;
import br.com.slim.venda.representante.RepresentanteFilialVO.RepresentanteFilial;
import br.com.slim.venda.representante.RepresentanteParcelamentoVO;
import br.com.slim.venda.representante.RepresentanteParcelamentoVO.RepresentanteParcelamento;
import br.com.slim.venda.representante.RepresentanteRotaController;
import br.com.slim.venda.representante.RepresentanteRotaVO;
import br.com.slim.venda.representante.RepresentanteTabPrecoVO;
import br.com.slim.venda.representante.RepresentanteTabPrecoVO.RepresentanteTabPreco;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.representante.RepresentanteVO.Representante;
import br.com.slim.venda.sincroniza.SincronizaModel;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoController;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.LogManeger;
import br.com.slim.venda.versao.VersaoBO;
import br.com.slim.venda.versao.VersaoDAO;
import br.com.slim.venda.versao.VersaoPdaVO;
import br.com.slim.venda.webservice.ClienteREST;
import br.com.slim.venda.webservice.FilialMoibleConfigREST;
import br.com.slim.venda.webservice.FilialREST;
import br.com.slim.venda.webservice.MensagemREST;
import br.com.slim.venda.webservice.ParcelamentoREST;
import br.com.slim.venda.webservice.PedidoREST;
import br.com.slim.venda.webservice.ProdutoREST;
import br.com.slim.venda.webservice.RepresentanteREST;
import br.com.slim.venda.webservice.SincronizacaoREST;
import br.com.slim.venda.webservice.TabelaPrecoREST;
import br.com.slim.venda.webservice.VersaoREST;
import br.com.slim.venda.webservice.WebsincREST;
import br.com.slim.venda.websinc.Websinc;
import br.com.slim.venda.websinc.WebsincDAO;

public class Integration {
	
	private final String CATEGORIA = "INTEGRATION";
	private Context context;
	public Integration(Context context) {
		this.context = context;
	}

	
	public Long getTimeServer() throws Exception{
		SincronizacaoREST sincREST = new SincronizacaoREST();
		return sincREST.getTimeServer();
	}
	
	public RepresentanteVO gerarRepresentanteDemonstracao(String email) throws Exception{		

		DeviceController deviceController =new DeviceController(context);
		Device device = deviceController.getDadosDevice();
		device.setEmail(email);

		RepresentanteREST representanteREST = new RepresentanteREST();			
		return representanteREST.generateRepresentative(device);

	}
	
	public void enviarVersao(){
		VersaoDAO versaoDAO = new VersaoDAO(context);
		VersaoREST versaoREST = new VersaoREST();
		
		VersaoPdaVO versaoPda = versaoDAO.getVersaoPda();		
		versaoREST.addVersionPda(versaoPda);		
	}
	
	public void receberDataExpiracao(String serial) throws Exception{
		VersaoREST versaoREST = new VersaoREST();
		String dataExpiracao =  versaoREST.getExpirationDate(serial);
		if(dataExpiracao != null && !dataExpiracao.trim().equals("")){
			String[] retorno = dataExpiracao.split(","); 
			boolean demoExpirou = Boolean.valueOf(retorno[1].trim());
			long dtExpiracao = Long.parseLong(retorno[0].trim());
			VersaoBO versaoBO = new VersaoBO(context);
			versaoBO.updateExpirateDate(dtExpiracao, demoExpirou);
		}

	}
	
	public void receber(Long dtHrServer){
		try {			
			
			receberClientes(dtHrServer);			
			receberFilial(dtHrServer);
			receberFilialConfig(dtHrServer);
			receberGrupoRepresentante(dtHrServer);
			receberGrupoProduto(dtHrServer);
			receberGrupoRepresentanteParcelamento(dtHrServer);
			receberGrupoRepresentanteTabPreco(dtHrServer);
			receberMensagem(dtHrServer);
			receberParcelamento(dtHrServer);
			receberTabelaPreco(dtHrServer);
			receberProduto(dtHrServer);
			receberRepresentanteFilial(dtHrServer);
			receberRepresentante(dtHrServer);
			receberRepresentanteParcelamento(dtHrServer);
			receberRepresentanteTabPreco(dtHrServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public SincronizacaoItensVO receberClientes(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;		
		try {
			long dtHrUltimaSinc = getHoraUltimaSinc(Cliente.TABELA);
			ClienteIntegrationController controller = new ClienteIntegrationController(context);
			ClienteREST clienteREST = new ClienteREST();			
			List<ClienteIntegration> clientes = clienteREST.getAllCustomerByChangeDate(dtHrUltimaSinc);
			
			if (clientes.size() > 0) {
				controller.salvar(clientes);
			}
			atualizarHoraUltimaSinc(dtHrServer, Cliente.TABELA);
			
			sincItemVO = new SincronizacaoItensVO("Clientes", clientes.size());
			
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		
		return sincItemVO;
	}

	public SincronizacaoItensVO receberFilial(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;	
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(Filial.TABELA);
			FilialREST filialREST = new FilialREST();
			List<FilialIntegration> lsFilial = filialREST.getAllBranchOfficeByChangeDate(dtHrUltimaSinc);
			FilialIntegrationController controller = new FilialIntegrationController(context);
			if(lsFilial.size() > 0){
				controller.salvar(lsFilial);
			}
			atualizarHoraUltimaSinc(dtHrServer, Filial.TABELA);
			sincItemVO = new SincronizacaoItensVO("Filiais", lsFilial.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		
		return sincItemVO;
	}
	
	
	public SincronizacaoItensVO receberFilialConfig(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(FilialMobileConfig.TABELA);
			FilialMoibleConfigREST filialMoibleConfigREST = new FilialMoibleConfigREST();
			List<FilialMobileConfigIntegration> lsFilialConfig = filialMoibleConfigREST.getAllBranchOfficeByChangeDate(dtHrUltimaSinc);
			FilialIntegrationController controller = new FilialIntegrationController(context);
			if(lsFilialConfig.size() > 0){
				controller.salvarConfig(lsFilialConfig);
			}
			atualizarHoraUltimaSinc(dtHrServer, FilialMobileConfig.TABELA);
			sincItemVO = new SincronizacaoItensVO("Configurações", lsFilialConfig.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();			
		}
		return sincItemVO;
	}
	

	public SincronizacaoItensVO receberGrupoProduto(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(GrupoProduto.TABELA);
			ProdutoREST produtoREST = new ProdutoREST();
			List<GrupoProdutoIntegration> lsGrupoProduto = produtoREST.getAllProductGroupByChangeDate(dtHrUltimaSinc);
			GrupoProdutoIntegrationController controller = new GrupoProdutoIntegrationController(context);
			if(lsGrupoProduto.size() > 0){
				controller.salvar(lsGrupoProduto);
			}
			atualizarHoraUltimaSinc(dtHrServer, GrupoProduto.TABELA);
			sincItemVO = new SincronizacaoItensVO("Grupo Produtos", lsGrupoProduto.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	
	public SincronizacaoItensVO receberProduto(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(Item.TABELA);
			ProdutoREST produtoREST = new ProdutoREST();
			List<ItemVO> lsGrupoProduto = produtoREST.getAllProductByChangeDate(dtHrUltimaSinc);
			ItemModel controller = new ItemModel(context);
			if(lsGrupoProduto.size() > 0){
				controller.salvar(lsGrupoProduto);
			}
			atualizarHoraUltimaSinc(dtHrServer, Item.TABELA);
			sincItemVO = new SincronizacaoItensVO("Produtos", lsGrupoProduto.size());
			
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}

	
	
	public SincronizacaoItensVO receberGrupoRepresentante(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(GrupoRepresentante.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<GrupoRepresentanteIntegration> lsGrupoRepresentante = representanteREST.getAllGroupRepresentativeByChangeDate(dtHrUltimaSinc);
			GrupoRepresentanteIntegrationController controller = new GrupoRepresentanteIntegrationController(context);
			if(lsGrupoRepresentante.size() > 0){
				controller.salvar(lsGrupoRepresentante);
			}
			atualizarHoraUltimaSinc(dtHrServer, GrupoRepresentante.TABELA);
			sincItemVO = new SincronizacaoItensVO("Grupo Representantes", lsGrupoRepresentante.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberGrupoRepresentanteParcelamento(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(GrupoRepresentanteParcelamento.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<GrupoRepresentanteParcelamentoVO> lsGrupoRepresentanteParcelamento = representanteREST.getAllGroupRepresentativeSubdivisionByChangeDate(dtHrUltimaSinc);
			GrupoRepresentanteParcelamentoController controller = new GrupoRepresentanteParcelamentoController(context);
			if(lsGrupoRepresentanteParcelamento.size() > 0){
				controller.salvar(lsGrupoRepresentanteParcelamento);
			}
			atualizarHoraUltimaSinc(dtHrServer, GrupoRepresentanteParcelamento.TABELA);
			sincItemVO = new SincronizacaoItensVO("Grupo Representantes Parcelamento", lsGrupoRepresentanteParcelamento.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberGrupoRepresentanteTabPreco(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(GrupoRepresentanteTabPreco.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<GrupoRepresentanteTabPrecoVO> lsGrupoRepresentanteTabPreco = representanteREST.getAllGroupRepresentativePriceOfTableByChangeDate(dtHrUltimaSinc);
			GrupoRepresentanteTabPrecoController controller = new GrupoRepresentanteTabPrecoController(context);
			if(lsGrupoRepresentanteTabPreco.size() > 0){
				controller.salvar(lsGrupoRepresentanteTabPreco);
			}
			atualizarHoraUltimaSinc(dtHrServer, GrupoRepresentanteTabPreco.TABELA);
			sincItemVO = new SincronizacaoItensVO("Grupo Representantes TabPreco", lsGrupoRepresentanteTabPreco.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberRepresentanteFilial(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(RepresentanteFilial.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<RepresentanteFilialVO> lsRepresentanteFilial = representanteREST.getAllRepresentativeBranchOfficeByChangeDate(dtHrUltimaSinc);
			RepresentanteController controller = new RepresentanteController(context);
			if(lsRepresentanteFilial.size() > 0){
				controller.salvarRepresentanteFilial(lsRepresentanteFilial);
			}
			atualizarHoraUltimaSinc(dtHrServer, RepresentanteFilial.TABELA);
			sincItemVO = new SincronizacaoItensVO("Representantes Filial", lsRepresentanteFilial.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberRepresentante(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(Representante.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<RepresentanteVO> lsRepresentante = representanteREST.getAllRepresentativeByChangeDate(dtHrUltimaSinc);
			RepresentanteController controller = new RepresentanteController(context);
			if(lsRepresentante.size() > 0){
				controller.salvarRepresentante(lsRepresentante);
			}
			atualizarHoraUltimaSinc(dtHrServer, Representante.TABELA);
			sincItemVO = new SincronizacaoItensVO("Representantes", lsRepresentante.size());
			
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberRepresentanteParcelamento(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(RepresentanteParcelamento.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<RepresentanteParcelamentoVO> lsRepresentanteParcelamento = representanteREST.getAllRepresentativeSubdivisionByChangeDate(dtHrUltimaSinc);
			RepresentanteController controller = new RepresentanteController(context);
			if(lsRepresentanteParcelamento.size() > 0){
				controller.salvarRepresentanteParcelamento(lsRepresentanteParcelamento);
			}
			atualizarHoraUltimaSinc(dtHrServer, RepresentanteParcelamento.TABELA);
			sincItemVO = new SincronizacaoItensVO("Representantes Parcelamento", lsRepresentanteParcelamento.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberRepresentanteTabPreco(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(RepresentanteTabPreco.TABELA);
			RepresentanteREST representanteREST = new RepresentanteREST();
			List<RepresentanteTabPrecoVO> lsRepresentanteTabPreco = representanteREST.getAllRepresentativePriceOfTableByChangeDate(dtHrUltimaSinc);
			RepresentanteController controller = new RepresentanteController(context);
			if(lsRepresentanteTabPreco.size() > 0){
				controller.salvarRepresentanteTabPreco(lsRepresentanteTabPreco);
			}
			atualizarHoraUltimaSinc(dtHrServer, RepresentanteTabPreco.TABELA);
			sincItemVO = new SincronizacaoItensVO("Representantes TabPreco", lsRepresentanteTabPreco.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberMensagem(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(Mensagem.TABELA);
			MensagemREST mensagemREST = new MensagemREST();
			List<MensagemVO> lsMensagem = mensagemREST.getAllMessageByChangeDate(dtHrUltimaSinc);
			MensagemController controller = new MensagemController(context);
			if(lsMensagem.size() > 0){
				controller.salvar(lsMensagem);
			}
			atualizarHoraUltimaSinc(dtHrServer, Mensagem.TABELA);
			sincItemVO = new SincronizacaoItensVO("Mensagens", lsMensagem.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberParcelamento(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(Parcela.TABELA);
			ParcelamentoREST parcelaREST = new ParcelamentoREST();
			List<ParcelamentoVO> lsParcelameto = parcelaREST.getAllCustomerByChangeDate(dtHrUltimaSinc);
			ParcelamentoController controller = new ParcelamentoController(context);
			if(lsParcelameto.size() > 0){
				controller.salvar(lsParcelameto);
			}
			atualizarHoraUltimaSinc(dtHrServer, Parcela.TABELA);
			sincItemVO = new SincronizacaoItensVO("Parcelamento", lsParcelameto.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		
		return sincItemVO;
	}
	
	public SincronizacaoItensVO receberTabelaPreco(long dtHrServer){
		SincronizacaoItensVO sincItemVO = null;
		try {
			long dtHrUltimaSinc= getHoraUltimaSinc(TabelaPreco.TABELA);
			TabelaPrecoREST tabelaPrecoREST = new TabelaPrecoREST();
			List<TabelaPrecoVO> lsTabelas = tabelaPrecoREST.getAllPriceTableByChangeDate(dtHrUltimaSinc);
			TabelaPrecoController controller = new TabelaPrecoController(context);
			if(lsTabelas.size() > 0){
				controller.salvar(lsTabelas);
			}
			atualizarHoraUltimaSinc(dtHrServer, TabelaPreco.TABELA);
			sincItemVO = new SincronizacaoItensVO("Tabela de Preco", lsTabelas.size());
		} catch (Exception e) {
			LogManeger.e(CATEGORIA, e, true);
			e.printStackTrace();
		}
		return sincItemVO;
	}
	
	
	public void enviarClientes() throws Exception{		
		ClienteIntegrationController controller = new ClienteIntegrationController(context);
		ArrayList<ClienteIntegration> lsCliente = controller.getAllNaoSincronizado();
		
		if(lsCliente != null && lsCliente.size() > 0){
			ClienteREST clienteREST = new ClienteREST();
			ArrayList<ClienteIntegration> lsClienteRetorno = clienteREST.addCustomers(lsCliente);
			controller.update(lsClienteRetorno);
		}		
	}
	
	public void enviarPedidos() throws Exception{
		PedidoIntegrationController controller = new PedidoIntegrationController(context);
		ArrayList<PedidoIntegration> lsPedidoIntegration = controller.getAllNaoSincronizado();		
		if(lsPedidoIntegration.size() > 0){
			PedidoREST pedidoREST = new PedidoREST();
			ArrayList<PedidoIntegration> lsPedidoRetorno = pedidoREST.addOrder(lsPedidoIntegration);				
			controller.update(lsPedidoRetorno);
		}
	}
	
	public void enviarLocalizacao(){		
		RepresentanteRotaController controller = new RepresentanteRotaController(context);
		ArrayList<RepresentanteRotaVO> lsRepresentanteRotaVO = controller.getAllNaoSincronizado();
				
		if(lsRepresentanteRotaVO != null && lsRepresentanteRotaVO.size() > 0){
			RepresentanteREST representanteREST = new RepresentanteREST();
			
			try {
				representanteREST.addLocationRepresentative(lsRepresentanteRotaVO, context);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Atualiza a data e hora da sincronização
	 */
	public void atualizarHoraUltimaSinc(long dtHrUltimaSinc, String tabela){
		SincronizaModel controller = new SincronizaModel(context);
		SincronizaVO sincronizaVO = new SincronizaVO();
		sincronizaVO.setDtHrSincronizacao(dtHrUltimaSinc);
		sincronizaVO.setIdFilial(UsuarioVO.idFilial);
		sincronizaVO.setTabela(tabela);
		controller.salvar(sincronizaVO);
	}
	
	public long getHoraUltimaSinc(String tabela){
		SincronizaModel controller = new SincronizaModel(context);
		return controller.getHoraUltimaSinc(tabela);
	}
	
	public void atualizar(){
			try {
				StringBuffer sb = new StringBuffer("0");
				WebsincREST websincREST = new WebsincREST();
				List<Websinc> lsWebsinc =  websincREST.sincroniza();
				WebsincDAO websincDAO = new WebsincDAO(context);
				for (Websinc websinc : lsWebsinc) {
					websincDAO.execute(websinc.getComando());
					
					sb.append(",");
					sb.append(websinc.getSequencia());					
				}
				
				if(sb.length() > 1){
					websincREST.deleteWebsinc(sb.toString());
				}
					
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	
	public List<Websinc> atualizar2() throws Exception{
		List<Websinc> lsWebsinc = null;
		
		WebsincREST websincREST = new WebsincREST();
		lsWebsinc =  websincREST.sincroniza();
		
		return lsWebsinc;

	}
	
	public void deleteWebsinc(StringBuffer sb){
		try {
			new WebsincREST().deleteWebsinc(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
