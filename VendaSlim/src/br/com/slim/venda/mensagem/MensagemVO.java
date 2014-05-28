package br.com.slim.venda.mensagem;


public class MensagemVO {
	
	private Integer idEmpresa;
	private Integer idMensagem;
	private Integer IdRepresentanetOrigem;
	private Integer IdRepresentanetDestino;
	private Integer usuarioOrigem;
	private Integer usuarioDestino;
	private String mensagem;
	private String titulo;
	private long dtHrCadastroLong;
	
	public static final class Mensagem{
		private Mensagem() {}
		
		public static final String TABELA = "MENSAGEM";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDMENSAGEM = "IDMENSAGEM";		
		public static final String IDREPRESENTANETORIGEM = "IDREPRESENTANETORIGEM";
		public static final String IDREPRESENTANETDESTINO = "IDREPRESENTANETDESTINO";
		public static final String USUARIOORIGEM = "USUARIOORIGEM";
		public static final String USUARIODESTINO = "USUARIODESTINO";
		public static final String TEXTO = "TEXTO";
		public static final String TITULO = "TITULO";
		public static final String DTHRCADASTRO = "DTHRCADASTRO";
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDMENSAGEM,IDREPRESENTANETORIGEM,IDREPRESENTANETDESTINO,USUARIOORIGEM,TEXTO, TITULO,DTHRCADASTRO};
	}
	
	
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}
	public Integer getIdRepresentanetOrigem() {
		return IdRepresentanetOrigem;
	}
	public void setIdRepresentanetOrigem(Integer idRepresentanetOrigem) {
		IdRepresentanetOrigem = idRepresentanetOrigem;
	}
	public Integer getIdRepresentanetDestino() {
		return IdRepresentanetDestino;
	}
	public void setIdRepresentanetDestino(Integer idRepresentanetDestino) {
		IdRepresentanetDestino = idRepresentanetDestino;
	}
	public Integer getUsuarioOrigem() {
		return usuarioOrigem;
	}
	public void setUsuarioOrigem(Integer usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}
	public Integer getUsuarioDestino() {
		return usuarioDestino;
	}
	public void setUsuarioDestino(Integer usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public long getDtHrCadastroLong() {
		return dtHrCadastroLong;
	}
	public void setDtHrCadastroLong(long dtHrCadastroLong) {
		this.dtHrCadastroLong = dtHrCadastroLong;
	}
	
	
	
}
