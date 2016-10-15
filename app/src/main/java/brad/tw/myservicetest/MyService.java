package brad.tw.myservicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private MediaPlayer mp;
    private Timer timer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.brad);

        int len = mp.getDuration();

        Intent it = new Intent("brad");
        it.putExtra("len", len);
        sendBroadcast(it);


        timer = new Timer();
        timer.schedule(new MyTask(),0,100);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if (mp!=null && mp.isPlaying()) {
                int nowpos = mp.getCurrentPosition();
                Intent it = new Intent("brad");
                it.putExtra("nowpos", nowpos);
                sendBroadcast(it);
            }
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isPause = intent.getBooleanExtra("isPause", false);
        int chPos = intent.getIntExtra("chPos", -1);

        if (chPos != -1){
            mp.seekTo(chPos);
        }else {

            if (isPause) {
                if (mp.isPlaying()) {
                    mp.pause();
                }
            } else {
                if (!mp.isPlaying()) {
                    mp.start();
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null ){
            mp.stop();
            mp.release();
            mp = null;
        }
        if (timer != null){
            timer.purge();
            timer.cancel();
            timer = null;
        }
    }

}
