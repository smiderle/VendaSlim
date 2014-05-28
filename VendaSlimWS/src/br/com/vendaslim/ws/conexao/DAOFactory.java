package br.com.vendaslim.ws.conexao;

import br.com.vendaslim.ws.infra.ClienteHibernate;
import br.com.vendaslim.ws.infra.DeviceHibernate;
import br.com.vendaslim.ws.infra.EmpresaHibernate;
import br.com.vendaslim.ws.infra.FilialHibernate;
import br.com.vendaslim.ws.infra.FilialMobileConfigHibernate;
import br.com.vendaslim.ws.infra.GrupoProdutoHibernate;
import br.com.vendaslim.ws.infra.GrupoRepresentanteHibernate;
import br.com.vendaslim.ws.infra.GrupoRepresentanteParcelamentoHibernate;
import br.com.vendaslim.ws.infra.GrupoRepresentanteTabPrecoHibernate;
import br.com.vendaslim.ws.infra.MensagemHibernate;
import br.com.vendaslim.ws.infra.ParcelamentoHibernate;
import br.com.vendaslim.ws.infra.PedidoHibernate;
import br.com.vendaslim.ws.infra.ProdutoHibernate;
import br.com.vendaslim.ws.infra.RepresentanteFilialHibernate;
import br.com.vendaslim.ws.infra.RepresentanteHibernate;
import br.com.vendaslim.ws.infra.RepresentanteParcelamentoHibernate;
import br.com.vendaslim.ws.infra.RepresentanteRotaHibernate;
import br.com.vendaslim.ws.infra.RepresentanteTabPrecoHibernate;
import br.com.vendaslim.ws.infra.SincronizacaoHibernate;
import br.com.vendaslim.ws.infra.TabelaPrecoHibernate;
import br.com.vendaslim.ws.infra.UsuarioHibernate;
import br.com.vendaslim.ws.infra.UsuarioPermissaoHibernate;
import br.com.vendaslim.ws.infra.VersaoPdaHibernate;
import br.com.vendaslim.ws.infra.WebsincHibernate;

public class DAOFactory {
	
	public static ClienteHibernate criaClienteRepository(){
		ClienteHibernate repository = new ClienteHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static GrupoProdutoHibernate criaGrupoProdutoRepository(){
		GrupoProdutoHibernate repository = new GrupoProdutoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static ProdutoHibernate criaProdutoRepository(){
		ProdutoHibernate repository = new ProdutoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static TabelaPrecoHibernate criaTabelaPrecoRepository(){
		TabelaPrecoHibernate repository = new TabelaPrecoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static ParcelamentoHibernate criaParcelamentoRepository(){
		ParcelamentoHibernate repository = new ParcelamentoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static SincronizacaoHibernate criaSincronizacaoRepository(){
		SincronizacaoHibernate repository = new SincronizacaoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static MensagemHibernate criaMensagemRepository(){
		MensagemHibernate repository = new MensagemHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static FilialHibernate criaFilialRepository(){
		FilialHibernate repository = new FilialHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static FilialMobileConfigHibernate criaFilialMobileCOnfigRepository(){
		FilialMobileConfigHibernate repository = new FilialMobileConfigHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static GrupoRepresentanteHibernate criaGrupoRepresentanteRepository(){
		GrupoRepresentanteHibernate repository = new GrupoRepresentanteHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static GrupoRepresentanteParcelamentoHibernate criaGrupoRepresentanteParcelamentoRepository(){
		GrupoRepresentanteParcelamentoHibernate repository = new GrupoRepresentanteParcelamentoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static GrupoRepresentanteTabPrecoHibernate criaGrupoRepresentanteTabPrecoRepository(){
		GrupoRepresentanteTabPrecoHibernate repository = new GrupoRepresentanteTabPrecoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static RepresentanteHibernate criaRepresentanteRepository(){
		RepresentanteHibernate repository = new RepresentanteHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static RepresentanteParcelamentoHibernate criaRepresentanteParcelamentoRepository(){
		RepresentanteParcelamentoHibernate repository = new RepresentanteParcelamentoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	

	public static RepresentanteTabPrecoHibernate criaRepresentanteTabPrecoRepository(){
		RepresentanteTabPrecoHibernate repository = new RepresentanteTabPrecoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	public static RepresentanteFilialHibernate criaRepresentanteFilialRepository(){
		RepresentanteFilialHibernate repository = new RepresentanteFilialHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	public static RepresentanteRotaHibernate criaRepresentanteRotaRepository(){
		RepresentanteRotaHibernate repository = new RepresentanteRotaHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	public static PedidoHibernate criaPedidoRepository(){
		PedidoHibernate repository = new PedidoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static DeviceHibernate criaDeviceRepository(){
		DeviceHibernate repository = new DeviceHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static VersaoPdaHibernate criaVersaoPdaRepository(){
		VersaoPdaHibernate repository = new VersaoPdaHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}


	public static EmpresaHibernate criaEmpresaRepository(){
		EmpresaHibernate  repository = new EmpresaHibernate ();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static UsuarioHibernate criaUsuarioRepository(){
		UsuarioHibernate repository = new UsuarioHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static UsuarioPermissaoHibernate criaUsuarioPermissaoRepository(){
		UsuarioPermissaoHibernate repository = new UsuarioPermissaoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static WebsincHibernate criaWebsincRepository(){
		WebsincHibernate repository = new WebsincHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	
	
	
	
}
