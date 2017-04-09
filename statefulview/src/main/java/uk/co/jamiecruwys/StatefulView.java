package uk.co.jamiecruwys;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import uk.co.jamiecruwys.contracts.ViewStateChange;
import uk.co.jamiecruwys.statefulview.R;

/**
 * Removes the need for hiding/showing views when the view's state changes.
 * Call {@link #setState(State)} with the new {@link State} that the view is in and it will change to the relevant layout.
 * <p>
 * You *must* set all of the relevant custom attributes on each {@link StatefulView}, otherwise it will throw an error at runtime.
 * This is so that missing states can be flagged as early as possible.
 */
public class StatefulView extends ViewFlipper implements ViewStateChange
{
	private static final int LOADING_INDEX = 0;
	private static final int CONTENT_INDEX = 1;
	private static final int EMPTY_INDEX = 2;
	private static final int ERROR_INDEX = 3;

	public StatefulView(@NonNull Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, uk.co.jamiecruwys.statefulview.R.styleable.StatefulView, 0, 0);
		int contentLayout, emptyLayout, loadingLayout, errorLayout;

		// Create a view placeholder for each one
		attemptHolderLayoutInflation(context, R.layout.stateful_view_holder, State.LOADING);
		attemptHolderLayoutInflation(context, R.layout.stateful_view_holder, State.SHOWING_CONTENT);
		attemptHolderLayoutInflation(context, R.layout.stateful_view_holder, State.EMPTY);
		attemptHolderLayoutInflation(context, R.layout.stateful_view_holder, State.ERROR);

		try
		{
			loadingLayout = attributes.getResourceId(uk.co.jamiecruwys.statefulview.R.styleable.StatefulView_loadingLayout, 0);
			contentLayout = attributes.getResourceId(uk.co.jamiecruwys.statefulview.R.styleable.StatefulView_contentLayout, 0);
			emptyLayout = attributes.getResourceId(uk.co.jamiecruwys.statefulview.R.styleable.StatefulView_emptyLayout, 0);
			errorLayout = attributes.getResourceId(uk.co.jamiecruwys.statefulview.R.styleable.StatefulView_errorLayout, 0);
		}
		finally
		{
			attributes.recycle();
		}

		if (loadingLayout != 0)
		{
			setLoadingLayout(context, loadingLayout);
		}

		if (contentLayout != 0)
		{
			setContentLayout(context, contentLayout);
		}

		if (emptyLayout != 0)
		{
			setEmptyLayout(context, emptyLayout);
		}

		if (errorLayout != 0)
		{
			setErrorLayout(context, errorLayout);
		}
	}

	public void setContentLayout(@NonNull Context context, @LayoutRes int layout)
	{
		attemptLayoutInflation(context, layout, (ViewGroup)getChildAt(CONTENT_INDEX), State.SHOWING_CONTENT);
	}

	public void setEmptyLayout(@NonNull Context context, @LayoutRes int layout)
	{
		attemptLayoutInflation(context, layout, (ViewGroup)getChildAt(EMPTY_INDEX), State.EMPTY);
	}

	public void setLoadingLayout(@NonNull Context context, @LayoutRes int layout)
	{
		attemptLayoutInflation(context, layout, (ViewGroup)getChildAt(LOADING_INDEX), State.LOADING);
	}

	public void setErrorLayout(@NonNull Context context, @LayoutRes int layout)
	{
		attemptLayoutInflation(context, layout, (ViewGroup)getChildAt(ERROR_INDEX), State.ERROR);
	}

	private void attemptHolderLayoutInflation(@NonNull Context context, @LayoutRes int layoutId, @NonNull State state)
	{
		attemptLayoutInflation(context, layoutId, this, state);
	}

	private void attemptLayoutInflation(@NonNull Context context, @LayoutRes int layoutId, @NonNull ViewGroup root, @NonNull State state)
	{
		// Clear all subviews inside the view holder
		if (root != this)
		{
			root.removeAllViews();
		}

		try
		{
			inflate(context, layoutId, root);
		}
		catch (Resources.NotFoundException e)
		{
			throw new RuntimeException(StatefulView.class.getSimpleName() + " must have custom attribute " + state.getName() + "Layout set");
		}
	}

	/**
	 * Sets the state of the view, which in turn updates the layout to be reflective of the new state
	 *
	 * @param state that the view is now in
	 */
	public void setState(@NonNull State state)
	{
		switch (state)
		{
			case LOADING:
				setDisplayedChild(LOADING_INDEX);
				break;

			case SHOWING_CONTENT:
				setDisplayedChild(CONTENT_INDEX);
				break;

			case EMPTY:
				setDisplayedChild(EMPTY_INDEX);
				break;

			default:
			case ERROR:
				setDisplayedChild(ERROR_INDEX);
				break;
		}
	}
}