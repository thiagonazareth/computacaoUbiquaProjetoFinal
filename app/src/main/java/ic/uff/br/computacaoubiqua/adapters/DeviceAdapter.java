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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.DetailActivity;
import ic.uff.br.computacaoubiqua.activities.HomeActivity;
import ic.uff.br.computacaoubiqua.activities.Relatorio;
import ic.uff.br.computacaoubiqua.activities.ui.detail.DetailFragment;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;


public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private Map<String, User> userMap = new LinkedHashMap<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        List<User> userList = new ArrayList<User>(userMap.values());
        holder.user = userList.get(position);
        String exibeNome = holder.user.getDeviceName() != null ? holder.user.getDeviceName() : holder.user.getMacAddress();
        holder.mContentView.setText(exibeNome);

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEVICE ADAPTER", "CLICK: " + exibeNome);
//                new UserInsertAsyncTask().execute(holder);
                Intent intent = new Intent(holder.mView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ARG_USER, holder.user);
                holder.mView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userMap.size();
    }

    public void addUser(User user) {
        this.userMap.put(user.getMacAddress(), user);
        notifyDataSetChanged();
    }

    public void remove(User user){
        userMap.remove(user.getMacAddress());
        notifyDataSetChanged();
    }

    public List<User> getUserList() {
        return new ArrayList<User>(userMap.values());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final Button mButtonView;
        public User user;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mButtonView = (Button) view.findViewById(R.id.button);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
