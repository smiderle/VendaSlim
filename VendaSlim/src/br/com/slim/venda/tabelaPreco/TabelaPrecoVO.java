package br.com.slim.venda.tabelaPreco;

import android.os.Parcel;
import android.os.Parcelable;

public class TabelaPrecoVO implements Parcelable{
	public TabelaPrecoVO() {}
	
	private Integer idTabPreco;	
	private int idEmpresa;
	private int idFilial;
	private String descricao;
	private double percentual;
	private boolean acrescimo;
	private boolean inativo;
		
	


	public Integer getIdTabPreco() {
		return idTabPreco;
	}

	public void setIdTabPreco(Integer idTabPreco) {
		this.idTabPreco = idTabPreco;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	public boolean isAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(boolean acrescimo) {
		this.acrescimo = acrescimo;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public static final class TabelaPreco{
		private TabelaPreco(){}
		public static final String TABELA = "TABPRECO";
		public static final String IDTABPRECO = "IDTABPRECO";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String DESCRICAO = "DESCRICAOTABELA";
		public static final String PERCENTUAL = "PERCENTUALTAB";
		public static final String ACRESCIMO = "ACRESCIMO";
		public static final String INATIVO = "INATIVO";
		
		
		public static final String[] COLUNAS = new String[]{IDTABPRECO, DESCRICAO, PERCENTUAL, IDEMPRESA, IDFILIAL, ACRESCIMO, INATIVO};
	}
	
	@Override
	public String toString() {	
		return getDescricao();
	}

	@Override
	public int describeContents() {

		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeValue(getIdTabPreco());
		dest.writeInt(getIdEmpresa());
		dest.writeInt(getIdFilial());
		dest.writeString(getDescricao());
		dest.writeDouble(getPercentual());
		dest.writeByte((byte) (isAcrescimo()? 1 : 0));
		dest.writeByte((byte) (isInativo()? 1 : 0));
		
	}
	
	public TabelaPrecoVO(Parcel in) {
		setIdTabPreco((Integer)in.readValue(Integer.class.getClassLoader()));
		setIdEmpresa(in.readInt());
		setIdFilial(in.readInt());
		setDescricao(in.readString());
		setPercentual(in.readDouble());
		setAcrescimo(in.readByte() == 1);
		setInativo(in.readByte() == 1);
		
	}
	
	public static final Parcelable.Creator<TabelaPrecoVO> CREATOR = new Parcelable.Creator<TabelaPrecoVO>() {
        public TabelaPrecoVO createFromParcel(Parcel in)
        {
            return new TabelaPrecoVO(in);
        }
 
        public TabelaPrecoVO[] newArray(int size)
        {
            return new TabelaPrecoVO[size];
        }
    };

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmpresa;
		result = prime * result + idFilial;
		result = prime * result
				+ ((idTabPreco == null) ? 0 : idTabPreco.hashCode());
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
		TabelaPrecoVO other = (TabelaPrecoVO) obj;
		if (idEmpresa != other.idEmpresa)
			return false;
		if (idFilial != other.idFilial)
			return false;
		if (idTabPreco == null) {
			if (other.idTabPreco != null)
				return false;
		} else if (!idTabPreco.equals(other.idTabPreco))
			return false;
		return true;
	}

    
	
	
}
