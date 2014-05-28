package br.com.slim.venda.util;

import java.util.ArrayList;

import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;

public class GeraEmail {

	public String emailString(PedidoVO pedidoVO){
		String contato = pedidoVO.getClienteVO().getFoneCelular() != null ? pedidoVO.getClienteVO().getFoneCelular() :
			pedidoVO.getClienteVO().getFoneComercial() != null ? pedidoVO.getClienteVO().getFoneComercial() :
				pedidoVO.getClienteVO().getFoneResidencial() != null ? pedidoVO.getClienteVO().getFoneResidencial() : " - " + pedidoVO.getClienteVO().getCidadeVO().getMunicipio() +" - "
			+pedidoVO.getClienteVO().getCidadeVO().getEstado() ; 
		
		String corpo ="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"+
				"<html>"+
				"<head>"+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"+
				"<title>Insert title here</title>"+
				"<style type=\"text/css\">"+
				"h5 {"+
				"	font: bold; 10 px verdana;"+
				"	padding: 0;"+
				"	margin: 0;"+
				"}"+
				"h6 {"+
				"color:#696969;"+
					"font: bold; 10 px verdana;"+
					"padding: 0;"+
					"margin: 0;"+
				"}"+
				"h4 {	"+
				"	color:#696969;"+
				"	font-size:12px;"+
				"	font: normal; 8 px verdana;"+
				"	padding: 0;"+
				"	margin: 0;"+
				"}"+
				"h3 {"+
				"	color:#0076BF;"+
				"	font-size:12px;"+
				"	font: bold; 12 px verdana;"+
				"	padding: 0;"+
				"	margin: 0;"+
				"}"+
				"}"+
				"</style>"+
				"</head>"+
				"<body>"+
				"	<table border=\"1\" width=\"80%\" align=\"center\">"+
				"		<tr height=\"100\">"+
				"			<td align=\"center\" width=\"20%\">Logo</td>"+
				"			<td align=\"left\" valign=\"top\" colspan=\"5\">"+
				"				<table align=\"left\" width=\"100%\">"+
				"					<tr>"+
				"						<td width=\"10px\"><h3>Cliente:</h3></td>"+
				"						<td><h4>"+pedidoVO.getClienteVO().getIdCliente()+ "  - "+pedidoVO.getClienteVO().getNome() + "</h4></td>"+
				"					<td><h3>"+((pedidoVO.getClienteVO().getTipo() == ClienteVO.TIPO_FISICA) ? "CPF" : "CNPJ") +":</h3></td>"+
				"						<td><h4>pedidoVO.getClienteVO().getCpf() != null ? pedidoVO.getClienteVO().getCpf() : </td>"+
				"					</tr>"+
				"					<tr>"+
				"						<td><h3>Fone:</h3></td>"+
				"						<td><h4>Teste</h4></td>"+
				"					</tr>"+
				"					<tr>"+
				"						<td><h3>Data:</h3></td>"+
				"						<td><h4>"+Convert.toDateTimeStr(pedidoVO.getDtEmisao())+"</h4></td>"+
				"					</tr>"+
				"				</table>"+
				"			</td>"+
				"			<td align=\"center\" width=\"15%\" style=\"font-size: 30px;\">50</td>"+
				"		</tr>"+
				"		<tr>"+
				"			<th align=\"center\">Código</th>"+
				"			<th align=\"center\" colspan=\"3\">Descricao</th>"+
				"			<th align=\"center\" width=\"10\">Quantidade</th>"+
				"			<th align=\"center\" width=\"10\">Desconto</th>"+
				"			<th align=\"center\">Valor Unitario R$</th>"+
				"		</tr>";
				
				for(PedidoItemVO pedidoItemVO : pedidoVO.getPedidoItemVO()){
					corpo += "		<tr>"+
							"			<td height=\"5\" align=\"center\"><h6>"+pedidoItemVO.getItemVO().getIdItem()+"</h6></td>"+
							"			<td height=\"5\" align=\"left\" colspan=\"3\"><h6>"+pedidoItemVO.getItemVO().getDescricao()+"</h6></td>"+
							"			<td height=\"5\" align=\"center\" width=\"10\"><h6>"+pedidoItemVO.getQuantidade()+"</h6></td>"+
							"			<td height=\"5\" align=\"center\" width=\"10\"><h6>"+pedidoItemVO.getDesconto()+"</h6></td>"+
							"			<td height=\"5\" align=\"center\"><h6>"+pedidoItemVO.getPrecoVenda()+"</h6></td>"+
							"		</tr>";
				}
				
				corpo +=
				"		<tr>"+
				"			<td align=\"right\"><h3>Observação:</h3></td>"+
				"			<td colspan=\"3\">"+pedidoVO.getObservacao()+"</td>"+
				"			<td align=\"right\" colspan=\"3\">Total Liquido R$: "+pedidoVO.getTotalLiquido()+"</td>"+
				"		</tr>		"+
				"	</table>"+
				"</body>"+
				"</html>";
				
				return corpo;
	}
		
	

	public String emailPedido(PedidoVO pedidoVO) {
		String body = "";
		try {
			Double valor = 0.0;
			body += "<html>";
			body += "<head>";
			body += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />";
			body += "<title>Pedidos Móbile</title>";
			body += "<style type=\"text/css\">";
			body += "* {";
			body += "font:10px Verdana;";
			body += "}";
			body += "h1 {";
			body += "font:bold 14px Verdana;";
			body += "}";
			body += ".tdRight {";
			body += "border-right:1px solid #000000;";
			body += "}";
			body += ".tdLeft {";
			body += "border-left:1px solid #000000;";
			body += "}";
			body += ".tdTop {";
			body += "border-top:1px solid #000000;";
			body += "}";
			body += ".tdBottom {";
			body += "border-bottom:1px solid #000000;";
			body += "}";
			body += ".tdTopRight {";
			body += "border-top:1px solid #000000;";
			body += "border-right: 1px solid #000000;";
			body += "}";
			body += ".tdTopRightBottom {";
			body += "border-top:1px solid #000000;";
			body += "border-right: 1px solid #000000;";
			body += "border-bottom: 1px solid #000000;";
			body += "}";
			body += ".tdTopRightBottomItem {";
			body += "border-top:1px solid #000000;";
			body += "border-right: 1px solid #000000;";
			body += "border-bottom: 1px solid #000000;";
			body += "height: 2px";
			body += "}";			
			body += ".tdTopRightLeft {";
			body += "border-top:1px solid #000000;";
			body += "border-right: 1px solid #000000;";
			body += "border-left: 1px solid #000000;";
			body += "}";
			body += ".tdTopRightLeftBottom{";
			body += "border-top:1px solid #000000;";
			body += "border-right: 1px solid #000000;";
			body += "border-left: 1px solid #000000;";
			body += "border-bottom: 1px solid #000000;";
			body += "}";
			body += "h2 {";
			body += "font:bold 11px verdana;";
			body += "}";
			body += "h3 {";
			body += "font:bold 12px verdana;";
			body += "color: red";
			body += "}";
			body += "h4 {";
			body += "font: 10px verdana;";
			body += "padding:0;";
			body += "margin: 0;";			
			body += "}";
			body += "h5 {";
			body += "font:bold 10px verdana;";
			body += "padding:0;";
			body += "margin: 0;";
			body += "}";
			body += "</style>";
			body += "</head>";
			body += "<body topmargin=\"10px\" leftmargin=\"20px\" background=\"#FFFFFF\" bgcolor=\"#FFFFFF\">";				
			// Inicio da tabela	
			body += "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">";
			body += "<tr>";
			body += "<td width=\"45px\">";
			body += "</td>";
			body += "<td width=\"45px\">";
			body += "</td>";
			body += "<td width=\"50px\">";
			body += " </td>";
			body += "<td width=\"140px\">";
			body += " </td>";
			body += "<td width=\"50px\">";
			body += " </td>";
			body += "<td width=\"120px\">";
			body += " </td>";
			body += "<td width=\"20px\">";
			body += " </td>";
			body += "<td width=\"80px\">";
			body += " </td>";
			body += "<td width=\"115px\">";
			body += " </td>";
			body += "</tr>";


			body += "<tr height=\"40px\">";
			body += "<td colspan=\"2\">";
			body += "<img src=\"http://www..jpg\" width=\"85px\" />";						
			body += "</td>";
		    body += "<td colspan=\"6\" align=\"left\" valign=\"top\">";
		    body += "<h4>VendaSlim  <br />";
			body += "Rua Itacolomi 2490 - Menino Deus - Pato Branco - PR - Cep 85502070 <br />";
			body += "<u>Empresa</u> | <u>" +			        
			        "</u>  - CONTATO: Ladair Carlos Smiderle Junior</h4>";
		    body += "</td>";
		    body += "<td colspan=\"1\"  align=\"center\" valign=\"middle\" >";
		    body += "<h1>&nbsp;&nbsp;&nbsp;" + pedidoVO.getIdPedido() + "&nbsp;&nbsp;&nbsp;</h1>";
		    body += "</td>";		    
		    body += "</tr>";
		    
			body += "<tr height=\"5px\">";  
		    body += "<td colspan=\"1\">";
		    body += "<h5>Cliente</h5>";
		    body += "</td>";

            body += "<td colspan=\"5\" >";
		    body += "<h4>"+ pedidoVO.getClienteVO().getIdCliente()+ " - " +pedidoVO.getClienteVO().getNome()+"</h4>";
		    body +=  "</td>";
		    body += "<td colspan=\"1\">";
		    body += "<h5>Data</h5>";
		    body +=  "</td>";

		    body += "<td colspan=\"2\" >";
		    body += "<h4>"+Convert.toDateTimeStr(pedidoVO.getDtEmisao()) + "&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp;";
		    body +=  "</td>";		    
		    body += "</tr>";
		    
			body += "<tr>";
			body += "<td colspan=\"1\">";
			body += "<h5>Contato </h5>";
			body += "</td>";
			body += "<td colspan=\"3\"><h4>";
			body += pedidoVO.getClienteVO().getContato() != null ? pedidoVO.getClienteVO().getContato() : "&nbsp;";
			body += "</h4></td>";			

			body += "<td colspan=\"1\">";
			body += "<h5>Região </h5>";
			body += "</td>";	
			body += "<td colspan=\"4\"><h4>";
			body +=  "SUL";
			body += "</h4></td>";
			body += "</tr>";
			
			body += "<tr>";  
			body += "<td colspan=\"1\" >";
			body += "<h5>Telefone</h5> ";
			body += "</td>";
	
			body += "<td colspan=\"3\"><h4>";
			body += pedidoVO.getClienteVO().getFoneValido();						
			body +=  "</h4></td>";
	        body += "<td colspan=\"1\">";
			body += "<h5>Fax </h5>";
			body += "</td>";
	
			body += "<td colspan=\"1\"><h4>";
			body += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			body += "</h4></td>";
	        body += "<td colspan=\"1\">";
			body += "<h5>Email</h5>";
			body +=  "</td>";			   
			body += "<td colspan=\"2\" ><h4>";
			body += pedidoVO.getClienteVO().getEmail() != null ? pedidoVO.getClienteVO().getEmail()  : "&nbsp;";
			body += "</h4></td>";
			body += "</tr>";
			
			body += "<tr>";			  
			body += "<td colspan=\"1\">";
			body += "<h5>CPF/CNPJ</h5>";
			body +=  "</td>";
	
			body += "<td colspan=\"5\" ><h4>";
			body += (pedidoVO.getClienteVO().getCpf() != null && !pedidoVO.getClienteVO().getCpf().equals("")) ? pedidoVO.getClienteVO().getCpf() : "&nbsp;";
			body +=  "</h4></td>";
	
			body += "<td colspan=\"1\">";
			body += "<h5>Rg/Insc</h5>";
			body += "</td>";
	
		    body += "<td colspan=\"2\" ><h4>";
		    body += pedidoVO.getClienteVO().getRg() != null ? pedidoVO.getClienteVO().getRg() : "&nbsp;";
		    body += "</h4></td>";
		    body +=  "</tr>";
		    body += "<tr>";
		  
		    body += "<td colspan=\"1\">";
		    body += "<h5>Endereço</h5>";
		    body +=  "</td>";

		    body += "<td colspan=\"5\"><h4>";		   	
		    body += pedidoVO.getClienteVO().getRua() != null ? pedidoVO.getClienteVO().getRua() : "&nbsp;"	+
				   " - " +pedidoVO.getClienteVO().getBairro() != null ? pedidoVO.getClienteVO().getBairro() : "&nbsp; SEM BAIRRO";
		   
		    body += "</h4></td>";

		    body += "<td colspan=\"1\">";
		    body += "<h5>Cidade</h5>";
		    body += "</td>";

		    body += "<td  colspan=\"2\"><h4>";
		    body += (pedidoVO.getClienteVO().getCidadeVO().getMunicipio() != null ? pedidoVO.getClienteVO().getCidadeVO().getMunicipio() : "&nbsp;") +
	        		"/" +(pedidoVO.getClienteVO().getCidadeVO().getEstado() != null ? pedidoVO.getClienteVO().getCidadeVO().getEstado() : "&nbsp;"); 

		    body +=  "</h4></td>";
		    body += "</tr>";
		    			//Pendencia financeira
						if (false) {						
						body += "<tr>";
						body += "<td colspan=\"9\" align=\"center\" valign=\"middle\" class=\"tdTopRightLeftBottom\">";
						body += "<h5>Cliente Possue Pendencia Financeira</h5>";
						body  += "</td>";
						body  += "</tr>";					
						}						
			body += "<tr>";
			body += "<td align=\"left\" colspan=\"1\" class=\"tdTopRightLeftBottom\">";
			body += "<h4>ITEM</h4>";
			body +=  "</td>";
			body += "<td align=\"center\" colspan=\"1\" class=\"tdTopRightBottom\">";
			body += "<h4>QTD.</h4>";
			body +=  "</td>";
			body += "<td align=\"left\" colspan=\"5\" class=\"tdTopRightBottom\">";
			body += "<h4>DESCRIÇÃOƒ</h4>";
			body += "</td align=\"center\">";
			body += "<td align=\"center\" colspan=\"1\" class=\"tdTopRightBottom\">";
			body += "<h4>&nbsp;UNITARIO&nbsp;</h4>";
			body +=  "</td>";
			body += "<td align=\"center\" colspan=\"1\" class=\"tdTopRightBottom\">";
			body += "<h4>&nbsp;TOTAL&nbsp;</h4>";
			body +=  "</td>";							
			body += "</tr>";
						
						//Inicio do Grid
						int cont = 0;				
						for (PedidoItemVO pedidoItemVO: pedidoVO.getPedidoItemVO()) {
							Double unitario = pedidoItemVO.getPrecoVenda() / pedidoItemVO.getQuantidade();
							valor += pedidoItemVO.getPrecoVenda();
							
							body += "<tr>";
							body += "<td colspan=\"1\" align=\"left\" valign=\"center\">";
							body += "<h5>"+pedidoItemVO.getItemVO().getIdItem()+"</h5>";
							body += "</td>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"center\">";
							body += "<h5>" +pedidoItemVO.getQuantidade()+"</h5>";
							body += "</td>";
							body += "<td colspan=\"5\" align=\"left\" valign=\"center\">";						
							body += "<h5>" + pedidoItemVO.getItemVO().getDescricao() + "</h5>";				
							body += "</td>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"center\">";
							body += "<h5>" +String.format("%.2f", unitario)+"</h5>";
							body += "</td>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"center\">";
							body += "<h5>" +String.format("%.2f", pedidoItemVO.getPrecoVenda())+"</h5>";
							body += "</td>";
							body += "</tr>";
							
							cont ++;
						}
						//Cria as linhas 
						if(cont <= 12){
							cont = 11 - cont;
							for(int i =0; i <= cont ; i++){						
							body += "<tr>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"middle\">";
							body += "&nbsp";
							body += "</td>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"middle\">";
							body += "&nbsp";
							body += "</td>";
														
							body += "<td colspan=\"5\">";
							body += "&nbsp;";
							body += "</td>";
														
							body += "<td colspan=\"1\" align=\"center\" valign=\"middle\">";
							body += "&nbsp";
							body += "</td>";
							body += "<td colspan=\"1\" align=\"center\" valign=\"middle\">";
							body += "&nbsp";
							body += "</td>";
							body += "</tr>";									
							}				
						}								
						
						// TOTAL
						body += "<tr>";
						body += "<td colspan=\"1\" align=\"left\" valign=\"middle\"><h5>";
						body += "SubTotal";
						body += "</h5></td>";
						
						body += "<td colspan=\"1\" align=\"left\" valign=\"middle\"><h5>";
						body += "&nbsp;" + String.format("%.2f", valor);
						body += "&nbsp;&nbsp;&nbsp;</h5></td>";	
						
						body += "<td colspan=\"3\" align=\"left\" valign=\"middle\"><h5>";
						body += "Frete&nbsp;&nbsp;&nbsp;";					
						body += "</h5></td>";
						body +="<td colspan=\"2\"><h5>";
						body += "&nbsp;&nbsp;&nbsp;";
						body +="</h5></td>";
							
						body += "<td colspan=\"1\" align=\"left\" valign=\"middle\"><h5>";
						body += "TOTAL";						
						body += "</td>";
						body += "<td colspan=\"1\" align=\"left\" valign=\"middle\"><h5>";
						body += "&nbsp;" ;
						body += "</h5></td>";						
						body += "</tr>";							
						
						body += "<tr>";											
						body += "<td colspan=\"2\" align=\"left\" valign=\"middle\"><h5>";
						body += "CONDIÇÕES: ";
						body += "&nbsp;" +pedidoVO.getParcelamentoVO().getDescricao();
						
						body += "<td colspan=\"7\" align=\"left\" valign=\"middle\"><h5>";
						body += "&nbsp;";
						body += "&nbsp;";
						//body += " - ";
						//body += pedido.getFreteEmite().equals("S") ? "Pago " : "A Pagar ";
						body += "</h5></td>";
						body += "</td>";
						body += "</tr>";					
							
						body += "<tr>";
						body += "<td colspan=\"2\" align=\"left\" valign=\"middle\">";
						body += "<h5>OBSERVAÇ‡ÃO</h5>";
						body += "</td>";
						body += "<td colspan=\"7\" align=\"left\" valign=\"center\"><h3>";
						body += pedidoVO.getObservacao() != null ? !pedidoVO.getObservacao() .equals("") ?pedidoVO.getObservacao() : "&nbsp;" : "&nbsp;";
						body += "</h3></td>";
						body += "</tr>";																		
						body +=  "</table>";						
					body += "</body>";
					body += "</html>";
					
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return body;
	
	} 
	
	
}
