package com.example.zz.chilq.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zz.chilq.R;

public class parent_task_recycler_view_holder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTitleView;
    public TextView mDescriptionView;
    public TextView mRewardTextView;
    public ImageButton mEditButton;
    public ImageButton mRemoveButton;

    public parent_task_recycler_view_holder(View view) {
        super(view);
        mImageView = (ImageView) view.findViewById(R.id.card_view_image);
        mTitleView = (TextView) view.findViewById(R.id.card_view_title);
        mDescriptionView = (TextView) view.findViewById(R.id.card_view_description);
        mRewardTextView = (TextView) view.findViewById(R.id.card_view_reward);
        mEditButton = view.findViewById(R.id.card_view_button_edit);
        mRemoveButton = view.findViewById(R.id.card_view_button_remove);
    }
}
