package ic.uff.br.computacaoubiqua.activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;

import ic.uff.br.computacaoubiqua.R;

public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter BTAdapter;
    public static int REQUEST_BLUETOOTH = 1;
    BluetoothAdapter mBluetoothAdapter;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_abertura);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            Log.d("myTag", "Dispositivos PAREADOS");
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.d("myTag", device.getName() + " -- " + device.getAddress());
                Toast.makeText(Bluetooth.this, device.getName() + " -- " + device.getAddress(), Toast.LENGTH_LONG).show();
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(Bluetooth.this, "ENTROU NO LOOP", Toast.LENGTH_LONG).show();

                if(mBluetoothAdapter.startDiscovery()){

                    Toast.makeText(Bluetooth.this, "Iniciou", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(Bluetooth.this, "Não rodou", Toast.LENGTH_LONG).show();
                }
            }
        }, 5000);


        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);


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
                Log.d("myTag", "Dispositivos ENCONTRADO");
                Log.d("myTag", device.getName() + " -- " + device.getAddress());
                Toast.makeText(Bluetooth.this, device.getName() + " -AQUI- " + device.getAddress(), Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    protected void onStop()
    {
        //unregisterReceiver(mReceiver);
        super.onStop();
    }

}
