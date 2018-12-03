package ic.uff.br.computacaoubiqua.adapters;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
 

public abstract class AbstractCustomAdapter<T> extends BaseAdapter {
    protected Activity activity;
    protected LayoutInflater inflater;
    protected List<T> list;
    
 
    public AbstractCustomAdapter(Activity activity, List<T> list) {
        this.activity = activity;
        this.list = list;
    }
 
    @Override
    public int getCount() {
    	if(list != null){
    		return list.size();
    	}
        return 0;
    }
 
    @Override
    public Object getItem(int location) {
        return list.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public abstract View getView(int position, View convertView, ViewGroup parent); 
    
    public abstract void remove(int position);
}