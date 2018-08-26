package com.example.andrewlow.iheart2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        Thread welcome = new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);  //Delay of 1 seconds
                }catch (Exception e){

                }finally {
                    Intent i = new Intent(StartUp.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcome.start();
    }
}
