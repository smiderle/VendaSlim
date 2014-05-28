package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.ItemIntegration;
import br.com.vendaslim.ws.infra.ProdutoHibernate;

public class ProdutoController {
private ProdutoHibernate produtoHibernate;
	
	public ProdutoController() {
		produtoHibernate = DAOFactory.criaProdutoRepository();
	}
		
	public List<ItemIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return produtoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
	
	
	public void geraProdutos(FilialIntegration filial){
		List<ItemIntegration> lsItem = new ArrayList<>();
		ItemIntegration item1 = new ItemIntegration();
		item1.setIdGrupo(1);
		item1.setIdItem(1489380);
		item1.setEstoque(250.0);
		item1.setCodbar("789216543121");
		item1.setDescricao("AGUA DE COCO DUCOCO 1 LITRO");
		item1.setDescMax(50.0);
		item1.setInativo(false);
		item1.setDtHrAlteracao(new GregorianCalendar());		
		item1.setIdEmpresa(filial.getIdEmpresa());
		item1.setIdFilial(filial.getIdFilial());
		item1.setPrecoVenda(0.90);		
		item1.setUnidade("GR");
		
		
		ItemIntegration item2 = new ItemIntegration();		
		item2.setIdItem(593966 );
		item2.setEstoque(123.0);
		item2.setCodbar("78921593966 ");
		item2.setDescricao("AGUA MINERAL CRYSTAL COM GÁS 1,5 LITROS");
		item2.setIdGrupo(1);
		item2.setInativo(false);
		item2.setDescMax(50.0);		
		item2.setDtHrAlteracao(new GregorianCalendar());		
		item2.setIdEmpresa(filial.getIdEmpresa());
		item2.setIdFilial(filial.getIdFilial());
		item2.setPrecoVenda(1.23);		
		item2.setUnidade("GR");
		
		ItemIntegration item3 = new ItemIntegration();
		item3.setIdItem(1317750);
		item3.setEstoque(323.0);		
		item3.setCodbar("7892321152");
		item3.setDescricao("CERVEJA BADEN BADEN ALE GOLDEN 600ML");
		item3.setInativo(false);
		item3.setIdGrupo(1);
		item3.setDescMax(50.0);		
		item3.setDtHrAlteracao(new GregorianCalendar());		
		item3.setIdEmpresa(filial.getIdEmpresa());
		item3.setIdFilial(filial.getIdFilial());
		item3.setPrecoVenda(10.53);
		item3.setReferencia("CERVEJA BADEN BADEN ALE GOLDEN 600ML");
		item3.setUnidade("GR");
		
		
		ItemIntegration item4 = new ItemIntegration();
		item4.setIdItem(130800);
		item4.setEstoque(500.0);		
		item4.setCodbar("789113213");
		item4.setDescricao("CERVEJA BAVARIA CLÁSSICA LATA 350ML");
		item4.setPrecoVenda(1.50);
		item4.setInativo(false);
		item4.setIdGrupo(1);
		item4.setDescMax(50.0);		
		item4.setDtHrAlteracao(new GregorianCalendar());		
		item4.setIdEmpresa(filial.getIdEmpresa());
		item4.setIdFilial(filial.getIdFilial());
		item4.setUnidade("GR");
		
		
		ItemIntegration item5 = new ItemIntegration();
		item5.setIdItem(130801);
		item5.setEstoque(500.0);		
		item5.setCodbar("789132323");
		item5.setDescricao("CERVEJA BLACK PRINCESS 600ML");
		item5.setPrecoVenda(1.30);
		item5.setInativo(false);
		item5.setIdGrupo(1);
		item5.setDescMax(50.0);		
		item5.setDtHrAlteracao(new GregorianCalendar());		
		item5.setIdEmpresa(filial.getIdEmpresa());
		item5.setIdFilial(filial.getIdFilial());
		item5.setUnidade("GR");
		
		
		
		ItemIntegration item6 = new ItemIntegration();
		item6.setIdItem(130802);
		item6.setEstoque(100.0);		
		item6.setCodbar("789113213");
		item6.setDescricao("CERVEJA BRAHMA CHOPP LATA 473ML");
		item6.setPrecoVenda(2.00);
		item6.setInativo(false);
		item6.setIdGrupo(1);
		item6.setDescMax(50.0);		
		item6.setDtHrAlteracao(new GregorianCalendar());		
		item6.setIdEmpresa(filial.getIdEmpresa());
		item6.setIdFilial(filial.getIdFilial());
		item6.setUnidade("GR");
		
		
		ItemIntegration item7 = new ItemIntegration();
		item7.setIdItem(130803);
		item7.setEstoque(500.0);		
		item7.setCodbar("789113122");
		item7.setDescricao("CERVEJA BUDWEISER LATA 350ML");
		item7.setPrecoVenda(1.75);
		item7.setInativo(false);
		item7.setIdGrupo(1);
		item7.setDescMax(50.0);		
		item7.setDtHrAlteracao(new GregorianCalendar());		
		item7.setIdEmpresa(filial.getIdEmpresa());
		item7.setIdFilial(filial.getIdFilial());
		item7.setUnidade("GR");
		
		
		ItemIntegration item8 = new ItemIntegration();
		item8.setIdItem(130804);
		item8.setEstoque(128.0);		
		item8.setCodbar("789113215");
		item8.setDescricao("CERVEJA CARACU LATA 350ML");
		item8.setPrecoVenda(4.11);
		item8.setInativo(false);
		item8.setIdGrupo(1);
		item8.setDescMax(50.0);		
		item8.setDtHrAlteracao(new GregorianCalendar());		
		item8.setIdEmpresa(filial.getIdEmpresa());
		item8.setIdFilial(filial.getIdFilial());
		item8.setUnidade("GR");

		ItemIntegration item9 = new ItemIntegration();
		item9.setIdItem(130805);
		item9.setEstoque(235.0);		
		item9.setCodbar("789113201");
		item9.setDescricao("CERVEJA CRYSTAL 0,0% ALCOOL LATA 350ML");
		item9.setPrecoVenda(1.01);
		item9.setInativo(false);
		item9.setIdGrupo(1);
		item9.setDescMax(50.0);		
		item9.setDtHrAlteracao(new GregorianCalendar());		
		item9.setIdEmpresa(filial.getIdEmpresa());
		item9.setIdFilial(filial.getIdFilial());
		item9.setUnidade("GR");
		
		
		ItemIntegration item10 = new ItemIntegration();
		item10.setIdItem(130806);
		item10.setEstoque(29.0);		
		item10.setCodbar("789113216");
		item10.setDescricao("REFRESCO EM PÓ CAMP SABOR MANGA 25G");
		item10.setPrecoVenda(1.75);
		item10.setInativo(false);
		item10.setIdGrupo(1);
		item10.setDescMax(50.0);		
		item10.setDtHrAlteracao(new GregorianCalendar());		
		item10.setIdEmpresa(filial.getIdEmpresa());
		item10.setIdFilial(filial.getIdFilial());
		item10.setUnidade("GR");
		
		
		
		ItemIntegration item11 = new ItemIntegration();
		item11.setIdItem(130807);
		item11.setEstoque(4.0);		
		item11.setCodbar("7891132191");
		item11.setDescricao("REFRESCO MID LIMÃO 25G");
		item11.setPrecoVenda(1.50);
		item11.setInativo(false);
		item11.setIdGrupo(1);
		item11.setDescMax(50.0);		
		item11.setDtHrAlteracao(new GregorianCalendar());		
		item11.setIdEmpresa(filial.getIdEmpresa());
		item11.setIdFilial(filial.getIdFilial());
		item11.setUnidade("GR");
		
		
		ItemIntegration item12 = new ItemIntegration();
		item12.setIdItem(130808);
		item12.setEstoque(9.0);		
		item12.setCodbar("789113213");
		item12.setDescricao("REFRIGERANTE SIMBA GUARANÁ 2 LITROS");
		item12.setPrecoVenda(3.79);
		item12.setInativo(false);
		item12.setIdGrupo(1);
		item12.setDescMax(50.0);		
		item12.setDtHrAlteracao(new GregorianCalendar());		
		item12.setIdEmpresa(filial.getIdEmpresa());
		item12.setIdFilial(filial.getIdFilial());
		item12.setUnidade("GR");

		
		
		
		ItemIntegration item13 = new ItemIntegration();
		item13.setIdItem(140808);
		item13.setEstoque(17.0);		
		item13.setCodbar("7891321313");
		item13.setDescricao("ABS INTIMUS GEL SUAVE C/ ABAS LV16PG14");
		item13.setPrecoVenda(4.89);
		item13.setInativo(false);
		item13.setIdGrupo(2);
		item13.setDescMax(50.0);		
		item13.setDtHrAlteracao(new GregorianCalendar());		
		item13.setIdEmpresa(filial.getIdEmpresa());
		item13.setIdFilial(filial.getIdFilial());
		item13.setUnidade("PC");
		
		
		ItemIntegration item14 = new ItemIntegration();
		item14.setIdItem(130810);
		item14.setEstoque(3.0);		
		item14.setCodbar("7981321313");
		item14.setDescricao("ALGODÃO TOPZ BOLA 50G");
		item14.setPrecoVenda(3.24);
		item14.setInativo(false);
		item14.setIdGrupo(2);
		item14.setDescMax(50.0);		
		item14.setDtHrAlteracao(new GregorianCalendar());		
		item14.setIdEmpresa(filial.getIdEmpresa());
		item14.setIdFilial(filial.getIdFilial());
		item14.setUnidade("PC");
		
		
		ItemIntegration item15 = new ItemIntegration();
		item15.setIdItem(130811);
		item15.setEstoque(9.0);		
		item15.setCodbar("78912313");
		item15.setDescricao("AMOLECEDOR FARMAX CUTICULA CREME 25G");
		item15.setPrecoVenda(6.69);
		item15.setInativo(false);
		item15.setIdGrupo(2);
		item15.setDescMax(50.0);		
		item15.setDtHrAlteracao(new GregorianCalendar());		
		item15.setIdEmpresa(filial.getIdEmpresa());
		item15.setIdFilial(filial.getIdFilial());
		item15.setUnidade("BS");
		
		ItemIntegration item16 = new ItemIntegration();
		item16.setIdItem(130814);
		item16.setEstoque(19.0);		
		item16.setCodbar("78945613213");
		item16.setDescricao("APARELHO BARBEAR BIC FLEX 4 PRATA COM 1");
		item16.setPrecoVenda(4.49);
		item16.setInativo(false);
		item16.setIdGrupo(2);
		item16.setDescMax(50.0);		
		item16.setDtHrAlteracao(new GregorianCalendar());		
		item16.setIdEmpresa(filial.getIdEmpresa());
		item16.setIdFilial(filial.getIdFilial());
		item16.setUnidade("CX");
		
		ItemIntegration item17 = new ItemIntegration();
		item17.setIdItem(130815);
		item17.setEstoque(21.0);		
		item17.setCodbar("789123132");
		item17.setDescricao("BLOQUEADOR SOLAR SUNDOWN FPS 50 120ML.");
		item17.setPrecoVenda(4.23);
		item17.setInativo(false);
		item17.setIdGrupo(2);
		item17.setDescMax(50.0);		
		item17.setDtHrAlteracao(new GregorianCalendar());		
		item17.setIdEmpresa(filial.getIdEmpresa());
		item17.setIdFilial(filial.getIdFilial());
		item17.setUnidade("GR");
		
		ItemIntegration item18 = new ItemIntegration();
		item18.setIdItem(130816);
		item18.setEstoque(13.0);		
		item18.setCodbar("7894564123");
		item18.setDescricao("CONDICIONADOR CLEAR WOMEN CRESCIMENTO EFORÇA 400ML");
		item18.setPrecoVenda(5.36);
		item18.setInativo(false);
		item18.setIdGrupo(2);
		item18.setDescMax(50.0);		
		item18.setDtHrAlteracao(new GregorianCalendar());		
		item18.setIdEmpresa(filial.getIdEmpresa());
		item18.setIdFilial(filial.getIdFilial());
		item18.setUnidade("GR");
		

		
		ItemIntegration item19 = new ItemIntegration();
		item19.setIdItem(130818);
		item19.setEstoque(5.0);		
		item19.setCodbar("4654646565");
		item19.setDescricao("TINTURA KOLESTON MECHAS");
		item19.setPrecoVenda(13.25);
		item19.setInativo(false);
		item19.setIdGrupo(2);
		item19.setDescMax(50.0);		
		item19.setDtHrAlteracao(new GregorianCalendar());		
		item19.setIdEmpresa(filial.getIdEmpresa());
		item19.setIdFilial(filial.getIdFilial());
		item19.setUnidade("GR");
		
		
		
		
		
		ItemIntegration item20 = new ItemIntegration();
		item20.setIdItem(130218);
		item20.setEstoque(13.0);		
		item20.setCodbar("7897546546");
		item20.setDescricao("LIMPADOR MR MUSCULO MANHA CAMPO 900ML");
		item20.setPrecoVenda(24.47);
		item20.setInativo(false);
		item20.setIdGrupo(3);
		item20.setDescMax(50.0);		
		item20.setDtHrAlteracao(new GregorianCalendar());		
		item20.setIdEmpresa(filial.getIdEmpresa());
		item20.setIdFilial(filial.getIdFilial());
		item20.setUnidade("GR");
		
		

		ItemIntegration item21 = new ItemIntegration();
		item21.setIdItem(130219);
		item21.setEstoque(15.0);		
		item21.setCodbar("78956466561");
		item21.setDescricao("REMOVEDOR VARSOL ESPECIAL CASA 500ML");
		item21.setPrecoVenda(7.50);
		item21.setInativo(false);
		item21.setIdGrupo(3);
		item21.setDescMax(50.0);		
		item21.setDtHrAlteracao(new GregorianCalendar());		
		item21.setIdEmpresa(filial.getIdEmpresa());
		item21.setIdFilial(filial.getIdFilial());
		item21.setUnidade("GR");
		

		ItemIntegration item22 = new ItemIntegration();
		item22.setIdItem(130820);
		item22.setEstoque(32.0);		
		item22.setCodbar("789415616");
		item22.setDescricao("REPELENTE SUPER REPELEX FAMILYCARE AÇÃO HIDRATANTE 200ML");
		item22.setPrecoVenda(15.45);
		item22.setInativo(false);
		item22.setIdGrupo(3);
		item22.setDescMax(50.0);		
		item22.setDtHrAlteracao(new GregorianCalendar());		
		item22.setIdEmpresa(filial.getIdEmpresa());
		item22.setIdFilial(filial.getIdFilial());
		item22.setUnidade("GR");
		
		

		ItemIntegration item23 = new ItemIntegration();
		item23.setIdItem(134821);
		item23.setEstoque(4.0);		
		item23.setCodbar("79851313210");
		item23.setDescricao("ALVEJANTE Q BOA LAVANDA 2 LITROS");
		item23.setPrecoVenda(4.95);
		item23.setInativo(false);
		item23.setIdGrupo(3);
		item23.setDescMax(50.0);		
		item23.setDtHrAlteracao(new GregorianCalendar());		
		item23.setIdEmpresa(filial.getIdEmpresa());
		item23.setIdFilial(filial.getIdFilial());
		item23.setUnidade("GR");
		

		ItemIntegration item24 = new ItemIntegration();
		item24.setIdItem(130822);
		item24.setEstoque(5.0);		
		item24.setCodbar("79865465465");
		item24.setDescricao("AMACIANTE FOFO FRUTAL 2 LITROS");
		item24.setPrecoVenda(3.29);
		item24.setInativo(false);
		item24.setIdGrupo(3);
		item24.setDescMax(50.0);		
		item24.setDtHrAlteracao(new GregorianCalendar());		
		item24.setIdEmpresa(filial.getIdEmpresa());
		item24.setIdFilial(filial.getIdFilial());
		item24.setUnidade("GR");
		
		
		ItemIntegration item25 = new ItemIntegration();
		item25.setIdItem(140922);
		item25.setEstoque(5.0);		
		item25.setCodbar("798621313125");
		item25.setDescricao("HAMBÚRGUER COSTA SUL DE PEIXE 448G");
		item25.setPrecoVenda(1.39);
		item25.setInativo(false);
		item25.setIdGrupo(4);
		item25.setDescMax(50.0);		
		item25.setDtHrAlteracao(new GregorianCalendar());		
		item25.setIdEmpresa(filial.getIdEmpresa());
		item25.setIdFilial(filial.getIdFilial());
		item25.setUnidade("GR");
		
		
		ItemIntegration item26 = new ItemIntegration();
		item26.setIdItem(130912);
		item26.setEstoque(5.0);		
		item26.setCodbar("798654612358");
		item26.setDescricao("POLPA FRUTA DOCE VIDA PÊSSEGO 100G");
		item26.setPrecoVenda(5.32);
		item26.setInativo(false);
		item26.setIdGrupo(4);
		item26.setDescMax(50.0);		
		item26.setDtHrAlteracao(new GregorianCalendar());		
		item26.setIdEmpresa(filial.getIdEmpresa());
		item26.setIdFilial(filial.getIdFilial());
		item26.setUnidade("GR");
		
		ItemIntegration item27 = new ItemIntegration();
		item27.setIdItem(130932);
		item27.setEstoque(5.0);		
		item27.setCodbar("79865465465");
		item27.setDescricao("SORVETE HAAGEN-DAZS MORANGO 473ML");
		item27.setPrecoVenda(3.29);
		item27.setInativo(false);
		item27.setIdGrupo(4);
		item27.setDescMax(50.0);		
		item27.setDtHrAlteracao(new GregorianCalendar());		
		item27.setIdEmpresa(filial.getIdEmpresa());
		item27.setIdFilial(filial.getIdFilial());
		item27.setUnidade("GR");
		
		lsItem.add(item1);
		lsItem.add(item2);
		lsItem.add(item3);
		lsItem.add(item4);
		lsItem.add(item5);
		lsItem.add(item6);
		lsItem.add(item7);
		lsItem.add(item8);
		lsItem.add(item9);
		lsItem.add(item10);
		lsItem.add(item11);
		lsItem.add(item12);
		lsItem.add(item13);
		lsItem.add(item14);
		lsItem.add(item15);
		lsItem.add(item16);
		lsItem.add(item17);
		lsItem.add(item18);
		lsItem.add(item19);
		lsItem.add(item20);
		lsItem.add(item21);
		lsItem.add(item22);
		lsItem.add(item23);
		lsItem.add(item24);
		lsItem.add(item25);
		lsItem.add(item26);
		lsItem.add(item27);
		produtoHibernate.save(lsItem);
	}
}
