package com.shenzhaus.sz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.shenzhaus.sz.R;
import com.shenzhaus.sz.model.Banner;

/**
 * Created by fengjh on 16/7/31.
 */
public class BannerItemViewHolder implements Holder<Banner>{

    private Context mContext;
    private View mRootView;

    @Override
    public View createView(Context context) {
        this.mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.layout_home_banner_item, null);
        return mRootView;
    }

    @Override
    public void UpdateUI(Context context, int position, Banner data) {
        ImageView imageView = (ImageView) mRootView.findViewById(R.id.image_banner);
        if (data != null) {
            String imageUrl = data.getPic();
            if (!StringUtils.isEmpty(imageUrl)) {
                Glide.with(context).load(imageUrl).into(imageView);
            }
        }
    }
}
