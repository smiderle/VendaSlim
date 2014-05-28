package br.com.slim.venda.pedidoItem;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoView;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;

public class PedidoItemVO implements Parcelable{
		
	public PedidoItemVO() {
		this.itemVO = new ItemVO();
		
	}
	
	private ItemVO itemVO;
	private double precoVenda;
	private double desconto;
	private int  idPedido;
	private int idSequencia;
	private double quantidade;
	private int idEmpresa;
	private int idFilial;
	
	
	
	public int getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(int idFilial) {
		this.idFilial = idFilial;
	}

	public ItemVO getItemVO() {
		return itemVO;
	}

	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	

	public int getIdSequencia() {
		return idSequencia;
	}

	public void setIdSequencia(int idSequencia) {
		this.idSequencia = idSequencia;
	}



	public static final class PedidoItem{
		private PedidoItem(){}
		public static final String TABELA = "PEDIDOITEM";
		public static final String IDITEM = "IDITEM";
		public static final String IDEMPRESA = "IDEMPRESA";		
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDSEQUENCIA = "IDSEQUENCIA";
		public static final String DESCONTO = "DESCONTO";
		public static final String PRECOVENDA = "PRECOVENDA";
		public static final String IDPEDIDO = "IDPEDIDO";
		public static final String QUANTIDADE = "QUANTIDADE";
		
		
		public static final String[] COLUNAS = new String[]{IDITEM,IDEMPRESA,IDFILIAL,IDSEQUENCIA, DESCONTO, PRECOVENDA, IDPEDIDO, QUANTIDADE};
	}
	
	public PedidoItemVO(Parcel in){		
		setPrecoVenda(in.readDouble());
		setDesconto(in.readDouble());
		setIdPedido(in.readInt());
		setQuantidade(in.readDouble());
		setIdEmpresa(in.readInt());
		setIdFilial(in.readInt());
		setIdSequencia(in.readInt());
		setItemVO((ItemVO)in.readParcelable(ItemVO.class.getClassLoader()));
	}
	
	public static final Parcelable.Creator<PedidoItemVO> CREATOR = new Parcelable.Creator<PedidoItemVO>() {
        public PedidoItemVO createFromParcel(Parcel in)
        {
            return new PedidoItemVO(in);
        }
 
        public PedidoItemVO[] newArray(int size)
        {
            return new PedidoItemVO[size];
        }
    };



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//private ItemVO itemVO;		
		dest.writeDouble(getPrecoVenda());		
		dest.writeDouble(getDesconto());
		dest.writeInt(getIdPedido());
		dest.writeDouble(getQuantidade());
		dest.writeInt(getIdEmpresa());
		dest.writeInt(getIdFilial());
		dest.writeInt(getIdSequencia());
		dest.writeParcelable(getItemVO(), flags);
		
		
	}
}
