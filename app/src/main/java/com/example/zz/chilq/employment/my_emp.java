package com.example.zz.chilq.employment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zz.chilq.MainActivity;
import com.example.zz.chilq.R;
import com.example.zz.chilq.adapter.child_task_list_adapter;

import java.util.ArrayList;

public class my_emp extends Fragment {

    // CardView
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_emp, container,
                false);
        getActivity().setTitle(R.string.fragment_my_tasks_title);

        // CardView
        ArrayList<String> myDataset = getDataSet();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new parent_task_list_adapter(myDataset);
        mAdapter = new child_task_list_adapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        // FAB
        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) view.getContext();
                Class fragmentClass = create_emp.class;
                try {
                    Fragment myFragment = null;
                    myFragment = (Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, myFragment).commit();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        // Hide FAB while scrolling
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return rootView;
    }

    private ArrayList<String> getDataSet() {
        ArrayList<String> mDataSet = new ArrayList();
        for (int i = 0; i < 15; i++) {
            mDataSet.add(i, "Test №" + i);
        }
        return mDataSet;
    }

}
