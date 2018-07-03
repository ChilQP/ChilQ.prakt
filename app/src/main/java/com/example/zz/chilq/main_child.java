package com.example.zz.chilq;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zz.chilq.employment.CreateTask;
import com.example.zz.chilq.employment.list_emp;
import com.example.zz.chilq.employment.my_emp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class main_child extends Fragment implements View.OnClickListener {

    private TextView display_name, display_uid;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_child, container,
                false);

        getActivity().setTitle("Ребенок");

        bundle=this.getArguments();

        display_name=(TextView)rootView.findViewById(R.id.display_name_child);
        display_uid=(TextView)rootView.findViewById(R.id.display_uid_child);

        rootView.findViewById(R.id.list_emp_child).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        display_name.setText(user.getDisplayName());
        display_uid.setText(user.getEmail());

        return rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_emp_child: {
                Class fragmentClass;
                fragmentClass = list_emp.class;
                actToFragment(fragmentClass);
                break;}
        }
    }

    private void actToFragment(Class fragmentClass){
        if(fragmentClass!=null) {
            try {
                Fragment myFragment = null;
                myFragment = (Fragment) fragmentClass.newInstance();
                FragmentManager fragmentManager = getFragmentManager();
                myFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.content_frame, myFragment).commit();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
