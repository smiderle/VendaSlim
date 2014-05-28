package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.infra.ClienteHibernate;

public class ClienteController {
private ClienteHibernate clienteHibernate;
	
	public ClienteController() {
		clienteHibernate = DAOFactory.criaClienteRepository();
	}
		
	public List<ClienteIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial) throws Exception{
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		
		return  clienteHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
	
	public Integer buscaProximoIdPorFilial(Integer idEmpresa, Integer idFilial){
		return this.clienteHibernate.buscaMariorIdPorFilial(idEmpresa,idFilial) + 1;
	}
	
	public ArrayList<ClienteIntegration> insert(List<ClienteIntegration> clientes){
		//Clientes, caso o id cadastrado no celular seja diferente do que sera cadastrado no banco, esses clientes são retornados para o celular para fazer um update no id
		ArrayList<ClienteIntegration> newLsCliente = new ArrayList<ClienteIntegration>();
		if(clientes.size() > 0){
			ClienteIntegration cliente = clientes.get(0);
			Integer idEmpresa = cliente.getIdEmpresa();
			Integer idFilial = cliente.getIdFilial();
			
			Integer proximoIdCliente = buscaProximoIdPorFilial(idEmpresa, idFilial);
			
			for (ClienteIntegration clienteIntegration : clientes) {
				if(clienteIntegration.getAlterado() != null && clienteIntegration.getAlterado().equals("T")){
					clienteIntegration.setDtHrAlteracao(new GregorianCalendar());
					ClienteIntegration newCliente = new ClienteIntegration();
					newCliente.setIdEmpresa(clienteIntegration.getIdEmpresa());
					newCliente.setIdFilial(clienteIntegration.getIdFilial());
					newCliente.setIdCliente(clienteIntegration.getIdCliente());
					newCliente.setIdClienteMobile(clienteIntegration.getIdCliente());
					newLsCliente.add(newCliente);
					
					update(clienteIntegration);
				} else {
					//Popula um novo List com somente as PKs do cliente para retornar para o mobile para fazer o update do novo id do cliente.
					clienteIntegration.setDtHrAlteracao(new GregorianCalendar());
					ClienteIntegration newCliente = new ClienteIntegration();
					newCliente.setIdEmpresa(clienteIntegration.getIdEmpresa());
					newCliente.setIdFilial(clienteIntegration.getIdFilial());
					newCliente.setIdCliente(proximoIdCliente);
					newCliente.setIdClienteMobile(clienteIntegration.getIdCliente());
					newLsCliente.add(newCliente);
					
					if(clienteIntegration.getIdCliente() != proximoIdCliente){
						clienteIntegration.setIdCliente(proximoIdCliente);
					}
					proximoIdCliente ++;
					clienteHibernate.save(clienteIntegration);
				}
			}
		}
		return newLsCliente;
	}
	
		
	public void save(List<ClienteIntegration> clientes){
		clienteHibernate.save(clientes);
	}
	
	public void save(ClienteIntegration cliente){
		clienteHibernate.save(cliente);
	}
	
	
	public void update(ClienteIntegration cliente){
		clienteHibernate.update(cliente);
	}
	
	public ArrayList<HashMap<String, Integer>> update2(List<ClienteIntegration> clientes){
		ArrayList<HashMap<String, Integer>> retornos = new ArrayList<HashMap<String, Integer>>();
		if(clientes.size() > 0){
			for (ClienteIntegration clienteIntegration : clientes) {
				HashMap<String, Integer> retorno = new HashMap<String, Integer>();
				
				clienteIntegration.setDtHrAlteracao(new GregorianCalendar());				
				clienteHibernate.update(clienteIntegration);
				
				retorno.put("idCliente", clienteIntegration.getIdCliente());
				retorno.put("idEmpresa", clienteIntegration.getIdEmpresa());
				retorno.put("idFilial", clienteIntegration.getIdFilial());
				retorno.put("alterado", 1);
				retornos.add(retorno);
			}
		}
		return retornos;
	}
	
	public void geraCliente(FilialIntegration filial){
		List<ClienteIntegration> lsCliente = new ArrayList<>();
		
		ClienteIntegration cliente1 = new ClienteIntegration();
		cliente1.setIdCliente(1);
		cliente1.setCelular("9321321");
		cliente1.setNome("SANTOS DUMONT");
		cliente1.setContato("SANTOS DUMONT");
		cliente1.setCpfCnpj("997465123819");
		cliente1.setDescMax(10.0);
		cliente1.setDtHrAlteracao(new GregorianCalendar());
		cliente1.setEmail("cliente@email.com");
		cliente1.setFax("3213271321");
		cliente1.setFoneComercial("32265421");
		cliente1.setFoneResidencial("326545686");
		cliente1.setInativo(false);
		cliente1.setIncricao("05648765231");
		cliente1.setNumero("232");
		cliente1.setRua("RUA PRINCIPAL");
		cliente1.setTipoPessoa(1);
		cliente1.setIdEmpresa(filial.getIdEmpresa());
		cliente1.setIdFilial(filial.getIdFilial());
		cliente1.setIdCidade(3451);
		cliente1.setCep("85502-070");
		cliente1.setBairro("Menino Deus");
		
		
		
		ClienteIntegration cliente2 = new ClienteIntegration();
		cliente2.setIdCliente(2);
		cliente2.setCelular("99849894");
		cliente2.setNome("PEDRO ALVARES CABRAL");
		cliente2.setContato("PEDRO ALVARES CABRAL");
		cliente2.setCpfCnpj("127465123815");
		cliente2.setDescMax(10.0);
		cliente2.setDtHrAlteracao(new GregorianCalendar());
		cliente2.setEmail("cliente@email.com");
		cliente2.setFax("3213271321");
		cliente2.setFoneComercial("32265421");
		cliente2.setFoneResidencial("326545686");
		cliente2.setInativo(false);
		cliente2.setIncricao("05648765231");
		cliente2.setNumero("231");
		cliente2.setRua("RUA PRINCIPAL");
		cliente2.setTipoPessoa(1);
		cliente2.setIdEmpresa(filial.getIdEmpresa());
		cliente2.setIdFilial(filial.getIdFilial());
		cliente2.setIdCidade(3451);
		cliente2.setCep("85502-070");
		cliente2.setBairro("Menino Deus");
		
		ClienteIntegration cliente3 = new ClienteIntegration();
		cliente3.setIdCliente(3);
		cliente3.setCelular("52132132");
		cliente3.setNome("HEBE CAMARGO");
		cliente3.setContato("HEBE CAMARGO");
		cliente3.setCpfCnpj("897445653815");
		cliente3.setDescMax(10.0);
		cliente3.setDtHrAlteracao(new GregorianCalendar());
		cliente3.setEmail("cliente@email.com");
		cliente3.setFax("3213271321");
		cliente3.setFoneComercial("32265421");
		cliente3.setFoneResidencial("326545686");
		cliente3.setInativo(false);
		cliente3.setIncricao("05648765231");
		cliente3.setNumero("231");
		cliente3.setRua("RUA PRINCIPAL");
		cliente3.setTipoPessoa(1);
		cliente3.setIdEmpresa(filial.getIdEmpresa());
		cliente3.setIdFilial(filial.getIdFilial());
		cliente3.setIdCidade(3451);
		cliente3.setCep("85502-070");
		cliente3.setBairro("Menino Deus");
		
		ClienteIntegration cliente4 = new ClienteIntegration();
		cliente4.setIdCliente(4);
		cliente4.setCelular("9332321");
		cliente4.setNome("GETULIO VARGAS");
		cliente4.setContato("GETULIO VARGAS");
		cliente4.setCpfCnpj("897465189815");
		cliente4.setDescMax(10.0);
		cliente4.setDtHrAlteracao(new GregorianCalendar());
		cliente4.setEmail("cliente@email.com");
		cliente4.setFax("3213271321");
		cliente4.setFoneComercial("32265421");
		cliente4.setFoneResidencial("326545686");
		cliente4.setInativo(false);
		cliente4.setIncricao("05648765231");
		cliente4.setNumero("231");
		cliente4.setRua("RUA PRINCIPAL");
		cliente4.setTipoPessoa(1);
		cliente4.setIdEmpresa(filial.getIdEmpresa());
		cliente4.setIdFilial(filial.getIdFilial());
		cliente4.setIdCidade(3451);
		cliente4.setCep("85502-070");
		cliente4.setBairro("Menino Deus");
		
		ClienteIntegration cliente5 = new ClienteIntegration();
		cliente5.setIdCliente(5);
		cliente5.setCelular("9321321");
		cliente5.setNome("JULLIO PRESTES");
		cliente5.setContato("JULLIO PRESTES");
		cliente5.setCpfCnpj("893269123815");
		cliente5.setDescMax(10.0);
		cliente5.setDtHrAlteracao(new GregorianCalendar());
		cliente5.setEmail("cliente@email.com");
		cliente5.setFax("3213271321");
		cliente5.setFoneComercial("32265421");
		cliente5.setFoneResidencial("326545686");
		cliente5.setInativo(false);
		cliente5.setIncricao("05648765231");
		cliente5.setNumero("231");
		cliente5.setRua("RUA PRINCIPAL");
		cliente5.setTipoPessoa(1);
		cliente5.setIdEmpresa(filial.getIdEmpresa());
		cliente5.setIdFilial(filial.getIdFilial());
		cliente5.setIdCidade(3451);
		cliente5.setCep("85502-070");
		cliente5.setBairro("Menino Deus");
		
		ClienteIntegration cliente6 = new ClienteIntegration();
		cliente6.setIdCliente(6);
		cliente6.setCelular("9321321");
		cliente6.setNome("WASHINGTON LUIS");
		cliente6.setContato("WASHINGTON LUIS");
		cliente6.setCpfCnpj("897465369815");
		cliente6.setDescMax(10.0);
		cliente6.setDtHrAlteracao(new GregorianCalendar());
		cliente6.setEmail("cliente@email.com");
		cliente6.setFax("3213271321");
		cliente6.setFoneComercial("32265421");
		cliente6.setFoneResidencial("326545686");
		cliente6.setInativo(false);
		cliente6.setIncricao("05648765231");
		cliente6.setNumero("231");
		cliente6.setRua("RUA PRINCIPAL");
		cliente6.setTipoPessoa(1);
		cliente6.setIdEmpresa(filial.getIdEmpresa());
		cliente6.setIdFilial(filial.getIdFilial());
		cliente6.setIdCidade(3451);
		cliente6.setCep("85502-070");
		cliente6.setBairro("Menino Deus");
		
		ClienteIntegration cliente7 = new ClienteIntegration();
		cliente7.setIdCliente(7);
		cliente7.setCelular("9321321");
		cliente7.setNome("ARTUR BERNARDES");
		cliente7.setContato("ARTUR BERNARDES");
		cliente7.setCpfCnpj("877465123815");
		cliente7.setDescMax(10.0);
		cliente7.setDtHrAlteracao(new GregorianCalendar());
		cliente7.setEmail("cliente@email.com");
		cliente7.setFax("3213271321");
		cliente7.setFoneComercial("32265421");
		cliente7.setFoneResidencial("326545686");
		cliente7.setInativo(false);
		cliente7.setIncricao("05648765231");
		cliente7.setNumero("231");
		cliente7.setRua("RUA PRINCIPAL");
		cliente7.setTipoPessoa(1);
		cliente7.setIdEmpresa(filial.getIdEmpresa());
		cliente7.setIdFilial(filial.getIdFilial());
		cliente7.setIdCidade(3451);
		cliente7.setCep("85502-070");
		cliente7.setBairro("Menino Deus");
		
		ClienteIntegration cliente8 = new ClienteIntegration();
		cliente8.setIdCliente(8);
		cliente8.setCelular("9321321");
		cliente8.setNome("AFONSO PENA");
		cliente8.setContato("AFONSO PENA");
		cliente8.setCpfCnpj("8932323232");
		cliente8.setDescMax(10.0);
		cliente8.setDtHrAlteracao(new GregorianCalendar());
		cliente8.setEmail("cliente@email.com");
		cliente8.setFax("3213271321");
		cliente8.setFoneComercial("32265421");
		cliente8.setFoneResidencial("326545686");
		cliente8.setInativo(false);
		cliente8.setIncricao("05648765231");
		cliente8.setNumero("231");
		cliente8.setRua("RUA PRINCIPAL");
		cliente8.setTipoPessoa(1);
		cliente8.setIdEmpresa(filial.getIdEmpresa());
		cliente8.setIdFilial(filial.getIdFilial());
		cliente8.setIdCidade(3451);
		cliente8.setCep("85502-070");
		cliente8.setBairro("Menino Deus");
		
		ClienteIntegration cliente9 = new ClienteIntegration();
		cliente9.setIdCliente(9);
		cliente9.setCelular("9321321");
		cliente9.setNome("FLORIANO PEIXOTO");
		cliente9.setContato("FLORIANO PEIXOTO");
		cliente9.setCpfCnpj("89746516235");
		cliente9.setDescMax(10.0);
		cliente9.setDtHrAlteracao(new GregorianCalendar());
		cliente9.setEmail("cliente@email.com");
		cliente9.setFax("3213271321");
		cliente9.setFoneComercial("32265421");
		cliente9.setFoneResidencial("326545686");
		cliente9.setInativo(false);
		cliente9.setIncricao("05648765231");
		cliente9.setNumero("231");
		cliente9.setRua("RUA PRINCIPAL");
		cliente9.setTipoPessoa(1);
		cliente9.setIdEmpresa(filial.getIdEmpresa());
		cliente9.setIdFilial(filial.getIdFilial());
		cliente9.setIdCidade(3451);
		cliente9.setCep("85502-070");
		cliente9.setBairro("Menino Deus");
		
		ClienteIntegration cliente10 = new ClienteIntegration();
		cliente10.setIdCliente(10);
		cliente10.setCelular("9321321");
		cliente10.setNome("DEODORO DA FONSECA");
		cliente10.setContato("DEODORO DA FONSECA");
		cliente10.setCpfCnpj("897465123815");
		cliente10.setDescMax(10.0);
		cliente10.setDtHrAlteracao(new GregorianCalendar());
		cliente10.setEmail("cliente@email.com");
		cliente10.setFax("3213271321");
		cliente10.setFoneComercial("32265421");
		cliente10.setFoneResidencial("326545686");
		cliente10.setInativo(false);
		cliente10.setIncricao("05648765231");
		cliente10.setNumero("231");
		cliente10.setRua("RUA PRINCIPAL");
		cliente10.setTipoPessoa(1);
		cliente10.setIdEmpresa(filial.getIdEmpresa());
		cliente10.setIdFilial(filial.getIdFilial());
		cliente10.setIdCidade(3451);
		cliente10.setCep("85502-070");
		cliente10.setBairro("Menino Deus");
		
		ClienteIntegration cliente11 = new ClienteIntegration();
		cliente11.setIdCliente(11);
		cliente11.setCelular("9321321");
		cliente11.setNome("TIM MAIA");
		cliente11.setContato("TIM MAIA");
		cliente11.setCpfCnpj("897465123815");
		cliente11.setDescMax(10.0);
		cliente11.setDtHrAlteracao(new GregorianCalendar());
		cliente11.setEmail("cliente@email.com");
		cliente11.setFax("3213271321");
		cliente11.setFoneComercial("32265421");
		cliente11.setFoneResidencial("326545686");
		cliente11.setInativo(false);
		cliente11.setIncricao("05648765231");
		cliente11.setNumero("231");
		cliente11.setRua("RUA PRINCIPAL");
		cliente11.setTipoPessoa(1);
		cliente11.setIdEmpresa(filial.getIdEmpresa());
		cliente11.setIdFilial(filial.getIdFilial());
		cliente11.setIdCidade(3451);
		cliente11.setCep("85502-070");
		cliente11.setBairro("Menino Deus");
		
		
		ClienteIntegration cliente12 = new ClienteIntegration();
		cliente12.setIdCliente(12);
		cliente12.setCelular("9321321");
		cliente12.setNome("EPITÁCIO PESSOA");
		cliente12.setContato("EPITÁCIO PESSOA");
		cliente12.setCpfCnpj("897465123115");
		cliente12.setDescMax(10.0);
		cliente12.setDtHrAlteracao(new GregorianCalendar());
		cliente12.setEmail("cliente@email.com");
		cliente12.setFax("3213271321");
		cliente12.setFoneComercial("32265421");
		cliente12.setFoneResidencial("326545686");
		cliente12.setInativo(false);
		cliente12.setIncricao("05648765231");
		cliente12.setNumero("231");
		cliente12.setRua("RUA PRINCIPAL");
		cliente12.setTipoPessoa(1);
		cliente12.setIdEmpresa(filial.getIdEmpresa());
		cliente12.setIdFilial(filial.getIdFilial());
		cliente12.setIdCidade(3451);
		cliente12.setCep("85502-070");
		cliente12.setBairro("Menino Deus");
		
		ClienteIntegration cliente13 = new ClienteIntegration();
		cliente13.setIdCliente(13);
		cliente13.setCelular("9321321");
		cliente13.setNome("PRUDENTE DE MORAIS");
		cliente13.setContato("PRUDENTE DE MORAIS");
		cliente13.setCpfCnpj("897465123115");
		cliente13.setDescMax(10.0);
		cliente13.setDtHrAlteracao(new GregorianCalendar());
		cliente13.setEmail("cliente@email.com");
		cliente13.setFax("3213271321");
		cliente13.setFoneComercial("32265421");
		cliente13.setFoneResidencial("326545686");
		cliente13.setInativo(false);
		cliente13.setIncricao("05648765231");
		cliente13.setNumero("231");
		cliente13.setRua("RUA PRINCIPAL");
		cliente13.setTipoPessoa(1);
		cliente13.setIdEmpresa(filial.getIdEmpresa());
		cliente13.setIdFilial(filial.getIdFilial());
		cliente13.setIdCidade(3451);
		cliente13.setCep("85502-070");
		cliente13.setBairro("Menino Deus");
		
		
		ClienteIntegration cliente14 = new ClienteIntegration();
		cliente14.setIdCliente(14);
		cliente14.setCelular("93861321");
		cliente14.setNome("CAMPOS SALES");
		cliente14.setContato("CAMPOS SALES");
		cliente14.setCpfCnpj("83265123005");
		cliente14.setDescMax(10.0);
		cliente14.setDtHrAlteracao(new GregorianCalendar());
		cliente14.setEmail("cliente@email.com");
		cliente14.setFax("3213271321");
		cliente14.setFoneComercial("32265421");
		cliente14.setFoneResidencial("326545686");
		cliente14.setInativo(false);
		cliente14.setIncricao("05648765231");
		cliente14.setNumero("231");
		cliente14.setRua("RUA PRINCIPAL");
		cliente14.setTipoPessoa(1);
		cliente14.setIdEmpresa(filial.getIdEmpresa());
		cliente14.setIdFilial(filial.getIdFilial());
		cliente14.setIdCidade(3451);
		cliente14.setCep("85502-070");
		cliente14.setBairro("Menino Deus");
		
		lsCliente.add(cliente1);
		lsCliente.add(cliente2);
		lsCliente.add(cliente3);
		lsCliente.add(cliente4);
		lsCliente.add(cliente5);
		lsCliente.add(cliente6);
		lsCliente.add(cliente7);
		lsCliente.add(cliente8);
		lsCliente.add(cliente9);
		lsCliente.add(cliente10);
		lsCliente.add(cliente11);
		lsCliente.add(cliente12);
		lsCliente.add(cliente13);
		lsCliente.add(cliente14);
		
		clienteHibernate.save(lsCliente);
	}
	
}
