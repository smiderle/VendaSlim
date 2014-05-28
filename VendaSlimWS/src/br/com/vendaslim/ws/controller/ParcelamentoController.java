package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.ParcelamentoIntegration;
import br.com.vendaslim.ws.infra.ParcelamentoHibernate;

public class ParcelamentoController {
private ParcelamentoHibernate parcelamentoHibernate;
	
	public ParcelamentoController() {
		parcelamentoHibernate = DAOFactory.criaParcelamentoRepository();
	}
		
	public List<ParcelamentoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return parcelamentoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
	
	public void geraParcelamento(FilialIntegration filial){
		List<ParcelamentoIntegration> lsParcelas = new ArrayList<>();

		ParcelamentoIntegration parcelamento5 = new ParcelamentoIntegration();
		parcelamento5.setIdEmpresa(filial.getIdEmpresa());
		parcelamento5.setIdFilial(filial.getIdFilial());
		parcelamento5.setIdParcelamento(1);
		parcelamento5.setCarencia(0);
		parcelamento5.setDescricao("A VISTA");
		parcelamento5.setDiasEntreParcela(1);
		parcelamento5.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento5.setInativo(false);
		parcelamento5.setNroParcela(1);
		parcelamento5.setPercentual(0.0);
		
		ParcelamentoIntegration parcelamento2 = new ParcelamentoIntegration();
		parcelamento2.setIdEmpresa(filial.getIdEmpresa());
		parcelamento2.setIdFilial(filial.getIdFilial());
		parcelamento2.setIdParcelamento(2);
		parcelamento2.setCarencia(15);
		parcelamento2.setDescricao("15 Dias");
		parcelamento2.setDiasEntreParcela(15);
		parcelamento2.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento2.setInativo(false);
		parcelamento2.setNroParcela(1);
		parcelamento2.setPercentual(5.0);
		
		ParcelamentoIntegration parcelamento = new ParcelamentoIntegration();
		parcelamento.setIdEmpresa(filial.getIdEmpresa());
		parcelamento.setIdFilial(filial.getIdFilial());
		parcelamento.setIdParcelamento(3);
		parcelamento.setCarencia(30);
		parcelamento.setDescricao("30 DIAS");
		parcelamento.setDiasEntreParcela(1);
		parcelamento.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento.setInativo(false);
		parcelamento.setNroParcela(1);
		parcelamento.setPercentual(0.0);
		
		ParcelamentoIntegration parcelamento3 = new ParcelamentoIntegration();
		parcelamento3.setIdEmpresa(filial.getIdEmpresa());
		parcelamento3.setIdFilial(filial.getIdFilial());
		parcelamento3.setIdParcelamento(4);
		parcelamento3.setCarencia(30);
		parcelamento3.setDescricao("30x60x90");
		parcelamento3.setDiasEntreParcela(30);
		parcelamento3.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento3.setInativo(false);
		parcelamento3.setNroParcela(3);
		parcelamento3.setPercentual(0.0);
				
		ParcelamentoIntegration parcelamento1 = new ParcelamentoIntegration();
		parcelamento1.setIdEmpresa(filial.getIdEmpresa());
		parcelamento1.setIdFilial(filial.getIdFilial());
		parcelamento1.setIdParcelamento(5);
		parcelamento1.setCarencia(30);
		parcelamento1.setDescricao("30x60x90x120");
		parcelamento1.setDiasEntreParcela(30);
		parcelamento1.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento1.setInativo(false);
		parcelamento1.setNroParcela(4);
		parcelamento1.setPercentual(10.0);
		
		ParcelamentoIntegration parcelamento4 = new ParcelamentoIntegration();
		parcelamento4.setIdEmpresa(filial.getIdEmpresa());
		parcelamento4.setIdFilial(filial.getIdFilial());
		parcelamento4.setIdParcelamento(6);
		parcelamento4.setCarencia(20);
		parcelamento4.setDescricao("20x40x60x80");
		parcelamento4.setDiasEntreParcela(20);
		parcelamento4.setDtHrAlteracao(new GregorianCalendar());		
		parcelamento4.setInativo(false);
		parcelamento4.setNroParcela(4);
		parcelamento4.setPercentual(0.0);
		
		lsParcelas.add(parcelamento);
		lsParcelas.add(parcelamento1);
		lsParcelas.add(parcelamento2);
		lsParcelas.add(parcelamento3);
		lsParcelas.add(parcelamento4);
		lsParcelas.add(parcelamento5);
		
		parcelamentoHibernate.save(lsParcelas);
	}
}
