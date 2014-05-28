package br.com.slim.venda.item;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO;

public class ItemVO implements Parcelable{
	
	public ItemVO() {}
	public ItemVO(Integer idItem, Integer idEmpresa) {
		super();
		this.idItem = idItem;
		this.idEmpresa = idEmpresa;
	}
	
	private int idItem;
	private int idFilial;
	private int idEmpresa;
	private String descricao;
	private double precoVenda;
	private double precoTabPreco;
	private GrupoProdutoVO itemGrupo;
	private double descMax;
	private boolean inativo;
	private double estoque;
	private boolean adicionadoCesta;
	private long dtCadastro;
	private double precoCompra;		
	
	private String referencia;
	private String codbar;	
	private double precoPromocao;
	private boolean promocao;
	private String unidade;
	private int idGrupo;
	
	

	public double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
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
	public double getPreco() {
		return precoVenda;
	}
	public void setPreco(double preco) {
		this.precoVenda = preco;
	}
	public double getPrecoTabPreco() {
		return precoTabPreco;
	}
	public void setPrecoTabPreco(double precoTabPreco) {
		this.precoTabPreco = precoTabPreco;
	}
	public GrupoProdutoVO getItemGrupo() {
		return itemGrupo;
	}
	public void setItemGrupo(GrupoProdutoVO itemGrupo) {
		this.itemGrupo = itemGrupo;
	}
	public double getDescMax() {
		return descMax;
	}
	public void setDescMax(double descMax) {
		this.descMax = descMax;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public double getEstoque() {
		return estoque;
	}
	public void setEstoque(double estoque) {
		this.estoque = estoque;
	}
	public boolean isAdicionadoCesta() {
		return adicionadoCesta;
	}
	public void setAdicionadoCesta(boolean adicionadoCesta) {
		this.adicionadoCesta = adicionadoCesta;
	}
	public long getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(long dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodbar() {
		return codbar;
	}
	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}
	public Double getPrecoPromocao() {
		return precoPromocao;
	}
	public void setPrecoPromocao(Double precoPromocao) {
		this.precoPromocao = precoPromocao;
	}
	public boolean isPromocao() {
		return promocao;
	}
	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}




	public static final class Item{
		private Item(){}
		public static final String TABELA = "ITEM";
		public static final String IDITEM = "IDITEM";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String DESCRICAO = "DESCRICAO";
		public static final String IDGRUPO = "IDGRUPO";
		public static final String PRECOVENDA = "PRECOVENDA";
		public static final String PRECOCOMPRA = "PRECOCOMPRA";
		public static final String DESCMAX = "DESCMAX";
		public static final String ESTOQUE = "ESTOQUE";
		public static final String INATIVO = "INATIVO";
		
		public static final String REFERENCIA = "REFERENCIA";
		public static final String CODBAR = "CODBAR";
		public static final String PRECOPROMOCAO = "PRECOPROMOCAO";
		public static final String PROMOCAO = "PROMOCAO";
		public static final String UNIDADE = "UNIDADE";		
						
		
		public static final String[] COLUNAS = new String[]{IDITEM, IDEMPRESA,IDFILIAL, DESCRICAO, IDGRUPO, PRECOVENDA, DESCMAX, ESTOQUE, INATIVO,PRECOCOMPRA,REFERENCIA, CODBAR, PRECOPROMOCAO, PROMOCAO, UNIDADE};
		
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ItemVO(Parcel in){
		setIdItem(in.readInt());
		setIdEmpresa(in.readInt());
		setIdFilial(in.readInt());
		setDescricao(in.readString());
		setPreco(in.readDouble());
		setPrecoTabPreco(in.readDouble());
		setItemGrupo((GrupoProdutoVO)in.readParcelable(GrupoProdutoVO.class.getClassLoader()));
		setDescMax(in.readDouble());
		setInativo(in.readByte() == 1);
		setEstoque(in.readDouble());
		setAdicionadoCesta(in.readByte() == 1);
		setDtCadastro(in.readLong());		
		setPrecoCompra(in.readDouble());
		
		setReferencia(in.readString());
		setCodbar(in.readString());
		setPrecoPromocao(in.readDouble());
		setPromocao(in.readInt() == 1);
		setUnidade(in.readString());
		setIdGrupo(in.readInt());
	
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getIdItem());
		dest.writeInt(getIdEmpresa());
		dest.writeInt(getIdFilial());
		dest.writeString(getDescricao());
		dest.writeDouble(getPreco());
		dest.writeDouble(getPrecoTabPreco());
		dest.writeParcelable(getItemGrupo(), flags);
		dest.writeDouble(getDescMax());
		dest.writeByte((byte) (isInativo()? 1 : 0));
		dest.writeDouble(getEstoque());
		dest.writeByte((byte) (isAdicionadoCesta()? 1 : 0));
		dest.writeLong(getDtCadastro());	
		dest.writeDouble(getPrecoCompra());
		dest.writeString(getReferencia());
		dest.writeString(getCodbar());
		dest.writeDouble(getPrecoPromocao());
		dest.writeByte((byte) (isPromocao()? 1 : 0));
		dest.writeString(getUnidade());
		dest.writeInt(getIdGrupo());
	}

	
	public static final Parcelable.Creator<ItemVO> CREATOR = new Parcelable.Creator<ItemVO>() {
        public ItemVO createFromParcel(Parcel in)
        {
            return new ItemVO(in);
        }
 
        public ItemVO[] newArray(int size)
        {
            return new ItemVO[size];
        }
    };
	
}
