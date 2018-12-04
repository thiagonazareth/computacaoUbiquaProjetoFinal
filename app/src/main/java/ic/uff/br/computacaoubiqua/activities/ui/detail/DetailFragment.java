package ic.uff.br.computacaoubiqua.activities.ui.detail;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.DetailActivity;
import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.user.UserViewModel;

public class DetailFragment extends Fragment {

    //    private VisitViewModel mViewModel;
    private Activity activity;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        TextView txView = ((TextView) view.findViewById(R.id.message));
        DetailActivity myActivity = (DetailActivity) getActivity();
        txView.setText(myActivity.user.getDeviceName());

        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
//        // TODO: Use the ViewModel
//    }

}
