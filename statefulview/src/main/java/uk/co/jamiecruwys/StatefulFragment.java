package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.jamiecruwys.interfaces.ViewStateChange;
import uk.co.jamiecruwys.interfaces.ViewStateLayouts;
import uk.co.jamiecruwys.interfaces.ViewStateRootLayout;
import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 06/04/2017.
 */
public abstract class StatefulFragment extends Fragment implements ViewStateLayouts, ViewStateRootLayout, ViewStateChange
{
	/**
	 * Override this if you want to provide your own layout which contains a {@link StatefulView}
	 * @return
	 */
	@LayoutRes public int provideLayout()
	{
		return R.layout.stateful_fragment;
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

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(provideLayout(), container, false);
		statefulView = (StatefulView)view.findViewById(provideStatefulViewId());

		statefulView.setContentLayout(getContext(), provideContentLayout());
		statefulView.setEmptyLayout(getContext(), provideEmptyLayout());
		statefulView.setLoadingLayout(getContext(), provideLoadingLayout());
		statefulView.setErrorLayout(getContext(), provideErrorLayout());

		return view;
	}

	public void setState(@NonNull State state)
	{
		statefulView.setState(state);
	}
}