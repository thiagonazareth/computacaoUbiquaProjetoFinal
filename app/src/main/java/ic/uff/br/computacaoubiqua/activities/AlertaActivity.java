package ic.uff.br.computacaoubiqua.activities;


import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class AlertaActivity extends AppCompatActivity {

    private static String EMAIL = "EMAIL";
    private static String ALERTAR = "ALERTAR";

    private EditText email;
    private CheckBox checkBox;
    private Button btnSave;
    //atributo da classe.
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);

        email = (EditText) findViewById(R.id.editEmailAlert);
        checkBox = (CheckBox) findViewById(R.id.checkBoxAlert);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        email.setText(prefs.getString(EMAIL, ""));
        checkBox.setChecked(prefs.getBoolean(ALERTAR, false));




    }

    @Override
    public void onResume(){
        super.onResume();

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(EMAIL, email.getText().toString());
                editor.putBoolean(ALERTAR, checkBox.isChecked());
                editor.commit();

                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertaActivity.this);
                //define o titulo
                builder.setTitle("Salvo com sucesso!");
                //define a mensagem
                builder.setMessage("Informação salva com sucesso!");
                //define um botão como positivo
                builder.setNeutralButton("Ok!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();
            }
        });

    }

}
