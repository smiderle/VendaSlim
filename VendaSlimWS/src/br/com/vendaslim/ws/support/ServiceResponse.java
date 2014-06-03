package br.com.vendaslim.ws.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceResponse<DOMAIN> {
	private DOMAIN value;
	private Long rowCount;

	public ServiceResponse() {
	}
	public ServiceResponse(final DOMAIN value, final Long rowCount) {
		this.value = value;
		this.rowCount = rowCount;
	}
	public Long getRowCount() {
		return rowCount;
	}
	public DOMAIN getValue() {
		return value;
	}
	public void setRowCount(final Long rowCount) {
		this.rowCount = rowCount;
	}
	public void setValue(final DOMAIN value) {
		this.value = value;
	}

}
