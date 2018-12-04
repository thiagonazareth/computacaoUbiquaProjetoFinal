package ic.uff.br.computacaoubiqua.database.visit;

import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.adapters.VisitListAdapter;

public class VisitListFragment extends Fragment {
    private VisitListAdapter visitListAdapter;
    private VisitViewModel visitViewModel;
    private Context context;

    public static VisitListFragment newInstance() {
        return new VisitListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        visitListAdapter = new VisitListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ultimas_visitas, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_visits);
        recyclerView.setAdapter(visitListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private void initData() {
        visitViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);
        visitViewModel.getVisitsList().observe(this, new Observer<List<Visit>>() {
            @Override
            public void onChanged(@Nullable List<Visit> visits) {
                visitListAdapter.setvisitList(visits);
            }
        });
    }

}