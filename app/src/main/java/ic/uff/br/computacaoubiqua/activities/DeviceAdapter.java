package ic.uff.br.computacaoubiqua.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.database.user.User;


public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private Map<String, User> userMap = new LinkedHashMap<>();
//    private final OnListFragmentInteractionListener mListener;


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

//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userMap.size();
    }

    public void addUser(User user) {
        this.userMap.put(user.getMacAddress(), user);
        notifyDataSetChanged();
    }

    public List<User> getUserList() {
        return new ArrayList<User>(userMap.values());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public User user;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
