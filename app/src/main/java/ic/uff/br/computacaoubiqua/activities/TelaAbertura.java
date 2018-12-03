package ic.uff.br.computacaoubiqua.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ic.uff.br.computacaoubiqua.R;

public class TelaAbertura extends AppCompatActivity {

    public static final int DURACAO_DA_TELA = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_abertura);
    }

    @Override
    public void onResume(){
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent minhaAcao = new Intent(TelaAbertura.this, HomeActivity.class);
                TelaAbertura.this.startActivity(minhaAcao);
                TelaAbertura.this.finish();
            }
        }, DURACAO_DA_TELA);
    }

}
