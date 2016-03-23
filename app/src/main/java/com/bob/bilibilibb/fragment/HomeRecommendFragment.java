package com.bob.bilibilibb.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.bilibilibb.R;
import com.bob.bilibilibb.adapter.RecommendRecyclerAdapter;
import com.bob.bilibilibb.api.APIService;
import com.bob.bilibilibb.bean.VideoListResult;
import com.bob.bilibilibb.db.VideoDao;
import com.bob.bilibilibb.utils.Constant;
import com.bob.bilibilibb.utils.VideoUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class HomeRecommendFragment extends BaseFragment {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private VideoDao videoDao;
    private static final int PAGE_SIZE = 20;//最多查询20条视频就够了 用来在专区首页展示 并且能够切换

    private int threadCount = Runtime.getRuntime().availableProcessors()+1;
    private RecommendRecyclerAdapter recommendRecyclerAdapter;

    @Override
    protected View initContentView(LayoutInflater inflater, ViewGroup container) {
        View content = inflater.inflate(R.layout.fragment_recommend, container,false);
        refreshLayout= (SwipeRefreshLayout) content.findViewById(R.id.refresh_home_base);
        recyclerView= (RecyclerView) content.findViewById(R.id.recycler_home_base);
        return content;
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommendRecyclerAdapter = new RecommendRecyclerAdapter(getActivity());
        recyclerView.setAdapter(recommendRecyclerAdapter);

        videoDao = new VideoDao(getActivity());
        new Thread(){
            @Override
            public void run() {
                requestJson(PAGE_SIZE);
                super.run();
            }
        }.start();
    }

    @Override
    protected void initListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestJson(PAGE_SIZE);
                recommendRecyclerAdapter.notifyDataSetChanged();
            }
        });
        recommendRecyclerAdapter.notifyDataSetChanged();
    }


    //加载BILIBILI服务端数据
    private Observable<ResponseBody> queryAPi(int typeId, int pageSize) {
        return queryAPi(typeId, 1, pageSize, "hot");
    }

    private Observable<ResponseBody> queryAPi(int typeId, int page, int pageSize, String order) {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(Constant.BASE_URL).build();
        APIService apiService = retrofit.create(APIService.class);
        return apiService.getVideoList(Constant.appkey, typeId + "", page + "", pageSize + "", order);
    }


    //八类数据限制每类20条，滚动再加载
    private Observable<ResponseBody> generateObservable(int pageSize) {
        ArrayList<Observable<ResponseBody>> list = new ArrayList<>();

        list.add(queryAPi(Constant.FAN_JU, pageSize));
        list.add(queryAPi(Constant.DONG_HUA, pageSize));
        list.add(queryAPi(Constant.YIN_YUE, pageSize));
        list.add(queryAPi(Constant.WU_DAO, pageSize));
        list.add(queryAPi(Constant.YOU_XI, pageSize));
        list.add(queryAPi(Constant.KE_JI, pageSize));
        list.add(queryAPi(Constant.YU_LE, pageSize));
        list.add(queryAPi(Constant.GUI_CHU, pageSize));

        return Observable.merge(list);
    }





    private void requestJson(int pageSize){

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(!refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(true);
                }
            }
        });


        final AtomicInteger atom = new AtomicInteger();
        generateObservable(pageSize).groupBy(new Func1<ResponseBody, Integer>() {
            @Override
            public Integer call(ResponseBody body) {
                return atom.getAndIncrement()&threadCount;
            }
        }).flatMap(new Func1<GroupedObservable<Integer, ResponseBody>, Observable<?>>() {
            @Override
            public Observable<?> call(GroupedObservable<Integer, ResponseBody> o) {
                return o.observeOn(Schedulers.from(Executors.newFixedThreadPool(threadCount))).
                        map(new Func1<ResponseBody, VideoListResult>() {
                    @Override
                    public VideoListResult call(ResponseBody body) {
                        VideoListResult videoListResult = null;
                        try {
                            String json = body.string();
                            System.out.println("A"+json);
                            int i = json.lastIndexOf(",");
                            json = json.substring(0,i)+"}}";
                            System.out.println("B"+json);
                            GsonBuilder builder = new GsonBuilder();
                            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                            videoListResult = builder.create().fromJson(json, VideoListResult.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return videoListResult;
                    }
                });
            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("complate");
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                });

                try {
                    throw new RuntimeException(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onNext(Object o) {
                if (o == null) {
                    throw new RuntimeException("videoListResult is null!");
                } else if (o instanceof VideoListResult) {
                    videoDao.insertVideo(
                            (VideoListResult) o,
                            VideoDao.TYPE_MAIN_HOT,
                            VideoUtils.getVideoTypeByName(((VideoListResult) o).getName()));
                }
            }
        });
    }



}
