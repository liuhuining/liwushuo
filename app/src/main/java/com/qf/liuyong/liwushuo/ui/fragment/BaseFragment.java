package com.qf.liuyong.liwushuo.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qf.liuyong.liwushuo.R;
import com.qf.liuyong.liwushuo.dagger.DaggerAppComponent;
import com.qf.liuyong.liwushuo.model.bean.SelectBean;
import com.qf.liuyong.liwushuo.presenter.IBasePresenter;
import com.qf.liuyong.liwushuo.ui.adapter.Base_Adapter;
import com.qf.liuyong.liwushuo.view.IBaseView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements IBaseView{

    private static final String KEY = "content";
    private Context context;
    private List<SelectBean.DataBean.ItemsBean> items = new ArrayList<>();

    @BindView(R.id.base_lv)
    ListView listView;
    @Inject
    IBasePresenter presenter;
    private Base_Adapter base_adapter;

    public BaseFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance(String content){
        BaseFragment baseFragment = new BaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,content);
        baseFragment.setArguments(bundle);
        return baseFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,null);
        DaggerAppComponent.create().inject(this);
        ButterKnife.bind(this,view);
        base_adapter = new Base_Adapter(items,getActivity());
        listView.setAdapter(base_adapter);
        presenter.querySelectedList(Integer.parseInt(getArguments().getString(KEY)),0);
        presenter.setBaseView(this);
        return view;
    }

    @Override
    public void setListData(List<SelectBean.DataBean.ItemsBean> itemsBean) {
        items.addAll(itemsBean);
        base_adapter.notifyDataSetChanged();
    }
}
