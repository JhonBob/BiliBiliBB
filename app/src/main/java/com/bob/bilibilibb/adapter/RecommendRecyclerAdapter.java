package com.bob.bilibilibb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bob.bilibilibb.R;
import com.bob.bilibilibb.holder.PagerHolder;
import com.bob.bilibilibb.holder.VideoGroupHolder;
import com.bob.bilibilibb.utils.Constant;

/**
 * Created by Administrator on 2016/3/23.
 */

public class RecommendRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public RecommendRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(Constant.PAGER_VIEW == getItemViewType(position)&&holder instanceof PagerHolder){
            ((PagerHolder) holder).initData(new int[]{R.mipmap.lunbo_01,R.mipmap.lunbo_02,R.mipmap.lunbo_03});
        }else{
            if(holder instanceof VideoGroupHolder) {
                ((VideoGroupHolder) holder).initData();
            }
        }


    }

    @Override
    public int getItemViewType(int position) {

        switch (position){
            case 0:
                return Constant.PAGER_VIEW;
            case 1:
                return Constant.RE_MEN_TUI_JIAN;
            case 2:
                return Constant.FAN_JU;
            case 3:
                return Constant.YIN_YUE;
            case 4:
                return Constant.WU_DAO;
            case 5:
                return Constant.YOU_XI;
            case 6:
                return Constant.KE_JI;
            case 7:
                return Constant.YU_LE;
            case 8:
                return Constant.GUI_CHU;
            default:
                throw new IllegalArgumentException("not an available position");

        }

    }

    @Override
    public int getItemCount() {
        return 9;
    }


    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent,int viewType){
        if(viewType == Constant.PAGER_VIEW) {
            return new PagerHolder(LayoutInflater.from(mContext).inflate(R.layout.item__imagepager, parent, false));
        }else{
            return new VideoGroupHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_recommend_itemgroup,parent,false),viewType);
        }

    }

}
