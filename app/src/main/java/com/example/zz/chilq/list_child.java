package com.example.zz.chilq;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.zz.chilq.adapter.child_list_adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marozzi.roundbutton.RoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link list_child.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link list_child#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list_child extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public list_child() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list_child.
     */
    // TODO: Rename and change types and number of parameters
    public static list_child newInstance(String param1, String param2) {
        list_child fragment = new list_child();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private RoundButton btn;

    private DatabaseReference myRef;
    private ChildEventListener mChildEventListener;
    FirebaseAuth mAuth;
    FirebaseUser user;

    private EditText idChild;
    private List<String> stringList= new ArrayList<>();
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
        childListAdapter= new child_list_adapter(stringList);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(childListAdapter);

        btn = (RoundButton) rootView.findViewById(R.id.bt);
        idChild=(EditText)rootView.findViewById(R.id.id_child);

        btn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        myRef = FirebaseDatabase.getInstance().getReference().child("Parents").child(user.getUid()).child("Child");

        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    stringList.add((String) dataSnapshot.getValue());
                    childListAdapter.notifyDataSetChanged();
                    btn.revertAnimation();
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



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                btn.startAnimation();
                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.child("Parents").child(user.getUid()).child("Child").push().setValue(idChild.getText().toString());
                myRef.child("Child").child(idChild.getText().toString()).child("Parent").push().setValue(user.getUid());
                break;
        }
    }

    @Override
    public void  onDestroy(){
        if(mChildEventListener!=null){
            myRef.removeEventListener(mChildEventListener);
            mChildEventListener=null;
        }
        super.onDestroy();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
