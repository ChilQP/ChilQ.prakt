package com.example.zz.chilq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

import static java.lang.Boolean.TRUE;

public class Auth extends AppCompatActivity implements View.OnClickListener {

    private RadioRealButton bParent, bChild;
    private RadioRealButtonGroup radioRealButtonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        findViewById(R.id.signGoogle).setOnClickListener(this);
        bParent = (RadioRealButton) findViewById(R.id.bParent);
        bChild = (RadioRealButton) findViewById(R.id.bChild);
        radioRealButtonGroup = (RadioRealButtonGroup) findViewById(R.id.bGroup);
        radioRealButtonGroup.setPosition(0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signGoogle:
                Intent intent=new Intent(Auth.this ,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
