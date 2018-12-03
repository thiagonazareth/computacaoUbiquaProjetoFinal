package ic.uff.br.computacaoubiqua.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.adapters.DeviceAdapter;
import ic.uff.br.computacaoubiqua.activities.ExibeVisita;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;

public class BluetoothService extends Service {

    BluetoothAdapter mBluetoothAdapter;
    Timer timer;
    TimerTask timerTask;
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();
    private final IBinder mBinder = new LocalBinder();
    private DeviceAdapter deviceAdapter = new DeviceAdapter();


    public String text = "Você está recebendo uma nova visita.";
    TextToSpeech t1;
    TextToSpeech t2;

    public DeviceAdapter getAdapter(){
        return this.deviceAdapter;
    }

    public class LocalBinder extends Binder {
        public BluetoothService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BluetoothService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        createNotificationChannel();

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
                final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                new UserQueryAsyncTask().execute(device);
            }
        }
    };

    private class UserQueryAsyncTask extends AsyncTask<BluetoothDevice, Integer, User> {
        protected User doInBackground(BluetoothDevice... devices) {
            if (devices.length > 0) {
                User user = AppDatabase.getInstance(BluetoothService.this).userDao().findByMacAddress(devices[0].getAddress());
                if (user == null) {
                    return new User(null, null, devices[0].getAddress(), devices[0].getName(), null, null, null, null);
                }
            }
            return null;
        }

        protected void onPostExecute(User user) {
            if (user != null) {
//                devices.put(user.getMacAddress(), user);
                deviceAdapter.addUser(user);
            }
            for (User u : deviceAdapter.getUserList()) {
                Log.d("SERVICO", "MAP SIZE: " + deviceAdapter.getItemCount());
                Log.d("SERVICO", u.getDeviceName() + " " + u.getMacAddress());
            }
        }
    }

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
//                        Toast.makeText(MainActivity.this, "ENTROU NO LOOP", Toast.LENGTH_SHORT).show();

                        if (mBluetoothAdapter.startDiscovery()) {

//                            t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                                @Override
//                                public void onInit(int status) {
//                                    if(status != TextToSpeech.ERROR) {
//                                        t1.setLanguage(new Locale("pt", "BR"));
//                                        //                    t1.setPitch(0.2f);
//                                        //                    t1.setSpeechRate(0.8f);
//                                        t1.setSpeechRate(0.5f);
//                                        t1.speak("Tim, dom!", TextToSpeech.QUEUE_FLUSH, null);
//                                        t1.setSpeechRate(1f);
//
//                                    }
//                                }});
//
//                            t2=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                                @Override
//                                public void onInit(int status) {
//                                    if(status != TextToSpeech.ERROR) {
//                                        t2.setLanguage(new Locale("pt", "BR"));
//                                        //                    t1.setPitch(0.2f);
//                                        //                    t1.setSpeechRate(0.8f);
//
//                                        t2.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//                                    }
//                                }});

                            Intent intent = new Intent(getApplicationContext(), ExibeVisita.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                                    .setSmallIcon(R.drawable.ic_launcher)
                                    .setContentTitle("Você tem uma nova visita.")
                                    .setContentText("É o Sr Wilson. Clique aqui para ver sua foto.")
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    // Set the intent that will fire when the user taps the notification
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true);
//                            mBuilder.setLights(Color.BLUE, 500, 500);
//                            long[] pattern = {500,500,500,500,500,500,500,500,500};
//                            mBuilder.setVibrate(pattern);
//                            mBuilder.setStyle(new NotificationCompat.InboxStyle());

                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());


                            // notificationId is a unique int for each notification that you must define
                            notificationManager.notify(1, mBuilder.build());


//                            Toast.makeText(MainActivity.this, "Iniciou", Toast.LENGTH_SHORT).show();
                        } else {

//                            Toast.makeText(MainActivity.this, "Não rodou", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", "teste", importance);
            channel.setDescription("teste");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}