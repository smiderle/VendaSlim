package br.com.vendaslim.domain.repository;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.representante.RepresentanteRota;

public interface IRepresentanteRotaRepository extends Repository{
	public List<RepresentanteRota> buscaTodosPorRepresentante(Empresa empresa, Integer idRepresentante, Calendar dthrinicio, Calendar dthrfim);
	
}
