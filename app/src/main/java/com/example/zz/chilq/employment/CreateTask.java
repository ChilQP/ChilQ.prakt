package com.example.zz.chilq.employment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zz.chilq.MainActivity;
import com.example.zz.chilq.R;
import com.example.zz.chilq.model.task_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class CreateTask extends Fragment implements View.OnClickListener {

    private ImageView upimg;
    private final int Pick_image = 1;
    boolean flimg=false;
    Uri simg;
    private Bundle bundle;

    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private EditText description, namet, reward;

    private long incr=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_task, container,
                false);

        getActivity().setTitle("Создание задания:");

        bundle=this.getArguments();

        incrementCounter();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Tasks");

        description = (EditText) rootView.findViewById(R.id.editTextDescription);
        namet = (EditText) rootView.findViewById(R.id.editTextNameTask);
        reward = (EditText)rootView.findViewById(R.id.editTextReward);

        upimg= rootView.findViewById(R.id.imageButton4);
        rootView.findViewById(R.id.imageButton).setOnClickListener(this);
        rootView.findViewById(R.id.button).setOnClickListener(this);
        rootView.findViewById(R.id.imageButtonAdd).setOnClickListener(this);
        ((EditText) rootView.findViewById(R.id.editTextDescription)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chbutton();
            }
        });
        ((EditText)rootView.findViewById(R.id.editTextNameTask)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chbutton();
            }
        });
        ((EditText) rootView.findViewById(R.id.editTextReward)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                chbutton();
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton:
                //signIn();
                undo();
                break;
            case R.id.button:
                upload();

                break;
            case R.id.imageButtonAdd:
                Add();
                break;
        }
    }


    private void chbutton(){
        if(checkT()){
            ImageView igm=getView().findViewById(R.id.imageButtonAdd);
            igm.setBackground(null);
            igm.setImageResource(R.drawable.okey_but);
        }else{
            ImageView igm=getView().findViewById(R.id.imageButtonAdd);
            igm.setBackground(null);
            igm.setImageResource(R.drawable.not_okey_but);
        }
    }


    private void undo(){
        Intent intent=new Intent(getActivity() ,MainActivity.class);
        intent.putExtra("role", bundle.getString("role"));
        startActivity(intent);
    }


    private void upload() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        //Тип получаемых объектов - image:
        photoPickerIntent.setType("image/*");
        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
        startActivityForResult(photoPickerIntent, Pick_image);
    }



    //Обрабатываем результат выбора в галерее:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
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

        if(description.getText().length()==0 || namet.getText().length()==0 || reward.getText().length()==0){
            //showWarning();
            return false;
        }else{
            return true;
        }
    }


    public void showWarning() {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                "Одно из полей пустое!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void Add(){
        if(checkT()){
            sentTask();
            undo();
        }else{
            showWarning();
        }
    }

    public void sentTask(){
        task_model taskModel = new task_model();
        taskModel.setName_task(namet.getText().toString());
        taskModel.setDate_complete("");
        taskModel.setReward(reward.getText().toString());
        taskModel.setDesc_task(description.getText().toString());
        taskModel.setTask_compl(0);
        taskModel.setName_child("da");
        taskModel.setName_parent(user.getUid());
        myRef.child(String.valueOf(incr)).setValue(taskModel);
    }

    public void incrementCounter() {
        DatabaseReference incrRef = FirebaseDatabase.getInstance().getReference().child("incr");
        incrRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(final MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(1);
                } else {
                    incr = (long) currentData.getValue();
                    currentData.setValue((Long) currentData.getValue() + 1);
                }

                return Transaction.success(currentData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                if (databaseError!= null) {
                    Log.d("","Firebase counter increment failed.");
                } else {
                    Log.d("","Firebase counter increment succeeded.");
                }
            }
        });
    }


}
