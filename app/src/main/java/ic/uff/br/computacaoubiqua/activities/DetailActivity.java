package ic.uff.br.computacaoubiqua.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.ui.detail.DetailFragment;
import ic.uff.br.computacaoubiqua.activities.ui.detail.DetailPersonFragment;
import ic.uff.br.computacaoubiqua.database.user.User;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_USER = "user";
    public static final String ARG_FRAG_DETAIL = "detail";
    public static final String ARG_FRAG_DETAIL_PERSON = "detail_person";
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        if (getIntent().hasExtra(ARG_USER)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            user = (User) getIntent().getSerializableExtra(ARG_USER);
        }

        if (savedInstanceState == null) {
            if (getIntent().hasExtra(ARG_FRAG_DETAIL)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, DetailFragment.newInstance())
                        .commitNow();
            }
            else if (getIntent().hasExtra(ARG_FRAG_DETAIL_PERSON)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, DetailPersonFragment.newInstance())
                        .commitNow();
            }
        }
    }
}
