package com.yalantis.pulltomakesoup.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yalantis.pulltomakesoup.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 28.01.2016.
 */
public class MainActivity extends AppCompatActivity {

    private PullToRefreshView mPullToRefreshView;
    private static final int REFRESH_DELAY = 4000;
    private static final String KEY_ICON = "icon";
    private static final String KEY_COLOR = "color";

    private List<Map<String, Integer>> mSampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, Integer> map;
        mSampleList = new ArrayList<>();

        int[] icons = {
                R.drawable.burger,
                R.drawable.pizza};

        int[] colors = {
                R.drawable.rounded_background_burger,
                R.drawable.rounded_background_pizza,
                R.drawable.rounded_background_burger,
                R.drawable.rounded_background_pizza};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            mSampleList.add(map);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new SampleAdapter());

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });
    }


    private class SampleAdapter extends RecyclerView.Adapter<SampleHolder> {

        @Override
        public SampleHolder onCreateViewHolder(ViewGroup parent, int pos) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new SampleHolder(view);
        }

        @Override
        public void onBindViewHolder(SampleHolder holder, int pos) {
            Map<String, Integer> data = mSampleList.get(pos);
            holder.bindData(data);
        }

        @Override
        public int getItemCount() {
            return mSampleList.size();
        }
    }

    private class SampleHolder extends RecyclerView.ViewHolder {

        private final View mRootView;
        private final ImageView mImageViewIcon;

        private Map<String, Integer> mData;

        public SampleHolder(View itemView) {
            super(itemView);

            mRootView = itemView;
            mImageViewIcon = (ImageView) itemView.findViewById(R.id.image_view_icon);
        }

        public void bindData(Map<String, Integer> data) {
            mData = data;

            mRootView.setBackgroundResource(mData.get(KEY_COLOR));
            mImageViewIcon.setImageResource(mData.get(KEY_ICON));
        }
    }
}
