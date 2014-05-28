package br.com.slim.venda.cidade;

import java.util.ArrayList;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.ArrayAdapter;
import org.holoeverywhere.widget.TextView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import br.com.slim.venda.R;
import br.com.slim.venda.cliente.ClienteVO;

public class CidadeSimpleAdapter  extends ArrayAdapter<CidadeVO>{

	ArrayList<CidadeVO> lsCities;
	ArrayList<CidadeVO> lsCitiesAll;
	ArrayList<CidadeVO> suggestions;
	private int viewResourceID;	
	public CidadeSimpleAdapter(Context context, int viewResourceId, ArrayList<CidadeVO> items) {
	        super(context, viewResourceId, items);
	        this.lsCities = items;
	        this.lsCitiesAll = (ArrayList<CidadeVO>)items.clone();
	        this.suggestions = new ArrayList<CidadeVO>();
	        this.viewResourceID = viewResourceId;	        
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater inflate = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflate.inflate(viewResourceID, null);
		}
		 CidadeVO cidadeVO = lsCities.get(position);
		 if(cidadeVO != null){
			 TextView cidadeLabel  = (TextView) v.findViewById(R.id.tvCidade);
			 if(cidadeLabel != null){
				 cidadeLabel.setText(cidadeVO.getMunicipio()+" - "+cidadeVO.getUF());
			 }
		 }
		return v;
	}
	
	
	
	@Override
    public Filter getFilter() {
        return cidadeFilter;
    }

    Filter cidadeFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            String str = ((CidadeVO)(resultValue)).getMunicipio(); 
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (CidadeVO cidadeVO : lsCitiesAll) {                	 
                    if(cidadeVO.getMunicipio().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(cidadeVO);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<CidadeVO> filteredList = (ArrayList<CidadeVO>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (CidadeVO c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };
	

}
