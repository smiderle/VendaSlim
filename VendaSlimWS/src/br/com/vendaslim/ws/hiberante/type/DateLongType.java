package br.com.vendaslim.ws.hiberante.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

//http://www.guj.com.br/java/145030-personalizando-tipos-do-hibernate-implementando-usertype--ou-extendendo--hibernatetypetimestamp

public class DateLongType implements UserType {

	 @Override  
	    public Object assemble(Serializable arg0, Object arg1) throws HibernateException {  
	        return arg0;  
	    }  
	  
	    @Override  
	    public Object deepCopy(Object arg0) throws HibernateException {  
	        return arg0;  
	    }  
	  
	    @Override  
	    public Serializable disassemble(Object arg0) throws HibernateException {  
	        return (Serializable) arg0;  
	    }  
	  
	    @Override  
	    public boolean equals(Object arg0, Object arg1) throws HibernateException {  
	        if (arg0 == arg1) {  
	            return true;  
	        }  
	        return false;  
	    }  
	  
	    @Override  
	    public int hashCode(Object arg0) throws HibernateException {  
	        return arg0.hashCode();  
	    }  
	  
	    @Override  
	    public boolean isMutable() {  
	        return false;  
	    }  

	@Override
	public Object nullSafeGet(ResultSet res, String[] names,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		Date data = res.getDate(names[0]);
		
		if(data!= null)
			return data.getTime();
		else
			return null;
          
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index,
			SessionImplementor arg3) throws HibernateException, SQLException {
	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public Class returnedClass() {
		// TODO Auto-generated method stub
		return HashMap.class;
		
	}

	@Override
	public int[] sqlTypes() {

		return new int[] { Types.TIMESTAMP };  
	}

}
