package ic.uff.br.computacaoubiqua.activities;


import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.services.BluetoothService;


public class HomeActivity extends AppCompatActivity {

	public static final int REQUEST_ENABLE_BT = 2;
	public static final int DURACAO_DA_TELA = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // Only ask for these permissions on runtime when running Android 6.0 or higher
			switch (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
				case PackageManager.PERMISSION_DENIED:
					((TextView) new AlertDialog.Builder(this)
							.setTitle("Runtime Permissions up ahead")
							.setMessage(Html.fromHtml("<p>To find nearby bluetooth devices please click \"Allow\" on the runtime permissions popup.</p>" +
									"<p>For more info see <a href=\"http://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-hardware-id\">here</a>.</p>"))
							.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
										ActivityCompat.requestPermissions(HomeActivity.this,
												new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
												1);
									}
								}
							})
							.show()
							.findViewById(android.R.id.message))
							.setMovementMethod(LinkMovementMethod.getInstance());       // Make the link clickable. Needs to be called after show(), in order to generate hyperlinks
					break;
				case PackageManager.PERMISSION_GRANTED:
					break;
			}
		}

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter != null) {
			if (!mBluetoothAdapter.isEnabled()) {
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			} else {
				Intent intent = new Intent( this, BluetoothService.class);
				startService(intent);
			}
		}
		else{
			Toast.makeText(HomeActivity.this, "Bluetooth não disponível, não é possível continuar!", Toast.LENGTH_SHORT).show();
			new Handler().postDelayed(new Runnable() {
				public void run() {
					HomeActivity.this.finish();
					moveTaskToBack(true);
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(1);
				}
			}, DURACAO_DA_TELA);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == REQUEST_ENABLE_BT) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				Intent intent = new Intent( this, BluetoothService.class);

				startService(intent);
			} else {
				Toast.makeText(HomeActivity.this, "Não é possível continuar!", Toast.LENGTH_SHORT).show();
				new Handler().postDelayed(new Runnable() {
					public void run() {
						HomeActivity.this.finish();
						moveTaskToBack(true);
						android.os.Process.killProcess(android.os.Process.myPid());
						System.exit(1);
					}
				}, DURACAO_DA_TELA);
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();



		final ImageView cadastrarVisitante = (ImageView) findViewById(R.id.img_cadastrar_visitante);
		cadastrarVisitante.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this,
						MainActivity.class);

				startActivity(intent);
			}
		});
//
		final ImageView ultimasVisitas = (ImageView) findViewById(R.id.img_ultimas_visitas);

		ultimasVisitas.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, UltimasVisitas.class);
				startActivity(intent);
			}
		});
//
//		final ImageView configurarAlerta = (ImageView) findViewById(R.id.img_meus_avisos);
//		configurarAlerta.setImageResource(R.drawable.button_avisa_ai);
//		configurarAlerta.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				configurarAlerta.setImageResource(R.drawable.button_avisa_ai_hover);
//				Intent intent = new Intent(HomeActivity.this,
//						ConfigurarAlerta.class);
//
//				startActivity(intent);
//			}
//		});
//
		final ImageView relatorios = (ImageView) findViewById(R.id.img_relatorio);

		relatorios.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, Relatorio.class);

				startActivity(intent);
			}
		});



	}


}
