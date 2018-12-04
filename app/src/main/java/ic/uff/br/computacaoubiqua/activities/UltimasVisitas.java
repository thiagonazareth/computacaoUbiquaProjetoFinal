package ic.uff.br.computacaoubiqua.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.adapters.PersonAdapter;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.UserViewModel;
import ic.uff.br.computacaoubiqua.database.visit.Visit;
import ic.uff.br.computacaoubiqua.database.visit.VisitListFragment;
import ic.uff.br.computacaoubiqua.database.visit.VisitViewModel;

public class UltimasVisitas extends AppCompatActivity  {

    private Fragment shownFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_visitas);

        setToolbar("Últimas visitas registradas");
        showFragment(VisitListFragment.newInstance());



    }

    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }


    @Override
    public void onResume(){
        super.onResume();


    }

    public void showFragment(final Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, fragment);
        fragmentTransaction.commitNow();
        shownFragment = fragment;
    }


}
