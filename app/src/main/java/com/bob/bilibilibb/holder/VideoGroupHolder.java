package com.bob.bilibilibb.holder;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.bilibilibb.R;
import com.bob.bilibilibb.View.VideoCardView;
import com.bob.bilibilibb.bean.VideoDetail;
import com.bob.bilibilibb.db.VideoDao;
import com.bob.bilibilibb.utils.Constant;
import com.bob.bilibilibb.utils.SizeUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class VideoGroupHolder extends RecyclerView.ViewHolder {
    RelativeLayout titleContainer;

    private Context mContext;
    private int mCategory;

    private TextView titleText;
    private TextView enterText;
    private VideoCardView card1;
    private VideoCardView card2;
    private VideoCardView card3;
    private VideoCardView card4;

    private ArrayList<VideoDetail> videoDetails = new ArrayList<>();

    public VideoGroupHolder(Context context,View itemView,int category) {
        super(itemView);
        mContext = context;
        mCategory = category;
        titleContainer= (RelativeLayout) itemView.findViewById(R.id.container_title_item_group);
        titleText= (TextView) itemView.findViewById(R.id.title_item_group);
        enterText= (TextView) itemView.findViewById(R.id.enter_image_item_group);
        card1= (VideoCardView) itemView.findViewById(R.id.card1_item_group);
        card2= (VideoCardView) itemView.findViewById(R.id.card2_item_group);
        card3= (VideoCardView) itemView.findViewById(R.id.card3_item_group);
        card4= (VideoCardView) itemView.findViewById(R.id.card4_item_group);
    }

    public void initData(){
        switch (mCategory){
            case Constant.RE_MEN_TUI_JIAN:
                titleText.setText("热门推荐");
                titleText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.hot_item_group, 0, 0, 0);
                enterText.setText("排行榜");
                        enterText.setBackgroundResource(R.drawable.item_group_right_more);
                enterText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.rank_item_group,0,0,0);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext, "设计中...",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.FAN_JU:
                titleText.setText("番剧推荐");
                titleText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fanju_item_group,0,0,0);
                enterText.setText("更多");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, FanJuActivity.class);
//                        mContext.startActivity(intent);
                    }
                });
                initCardItem();
                break;
            case Constant.DONG_HUA:
                titleText.setText("动画区");
                Rect rect1 = new Rect(0,0, SizeUtils.dp2px(mContext, 24),SizeUtils.dp2px(mContext,24));
                Drawable drawable1 = mContext.getResources().getDrawable(R.mipmap.ic_category_t1);
                if(drawable1!=null) {
                    drawable1.setBounds(rect1);
                    titleText.setCompoundDrawables(drawable1, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.YIN_YUE:
                titleText.setText("音乐区");
                Rect rect2 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable2 = mContext.getResources().getDrawable(R.mipmap.ic_category_t3);
                if(drawable2!=null) {
                    drawable2.setBounds(rect2);
                    titleText.setCompoundDrawables(drawable2, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.WU_DAO:
                titleText.setText("舞蹈区");
                Rect rect3 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable3 = mContext.getResources().getDrawable(R.mipmap.ic_category_t129);
                if(drawable3!=null) {
                    drawable3.setBounds(rect3);
                    titleText.setCompoundDrawables(drawable3, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.YOU_XI:
                titleText.setText("游戏区");
                Rect rect4 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable4 = mContext.getResources().getDrawable(R.mipmap.ic_category_t4);
                if(drawable4!=null) {
                    drawable4.setBounds(rect4);
                    titleText.setCompoundDrawables(drawable4, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.KE_JI:
                titleText.setText("科技区");
                Rect rect5 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable5 = mContext.getResources().getDrawable(R.mipmap.ic_category_t36);
                if(drawable5!=null) {
                    drawable5.setBounds(rect5);
                    titleText.setCompoundDrawables(drawable5, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.YU_LE:
                titleText.setText("娱乐区");
                Rect rect6 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable6 = mContext.getResources().getDrawable(R.mipmap.ic_category_t5);
                if(drawable6!=null) {
                    drawable6.setBounds(rect6);
                    titleText.setCompoundDrawables(drawable6, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.GUI_CHU:
                titleText.setText("鬼畜区");
                Rect rect7 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable7 = mContext.getResources().getDrawable(R.mipmap.ic_category_t119);
                if(drawable7!=null) {
                    drawable7.setBounds(rect7);
                    titleText.setCompoundDrawables(drawable7, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.DIAN_YING:
                titleText.setText("电影区");
                Rect rect8 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable8 = mContext.getResources().getDrawable(R.mipmap.ic_category_t23);
                if(drawable8!=null) {
                    drawable8.setBounds(rect8);
                    titleText.setCompoundDrawables(drawable8, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;
            case Constant.DIAN_SHI_JU:
                titleText.setText("电视剧");
                Rect rect9 = new Rect(0,0,SizeUtils.dp2px(mContext,24),SizeUtils.dp2px(mContext,24));
                Drawable drawable9 = mContext.getResources().getDrawable(R.mipmap.ic_category_t11);
                if(drawable9!=null) {
                    drawable9.setBounds(rect9);
                    titleText.setCompoundDrawables(drawable9, null, null, null);
                }
                enterText.setText("进去看看");
                enterText.setBackgroundResource(R.drawable.item_group_right_more_mask);
                titleContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                        Toast.makeText(mContext,"完善中",Toast.LENGTH_SHORT).show();
                    }
                });
                initCardItem();
                break;

        }
        enterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCardItem();
                showCardItem();
            }
        });
    }


    //初始化卡
    public void initCardItem(){
        if(videoDetails.isEmpty()){
            changeCardItem();
            showCardItem();
        }else {
            showCardItem();
        }
    }

    //显示卡条目
    private void showCardItem(){
        if(videoDetails.size()>=4) {
            VideoDetail detail1 = this.videoDetails.get(0);
            card1.setTitle(detail1.getTitle());
            card1.setPlayCount(detail1.getPlay());
            card1.setDanmaCount(detail1.getVideoReview());
            card1.setStartPlayActivityListener(detail1.getAid(), detail1.getPic());
            if (detail1.getPic() != null && !"".equals(detail1.getPic())) {
                Picasso.with(mContext).load(detail1.getPic()).into(card1.getImageView());
            }
            VideoDetail detail2 = this.videoDetails.get(1);
            card2.setTitle(detail2.getTitle());
            card2.setPlayCount(detail2.getPlay());
            card2.setDanmaCount(detail2.getVideoReview());
            card2.setStartPlayActivityListener(detail2.getAid(), detail2.getPic());
            if (detail2.getPic() != null && !"".equals(detail2.getPic())) {
                Picasso.with(mContext).load(detail2.getPic()).into(card2.getImageView());
            }
            VideoDetail detail3 = this.videoDetails.get(2);
            card3.setTitle(detail3.getTitle());
            card3.setPlayCount(detail3.getPlay());
            card3.setDanmaCount(detail3.getVideoReview());
            card3.setStartPlayActivityListener(detail3.getAid(), detail3.getPic());
            if (detail3.getPic() != null && !"".equals(detail3.getPic())) {
                Picasso.with(mContext).load(detail3.getPic()).into(card3.getImageView());
            }
            VideoDetail detail4 = this.videoDetails.get(3);
            card4.setTitle(detail4.getTitle());
            card4.setPlayCount(detail4.getPlay());
            card4.setDanmaCount(detail4.getVideoReview());
            card4.setStartPlayActivityListener(detail4.getAid(), detail4.getPic());
            if (detail4.getPic() != null && !"".equals(detail4.getPic())) {
                Picasso.with(mContext).load(detail4.getPic()).into(card4.getImageView());
            }
        }
    }

    public void changeCardItem(){
        VideoDao dao = new VideoDao(mContext);
        videoDetails.clear();
        ArrayList<VideoDetail> videos1 = dao.queryVideoByCategory(VideoDao.TYPE_MAIN_HOT, mCategory, VideoDao.FLAG_RANDOM);
        this.videoDetails.addAll(videos1);
    }

}
