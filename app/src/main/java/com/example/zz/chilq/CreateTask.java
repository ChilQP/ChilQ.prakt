package com.example.zz.chilq;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CreateTask extends AppCompatActivity implements View.OnClickListener{
    private ImageView upimg;
    private final int Pick_image = 1;
    boolean flimg=false;
    Uri simg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_create_task);
        upimg=(ImageView)findViewById(R.id.imageTask);
        findViewById(R.id.imageButton).setOnClickListener(this);
        findViewById(R.id.uploadimg).setOnClickListener(this);
        findViewById(R.id.imageButtonAdd).setOnClickListener(this);
        ((EditText) findViewById(R.id.editTextDescription)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkT()){
                    ImageView igm=findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                }else{
                    ImageView igm=findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.not_okey_but);
                }
            }
        });
        ((EditText) findViewById(R.id.editTextNameTask)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkT()){
                    ImageView igm=findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                }    else{
                ImageView igm=findViewById(R.id.imageButtonAdd);
                igm.setBackground(null);
                igm.setImageResource(R.drawable.not_okey_but);
            }
            }
        });
        ((EditText) findViewById(R.id.editTextReward)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkT()){
                    ImageView igm=findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                } else{
                    ImageView igm=findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.not_okey_but);
                }
            }
        });
    }

protected void Save(Bundle outState){
        super.onSaveInstanceState(outState);

}
protected void REstore(Bundle save){
        super.onRestoreInstanceState(save);
    Picasso.get().load(simg).resize(350,350).into(upimg);
}



private void undo(){
    Intent intent=new Intent(this ,MainActivity.class);
    startActivity(intent);
}


private void upload(View v) {

    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
    //Тип получаемых объектов - image:
    photoPickerIntent.setType("image/*");
    photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
    //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
    startActivityForResult(photoPickerIntent, Pick_image);
}



//Обрабатываем результат выбора в галерее:
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
    super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

    switch (requestCode) {
        case Pick_image:
            if (resultCode == RESULT_OK) {
                simg=imageReturnedIntent.getData();
                Picasso.get().load(imageReturnedIntent.getData()).resize(350,350).into(upimg);
                flimg=true;
            }
    }
}
public boolean checkT(){
    EditText description = (EditText) findViewById(R.id.editTextDescription);
    EditText namet = (EditText) findViewById(R.id.editTextNameTask);
    EditText reward = (EditText) findViewById(R.id.editTextReward);
    ImageView upimg = (ImageView) findViewById(R.id.imageTask);
        if(description.getText().length()==0 || namet.getText().length()==0 || reward.getText().length()==0){
          //showWarning();
          return false;
        }else{
           return true;
        }
}


    public void showWarning() {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getApplicationContext(),
                "Одно из полей пустое!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

public void Add(View v){
        if(checkT()){
            undo();
        }
}



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                //signIn();
                undo();
                break;
            case R.id.uploadimg:
                upload(v);

                break;
            case R.id.imageButtonAdd:
                Add(v);

                break;
        }
    }



}
