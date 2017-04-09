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
 * A view that removes the need to show/hide views in order to
 * change between view states such as loading, loaded, empty and error.
 */
public class StatefulView extends ViewFlipper implements ViewStateChange
{
	private static final int DEFAULT_LAYOUT_VALUE = 0;

	public StatefulView(@NonNull Context context, AttributeSet attrs)
	{
		super(context, attrs);

		createViewStubs(context);
		initialiseFromAttributes(context, attrs);
	}

	/**
	 * Creates a view stub for each {@link ViewState}
	 */
	private void createViewStubs(@NonNull Context context)
	{
		for (ViewState viewState : ViewState.values())
		{
			attemptLayoutInflation(context, R.layout.stateful_view_holder, this, viewState);
		}
	}

	/**
	 * Initialises the layouts from any attributes that were set
	 */
	private void initialiseFromAttributes(@NonNull Context context, AttributeSet attrs)
	{
		TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, uk.co.jamiecruwys.statefulview.R.styleable.StatefulView, 0, 0);

		for (ViewState viewState : ViewState.values())
		{
			int layout = attributes.getResourceId(viewState.getStyleableAttr(), DEFAULT_LAYOUT_VALUE);
			setStateLayout(context, layout, viewState);
		}

		attributes.recycle();
	}

	/**
	 * Sets the layout for a given {@link ViewState}
	 * @param context necessary to inflate the layout
	 * @param layout to inflate
	 * @param state the layout is for
	 */
	public void setStateLayout(@NonNull Context context, @LayoutRes int layout, @NonNull ViewState state)
	{
		if (layout != DEFAULT_LAYOUT_VALUE)
		{
			attemptLayoutInflation(context, layout, (ViewGroup)getChildAt(state.getPosition()), state);
		}
	}

	/**
	 * Try and inflate the view for the given state
	 * @param context necessary to inflate the layout
	 * @param layout to inflate
	 * @param root view
	 * @param state the layout is for
	 */
	private void attemptLayoutInflation(@NonNull Context context, @LayoutRes int layout, @NonNull ViewGroup root, @NonNull ViewState state)
	{
		boolean inViewStub = root != this;

		// Clear all of the views for this layout's state. Do nothing if we are inflating the initial stub layouts.
		if (inViewStub)
		{
			root.removeAllViews();
		}

		try
		{
			inflate(context, layout, root);
		}
		catch (Resources.NotFoundException e)
		{
			throw new RuntimeException(StatefulView.class.getSimpleName() + " must have custom attribute " + state.getName() + "Layout set");
		}
	}

	/**
	 * Sets the new view state to transition to
	 * @param state which it is transitioning to
	 */
	public void setViewState(@NonNull ViewState state)
	{
		setDisplayedChild(state.getPosition());
	}
}