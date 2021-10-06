package com.dharmik953.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class intro extends AppCompatActivity {

    final private static int TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(() -> {
            Intent HomeIndent = new Intent(intro.this,MainActivity.class);
            startActivity(HomeIndent);
            finish();
        },TIME_OUT);

    }
}