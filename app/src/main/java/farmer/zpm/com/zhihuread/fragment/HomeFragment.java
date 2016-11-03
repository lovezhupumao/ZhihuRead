package farmer.zpm.com.zhihuread.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import farmer.zpm.com.zhihuread.R;
import farmer.zpm.com.zhihuread.adapter.FragmentAdapter;
import farmer.zpm.com.zhihuread.adapter.RecycleViewAdapter;
import farmer.zpm.com.zhihuread.api.Api;
import farmer.zpm.com.zhihuread.entity.News;
import farmer.zpm.com.zhihuread.viewholder.BaseViewHolder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ll_emptyview)
    LinearLayout ll_emptyview;
    private LinearLayoutManager mLayoutManager;
    private RecycleViewAdapter adapter;
    private int begin = 0;
    private boolean isRefreshable = true, isHasHeadView = false, isEmpty = false;

    public static HomeFragment newInstance(Class<? extends BaseViewHolder> vh, String type) {
        Bundle arguments = new Bundle();
        arguments.putString("vh", vh.getCanonicalName());
        arguments.putString("type",type);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    public static HomeFragment newInstance(String vh, String type) {
        Bundle arguments = new Bundle();
        arguments.putString(vh, type);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "HomeFragment-----onCreateView");


        View view = inflater.inflate(R.layout.view_home_fragment, container, false);
        ButterKnife.bind(this,view);
        initView(getActivity());
        return view;
    }
    private void initView(Context context) {
        swiperefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        swiperefresh.setEnabled(isRefreshable);
        swiperefresh.setOnRefreshListener(() -> reFetch());
        recyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        Api.getInstance().service.login().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {
                        Log.i("1111111111","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("--------",e.getMessage());
                    }

                    @Override
                    public void onNext(News news) {
                        Log.i("1111111111",news.getDate());
                        adapter=new RecycleViewAdapter(news,getActivity());
                        recyclerview.setAdapter(adapter);
                    }
                });


    }
    public void reFetch() {
        this.begin = 0;
        swiperefresh.setRefreshing(true);
        fetch();
    }

    public void fetch() {
        begin++;
        if (isEmpty) {
            ll_emptyview.setVisibility(View.GONE);
            swiperefresh.setVisibility(View.VISIBLE);
        }

    }

}
