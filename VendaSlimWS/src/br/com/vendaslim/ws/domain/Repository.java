package br.com.vendaslim.ws.domain;

import java.util.Calendar;
import java.util.List;

import javax.management.Query;

public interface Repository {	
	void save(Domain domain);	
	void save(List<? extends Domain> domains);
	void saveOrUpdate(Domain domain);
	void saveOrUpdate(List<? extends Domain> domains);
	void update(Domain domain);
	void update(List<? extends Domain> domains);
}
