package ic.uff.br.computacaoubiqua.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.services.BluetoothService;

public class TelaAbertura extends AppCompatActivity {

    public static final int DURACAO_DA_TELA = 2000;
    TextToSpeech t1;

//    public String text = "Flamengo ficou so no cheirinho. Flamengo ficou so no cheirinho.";

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
                Intent minhaAcao = new Intent(TelaAbertura.this, MainActivity.class);
                TelaAbertura.this.startActivity(minhaAcao);
                TelaAbertura.this.finish();
            }
        }, DURACAO_DA_TELA);
    }

}
