package com.example.statefulview.example.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.statefulview.example.R;

import uk.co.jamiecruwys.ViewState;
import uk.co.jamiecruwys.StatefulView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewExampleActivity extends AppCompatActivity
{
    @BindView(R.id.stateful_view)
    StatefulView statefulView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contentButton) void onShowingContentState()
    {
        statefulView.setViewState(ViewState.SHOWING_CONTENT);
    }

    @OnClick(R.id.emptyButton) void onEmptyState()
    {
        statefulView.setViewState(ViewState.EMPTY);
    }

    @OnClick(R.id.loadingButton) void onLoadingState()
    {
        statefulView.setViewState(ViewState.LOADING);
    }

    @OnClick(R.id.errorButton) void onErrorState()
    {
        statefulView.setViewState(ViewState.ERROR);
    }
}