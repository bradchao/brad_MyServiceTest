package brad.tw.myservicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private MediaPlayer mp;

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
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isPause = intent.getBooleanExtra("isPause", false);
        if (isPause){
            if (mp.isPlaying()) {
                mp.pause();
            }
        }else {
            if (!mp.isPlaying()) {
                mp.start();
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
    }

}
