package br.com.vendaslim.util;

import br.com.vendaslim.conexao.HibernateUtil;
import br.com.vendaslim.domain.repository.ICidadeRepository;
import br.com.vendaslim.domain.repository.IClienteRepository;
import br.com.vendaslim.domain.repository.IFilialRepository;
import br.com.vendaslim.domain.repository.IGrupoProdutoRepository;
import br.com.vendaslim.domain.repository.IGrupoRepresentanteRepository;
import br.com.vendaslim.domain.repository.IMensagemRepository;
import br.com.vendaslim.domain.repository.IMenuRepository;
import br.com.vendaslim.domain.repository.IParcelamentoRepository;
import br.com.vendaslim.domain.repository.IPedidoRepository;
import br.com.vendaslim.domain.repository.IProdutoRepository;
import br.com.vendaslim.domain.repository.IRepresentanteParcelamento;
import br.com.vendaslim.domain.repository.IRepresentanteRepository;
import br.com.vendaslim.domain.repository.IRepresentanteRotaRepository;
import br.com.vendaslim.domain.repository.IRepresentanteTabelaPreco;
import br.com.vendaslim.domain.repository.ITabelaPrecoRepository;
import br.com.vendaslim.domain.repository.IUsuarioRepository;
import br.com.vendaslim.infra.cadastro.CidadeHibernate;
import br.com.vendaslim.infra.cadastro.FilialHibernate;
import br.com.vendaslim.infra.cadastro.MensagemHibernate;
import br.com.vendaslim.infra.cadastro.MenuHibernate;
import br.com.vendaslim.infra.cadastro.UsuarioHibernate;
import br.com.vendaslim.infra.cliente.ClienteHibernate;
import br.com.vendaslim.infra.pedido.ParcelamentoHibernate;
import br.com.vendaslim.infra.pedido.PedidoHibernate;
import br.com.vendaslim.infra.pedido.TabelaPrecoHibernate;
import br.com.vendaslim.infra.produto.GrupoProdutoHibernate;
import br.com.vendaslim.infra.produto.ProdutoHibernate;
import br.com.vendaslim.infra.representante.GrupoRepresentanteHibernate;
import br.com.vendaslim.infra.representante.RepresentaneParcelamentoHibernate;
import br.com.vendaslim.infra.representante.RepresentaneTabPrecoHibernate;
import br.com.vendaslim.infra.representante.RepresentanteHibernate;
import br.com.vendaslim.infra.representante.RepresentanteRotaHibernate;

public class DAOFactory {
	public static IMenuRepository criaMenuRepository(){
		MenuHibernate menuHibernate = new MenuHibernate();
		menuHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return menuHibernate;
	}

	public static IUsuarioRepository criaUsuarioDAO(){
		UsuarioHibernate usuarioHibernate = new UsuarioHibernate();
		usuarioHibernate.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioHibernate;
	}
		
	public static IGrupoRepresentanteRepository criaUsuarioGrupoDAO(){
		GrupoRepresentanteHibernate usuarioGrupoDAO = new GrupoRepresentanteHibernate();
		usuarioGrupoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioGrupoDAO;
	}
	
	public static IRepresentanteRepository criaRepresentanteDAO(){
		RepresentanteHibernate representanteDAO = new RepresentanteHibernate();
		representanteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return representanteDAO;
	}
	
	public static IFilialRepository criaFilialDAO(){
		FilialHibernate filialDAO =new FilialHibernate();
		filialDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return filialDAO;
	}

	public static ITabelaPrecoRepository criaTabelaPrecoRepository(){
		TabelaPrecoHibernate repository = new TabelaPrecoHibernate();
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IParcelamentoRepository criaParcelamentoRepository(){
		ParcelamentoHibernate repository = new ParcelamentoHibernate();
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static ICidadeRepository criaCidadeRepository(){
		CidadeHibernate repository = new CidadeHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IGrupoProdutoRepository criaGrupoProdutoRepository(){
		GrupoProdutoHibernate repository = new GrupoProdutoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IProdutoRepository criaProdutoRepository(){
		ProdutoHibernate repository = new ProdutoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IRepresentanteRotaRepository criaRepresentanteRotaRepository(){
		RepresentanteRotaHibernate repository = new RepresentanteRotaHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	public static IClienteRepository criaClienteRepository(){
		ClienteHibernate repository = new ClienteHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	public static IPedidoRepository criaPedidoRepository(){
		PedidoHibernate repository = new PedidoHibernate ();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IMensagemRepository criaMensagemRepository(){
		MensagemHibernate repository = new MensagemHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	public static IRepresentanteTabelaPreco criaRepresentanteTabPrecoRepository(){
		RepresentaneTabPrecoHibernate repository = new RepresentaneTabPrecoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
		
	public static IRepresentanteParcelamento criaRepresentanteParcelamentoRepository(){
		RepresentaneParcelamentoHibernate repository = new RepresentaneParcelamentoHibernate();		
		repository.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return repository;
	}
	
	
	
}
