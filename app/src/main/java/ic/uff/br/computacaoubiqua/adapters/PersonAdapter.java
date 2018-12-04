package ic.uff.br.computacaoubiqua.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.database.user.User;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<User> userList = new ArrayList<>();
//    private final OnListFragmentInteractionListener mListener;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = userList.get(position);

        String first_name = holder.user.getFirstName() != null ? holder.user.getFirstName() : holder.user.getDeviceName();
        String last_name = holder.user.getLastName() != null ? holder.user.getLastName() : holder.user.getMacAddress();
        String exibe_nome = first_name != null ? first_name : last_name;
        holder.mContentView.setText(exibe_nome);

        Log.d("PATH", holder.user.getPhotoPath());

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PERSON ADAPTER", "CLICK: " + exibe_nome);
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
