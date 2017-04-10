package uk.co.jamiecruwys.contracts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Contract for returning data to the listing
 */
public interface ListingData<ITEM_TYPE>
{
	/**
	 * Successfully retrieved content
	 *
	 * @param items that were retrieved
	 */
	void onListingDataRetrieved(@NonNull List<ITEM_TYPE> items);

	/**
	 * Failed to retrieve content
	 *
	 * @param throwable error
	 */
	void onListingDataError(@Nullable Throwable throwable);
}