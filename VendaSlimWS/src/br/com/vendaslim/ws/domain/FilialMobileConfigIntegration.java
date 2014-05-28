package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FILIAL_MOBILE_CONFIG")
@IdClass(FilialPK.class)
public class FilialMobileConfigIntegration implements Domain{
		
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;	
	
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
	private Integer acaoLimiteCredito;
	
	@Column(name="acao_titulo_vencido")
	private Integer acaoTituloVencido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;

	@Column(name="dias_vencimento")
	private Integer diasVenciemento;

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

	public Integer getAcaoLimiteCredito() {
		return acaoLimiteCredito;
	}

	public void setAcaoLimiteCredito(Integer acaoLimiteCredito) {
		this.acaoLimiteCredito = acaoLimiteCredito;
	}

	public Integer getAcaoTituloVencido() {
		return acaoTituloVencido;
	}

	public void setAcaoTituloVencido(Integer acaoTituloVencido) {
		this.acaoTituloVencido = acaoTituloVencido;
	}

	
	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	public Integer getDiasVenciemento() {
		return diasVenciemento;
	}

	public void setDiasVenciemento(Integer diasVenciemento) {
		this.diasVenciemento = diasVenciemento;
	}	
}
