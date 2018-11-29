package ic.uff.br.computacaoubiqua.activities;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
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
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;

public class Bluetooth extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    Timer timer;
    TimerTask timerTask;

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_abertura);
    }

    @Override
    public void onResume(){
        super.onResume();
//        startTimer();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

}
