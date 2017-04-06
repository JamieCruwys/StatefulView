package uk.co.jamiecruwys;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.jamiecruwys.statefulview.R;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 06/04/2017.
 */
public abstract class StatefulFragment extends Fragment
{
	@LayoutRes protected abstract int provideContentLayout();

	@LayoutRes protected abstract int provideEmptyLayout();

	@LayoutRes protected abstract int provideLoadingLayout();

	@LayoutRes protected abstract int provideErrorLayout();

	protected StatefulView statefulView;

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		statefulView = (StatefulView)inflater.inflate(R.layout.main_stateful_fragment, container, false);

		statefulView.setContentLayout(getContext(), provideContentLayout());
		statefulView.setEmptyLayout(getContext(), provideEmptyLayout());
		statefulView.setLoadingLayout(getContext(), provideLoadingLayout());
		statefulView.setErrorLayout(getContext(), provideErrorLayout());

		return statefulView;
	}

	public void setState(@NonNull State state)
	{
		statefulView.setState(state);
	}
}