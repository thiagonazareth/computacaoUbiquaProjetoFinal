package ic.uff.br.computacaoubiqua.adapters;

import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.visit.Visit;


public class VisitListAdapter extends RecyclerView.Adapter<VisitListAdapter.VisitViewHolder> {


    private LayoutInflater layoutInflater;
    private List<Visit> visitList;
    private Context context;
    User user;
    Visit visit;

    public VisitListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void setvisitList(List<Visit> visitList) {
        this.visitList = visitList;
        notifyDataSetChanged();
    }

    @Override
    public VisitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.visit_fragment_item, parent, false);
        return new VisitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VisitViewHolder holder, int position) {
        if (visitList == null) {
            return;
        }



        visit = visitList.get(position);
        PreencheDados preencheDados = new PreencheDados();
        preencheDados.execute(holder);


//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                user = AppDatabase.getInstance(context).userDao().findByMacAddress(visit.getMacAddress());
//
//                return null;
//            }
//        }.execute();



//        holder.txtVisitName.setText(visit.getMacAddress());
//        holder.txtVisitDate.setText(formatDate(visit.getVisitDate()));

    }

    @Override
    public int getItemCount() {
        if (visitList == null) {
            return 0;
        } else {
            return visitList.size();
        }
    }

    static class VisitViewHolder extends RecyclerView.ViewHolder {
        private TextView txtVisitName;
        private TextView txtVisitDate;

        public VisitViewHolder(View itemView) {
            super(itemView);

            txtVisitName = itemView.findViewById(R.id.txtVisitName);
            txtVisitDate = itemView.findViewById(R.id.txtVisitDate);

        }
    }

    private String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        String dateString = format.format(date);
        return dateString;
    }

    private class PreencheDados extends AsyncTask<VisitViewHolder, Void, VisitViewHolder> {
        protected VisitViewHolder doInBackground(VisitViewHolder... holder) {

            user = AppDatabase.getInstance(context).userDao().findByMacAddress(visit.getMacAddress());
            return holder[0];
        }

        protected void onProgressUpdate() {

        }

        protected void onPostExecute(VisitViewHolder holder) {
            holder.txtVisitName.setText(user.getFirstName() + " " + user.getLastName());
            holder.txtVisitDate.setText(formatDate(visit.getVisitDate()));
        }
    }
    
}
