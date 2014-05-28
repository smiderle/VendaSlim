package br.com.vendaslim.controler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.repository.IRepresentanteRotaRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.domain.representante.RepresentanteRota;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.util.ContextoUtil;

public class RepresentanteRotaControler {
	private IRepresentanteRotaRepository repository;
	
	public RepresentanteRotaControler() {
		this.repository = DAOFactory.criaRepresentanteRotaRepository();
	}
	
	public List<RepresentanteRota> buscaTodosPorRepresentante(Representante representante, Date data){
		Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
		Calendar dtInicio = new GregorianCalendar();
		dtInicio.setTime(data);
		dtInicio.set(Calendar.HOUR_OF_DAY, 8);
		dtInicio.set(Calendar.MINUTE, 00);
		
		Calendar dtFim = new GregorianCalendar();
		dtFim.setTime(data);
		dtFim.set(Calendar.HOUR_OF_DAY, 23);
		dtFim.set(Calendar.MINUTE, 30);
		
		return this.repository.buscaTodosPorRepresentante(
				empresa, representante.getIdRepresentante(), dtInicio, dtFim);
	}
	
	
}
