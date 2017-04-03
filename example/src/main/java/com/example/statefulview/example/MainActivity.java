package com.example.statefulview.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.co.jamiecruwys.State;
import uk.co.jamiecruwys.StatefulView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.stateful_view)
    StatefulView statefulView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contentButton) void onShowingContentState()
    {
        statefulView.setState(State.SHOWING_CONTENT);
    }

    @OnClick(R.id.emptyButton) void onEmptyState()
    {
        statefulView.setState(State.EMPTY);
    }

    @OnClick(R.id.loadingButton) void onLoadingState()
    {
        statefulView.setState(State.LOADING);
    }

    @OnClick(R.id.errorButton) void onErrorState()
    {
        statefulView.setState(State.ERROR);
    }

    @OnClick(R.id.offlineButton) void onOfflineState()
    {
        statefulView.setState(State.OFFLINE);
    }
}