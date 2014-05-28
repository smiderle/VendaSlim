package br.com.slim.venda.parcelamento;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelamentoVO implements Parcelable{
	
	public ParcelamentoVO() {}
	
	public ParcelamentoVO(int idEmpresa, int idFilial, int idParcelamento) {
		this.idEmpresa = idEmpresa;
		this.idFilial = idFilial;
		this.idParcelamento = idParcelamento;
	}
	
	private int idEmpresa;
	private int idFilial;
	private Integer idParcelamento;	
	private String descricao;
	private int carencia;
	private int nroParcela;	
	private int diasEntreParcela;
	private double percentual;
	private boolean inativo;
	
	
	
	public Integer getIdParcelamento() {
		return idParcelamento;
	}
	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getNroParcela() {
		return nroParcela;
	}
	public void setNroParcela(int nroParcela) {
		this.nroParcela = nroParcela;
	}
	public int getCarencia() {
		return carencia;
	}
	public void setCarencia(int carencia) {
		this.carencia = carencia;
	}
	public int getDiasEntreParcela() {
		return diasEntreParcela;
	}
	public void setDiasEntreParcela(int diasEntreParcela) {
		this.diasEntreParcela = diasEntreParcela;
	}
	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}
	
	

	
	
	public int getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carencia;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + diasEntreParcela;
		result = prime * result + idEmpresa;
		result = prime * result + idParcelamento;
		result = prime * result + nroParcela;
		long temp;
		temp = Double.doubleToLongBits(percentual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ParcelamentoVO other = (ParcelamentoVO) obj;
		if (carencia != other.carencia)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diasEntreParcela != other.diasEntreParcela)
			return false;
		if (idEmpresa != other.idEmpresa)
			return false;
		if (idParcelamento != other.idParcelamento)
			return false;
		if (nroParcela != other.nroParcela)
			return false;
		if (Double.doubleToLongBits(percentual) != Double
				.doubleToLongBits(other.percentual))
			return false;
		return true;
	}



	public final static class Parcela{
		private Parcela() {}
		public static final String TABELA = "PARCELAMENTO";
		public static final String IDPARCELAMENTO = "IDPARCELAMENTO";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String DESCRICAO = "DESCRICAOPARCELA";
		public static final String NROPARCELA = "NROPARCELA";
		public static final String CARENCIA = "CARENCIA";
		public static final String DIASENTREPARCELA = "DIASENTREPARCELA";
		public static final String PERCENTUAL = "PERCENTUAL";
		public static final String INATIVO = "INATIVO";
		public static final String[] COLUNAS = new String[]{IDPARCELAMENTO, IDEMPRESA, DESCRICAO,
			NROPARCELA, CARENCIA, DIASENTREPARCELA, PERCENTUAL, INATIVO};
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
		dest.writeValue(getIdParcelamento());
		dest.writeInt(getIdEmpresa());
		dest.writeInt(getIdFilial());
		dest.writeString(getDescricao());
		dest.writeInt(getNroParcela());
		dest.writeInt(getCarencia());
		dest.writeInt(getDiasEntreParcela());
		dest.writeDouble(getPercentual());
	}
	
	public ParcelamentoVO(Parcel in) {
		setIdParcelamento((Integer) in.readValue(Integer.class.getClassLoader()));
		setIdEmpresa(in.readInt());
		setIdFilial(in.readInt());
		setDescricao(in.readString());
		setNroParcela(in.readInt());
		setCarencia(in.readInt());
		setDiasEntreParcela(in.readInt());
		setPercentual(in.readDouble());
	}
	
	public static final Parcelable.Creator<ParcelamentoVO> CREATOR = new Parcelable.Creator<ParcelamentoVO>() {
        public ParcelamentoVO createFromParcel(Parcel in)
        {
            return new ParcelamentoVO(in);
        }
 
        public ParcelamentoVO[] newArray(int size)
        {
            return new ParcelamentoVO[size];
        }
    };
	
}
