package ic.uff.br.computacaoubiqua.activities;

import java.io.IOException;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import ic.uff.br.computacaoubiqua.R;


public class Home extends AppCompatActivity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}



	@Override
	public void onResume() {
		super.onResume();



		final ImageView cadastrarVisitante = (ImageView) findViewById(R.id.img_cadastrar_visitante);
		cadastrarVisitante.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Home.this,
						MainActivity.class);

				startActivity(intent);
			}
		});
//
//		final ImageView ultimasVisitas = (ImageView) findViewById(R.id.img_meus_amigos);
//		ultimasVisitas.setImageResource(R.drawable.button_meus_amigos);
//		ultimasVisitas.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				ultimasVisitas
//						.setImageResource(R.drawable.button_meus_amigos_hover);
//				Intent intent = new Intent(Home.this, UltimasVisitas.class);
//
//				startActivity(intent);
//			}
//		});
//
//		final ImageView configurarAlerta = (ImageView) findViewById(R.id.img_meus_avisos);
//		configurarAlerta.setImageResource(R.drawable.button_avisa_ai);
//		configurarAlerta.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				configurarAlerta.setImageResource(R.drawable.button_avisa_ai_hover);
//				Intent intent = new Intent(Home.this,
//						ConfigurarAlerta.class);
//
//				startActivity(intent);
//			}
//		});
//
		final ImageView relatorios = (ImageView) findViewById(R.id.img_relatorio);

		relatorios.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Home.this, Relatorio.class);

				startActivity(intent);
			}
		});



	}


}
