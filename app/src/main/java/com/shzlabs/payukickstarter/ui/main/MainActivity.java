package com.shzlabs.payukickstarter.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.shzlabs.payukickstarter.R;
import com.shzlabs.payukickstarter.data.model.Kickstarter;
import com.shzlabs.payukickstarter.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView{

    @Inject
    MainPresenter presenter;
    @BindView(R.id.main_recycler_view)
    RecyclerView mainRecyclerList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTheApplication().getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Setup list
        mainRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerList.setAdapter(adapter);

        // Attach presenter
        presenter.attachView(this);

        // Load Items
        presenter.loadItems();


    }

    @Override
    public void displayItems(List<Kickstarter> items) {
        progressBar.setVisibility(View.GONE);
        mainRecyclerList.setVisibility(View.VISIBLE);
        adapter.setItems(items);
    }

    @Override
    public void displayLoadingScreen() {
        mainRecyclerList.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
