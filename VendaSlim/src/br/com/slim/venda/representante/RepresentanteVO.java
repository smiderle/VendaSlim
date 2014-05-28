package br.com.slim.venda.representante;

import br.com.slim.venda.cliente.ClienteVO;
import android.os.Parcel;
import android.os.Parcelable;


public class RepresentanteVO implements Parcelable{
	
	public RepresentanteVO() {	
	}
	
	private Integer idEmpresa;	
	private Integer idRepresentante;	
	private String login;	
	private String senha;
	private String nome;	
	private boolean inativo;
		
	public static final class Representante{
		private Representante() {}
		
		public static final String TABELA = "REPRESENTANTE";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String IDEMPRESA = "IDEMPRESA";		
		public static final String NOME = "NOME";
		public static final String LOGIN = "LOGIN";
		public static final String SENHA = "SENHA";
		public static final String INATIVO = "INATIVO";
		
		public static final String[] COLUNAS = new String[]{IDREPRESENTANTE,IDEMPRESA,NOME, LOGIN, SENHA, INATIVO};
	}
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}	
	

	@Override
	public int describeContents() {		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(getIdEmpresa());
		dest.writeValue(getIdRepresentante());
		dest.writeString(getLogin());
		dest.writeString(getSenha());
	}
	

	 public RepresentanteVO(Parcel in) {
		 setIdEmpresa((Integer) in.readValue(Integer.class.getClassLoader()));
		 setIdRepresentante((Integer) in.readValue(Integer.class.getClassLoader()));
		 setLogin(in.readString());
		 setSenha(in.readString());
	 }
	 
		public static final Parcelable.Creator<RepresentanteVO> CREATOR = new Parcelable.Creator<RepresentanteVO>() {
	        public RepresentanteVO createFromParcel(Parcel in)
	        {
	            return new RepresentanteVO(in);
	        }
	 
	        public RepresentanteVO[] newArray(int size)
	        {
	            return new RepresentanteVO[size];
	        }
	    };
}
