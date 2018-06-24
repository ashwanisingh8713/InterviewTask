package com.interview.task.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.interview.task.R;
import com.interview.task.adapter.TwoColumnAdapter;
import com.interview.task.model.TwoColumnBean;
import com.interview.task.repo.NetworkCall;
import com.interview.task.repo.Response;

import java.util.ArrayList;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public class HomeActivity extends BaseActivity implements Response<ArrayList<TwoColumnBean>> {

    private SearchView mSearchView;
    private ListView mListView;
    private TwoColumnAdapter mAdapter;
    private TextView mEmptyTV;
    private ProgressBar mProgressBar;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = findViewById(R.id.listView);
        mSearchView = findViewById(R.id.searchView);
        mEmptyTV = findViewById(R.id.empty_tv);
        mProgressBar = findViewById(R.id.progressbar);

        mSearchView.setIconified(false);
        mSearchView.onActionViewExpanded();

        mAdapter = new TwoColumnAdapter(this, R.layout.item_row_two_column, new ArrayList<>());
        mListView.setAdapter(mAdapter);

        mListView.setEmptyView(mEmptyTV);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                makeSearchQueryRequest(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }

    /**
     * It makes search query request.
     * @param query
     */
    private void makeSearchQueryRequest(String query) {
        showProgressbar();
        NetworkCall.fetchSearchQuery(this, query);
    }


    @Override
    public void onNext(ArrayList<TwoColumnBean> twoColumnBeans) {
        mAdapter.updateData(twoColumnBeans);
        if(twoColumnBeans.size()==0) {
            mEmptyTV.setText("No result, Please use different bird name.");
        }

        hideProgressbar();
    }

    @Override
    public void onError(Throwable throwable) {
        mEmptyTV.setText("Got error, Please try again.");
        hideProgressbar();
    }

    @Override
    public void onComplete() {

    }

    private void showProgressbar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        mProgressBar.setVisibility(View.GONE);
    }

}
