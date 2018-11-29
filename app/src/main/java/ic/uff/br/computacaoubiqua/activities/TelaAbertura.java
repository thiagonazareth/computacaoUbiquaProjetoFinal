package ic.uff.br.computacaoubiqua.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.services.BluetoothService;

public class TelaAbertura extends AppCompatActivity {

    private final int DURACAO_DA_TELA = 5000;
    TextToSpeech t1;

    public String text = "Flamengo ficou so no cheirinho. Flamengo ficou so no cheirinho.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_abertura);
        startService(new Intent(this, BluetoothService.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
//                    t1.setPitch(0.2f);
//                    t1.setSpeechRate(0.8f);
                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent minhaAcao = new Intent(TelaAbertura.this, Bluetooth.class);
                TelaAbertura.this.startActivity(minhaAcao);
                TelaAbertura.this.finish();
            }
        }, DURACAO_DA_TELA);
    }

}
