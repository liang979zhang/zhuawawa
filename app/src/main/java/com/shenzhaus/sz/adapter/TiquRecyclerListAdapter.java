package com.shenzhaus.sz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.common.GlideCircleTransform;
import com.shenzhaus.sz.intf.OnRecyclerViewItemClickListener;
import com.shenzhaus.sz.model.DanmuMessage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TiquRecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private ArrayList<DanmuMessage> mItems;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public TiquRecyclerListAdapter(Context mContext, ArrayList<DanmuMessage> mVideoItems) {
        this.mContext = mContext;
        this.mItems = mVideoItems;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record_tq, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MessageViewHolder temp = (MessageViewHolder) holder;
        DanmuMessage t = mItems.get(position);
        temp.zq_name.setText(t.getUserName());
        temp.zq_result.setText(t.getMessageContent());
        temp.zq_time.setText(t.getUid());
        if ("1".equals(t.getRemoteUid())) {
            temp.mCheckBoxSelect.setChecked(true);
        } else {
            temp.mCheckBoxSelect.setChecked(false);
        }
        Glide.with(mContext).load(t.getAvator())
                .error(R.mipmap.logo)
                .transform(new GlideCircleTransform(mContext))
                .into(temp.zq_avator);
        temp.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewItemClickListener != null) {
                    mOnRecyclerViewItemClickListener.onRecyclerViewItemClick(v, position);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        int count = (mItems == null ? 0 : mItems.size());
        return count;
    }


    class MessageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_danmu_container)
        RelativeLayout mContainer;
        @Bind(R.id.zq_name)
        TextView zq_name;
        @Bind(R.id.zq_result)
        TextView zq_result;
        @Bind(R.id.zq_avator)
        ImageView zq_avator;
        @Bind(R.id.zq_time)
        TextView zq_time;
        @Bind(R.id.checkbox_select)
        CheckBox mCheckBoxSelect;

        public MessageViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }


}
