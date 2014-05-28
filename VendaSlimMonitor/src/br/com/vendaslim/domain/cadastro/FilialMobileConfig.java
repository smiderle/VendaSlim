package br.com.vendaslim.domain.cadastro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;

@Entity
@Table(name="FILIAL_MOBILE_CONFIG")
@IdClass(FilialPK.class)
public class FilialMobileConfig implements Domain{
	

	public FilialMobileConfig() {
		empresa = new Empresa();
	}
	
	@Id
	private Empresa empresa;
	
	@Id
	private Integer idFilial;	
	
	@OneToOne(mappedBy="filialMobileConfig")
	private Filial filial;

	@Column(name="exibe_estoque")
	private boolean exibeEstoque;
	
	@Column(name="estoque_negativo")
	private boolean permiteVendaEstoqueNegativo;
	@Column(name="cadastrar_cliente")
	private boolean PermiteCadastrarCliente;
	@Column(name="email_pedido_cliente")
	private boolean enviarEmailCliente;
	
	@Column(name="email_pedido_admin")
	private boolean enviarEmailAdmin;

	@Column(name="email_pedidos")
	private String emailPedidos;
	
	@Column(name="acao_limite_credito")
	private Acao acaoLimiteCredito;
	
	@Column(name="acao_titulo_vencido")
	private Acao acaoTituloVencido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrAlteracao;

	@Column(name="dias_vencimento")
	private Integer diasVenciemento;
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}

	
	public boolean isExibeEstoque() {
		return exibeEstoque;
	}

	public void setExibeEstoque(boolean exibeEstoque) {
		this.exibeEstoque = exibeEstoque;
	}

	public boolean isPermiteVendaEstoqueNegativo() {
		return permiteVendaEstoqueNegativo;
	}

	public void setPermiteVendaEstoqueNegativo(boolean permiteVendaEstoqueNegativo) {
		this.permiteVendaEstoqueNegativo = permiteVendaEstoqueNegativo;
	}

	public boolean isPermiteCadastrarCliente() {
		return PermiteCadastrarCliente;
	}

	public void setPermiteCadastrarCliente(boolean permiteCadastrarCliente) {
		PermiteCadastrarCliente = permiteCadastrarCliente;
	}

	public boolean isEnviarEmailCliente() {
		return enviarEmailCliente;
	}

	public void setEnviarEmailCliente(boolean enviarEmailCliente) {
		this.enviarEmailCliente = enviarEmailCliente;
	}

	public boolean isEnviarEmailAdmin() {
		return enviarEmailAdmin;
	}

	public void setEnviarEmailAdmin(boolean enviarEmailAdmin) {
		this.enviarEmailAdmin = enviarEmailAdmin;
	}

	public String getEmailPedidos() {
		return emailPedidos;
	}

	public void setEmailPedidos(String emailPedidos) {
		this.emailPedidos = emailPedidos;
	}

	public Acao getAcaoLimiteCredito() {
		return acaoLimiteCredito;
	}

	public void setAcaoLimiteCredito(Acao acaoLimiteCredito) {
		this.acaoLimiteCredito = acaoLimiteCredito;
	}

	public Acao getAcaoTituloVencido() {
		return acaoTituloVencido;
	}

	public void setAcaoTituloVencido(Acao acaoTituloVencido) {
		this.acaoTituloVencido = acaoTituloVencido;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Date getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Date dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	public Integer getDiasVenciemento() {
		return diasVenciemento;
	}

	public void setDiasVenciemento(Integer diasVenciemento) {
		this.diasVenciemento = diasVenciemento;
	}
	
	
	
}
