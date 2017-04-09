package uk.co.jamiecruwys.interfaces;

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
	 * Override this if you are providing your own custom layout. If you are then you need to override this and provide the id of the {@link StatefulView}
	 * @return
	 */
	@IdRes int provideStatefulViewId();
}