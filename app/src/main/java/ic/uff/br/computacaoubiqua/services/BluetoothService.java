package ic.uff.br.computacaoubiqua.services;

import android.app.IntentService;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import ic.uff.br.computacaoubiqua.activities.Bluetooth;

public class BluetoothService extends Service {

    private BluetoothAdapter BTAdapter;
    public static int REQUEST_BLUETOOTH = 1;
    BluetoothAdapter mBluetoothAdapter;
    Timer timer;
    TimerTask timerTask;

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    public String text = "Flamengo ficou so no cheirinho. Flamengo ficou so no cheirinho.";
    TextToSpeech t1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            Log.d("ComputacaoUbiqua", "Dispositivos PAREADOS");
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.d("ComputacaoUbiqua", device.getName() + " -- " + device.getAddress());
//                Toast.makeText(Bluetooth.this, device.getName() + " -- " + device.getAddress(), Toast.LENGTH_LONG).show();
            }
        }


        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        startTimer();

        //start sticky means service will be explicity started and stopped
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopping the player when service is destroyed
        unregisterReceiver(mReceiver);

    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.d("ComputacaoUbiqua", "Dispositivos ENCONTRADO");
                Log.d("ComputacaoUbiqua", device.getName() + " --AQUI-- " + device.getAddress());
//                Toast.makeText(Bluetooth.this, device.getName() + " -AQUI- " + device.getAddress(), Toast.LENGTH_SHORT).show();
                t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            t1.setLanguage(new Locale("pt", "BR"));
                    t1.setPitch(0.2f);
                    t1.setSpeechRate(0.8f);
                            t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        }
    };

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, 12000); //
    }


    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
//                        Toast.makeText(Bluetooth.this, "ENTROU NO LOOP", Toast.LENGTH_SHORT).show();

                        if(mBluetoothAdapter.startDiscovery()){

//                            Toast.makeText(Bluetooth.this, "Iniciou", Toast.LENGTH_SHORT).show();
                        } else {

//                            Toast.makeText(Bluetooth.this, "Não rodou", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
    }

}