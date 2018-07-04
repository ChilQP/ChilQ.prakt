package com.example.zz.chilq;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.zz.chilq.employment.CreateTask;
import com.example.zz.chilq.employment.list_emp;
import com.example.zz.chilq.employment.my_emp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle mToggle;
    private Intent myIntent;
    Bundle bundle=new Bundle();

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("");


        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout , R.string.openDrwm,R.string.closeDrwm);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        nvDrawer=(NavigationView)findViewById(R.id.nav_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon((getResources().getDrawable(R.drawable.ic_menu)));
        setSupportActionBar(toolbar);

        myIntent = getIntent();
        Class fragmentClass = null;
        bundle.putString("role",(myIntent.getStringExtra("role")));



        if(myIntent.getStringExtra("role").equals("parent")) {
            fragmentClass = main_parent.class;
            nvDrawer.getMenu().clear();
            nvDrawer.inflateMenu(R.menu.nav_menu);
        }
        else {
            nvDrawer.getMenu().clear();
            nvDrawer.inflateMenu(R.menu.nav_menu_child);
            fragmentClass = main_child.class;
        }

        actToFragment(fragmentClass, false);

        setupDrawerContent(nvDrawer);
    }

    private void selectItemDrawer(MenuItem item){


        Class fragmentClass = null;

        switch (item.getItemId()) {
            case R.id.nav_my_emp:
                if(myIntent.getStringExtra("role").equals("parent"))
                    fragmentClass=my_emp.class;
                else
                    fragmentClass=list_emp.class;
                break;
            case R.id.nav_create_emp:
                fragmentClass=CreateTask.class;
                break;
            case R.id.nav_list_child:
                fragmentClass=list_child.class;
                break;
            case R.id.nav_signout:
                signOut();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_text_exit_title);
                String message = getResources().getString(R.string.dialog_text_exit_message);
                builder.setMessage(message)
                        .setPositiveButton(R.string.dialog_text_exit_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(MainActivity.this ,Auth.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.dialog_text_exit_no, null);
                builder.create().show();
                break;
            case R.id.nav_main_menu:
                if(myIntent.getStringExtra("role").equals("parent"))
                    fragmentClass=main_parent.class;
                else
                    fragmentClass=main_child.class;
                break;
        }
        if(fragmentClass!=null)
            actToFragment(fragmentClass, true);

        mDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigateionView){
        navigateionView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);

        // instanceof is very slow, replace to something fastest
        if(fragment instanceof main_child || fragment instanceof main_parent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.dialog_text_exit_title);
            String message = getResources().getString(R.string.dialog_text_exit_message);
            builder.setMessage(message)
                    .setPositiveButton(R.string.dialog_text_exit_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(MainActivity.this ,Auth.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.dialog_text_exit_no, null);
            builder.create().show();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * Transition from a fragment to a fragment
     *
     * @param fragmentClass - class where to make the transition
     * @param addToBackStack - remember from which fragment the transition occurred
     */
    private void actToFragment(Class fragmentClass, boolean addToBackStack){
        if(fragmentClass!=null) {
            try {
                Fragment myFragment = null;
                myFragment = (Fragment) fragmentClass.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                myFragment.setArguments(bundle);

                FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.content_frame, myFragment);
                if(addToBackStack) {
                    transaction = transaction.addToBackStack(null);
                }
                transaction.commit();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }

        return super.dispatchTouchEvent(event);
    }

    private void signOut() {
        // Firebase sign out
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

}

