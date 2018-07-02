package com.example.zz.chilq;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class main_child extends Fragment {

    private TextView display_name, display_uid;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_child, container,
                false);

        getActivity().setTitle("Ребенок");

        display_name=(TextView)rootView.findViewById(R.id.display_name_child);
        display_uid=(TextView)rootView.findViewById(R.id.display_uid_child);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        display_name.setText(user.getDisplayName());
        display_uid.setText(user.getUid());

        return rootView;
    }


}
