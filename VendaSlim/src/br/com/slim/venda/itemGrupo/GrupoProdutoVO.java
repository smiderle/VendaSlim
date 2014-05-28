package br.com.slim.venda.itemGrupo;

import android.os.Parcel;
import android.os.Parcelable;

public class GrupoProdutoVO implements Parcelable{
	
	
	public GrupoProdutoVO(Integer idGrupo, String descricao) {
		super();
		this.idGrupo = idGrupo;
		this.descricao = descricao;
	}
	public GrupoProdutoVO() {		
			
	}
	public GrupoProdutoVO(Integer grupoID) {		
		this.idGrupo = grupoID;		
	}

	private Integer idEmpresa;
	private Integer idFilial;
	private Integer idGrupo;
	private String descricao;
	private Double descMax;
	
	
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Double getDescMax() {
		return descMax;
	}
	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}



	public static class GrupoProduto{
		private GrupoProduto() {}
		public static final String TABELA = "GRUPO_PRODUTO";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDGRUPO = "IDGRUPO";
		public static final String DESCRICAO = "DESCRICAO";
		public static final String DESCMAX = "DESCMAX";
		public static final String[] COLUNAS = new String[]{IDGRUPO, DESCRICAO, IDFILIAL, IDGRUPO, DESCRICAO, DESCMAX};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		GrupoProdutoVO other = (GrupoProdutoVO) obj;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {	
		return getDescricao();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getIdGrupo());
		dest.writeString(getDescricao());
	}

	public GrupoProdutoVO(Parcel in){
		setIdGrupo(in.readInt());
		setDescricao(in.readString());
	}
	
	public static final Parcelable.Creator<GrupoProdutoVO> CREATOR = new Parcelable.Creator<GrupoProdutoVO>() {
        public GrupoProdutoVO createFromParcel(Parcel in)
        {
            return new GrupoProdutoVO(in);
        }
 
        public GrupoProdutoVO[] newArray(int size)
        {
            return new GrupoProdutoVO[size];
        }
    };
	
}
