package com.shenzhaus.sz.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by fengjh on 16/7/31.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.top = space;
        outRect.bottom = 0;
        outRect.right = 0;
        if(parent.getChildLayoutPosition(view)%2 == 0){
            outRect.right = space;
            outRect.top = space;
            outRect.bottom = 0;
            outRect.left = space;
        }else{
            outRect.left = space;
            outRect.top = space;
            outRect.bottom = 0;
            outRect.right = 0;
        }
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = 0;
            outRect.bottom = 0;
            outRect.right = 0;
            outRect.top = 0;
        }
    }
}