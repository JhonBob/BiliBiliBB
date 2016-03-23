package com.bob.bilibilibb.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bob.bilibilibb.R;
import com.bob.bilibilibb.View.ImageViewPager;


public class PagerHolder extends RecyclerView.ViewHolder {
    //轮播控件
    ImageViewPager imageViewPager;

    public PagerHolder(View itemView) {
        super(itemView);
        imageViewPager= (ImageViewPager) itemView.findViewById(R.id.image_pager_recommend);
    }

    public void initData(int[] res){
        imageViewPager.setupWithImageResource(res);
    }
}
