package br.com.slim.venda.pedido;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.util.Convert;
import br.com.slim.venda.util.Util;

public class PedidoVO implements Parcelable{
	
	
	public PedidoVO() {
		this.clienteVO = new ClienteVO();
		this.tabelaPrecoVO = new TabelaPrecoVO();
		this.pedidoItemVO = new ArrayList<PedidoItemVO>();
		this.pedidosPgtoVO = new ArrayList<PedidoPgtoVO>();
	}
		
	public static Integer FORMA_PGTO_DINHEIRO = 0;
	public static Integer FORMA_PGTO_CHEQUE = 1;
	public static Integer FORMA_PGTO_CARTAO = 2;
	public static Integer FORMA_PGTO_DUPLICATA = 3;
	public static Integer FORMA_PGTO_BOLETO = 4;
	public static Integer FORMA_PGTO_PROMISSORIA = 5;
	
	
		private int idPedido;
		private int idEmpresa;
		private int idFilial;
		private ClienteVO clienteVO;
		private TabelaPrecoVO tabelaPrecoVO;
		private String observacao;
		private long dtEmisao;
		private double totalLiquido;
		private double totalBruto;
		private double descTotal;
		private ParcelamentoVO parcelamentoVO;
		private int idFormaPagamento;
		private int idUsuario;
		private ArrayList<PedidoPgtoVO> pedidosPgtoVO;
		private ArrayList<PedidoItemVO> pedidoItemVO; 
		private String sincronizado;
		//Utilizado pela listview sincronizacao, para definir se é a section do listview
		private boolean isSection = false;
		
		//Pedido esta sendo editao
		public boolean isEdition(){
			return dtEmisao != 0;
		}

		public String getSincronizado() {
			return sincronizado;
		}

		public double getTotalBruto() {
			return totalBruto;
		}

		public void setTotalBruto(double totalBruto) {
			this.totalBruto = totalBruto;
		}



		public void setSincronizado(String sincronizado) {
			this.sincronizado = sincronizado;
		}



		public void setSincronizado(boolean sincronizado){
			setSincronizado(sincronizado ? SincronizaVO.SINCRONIZADO : SincronizaVO.NAO_SINCRONIZADO);
		}
		
		public boolean isSincronizado(){
			return getSincronizado() != null && getSincronizado().trim().equals(SincronizaVO.SINCRONIZADO);
		}

		public int getIdPedido() {
			return idPedido;
		}

		public void setIdPedido(int idPedido) {
			this.idPedido = idPedido;
		}

		public int getIdEmpresa() {
			return idEmpresa;
		}

		public void setIdEmpresa(int idEmpresa) {
			this.idEmpresa = idEmpresa;
		}

		public ClienteVO getClienteVO() {
			return clienteVO;
		}

		public void setClienteVO(ClienteVO clienteVO) {
			this.clienteVO = clienteVO;
		}

		public TabelaPrecoVO getTabelaPrecoVO() {
			return tabelaPrecoVO;
		}

		public void setTabelaPrecoVO(TabelaPrecoVO tabelaPrecoVO) {
			this.tabelaPrecoVO = tabelaPrecoVO;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public long getDtEmisao() {
			return dtEmisao;
		}

		public void setDtEmisao(long dtEmisao) {
			this.dtEmisao = dtEmisao;
		}	

		public double getTotalLiquido() {
			return totalLiquido;
		}

		public void setTotalLiquido(double totalLiquido) {
			this.totalLiquido = totalLiquido;
		}

		public double getDescTotal() {
			return descTotal;
		}

		public void setDescTotal(double descTotal) {
			this.descTotal = descTotal;
		}

		public ParcelamentoVO getParcelamentoVO() {
			return parcelamentoVO;
		}

		public void setParcelamentoVO(ParcelamentoVO parcelamentoVO) {
			this.parcelamentoVO = parcelamentoVO;
		}

		public int getIdFormaPagamento() {
			return idFormaPagamento;
		}

		public void setIdFormaPagamento(int idFormaPagamento) {
			this.idFormaPagamento = idFormaPagamento;
		}

		public int getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}


		public boolean isSection() {
			return isSection;
		}

		public void setSection(boolean isSection) {
			this.isSection = isSection;
		}

		public int getIdFilial() {
			return idFilial;
		}

		public void setIdFilial(int idFilial) {
			this.idFilial = idFilial;
		}

		public ArrayList<PedidoPgtoVO> getPedidosPgtoVO() {
			if(pedidosPgtoVO == null)
				pedidosPgtoVO = new ArrayList<PedidoPgtoVO>();
			return pedidosPgtoVO;
		}

		public void setPedidosPgtoVO(ArrayList<PedidoPgtoVO> pedidosPgtoVO) {
			this.pedidosPgtoVO = pedidosPgtoVO;
		}

		public ArrayList<PedidoItemVO> getPedidoItemVO() {
			if(pedidoItemVO == null)
				pedidoItemVO = new ArrayList<PedidoItemVO>();
			return pedidoItemVO;
		}

		public void setPedidoItemVO(ArrayList<PedidoItemVO> pedidoItemVO) {
			this.pedidoItemVO = pedidoItemVO;
		}
		/**
		 * Percorre o pedidoItem e soma seu total
		 * @return
		 */
		public double getValorTotalPedido(){
			double valorTotal = 0.0;
			for(PedidoItemVO pedidoItemVO : getPedidoItemVO()){
				valorTotal += pedidoItemVO.getPrecoVenda()* pedidoItemVO.getQuantidade();
			}
			return Util.arredondaDouble(valorTotal);
		}
		
		/**
		 * Percorre o pedidoItem e soma seu total de desconto
		 * @return
		 */
		public double getDescontoTotalPedido(){
			double descontosTotal = 0.0;
			for(PedidoItemVO pedidoItemVO : getPedidoItemVO()){
				descontosTotal += pedidoItemVO.getDesconto()*pedidoItemVO.getQuantidade();				
			}			
			return descontosTotal;
		}

		public static class Pedido{
			private Pedido(){}
			public static final String TABELA = "PEDIDO";
			public static final String IDPEDIDO = "IDPEDIDO";
			public static final String IDEMPRESA = "IDEMPRESA";
			public static final String IDFILIAL = "IDFILIAL";
			public static final String IDCLIENTE = "IDCLIENTE";
			public static final String IDTABPRECO = "IDTABPRECO";
			public static final String DTEMISAO = "DTEMISAO";
			public static final String TOTALIQUIDO = "TOTALIQUIDO";
			public static final String TOTALBRUTO = "TOTALBRUTO";
			public static final String DESCTOTAL = "DESCTOTAL";
			public static final String OBSERVACAO = "PED_OBSERVACAO";
			public static final String IDPARCELAMENTO = "IDPARCELAMENTO";
			public static final String IDFORMAPAGTO = "IDFORMAPAGTO";
			public static final String IDUSUARIO = "IDUSUARIO";
			public static final String SINC = "SINC";
						
			public static final String[] COLUNAS = new String[]{IDPEDIDO, IDEMPRESA,IDFILIAL, IDCLIENTE, IDTABPRECO, DTEMISAO, OBSERVACAO, IDPARCELAMENTO, IDUSUARIO, SINC, DESCTOTAL, TOTALIQUIDO, TOTALBRUTO};
		}


		public PedidoVO(Parcel in){
			setIdPedido(in.readInt());
			setIdEmpresa(in.readInt());
			setIdFilial(in.readInt());
			setClienteVO((ClienteVO)in.readParcelable(ClienteVO.class.getClassLoader()));
			setTabelaPrecoVO((TabelaPrecoVO)in.readParcelable(TabelaPrecoVO.class.getClassLoader()));
			setObservacao(in.readString());
			setDtEmisao(in.readLong());
			setTotalLiquido(in.readDouble());
			setTotalBruto(in.readDouble());
			setDescTotal(in.readDouble());
			setParcelamentoVO((ParcelamentoVO)in.readParcelable(ParcelamentoVO.class.getClassLoader()));
			setIdFormaPagamento(in.readInt());
			setIdUsuario(in.readInt());
			in.readTypedList(getPedidosPgtoVO(), PedidoPgtoVO.CREATOR);
			in.readTypedList(getPedidoItemVO(), PedidoItemVO.CREATOR);
			setSincronizado(in.readString());
		}
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(getIdPedido());
			dest.writeInt(getIdEmpresa());
			dest.writeInt(getIdFilial());
			dest.writeParcelable(getClienteVO(), flags);
			dest.writeParcelable(getTabelaPrecoVO(), flags);
			dest.writeString(getObservacao());
			dest.writeLong(getDtEmisao());
			dest.writeDouble(getTotalLiquido());
			dest.writeDouble(getTotalBruto());
			dest.writeDouble(getDescTotal());
			dest.writeParcelable(getParcelamentoVO(), flags);
			dest.writeInt(getIdFormaPagamento());
			dest.writeInt(getIdUsuario());
			dest.writeTypedList(getPedidosPgtoVO());
			dest.writeTypedList(getPedidoItemVO());
			dest.writeString(getSincronizado());
		}
		
		
		public static final Parcelable.Creator<PedidoVO> CREATOR = new Parcelable.Creator<PedidoVO>() {
	        public PedidoVO createFromParcel(Parcel in)
	        {
	            return new PedidoVO(in);
	        }
	 
	        public PedidoVO[] newArray(int size)
	        {
	            return new PedidoVO[size];
	        }
	    };


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((clienteVO == null) ? 0 : clienteVO.hashCode());
			long temp;
			temp = Double.doubleToLongBits(descTotal);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + (int) (dtEmisao ^ (dtEmisao >>> 32));
			result = prime * result + idEmpresa;
			result = prime * result + idFormaPagamento;
			result = prime * result + idPedido;
			result = prime * result + idUsuario;
			result = prime * result
					+ ((observacao == null) ? 0 : observacao.hashCode());
			result = prime
					* result
					+ ((parcelamentoVO == null) ? 0 : parcelamentoVO.hashCode());
			result = prime * result
					+ ((pedidoItemVO == null) ? 0 : pedidoItemVO.hashCode());
			result = prime * result
					+ ((pedidosPgtoVO == null) ? 0 : pedidosPgtoVO.hashCode());
			result = prime * result
					+ ((tabelaPrecoVO == null) ? 0 : tabelaPrecoVO.hashCode());
			temp = Double.doubleToLongBits(totalLiquido);
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
			PedidoVO other = (PedidoVO) obj;
			if (clienteVO == null) {
				if (other.clienteVO != null)
					return false;
			} else if (!clienteVO.equals(other.clienteVO))
				return false;
			if (Double.doubleToLongBits(descTotal) != Double
					.doubleToLongBits(other.descTotal))
				return false;
			if (dtEmisao != other.dtEmisao)
				return false;
			if (idEmpresa != other.idEmpresa)
				return false;
			if (idFormaPagamento != other.idFormaPagamento)
				return false;
			if (idPedido != other.idPedido)
				return false;
			if (idUsuario != other.idUsuario)
				return false;
			if (observacao == null) {
				if (other.observacao != null)
					return false;
			} else if (!observacao.equals(other.observacao))
				return false;
			if (parcelamentoVO == null) {
				if (other.parcelamentoVO != null)
					return false;
			} else if (!parcelamentoVO.equals(other.parcelamentoVO))
				return false;
			if (pedidoItemVO == null) {
				if (other.pedidoItemVO != null)
					return false;
			} else if (!pedidoItemVO.equals(other.pedidoItemVO))
				return false;
			if (pedidosPgtoVO == null) {
				if (other.pedidosPgtoVO != null)
					return false;
			} else if (!pedidosPgtoVO.equals(other.pedidosPgtoVO))
				return false;
			if (tabelaPrecoVO == null) {
				if (other.tabelaPrecoVO != null)
					return false;
			} else if (!tabelaPrecoVO.equals(other.tabelaPrecoVO))
				return false;
			if (Double.doubleToLongBits(totalLiquido) != Double
					.doubleToLongBits(other.totalLiquido))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return getIdPedido()+" - "+ getClienteVO().getNome()+"\n"+Convert.toDateStr(getDtEmisao()); 
			
		}
		
				
}
