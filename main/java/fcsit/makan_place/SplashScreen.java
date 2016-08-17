package fcsit.makan_place;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;

/**
 * Created by elliotching on 28-Jun-16.
 */
public class SplashScreen extends AppCompatActivity {

    public void onCreate(Bundle ins) {
        super.onCreate(ins);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
        startActivity(new Intent(this, Makan_main.class));
    }
}
