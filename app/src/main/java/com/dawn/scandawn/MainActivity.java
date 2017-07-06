package com.dawn.scandawn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jumpToScan(View view){
        startActivity(new Intent(this, ScanActivity.class));
    }
    public void jumpToGenerate(View view){
        startActivity(new Intent(this, GenerateActivity.class));
    }
}
