package com.example.statefulview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Removes the need for hiding/showing views when the view's state changes.
 * Call {@link #setState(State)} with the new {@link State} that the view is in and it will change to the relevant layout.
 *
 * You *must* set all of the relevant custom attributes on each {@link StatefulView}, otherwise it will throw an error at runtime.
 * This is so that missing states can be flagged as early as possible.
 */
public class StatefulView extends ViewFlipper
{
    public StatefulView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StatefulView, 0, 0);
        int contentLayout, emptyLayout, loadingLayout, errorLayout, offlineLayout;

        try
        {
            contentLayout = attributes.getResourceId(R.styleable.StatefulView_contentLayout, 0);
            emptyLayout = attributes.getResourceId(R.styleable.StatefulView_emptyLayout, 0);
            loadingLayout = attributes.getResourceId(R.styleable.StatefulView_loadingLayout, 0);
            errorLayout = attributes.getResourceId(R.styleable.StatefulView_errorLayout, 0);
            offlineLayout = attributes.getResourceId(R.styleable.StatefulView_offlineLayout, 0);
        }
        finally
        {
            attributes.recycle();
        }

        attemptLayoutInflation(context, contentLayout, State.SHOWING_CONTENT);
        attemptLayoutInflation(context, emptyLayout, State.EMPTY);
        attemptLayoutInflation(context, loadingLayout, State.LOADING);
        attemptLayoutInflation(context, errorLayout, State.ERROR);
        attemptLayoutInflation(context, offlineLayout, State.OFFLINE);
    }

    private void attemptLayoutInflation(Context context, int layoutId, @NonNull State state)
    {
        try
        {
            inflate(context, layoutId, this);
        }
        catch (Resources.NotFoundException e)
        {
            String resourceEntryName = context.getResources().getResourceEntryName(getId());
            throw new RuntimeException(StatefulView.class.getSimpleName() + " with id " + resourceEntryName + ", must have custom attribute " + state.getName() + "Layout set");
        }
    }

    /**
     * Sets the state of the view, which in turn updates the layout to be reflective of the new state
     * @param state that the view is now in
     */
    public void setState(@NonNull State state)
    {
        switch (state)
        {
            case SHOWING_CONTENT:
                setDisplayedChild(0);
                break;

            case EMPTY:
                setDisplayedChild(1);
                break;

            case LOADING:
                setDisplayedChild(2);
                break;

            default:
            case ERROR:
                setDisplayedChild(3);
                break;

            case OFFLINE:
                setDisplayedChild(4);
                break;
        }
    }
}