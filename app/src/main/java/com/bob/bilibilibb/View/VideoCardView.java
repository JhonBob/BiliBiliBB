package com.bob.bilibilibb.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bilibilibb.R;
import com.bob.bilibilibb.utils.VideoUtils;


//封装好的CardView 方便在布局中直接添加和动态改变内容
//属性介绍：
// pic 引用 默认显示图片
// video_title String 标题
// play_count String 播放次数
// comment_count String 评论条数

public class VideoCardView extends CardView {
    private Context mContext;
    private LinearLayout mContainer;
    private ImageView mImage;
    private TextView mTitle;
    private LinearLayout mPlayDetailContainer;
    private TextView mPlayCount;
    private TextView mCommentCount;


    public VideoCardView(Context context) {
        this(context,null);
    }

    public VideoCardView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    @SuppressWarnings("deprecation")
    public VideoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.VideoCardView);
        Drawable drawable = typedArray.getDrawable(R.styleable.VideoCardView_pic);
        String title = typedArray.getString(R.styleable.VideoCardView_video_title);
        String playCount = typedArray.getString(R.styleable.VideoCardView_play_count);
        String commentCount = typedArray.getString(R.styleable.VideoCardView_comment_count);
        typedArray.recycle();

        //初始化容器
        mContainer = new LinearLayout(mContext);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        mContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContainer.setClickable(false); //不可点击 从而state不会变为pressed状态
        addView(mContainer);

        //初始化ImageView 用于展示图片
        mImage = new ImageView(mContext);
        mImage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,110f)); //单位为px 需要转换
        mImage.setScaleType(ImageView.ScaleType.FIT_XY);
        if(drawable!=null) {
            mImage.setImageDrawable(drawable);
        }
        mImage.setClickable(false);
        mContainer.addView(mImage);

        //初始化TextView 用于展示标题
        mTitle = new TextView(mContext);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0,30f);
        titleLp.topMargin = dp2px(5);
        mTitle.setLayoutParams(titleLp);
        mTitle.setEllipsize(TextUtils.TruncateAt.END);
        mTitle.setMaxLines(2);
        mTitle.setTextColor(Color.parseColor("#000000"));
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        mTitle.setGravity(Gravity.CENTER);
        mTitle.setText(title!=null?title:getResources().getString(R.string.vcv_title));
        mTitle.setClickable(false);
        mContainer.addView(mTitle);

        //初始化播放情况容器
        mPlayDetailContainer = new LinearLayout(mContext);
        mPlayDetailContainer.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams detailLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        detailLp.topMargin = dp2px(5);
        detailLp.bottomMargin = dp2px(5);
        mPlayDetailContainer.setLayoutParams(detailLp);
        mPlayDetailContainer.setClickable(false);
        mContainer.addView(mPlayDetailContainer);

        //初始化播放总数情况TextView
        mPlayCount = new TextView(mContext);
        LinearLayout.LayoutParams countLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        countLp.leftMargin = dp2px(5);
        mPlayCount.setLayoutParams(countLp);
        mPlayCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,9); //单位：SP
        Drawable playCountDrawable = getResources().getDrawable(R.mipmap.card_playcount_item_group);
        if(playCountDrawable!=null) {
            int intrinsicWidth = playCountDrawable.getIntrinsicWidth();
            int intrinsicHeight = playCountDrawable.getIntrinsicHeight();
            playCountDrawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        mPlayCount.setCompoundDrawables(playCountDrawable, null, null, null); //调用此方法 需要给Drawable设置边界
        mPlayCount.setCompoundDrawablePadding(dp2px(1));
        mPlayCount.setText(playCount!=null?playCount:getResources().getString(R.string.vcv_play_count));
        mPlayCount.setClickable(false);
        mPlayDetailContainer.addView(mPlayCount);

        //初始化评论总数情况TextView
        mCommentCount = new TextView(mContext);
        LinearLayout.LayoutParams commentLp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        commentLp.leftMargin = dp2px(5);
        mCommentCount.setLayoutParams(commentLp);
        mCommentCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,9); //单位：SP
        Drawable commentCountDrawable = getResources().getDrawable(R.mipmap.card_danmacount_item_group);
        if(commentCountDrawable!=null){
            int intrinsicWidth = commentCountDrawable.getIntrinsicWidth();
            int intrinsicHeight = commentCountDrawable.getIntrinsicHeight();
            commentCountDrawable.setBounds(0,0,intrinsicWidth,intrinsicHeight);
        }
        mCommentCount.setCompoundDrawables(commentCountDrawable,null,null,null);
        mCommentCount.setCompoundDrawablePadding(dp2px(1));
        mCommentCount.setText(commentCount!=null?commentCount:getResources().getString(R.string.vcv_comment_count));
        mCommentCount.setClickable(false);
        mPlayDetailContainer.addView(mCommentCount);
    }

    public ImageView getImageView(){
        return mImage;
    }

    public void setTitle(String title){
        mTitle.setText(title);
    }

    public void setPlayCount(String playCount){
        mPlayCount.setText(VideoUtils.getStringVideoDetailCount(playCount));
    }

    public void setDanmaCount(String commentCount){
        mCommentCount.setText(VideoUtils.getStringVideoDetailCount(commentCount));
    }

    public void setStartPlayActivityListener(final String aid,final String picPath){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PlayVideoActivity.class);
//                intent.putExtra("aid",aid);
//                intent.putExtra("picPath",picPath);
//                mContext.startActivity(intent);
            }
        });
    }

    private int dp2px(int dp){
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp*density + 0.5f);
    }

}
