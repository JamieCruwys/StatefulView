package uk.co.jamiecruwys.contracts;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import uk.co.jamiecruwys.StatefulView;

/**
 * Contract for providing a root layout that a {@link StatefulView} is a child view in
 */
public interface ViewStateRootLayout
{
	/**
	 * Provide the layout resource for this fragment/activity, that contains a {@link StatefulView}
	 * @return layout resource for this fragment/activity
	 */
	@LayoutRes int provideLayout();

	/**
	 * Provide the layout id of the {@link StatefulView} in the layout resource provided by {@link #provideLayout()}
	 * @return layout id of the {@link StatefulView} in the layout resource provided by {@link #provideLayout()}
	 */
	@IdRes int provideStatefulViewId();
}