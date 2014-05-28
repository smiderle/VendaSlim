package br.com.vendaslim.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MensagemUtil {
	
	public static final String ERRO_EXCLUSAO_NEGADA_REPRESENTANTE =  "erro_exclusao_negada_representante";
	public static final String ERRO_EXCLUSAO_NEGADA_PRODUTO =  "erro_exclusao_negada_produto";
	public static final String ERRO_EXCLUSAO_NEGADA_GRUPO_PRODUTO =  "erro_exclusao_negada_grupo_produto";
	public static final String ERRO_EXCLUSAO_NEGADA_CLIENTE =  "erro_exclusao_negada_cliente";
	public static final String ERRO_EXCLUSAO_NEGADA =  "erro_exclusao_negada";
	public static final String ERRO_EXCLUSAO_NEGADA_TABELA_PRECO =  "erro_exclusao_negada_tabela_preco";
	public static final String ERRO_EXCLUSAO_NEGADA_PARCELAMENTO =  "erro_exclusao_negada_parcelamento";
	public static final String ERRO_CADASTRO_NEGADO =  "erro_cadastro_negado";
	public static final String ERRO_CADASTRO_NEGADO_REPRESENTANTE =  "erro_cadastro_negado_representante";
	public static final String ERRO_CADASTRO_NEGADO_CODIGO_EXISTENTE =  "erro_cadastro_negado_codigo_existente";
	public static final String ERRO_CADASTRO_NEGADO_DESCRICAO_EXISTENTE =  "erro_cadastro_negado_descricao_existente";
	public static final String ERRO_CADASTRO_NEGADO_CLIENTE =  "erro_cadastro_negado_cliente";
			
	
	
	
	private static final String PACOTE_MENSAGENS_IDIOMAS = "br.com.vendaslim.util.idioma.mensagens";
	
	public static String getMensagem(String propriedade){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		return bundle.getString(propriedade);
	}
	
	
	public static String getMensagem(String propriedade, Object... parametros){
		String mensagem = getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}

}
