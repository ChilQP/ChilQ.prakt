package com.example.zz.chilq;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.zz.chilq.adapter.child_list_adapter;
import com.example.zz.chilq.model.user_model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.marozzi.roundbutton.RoundButton;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class list_child extends Fragment implements View.OnClickListener {


    private RoundButton btn;

    private long incr=0;

    private DatabaseReference myRef;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private EditText idChild;
    private List<user_model> user_modelList= new ArrayList<>();
    private RecyclerView recyclerView;
    private child_list_adapter childListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_child, container,
                false);

        getActivity().setTitle("Мои дети");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.child_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        if(!user_modelList.isEmpty()) {
            user_modelList.clear();
        }
        childListAdapter= new child_list_adapter(user_modelList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(childListAdapter);

        btn = (RoundButton) rootView.findViewById(R.id.bt);
        idChild=(EditText)rootView.findViewById(R.id.id_child);

        btn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference().child("Parents").child(user.getUid()).child("Child");
        incrementCounter();

        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    user_model userModel= null;
                    userModel = dataSnapshot.getValue(user_model.class);
                    user_modelList.add(userModel);
                    childListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Не смогли установить onDisconnect:" + databaseError.getMessage());

                }
            };
        }

        myRef.addChildEventListener(mChildEventListener);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Child").orderByChild(user.getUid()).equalTo("fg");

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });



        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                if(!idChild.getText().toString().isEmpty()) {
                    myRef = FirebaseDatabase.getInstance().getReference();
                    incrementCounter();
                    user_model userModel = new user_model(incr, idChild.getText().toString());
                    myRef.child("Parents").child(user.getUid()).child("Child").child(userModel.getS_uid()).setValue(userModel);
                    userModel.setS_uid(user.getUid());
                    myRef.child("Child").child(idChild.getText().toString()).child("Parent").child(userModel.getS_uid()).setValue(userModel);

                }

                break;
        }
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

    @Override
    public void  onDestroy(){
        if(mChildEventListener!=null){
            myRef.removeEventListener(mChildEventListener);
            mChildEventListener=null;
        }
        super.onDestroy();
    }
}
