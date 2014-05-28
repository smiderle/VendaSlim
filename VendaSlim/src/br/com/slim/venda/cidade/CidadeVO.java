package br.com.slim.venda.cidade;

import android.os.Parcel;
import android.os.Parcelable;

public class CidadeVO implements Parcelable{
	private Integer idCidade;
	private String municipio;
	private String estado;
	private String UF;
	private String ddd;
	
	
	public CidadeVO() {}
	
	public CidadeVO(Integer idCidade) {
		this.idCidade = idCidade;
	}
		
	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public static class Cidade {
		private Cidade(){}
		public static final String TABELA = "CIDADE";
		public static final String IDCIDADE = "CODIGO";
		public static final String MUNICIPIO = "MUNICIPIO";
		public static final String UF = "UF";
		public static final String[] COLUNAS = new String[]{IDCIDADE, MUNICIPIO, UF};
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UF == null) ? 0 : UF.hashCode());
		result = prime * result + ((ddd == null) ? 0 : ddd.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + idCidade;
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CidadeVO other = (CidadeVO) obj;
		if (UF == null) {
			if (other.UF != null)
				return false;
		} else if (!UF.equals(other.UF))
			return false;
		if (ddd == null) {
			if (other.ddd != null)
				return false;
		} else if (!ddd.equals(other.ddd))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idCidade != other.idCidade)
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		return true;
	}

	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(idCidade);
		dest.writeString(municipio);
		dest.writeString(estado);
		dest.writeString(UF);
		dest.writeString(ddd);
	}
	
	public CidadeVO(Parcel in) {
		setIdCidade((Integer) in.readValue(Integer.class.getClassLoader()));
		setMunicipio(in.readString());
		setEstado(in.readString());
		setUF(in.readString());
		setDdd(in.readString());
	}
	
	public static final Parcelable.Creator<CidadeVO> CREATOR = new Parcelable.Creator<CidadeVO>() {

		@Override
		public CidadeVO createFromParcel(Parcel source) {			
			return new CidadeVO(source);
		}

		@Override
		public CidadeVO[] newArray(int size) {
			return new CidadeVO[size];
		}
	};
	
	
}
