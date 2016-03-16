package com.yis.gamemsgapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yis.gamemsgapp.R;
import com.yis.gamemsgapp.bean.NewsSummary;
import com.yis.gamemsgapp.model.loader.BModLoader;
import com.yis.gamemsgapp.utils.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * Created by yinjianhua on 16/3/14.
 */
public class ListFragment extends Fragment {

    private List<NewsSummary> mList = new ArrayList();
    private PullToRefreshListView mListView;
    private View mFootView;
    private MyAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_list,null);

        new TitleBuilder(root).setTitleText("首页");

        mListView = (PullToRefreshListView) root.findViewById(R.id.lv_list);
        mAdapter = new MyAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();
            }
        });

        mListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadData();
            }
        });

        mFootView = inflater.inflate(R.layout.footview_loading,null);

        addFootView(mListView, mFootView);

        return root;
    }

    private void loadData(){

        BModLoader.getInstance().load(getActivity().getApplicationContext(), new FindListener<NewsSummary>() {
            @Override
            public void onSuccess(List<NewsSummary> list) {
                if ((null != mListView)&&(null != list)) {

                    mList.addAll(list);
                    mListView.onRefreshComplete();
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });



    }


    private void addFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if(lv.getFooterViewsCount() == 1) {
            lv.addFooterView(footView);
        }
    }

    private void removeFootView(PullToRefreshListView plv, View footView) {
        ListView lv = plv.getRefreshableView();
        if(lv.getFooterViewsCount() > 1) {
            lv.removeFooterView(footView);
        }
    }


    class MyAdapter extends BaseAdapter{

        private List<NewsSummary> mList;
        private LayoutInflater mInflater;

        public MyAdapter(Context context,List<NewsSummary> list){
            mList = list;
            mInflater =  LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if(null != mList){
                return mList.size();
            }

            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if (view == null || view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
                view = mInflater.inflate(R.layout.item_list, null);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(mList.get(i).getTitle());

            return view;
        }

        class ViewHolder{
            TextView textView;
        }
    }
}
