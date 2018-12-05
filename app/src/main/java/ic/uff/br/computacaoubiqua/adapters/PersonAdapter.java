package ic.uff.br.computacaoubiqua.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.DetailActivity;
import ic.uff.br.computacaoubiqua.activities.MainActivity;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<User> userList = new ArrayList<>();
//    private final OnListFragmentInteractionListener mListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = userList.get(position);

        String first_name = holder.user.getFirstName() != null ? holder.user.getFirstName() : holder.user.getDeviceName();
        String last_name = holder.user.getLastName() != null ? holder.user.getLastName() : holder.user.getMacAddress();
        String exibe_nome = first_name != null ? first_name : last_name;
        holder.mContentView.setText(exibe_nome);

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserDeleteAsyncTask().execute(holder);
                Log.d("PERSON ADAPTER", "CLICK: " + exibe_nome);
            }
        });

        holder.mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PERSON ADAPTER", "CLICK VIEW: " + exibe_nome);
                Intent intent = new Intent(holder.mView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_USER, holder.user);
                intent.putExtra(DetailActivity.ARG_FRAG_DETAIL_PERSON, "2");
                holder.mView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUsers(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final Button mButtonView;
        public final Button mButtonView2;
        public User user;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mButtonView = (Button) view.findViewById(R.id.button);
            mButtonView2 = (Button) view.findViewById(R.id.button2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    private class UserDeleteAsyncTask extends AsyncTask<PersonAdapter.ViewHolder, Integer, PersonAdapter.ViewHolder> {
        protected PersonAdapter.ViewHolder doInBackground(PersonAdapter.ViewHolder... holders) {
            if (holders.length > 0) {
                AppDatabase.getInstance(holders[0].mView.getContext()).userDao().delete(holders[0].user);
                return holders[0];
            }
            return null;
        }

        protected void onPostExecute(PersonAdapter.ViewHolder holder) {
            if (holder != null){
//                if (MainActivity.deviceAdapter != null && MainActivity.deviceAdapter instanceof DeviceAdapter) {
//                    ((DeviceAdapter) MainActivity.deviceAdapter).addUser(holder.user);
//                }
                Toast.makeText(holder.mView.getContext(), "Pessoa removida!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
