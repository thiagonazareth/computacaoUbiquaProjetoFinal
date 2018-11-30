package ic.uff.br.computacaoubiqua.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import ic.uff.br.computacaoubiqua.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ExibeVisita extends Activity {

    TextView buttonNao;
    TextView buttonSim;
    ImageView image;
    TextToSpeech t1;
    private AlertDialog alerta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibe_visita);

        addListenerOnButtons();

    }

    public void addListenerOnButtons() {

        buttonNao = (TextView) findViewById(R.id.txtBtnNao);
        buttonSim = (TextView) findViewById(R.id.txtBtnSim);

        image = (ImageView) findViewById(R.id.imageView1);


        buttonNao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakVisitorDescription();
                askForRememberVisitorAfterVisitorDescription();
            }

        });

        buttonSim.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                speakPermissionToOpenDoor();
                askForOpenDoor();
            }

        });

    }

    private void speakVisitorDescription(){

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
                    //                    t1.setPitch(0.2f);
                    //                    t1.setSpeechRate(0.8f);
                    t1.speak("Aqui será feito a descrição do visitante e exibiremos uma mensagem pra perguntar se ele lembrou! Também será contabilizado a informação que ele lembrou aqui.",
                            TextToSpeech.QUEUE_FLUSH, null);
                    t1.setSpeechRate(1f);

                }
            }});
    }

    private void speakPermissionToOpenDoor(){
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
                    //                    t1.setPitch(0.2f);
                    //                    t1.setSpeechRate(0.8f);

                    t1.speak("Você deseja abrir a porta para sua visita?",
                            TextToSpeech.QUEUE_FLUSH, null);
                    t1.setSpeechRate(1f);

                }
            }});
    }

    private void askForOpenDoor(){
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Abrir a porta?");
        //define a mensagem
        builder.setMessage("Selecione a opção SIM para abrir a porta.");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void askForRememberVisitorAfterVisitorDescription(){

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Lembrou?");
        //define a mensagem
        builder.setMessage("Você se lembrou dele após essa descrição?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }



}
