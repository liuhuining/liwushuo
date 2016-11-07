package com.qf.liuyong.liwushuo.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.qf.liuyong.liwushuo.R;
import com.qf.liuyong.liwushuo.dagger.DaggerAppComponent;
import com.qf.liuyong.liwushuo.presenter.IMainPresenter;
import com.qf.liuyong.liwushuo.ui.adapter.MainAdapter;
import com.qf.liuyong.liwushuo.ui.fragment.BaseFragment;
import com.qf.liuyong.liwushuo.ui.fragment.SelectFragment;
import com.qf.liuyong.liwushuo.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainView{

    @BindView(R.id.main_tab)
    TabLayout tabLayout;
    @BindView(R.id.main_vp)
    ViewPager viewPager;
    @Inject
    IMainPresenter mainPresenter;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private MainAdapter mainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerAppComponent.create().inject(this);
        mainAdapter = new MainAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(mainAdapter);
        mainPresenter.queryMainBean();
        mainPresenter.setView(this);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void refreshAdapter(List<String> beanList,List<Integer> integers) {
        titles.addAll(beanList);
        fragmentList.add(SelectFragment.newInstance(String.valueOf(integers.get(0))));
        for (int i = 1;i<integers.size();i++){
            fragmentList.add(BaseFragment.newInstance(String.valueOf(integers.get(i))));
        }
        mainAdapter.notifyDataSetChanged();
    }
}
