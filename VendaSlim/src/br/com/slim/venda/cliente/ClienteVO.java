package br.com.slim.venda.cliente;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;

public class ClienteVO implements Parcelable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public ClienteVO() {
		parcelamentoVO = new ParcelamentoVO();
		tabPrecovo = new TabelaPrecoVO();
		cidadeVO = new CidadeVO();
	}
	
	public static final Integer TIPO_FISICA = 0;
	public static final Integer TIPO_JURIDICA = 1;
	
	
	
	
	public Long getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(long dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	private Integer idCliente;
	private Integer idEmpresa;
	private Integer idFilial;
	private String nome;
	private String contato;
	private int tipo;
	private String cpf;
	private String rg;
	private Long dtNascimento;
	private CidadeVO cidadeVO;
	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	private String foneResidencial;
	private String foneComercial;
	private String foneCelular;
	private String email;
	private TabelaPrecoVO tabPrecovo;
	private ParcelamentoVO parcelamentoVO;
	private int idFormaPagamento;
	private double limiteCredito;
	private double descMax;
	private String observacao;
	private double valorTitulos;
	private String sincronizado;
	private Integer idRepresentante;
	private String alterado;
	private boolean inativo;
	
	
	public String getFoneValido(){
		if(getFoneCelular() != null)
			return getFoneCelular();
			else if (getFoneComercial() != null)
				return getFoneComercial();
			else if(getFoneResidencial() != null)
				return getFoneResidencial();
			else return "Sem fone cadastrado";
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public CidadeVO getCidadeVO() {
		return cidadeVO;
	}
	public void setCidadeVO(CidadeVO cidadeVO) {
		this.cidadeVO = cidadeVO;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}
	public String getFoneComercial() {
		return foneComercial;
	}
	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}
	public String getFoneCelular() {
		return foneCelular;
	}
	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TabelaPrecoVO getTabPrecovo() {
		return tabPrecovo;
	}
	public void setTabPrecovo(TabelaPrecoVO tabPrecovo) {
		this.tabPrecovo = tabPrecovo;
	}
	public ParcelamentoVO getParcelamentoVO() {
		return parcelamentoVO;
	}
	public void setParcelamentoVO(ParcelamentoVO parcelamentoVO) {
		this.parcelamentoVO = parcelamentoVO;
	}

	
	public int getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public void setIdFormaPagamento(int idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	public double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public double getDescMax() {
		return descMax;
	}
	public void setDescMax(double descMax) {
		this.descMax = descMax;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public double getValorTitulos() {
		return valorTitulos;
	}
	public void setValorTitulos(double valorTitulos) {
		this.valorTitulos = valorTitulos;
	}
	public String getSincronizado() {
		return sincronizado;
	}
	public void setSincronizado(String sincronizado) {
		this.sincronizado = sincronizado;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public String getAlterado() {
		return alterado;
	}
	public void setAlterado(String alterado) {
		this.alterado = alterado;
	}
	public void setDtNascimento(Long dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	



	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}





	public static final class Cliente{
		private Cliente() {}
		
		public static final String TABELA = "CLIENTE";
		public static final String IDCLIENTE = "IDCLIENTE";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String NOME = "NOME";
		public static final String CONTATO = "CONTATO";
		public static final String TIPO = "TIPOCLIENTE";
		public static final String CPF = "CPF";
		public static final String RG = "RG";
		public static final String DTNASCIMENTO = "DTNASCIMENTO";
		public static final String IDCIDADE = "IDCIDADE";
		public static final String RUA = "RUA";
		public static final String NUMERO = "NUMERO";
		public static final String BAIRRO = "BAIRRO";
		public static final String CEP = "CEP";
		public static final String FONERESIDENCIAL = "FONERESIDENCIAL";
		public static final String FONECOMERCIAL = "FONECOMERCIAL";
		public static final String FONECELULAR = "FONECELULAR";
		public static final String EMAIL = "EMAIL";
		public static final String IDTABPRECO = "IDTABPRECO";
		public static final String IDFORMAPARCELAMENTO = "IDFORMAPARCELAMENTO";
		public static final String IDFORMAPAGAMENTO = "IDFORMAPAGAMENTO";
		public static final String LIMITECREDITO = "LIMITECREDITO";
		public static final String DESCMAX = "DESCMAX";
		public static final String OBSERVACAO = "OBSERVACAO";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String SINCRONIZADO = "SINCRONIZADO";
		public static final String ALTERADO = "ALTERADO";
		public static final String INATIVO = "INATIVO";
		
		public static final String[] COLUNAS = new String[]{IDCLIENTE,IDEMPRESA,IDFILIAL,NOME,CONTATO
			 ,TIPO,CPF,RG,DTNASCIMENTO,IDCIDADE,RUA,NUMERO,BAIRRO,CEP
			 ,FONERESIDENCIAL,FONECOMERCIAL,FONECELULAR,EMAIL,IDTABPRECO,
			 IDFORMAPAGAMENTO,IDFORMAPARCELAMENTO,LIMITECREDITO,DESCMAX, OBSERVACAO, SINCRONIZADO, IDREPRESENTANTE, ALTERADO, INATIVO};
	}



	@Override
	public int describeContents() {		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(idEmpresa);
		dest.writeValue(idFilial);
		dest.writeValue(idCliente);			
		dest.writeString(nome);
		dest.writeString(contato);
		dest.writeString(cpf);
		dest.writeString(rg);
		dest.writeString(rua);
		dest.writeString(numero);
		dest.writeString(bairro);
		dest.writeString(cep);
		dest.writeString(foneResidencial);
		dest.writeString(foneComercial);
		dest.writeString(foneCelular);
		dest.writeString(email);
		dest.writeString(observacao);
		dest.writeDouble(descMax);
		dest.writeDouble(limiteCredito);
		dest.writeInt(idFormaPagamento);
		dest.writeInt(tipo);
		dest.writeValue(dtNascimento);		
		dest.writeParcelable(getCidadeVO(), flags);
		dest.writeParcelable(getTabPrecovo(), flags);
		dest.writeParcelable(getParcelamentoVO(), flags);
		dest.writeValue(idRepresentante);		
	}
	
	
	 public ClienteVO(Parcel in) {
	        setIdEmpresa((Integer) in.readValue(Integer.class.getClassLoader()));
	        setIdFilial((Integer) in.readValue(Integer.class.getClassLoader()));
	        setIdCliente((Integer) in.readValue(Integer.class.getClassLoader()));
	        setNome(in.readString());
	        setContato(in.readString());
	        setCpf(in.readString());
	        setRg(in.readString());
	        setRua(in.readString());
	        setNumero(in.readString());
	        setBairro(in.readString());
	        setCep(in.readString());
	        setFoneResidencial(in.readString());
	        setFoneComercial(in.readString());
	        setFoneCelular(in.readString());	        
	        setEmail(in.readString());
	        setObservacao(in.readString());
	        setDescMax(in.readDouble());
	        setLimiteCredito(in.readDouble());
	        setIdFormaPagamento(in.readInt());
	        setTipo(in.readInt());
	        setDtNascimento((Long) in.readValue(Long.class.getClassLoader()));
	        setCidadeVO((CidadeVO)in.readParcelable(CidadeVO.class.getClassLoader()));
	        setTabPrecovo((TabelaPrecoVO) in.readParcelable(TabelaPrecoVO.class.getClassLoader()));
	        setParcelamentoVO((ParcelamentoVO) in.readParcelable(ParcelamentoVO.class.getClassLoader()));
	        setIdRepresentante((Integer) in.readValue(Integer.class.getClassLoader()));
	    }
	
	public static final Parcelable.Creator<ClienteVO> CREATOR = new Parcelable.Creator<ClienteVO>() {
        public ClienteVO createFromParcel(Parcel in)
        {
            return new ClienteVO(in);
        }
 
        public ClienteVO[] newArray(int size)
        {
            return new ClienteVO[size];
        }
    };


}