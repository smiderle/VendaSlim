package br.com.vendaslim.ws.conexao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import br.com.vendaslim.ws.controller.ClienteController;
import br.com.vendaslim.ws.controller.SincronizacaoController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.Sincronizacao;

public class TestaConexao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			SessionFactory sf= HibernateUtil.getSessionFactory();
			sf.getCurrentSession().beginTransaction();
			
			SincronizacaoController controlerS = new SincronizacaoController();
			Sincronizacao sincronizacao = controlerS.buscarUltimaSincronizacao(1, 1, 1, Sincronizacao.TABELA_CLIENTE);
			Sincronizacao novaSincronizacao = controlerS.geraNova(1, 1, 1, Sincronizacao.TABELA_CLIENTE);
			
			ClienteController controler = new ClienteController();
			List<ClienteIntegration> clientes;
			try {
				//clientes = controler.buscarPorDataAlteracao(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sf.getCurrentSession().getTransaction().commit();
			sf.getCurrentSession().close();		
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
