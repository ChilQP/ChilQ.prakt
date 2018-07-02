package com.example.zz.chilq.employment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zz.chilq.R;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link create_emp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link create_emp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class create_emp extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public create_emp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment create_emp.
     */
    // TODO: Rename and change types and number of parameters
    public static create_emp newInstance(String param1, String param2) {
        create_emp fragment = new create_emp();
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

    private EditText description ,namet, reward;
    private ImageView upimg;
    private final int Pick_image = 1;
    boolean flimg=false;
    Uri simg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_create_emp, container,
                false);
        getActivity().setTitle("Создать дело");

        upimg=(ImageView)rootView.findViewById(R.id.imageTask);

        EditText description = (EditText)rootView. findViewById(R.id.editTextDescription);
        EditText namet = (EditText)rootView. findViewById(R.id.editTextNameTask);
        EditText reward = (EditText) rootView.findViewById(R.id.editTextReward);
        ImageView upimg = (ImageView) rootView.findViewById(R.id.imageTask);

        rootView.findViewById(R.id.imageButton).setOnClickListener(this);
        rootView. findViewById(R.id.uploadimg).setOnClickListener(this);
        rootView. findViewById(R.id.imageButtonAdd).setOnClickListener(this);
        ((EditText) rootView.findViewById(R.id.editTextDescription)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(true){
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                }else{
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.not_okey_but);
                }
            }
        });
        ((EditText) rootView.findViewById(R.id.editTextNameTask)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(true){
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                }    else{
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.not_okey_but);
                }
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
                if(true){
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.okey_but);
                } else{
                    ImageView igm=rootView.findViewById(R.id.imageButtonAdd);
                    igm.setBackground(null);
                    igm.setImageResource(R.drawable.not_okey_but);
                }
            }
        });




        return  rootView;
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





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                break;
            case R.id.uploadimg:
                upload(v);

                break;
            case R.id.imageButtonAdd:
                break;
        }
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
