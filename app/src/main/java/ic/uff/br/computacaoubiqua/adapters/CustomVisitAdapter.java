package ic.uff.br.computacaoubiqua.adapters;

import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.UltimasVisitas;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.visit.Visit;


public class CustomVisitAdapter extends AbstractCustomAdapter<Visit> {
 
    public CustomVisitAdapter(Activity activity, List<Visit> list) {
		super(activity, list);
	}
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
    	if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.lista_visitas_row, null);
        
        ImageView thumbNail = (ImageView) convertView
                .findViewById(R.id.thumbnail);
        
        TextView linha_trajeto = (TextView) convertView.findViewById(R.id.linha_trajeto_historico_viagem);
        TextView horario = (TextView) convertView.findViewById(R.id.horario_historico_viagem);
        
        Visit visita = list.get(position);
        User user = AppDatabase.getInstance(convertView.getContext()).userDao().findByMacAddress(visita.getMacAddress());
        linha_trajeto.setText(user.getFirstName());
        horario.setText(visita.getVisitDate().toString());
//        convertView.setLongClickable(true);
 
        return convertView;
    }
    
    public void remove(int position){
    }
    
}