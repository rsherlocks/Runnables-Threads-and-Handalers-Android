package com.example.androidshaper.runnablesthreadsandhandalers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Switch aSwitch;
    Button buttonTest;
    TextView textView;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch=findViewById(R.id.switchCheck);
        buttonTest=findViewById(R.id.buttonTest);
        textView=findViewById(R.id.textView);
        buttonTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.buttonTest)
        {

            final Handler handler=new Handler();
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Thread 2:"+Thread.currentThread().getName());


                    synchronized (this){
                        try {
                            wait(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Runnable1 is run",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.i(TAG, "Download Finished");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Runnable2 is run",Toast.LENGTH_SHORT).show();

                        }
                    },5000);




                }
            };
            //runnable.run();
            Log.i(TAG, "Thread 1:"+Thread.currentThread().getName());
            Thread thread=new Thread(runnable);
            thread.start();

        }

    }
}