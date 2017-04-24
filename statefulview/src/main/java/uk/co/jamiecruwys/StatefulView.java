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
 * A {@link ViewFlipper} that shows a single {@link ViewState}'s layout at any given time. You can:
 *
 * <ul>
 *     <li>Call {@link #setViewState(ViewState)} to update the {@link ViewState} and show its accompanying layout.</li>
 *     <li>Get the current {@link ViewState} using {@link #getViewState()}</li>
 *     <li>Manually set a layout for a view state using {@link #setStateLayout(Context, int, ViewState)}. You shouldn't need to do this.</li>
 * </ul>
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
		for (ViewState state : ViewState.values())
		{
			attemptLayoutInflation(context, R.layout.stateful_stub, this, state);
		}
	}

	/**
	 * Try and inflate the view for the given state
	 *
	 * @param context necessary to inflate the layout
	 * @param layout to inflate
	 * @param root view
	 * @param state the layout is for
	 */
	private void attemptLayoutInflation(@NonNull Context context, @LayoutRes int layout, @NonNull ViewGroup root, @NonNull ViewState state)
	{
		resetViewStateLayout(root);

		try
		{
			inflate(context, layout, root);
		}
		catch (Resources.NotFoundException e)
		{
			throw new RuntimeException(StatefulView.class.getSimpleName() + " must have custom attribute " + state.getAttributeName() + " set");
		}
	}

	/**
	 * Resets the layout for this {@link ViewState}'s view stub
	 *
	 * @param viewGroup
	 */
	private void resetViewStateLayout(@NonNull ViewGroup viewGroup)
	{
		if (viewGroup == this)
		{
			// We have been given the StatefulView view instead of the StatefulView view stub
			return;
		}

		viewGroup.removeAllViews();
	}

	/**
	 * Initialises the layouts from any attributes that were set
	 */
	private void initialiseFromAttributes(@NonNull Context context, AttributeSet attrs)
	{
		TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, uk.co.jamiecruwys.statefulview.R.styleable.StatefulView, 0, 0);

		for (ViewState state : ViewState.values())
		{
			int layout = attributes.getResourceId(state.getStyleableAttr(), DEFAULT_LAYOUT_VALUE);
			setStateLayout(context, layout, state);
		}

		attributes.recycle();
	}

	/**
	 * Sets the layout for a given {@link ViewState}
	 *
	 * @param context necessary to inflate the layout
	 * @param layout to inflate
	 * @param state the layout is for
	 */
	public void setStateLayout(@NonNull Context context, @LayoutRes int layout, @NonNull ViewState state)
	{
		if (layout != DEFAULT_LAYOUT_VALUE)
		{
			attemptLayoutInflation(context, layout, getViewStub(state), state);
		}
	}

	/**
	 * Gets the view stub for a given {@link ViewState}
	 *
	 * @param state to get the view stub for
	 * @return {@link ViewGroup} view stub for the given {@link ViewState}
	 */
	private ViewGroup getViewStub(@NonNull ViewState state)
	{
		return (ViewGroup)getChildAt(state.getPosition());
	}

	/**
	 * Sets the new view state to transition to
	 *
	 * @param state which it is transitioning to
	 */
	public void setViewState(@NonNull ViewState state)
	{
		setDisplayedChild(state.getPosition());
	}

	/**
	 * Gets the current {@link ViewState}
	 *
	 * @return current {@link ViewState} or {@link ViewState#ERROR}
	 */
	@NonNull @Override public ViewState getViewState()
	{
		int currentStatePosition = getDisplayedChild();

		for (ViewState state : ViewState.values())
		{
			if (state.getPosition() == currentStatePosition)
			{
				return state;
			}
		}

		return ViewState.ERROR;
	}
}