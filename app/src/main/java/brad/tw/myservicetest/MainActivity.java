package brad.tw.myservicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View v){
        Intent it = new Intent(this, MyService.class);
        startService(it);
    }
    public void pause(View v){
        Intent it = new Intent(this, MyService.class);
        it.putExtra("isPause", true);
        startService(it);
    }
    public void stopService(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);
    }

}
