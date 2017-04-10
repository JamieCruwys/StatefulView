package uk.co.jamiecruwys.contracts;

import android.support.annotation.NonNull;

import uk.co.jamiecruwys.StatefulAdapter;

/**
 * Created by Jamie Cruwys of 3 SIDED CUBE on 10/04/2017.
 */
public interface ViewStateDataObserver
{
	void registerStatefulDataObserver(@NonNull StatefulAdapter adapter);

	void unregisterStatefulDataObserver(@NonNull StatefulAdapter adapter);
}