package br.com.slim.venda.representante;

public class RepresentanteRotaVO {
	
	private int idEmpresa;
	private int idRepresentante;
	private long dtHrRotaLong;
	private double latitude;
	private double longitude;
	private int sincronizado;
	
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(int idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	
	
	public long getDtHrRotaLong() {
		return dtHrRotaLong;
	}
	public void setDtHrRotaLong(long dtHrRotaLong) {
		this.dtHrRotaLong = dtHrRotaLong;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public int getSincronizado() {
		return sincronizado;
	}
	public void setSincronizado(int sincronizado) {
		this.sincronizado = sincronizado;
	}


	public static final class RepresentanteRota{
		private RepresentanteRota() {}
		
		public static final String TABELA = "REPRESENTANTE_ROTA";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String IDEMPRESA = "IDEMPRESA";		
		public static final String DTHRROTA = "DTHRROTA";
		public static final String LATITUDE = "LATITUDE";
		public static final String LONGITUDE = "LONGITUDE";
		public static final String SINCRONIZADO = "SINCRONIZADO";
		public static final String[] COLUNAS = new String[]{IDREPRESENTANTE,IDEMPRESA,DTHRROTA, LATITUDE, LONGITUDE, SINCRONIZADO};
	}
	
	
}
