package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import uk.co.jamiecruwys.interfaces.StateChange;
import uk.co.jamiecruwys.interfaces.StateLayouts;
import uk.co.jamiecruwys.interfaces.StateRootLayout;
import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 07/04/2017.
 */
public abstract class StatefulActivity extends AppCompatActivity implements StateLayouts, StateRootLayout, StateChange
{
	/**
	 * Override this if you want to provide your own layout which contains a {@link StatefulView}
	 * @return
	 */
	@LayoutRes public int provideLayout()
	{
		return R.layout.stateful_activity;
	}

	/**
	 * Override this if you are providing your own custom layout. If you are then you need to override this and provide the id of the {@link StatefulView}
	 * @return
	 */
	@IdRes public int provideStatefulViewId()
	{
		return R.id.statefulview;
	}

	protected StatefulView statefulView;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(provideLayout());
		statefulView = (StatefulView)findViewById(provideStatefulViewId());

		statefulView.setContentLayout(this, provideContentLayout());
		statefulView.setEmptyLayout(this, provideEmptyLayout());
		statefulView.setLoadingLayout(this, provideLoadingLayout());
		statefulView.setErrorLayout(this, provideErrorLayout());
	}

	public void setState(@NonNull State state)
	{
		statefulView.setState(state);
	}
}