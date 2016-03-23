package com.bob.bilibilibb.View;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bob.bilibilibb.R;
import com.bob.bilibilibb.app.BaseApplication;

import java.util.ArrayList;


//Created by Kaede on 2016/1/21.
public class ImageViewPager extends FrameLayout {

    private ViewPager mPager;
    private LinearLayout mPointContainer;
    private MyOnPagerChangeListener mPageListener;
    private ArrayList<ImageView> imageList = new ArrayList<>();
    private Context mContext;
    private AutoSwitch mAutoSwitch;

    private int imageSize = 0;
    private int beginPosition = 0;
    private int currPosition = 0;
    private int switchTime = 0;

    private final int DEFAUL_SWITHTIME = 2000;

//    private static Handler mHandler = new Handler();

    public ImageViewPager(Context context) {
        super(context);
    }

    public ImageViewPager(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ImageViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        //初始化ViewPager
        mPager = new ViewPager(context);
        mPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mPager);

        //初始化圆点容器
        mPointContainer = new LinearLayout(context);
        mPointContainer.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM|Gravity.END; //设置layout_gravity 实际上是设置容器的gravity
        lp.rightMargin = 10; //TODO 圆点容器右边距
        lp.bottomMargin = 10; //TODO 圆点容器底部边距
        mPointContainer.setLayoutParams(lp);
        addView(mPointContainer);
    }

    public void setupWithImageResource(int[] res){
        setupWithImageResource(res,DEFAUL_SWITHTIME);
    }

    public void setupWithImageResource(int[] res,int switchTime){
        imageSize = res.length;
        beginPosition = Integer.MAX_VALUE/2 - (Integer.MAX_VALUE/2)%imageSize;
        this.switchTime = switchTime;

        imageList.clear();
        mPointContainer.removeAllViews();
        for(int i=0;i<imageSize;i++){
            //实际图片
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(res[i]);
            imageList.add(iv);

            //圆点
            ImageView point = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 5; //TODO 右边距
            point.setLayoutParams(lp);
            point.setImageResource(R.drawable.pager_point_unselect);
            mPointContainer.addView(point);
        }

        mPager.setAdapter(new ImagePagerAdapter());
        mPager.setCurrentItem(beginPosition);
        currPosition = beginPosition;
        mPageListener = new MyOnPagerChangeListener();
        mPager.addOnPageChangeListener(mPageListener);
        mPager.setOnTouchListener(new MyOnTouchListener());
        if(mAutoSwitch == null) {
            mAutoSwitch = new AutoSwitch();
        }
        mAutoSwitch.start();
    }

    private class MyOnTouchListener implements OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mAutoSwitch.stop();
                    break;
                case MotionEvent.ACTION_UP:
                    mAutoSwitch.start();
                    break;
            }
            return false;
        }
    }

    private class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {//切换圆点图片背景
            position = position%imageSize;
            for(int i=0;i<imageSize;i++){
               ImageView point = (ImageView)mPointContainer.getChildAt(i);
                if(i == position){
                    point.setImageResource(R.drawable.pager_point_select);
                }else {
                    point.setImageResource(R.drawable.pager_point_unselect);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class AutoSwitch implements Runnable{
        @Override
        public void run() {
            currPosition++;
            mPager.setCurrentItem(currPosition);
            BaseApplication.getMainHandler().postDelayed(this,switchTime);//递归调用自己的run方法
        }

        public void start(){
            stop();
            BaseApplication.getMainHandler().postDelayed(this,switchTime);
        }

        public void stop(){
            BaseApplication.getMainHandler().removeCallbacks(this);
        }
    }


    private class ImagePagerAdapter extends PagerAdapter{
        public ImagePagerAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageList.get(position%imageSize));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imageList.get(position%imageSize);
            if(view.getParent()!=null){
                ViewParent parent = view.getParent();
                if(parent instanceof ViewGroup){
                 ((ViewGroup) parent).removeView(view);
                }
            }
            container.addView(view);
            return view;
        }
    }

    private int dp2px(float dp){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        return (int)(dp*density+0.5f);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAutoSwitch.stop();
        mPager.removeOnPageChangeListener(mPageListener);
//        mHandler = null;
    }
}
