package com.teamguyana.storingnumberscratch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Ryan on 12/17/2014.
 */
public class Main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    public void MainStartClick(View v) {
        //http://stackoverflow.com/questions/20382521/using-multiple-button-to-start-different-activities-android
        //Used this source to help  simplify the button code.
        switch (v.getId()) {
            case R.id.btnStartGame:
                Intent intent = new Intent(Main.this, Game.class);
                startActivity(intent);
                break;
        }
    }
}