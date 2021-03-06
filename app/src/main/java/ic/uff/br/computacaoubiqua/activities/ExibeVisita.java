package ic.uff.br.computacaoubiqua.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.visit.Visit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExibeVisita extends AppCompatActivity {

    TextView buttonNao;
    TextView buttonSim;
    TextView txtPergunta;
    ImageView image;
    TextToSpeech t1;
    private AlertDialog alerta;
    public static final String ARG_USER = "user";
    private User user;
    private Visit visit;
    private static String[] OPTIONS = new String[]{"Descrição","Lugar","Parentesco"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibe_visita);

        user = null;
        txtPergunta = (TextView) findViewById(R.id.txtPergunta);

        if (getIntent().hasExtra(ARG_USER)) {
            user = (User) getIntent().getSerializableExtra(ARG_USER);
            txtPergunta.setText("Você se lembra do " + user.getFirstName() + "?");
            getIntent().removeExtra(ARG_USER);
        }

        visit = new Visit(new Date(), 0, user.getMacAddress());
        updateQuestionsNumberVisit(visit);

        addListenerOnButtons();

    }

    public void addListenerOnButtons() {

        buttonNao = (TextView) findViewById(R.id.txtBtnNao);
        buttonSim = (TextView) findViewById(R.id.txtBtnSim);

        image = (ImageView) findViewById(R.id.imageView1);


        buttonNao.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //speakVisitorDescription();
//                askForRememberVisitorAfterVisitorDescription();
                showDialogAlertWichOtherAudioOptions();
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
                    t1.speak("Vamos dar uma breve descrição para você tentar lembrar. " + user.getDescription(),
                            TextToSpeech.QUEUE_FLUSH, null);

                }
            }});
    }

    private void speakPermissionToOpenDoor(){
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
                    t1.speak("Você deseja abrir a porta para sua visita?",
                            TextToSpeech.QUEUE_FLUSH, null);
                    t1.setSpeechRate(1f);

                }
            }});
    }

    private void askForOpenDoor(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Abrir a porta?");
        builder.setMessage("Selecione a opção SIM para abrir a porta.");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "A porta foi aberta!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExibeVisita.this,
                        HomeActivity.class);

                startActivity(intent);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {


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
        builder.setMessage("Você se lembrou dele após esse áudio?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ExibeVisita.this, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

    private void speakVisitPlace(){
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
                    //                    t1.setPitch(0.2f);
                    //                    t1.setSpeechRate(0.8f);
                    t1.speak("Você conhece ele do seguinte lugar: " + user.getPlace(),
                            TextToSpeech.QUEUE_FLUSH, null);

                }
            }});
    }

    private void speakVisitKindship(){
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(new Locale("pt", "BR"));
                    //                    t1.setPitch(0.2f);
                    //                    t1.setSpeechRate(0.8f);
                    t1.speak("Ele tem o seguinte parentesco com você: " + user.getKinship(),
                            TextToSpeech.QUEUE_FLUSH, null);

                }
            }});
    }

    private void showDialogAlertWichOtherAudioOptions(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ExibeVisita.this);
        builder1.setTitle("Ouvir Outras Opções")
                .setItems(OPTIONS, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                speakVisitorDescription();
                                break;
                            case 1:
                                speakVisitPlace();
                                break;
                            case 2:
                                speakVisitKindship();
                                break;

                        }
                    }
                });
        AlertDialog showOptionsToPacient = builder1.create();
        showOptionsToPacient.show();

    }

    private void updateQuestionsNumberVisit(Visit visit){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                visit.setQtdPerguntas(visit.getQtdPerguntas() + 1);
                AppDatabase.getInstance(ExibeVisita.this).visitDao().insertAll(visit);
            }
        });

    }





}
