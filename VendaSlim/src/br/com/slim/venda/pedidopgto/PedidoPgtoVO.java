package br.com.slim.venda.pedidopgto;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO;

public class PedidoPgtoVO implements Parcelable {
	public static int PAGAMENTO_PENDENTE = 0;
	public static int PAGAMENTO_PARCIAL = 1;
	public static int PAGAMENTO_TOTAL = 2;
	
	public PedidoPgtoVO() {
		this.parcelamentoVO = new ParcelamentoVO();
		this.clienteVO = new ClienteVO();
	}
	
		private int idSequencia;
		private int idPedido;
		private int idEmpresa;
		private int idFilial;
		private ParcelamentoVO parcelamentoVO; //Tirar
		//PAGAMENTO_PARCIAL ou PAGAMENTO_TOTAL
		private int parcelaPaga;
		private double valorParcela;
		private double valorPago;
		private long dtVencimento;
		private ClienteVO clienteVO; //Tirar
		private long dtPagamento;
		
				
		
		
		public int getIdFilial() {
			return idFilial;
		}
		public void setIdFilial(int idFilial) {
			this.idFilial = idFilial;
		}
		public int getIdSequencia() {
			return idSequencia;
		}
		public void setIdSequencia(int idSequencia) {
			this.idSequencia = idSequencia;
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
		public ParcelamentoVO getParcelamentoVO() {
			return parcelamentoVO;
		}
		public void setParcelamentoVO(ParcelamentoVO parcelamentoVO) {
			this.parcelamentoVO = parcelamentoVO;
		}
		public int getParcelaPaga() {
			return parcelaPaga;
		}
		public void setParcelaPaga(int parcelaPaga) {
			this.parcelaPaga = parcelaPaga;
		}
		public double getValorParcela() {
			return valorParcela;
		}
		public void setValorParcela(double valorParcela) {
			this.valorParcela = valorParcela;
		}
		public double getValorPago() {
			return valorPago;
		}
		public void setValorPago(double valorPago) {
			this.valorPago = valorPago;
		}
		public long getDtVencimento() {
			return dtVencimento;
		}
		public void setDtVencimento(long dtVencimento) {
			this.dtVencimento = dtVencimento;
		}
		public ClienteVO getClienteVO() {
			return clienteVO;
		}
		public void setClienteVO(ClienteVO clienteVO) {
			this.clienteVO = clienteVO;
		}
		public long getDtPagamento() {
			return dtPagamento;
		}
		public void setDtPagamento(long dtPagamento) {
			this.dtPagamento = dtPagamento;
		}

		public static final class PedidoPgto {
			private PedidoPgto() {}
			public static final String TABELA = "PEDIDOPGTO";
			public static final String IDSEQUENCIA = "IDSEQUENCIA";
			public static final String IDPEDIDO = "IDPEDIDO";
			public static final String IDEMPRESA = "IDEMPRESA";
			public static final String IDFILIAL = "IDFILIAL";
			public static final String IDPARCELAMENTO = "IDPARCELAMENTO";
			public static final String PARCELAPAGA = "PARCELAPAGA";
			public static final String VALORPAGO = "VALORPAGO";
			public static final String DTVENCIMENTO = "DTVENCIMENTO";
			public static final String DTPAGAMENTO = "DTPAGAMENTO";
			public static final String VALORPARCELA = "VALORPARCELA";
			public static final String IDCLIENTE = "IDCLIENTE";
			public static final String[] COLUNAS = new String[]{IDSEQUENCIA, IDPEDIDO,IDFILIAL, IDEMPRESA, IDPARCELAMENTO, PARCELAPAGA, VALORPAGO,VALORPARCELA,IDCLIENTE, DTVENCIMENTO, DTPAGAMENTO};
			
		}


		@Override
		public int describeContents() {
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(getIdSequencia());
			dest.writeInt(getIdPedido());
			dest.writeInt(getIdEmpresa());
			dest.writeInt(getIdFilial());
			dest.writeInt(getParcelaPaga());
			dest.writeDouble(getValorParcela());
			dest.writeDouble(getValorPago());
			dest.writeLong(getDtVencimento());
			dest.writeLong(getDtPagamento());			
		}
		
		public PedidoPgtoVO(Parcel in){
			setIdSequencia(in.readInt());
			setIdPedido(in.readInt());
			setIdEmpresa(in.readInt());
			setIdFilial(in.readInt());
			setParcelaPaga(in.readInt());
			setIdSequencia(in.readInt());
			setValorParcela(in.readDouble());
			setValorPago(in.readDouble());
			setDtVencimento(in.readLong());
			setDtPagamento(in.readLong());
		}
		
		public static final Parcelable.Creator<PedidoPgtoVO> CREATOR = new Parcelable.Creator<PedidoPgtoVO>() {
	        public PedidoPgtoVO createFromParcel(Parcel in)
	        {
	            return new PedidoPgtoVO(in);
	        }
	 
	        public PedidoPgtoVO[] newArray(int size)
	        {
	            return new PedidoPgtoVO[size];
	        }
	    };
}
