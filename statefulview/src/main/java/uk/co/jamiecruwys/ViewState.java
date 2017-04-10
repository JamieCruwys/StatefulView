package uk.co.jamiecruwys;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StyleableRes;

import uk.co.jamiecruwys.statefulview.R;

/**
 * The different states a view can be in
 */
public enum ViewState
{
	/**
	 * Loading content
	 */
	LOADING(0, R.styleable.StatefulView_loadingLayout, "loadingLayout"),

	/**
	 * Loaded, but has no content
	 */
	EMPTY(1, R.styleable.StatefulView_emptyLayout, "emptyLayout"),

	/**
	 * Loaded one or more pieces of content
	 */
	LOADED(2, R.styleable.StatefulView_loadedLayout, "loadedLayout"),

	/**
	 * Error while loading content
	 */
	ERROR(3, R.styleable.StatefulView_errorLayout, "errorLayout");

	/**
	 * Position in the order of inflating layouts. Values for this should start at 0 and increase by 1 every time.
	 */
	@IntRange(from = 0) private int position;

	/**
	 * The styleable resource attribute for this view, derived from attrs.xml
	 */
	@StyleableRes private int styleableAttr;

	/**
	 * Name of the styleable resource attribute from attrs.xml
	 */
	@NonNull private String attributeName;

	ViewState(@IntRange(from = 0) int position, @StyleableRes int styleableAttr, @NonNull String attributeName)
	{
		this.position = position;
		this.styleableAttr = styleableAttr;
		this.attributeName = attributeName;
	}

	@IntRange(from = 0) public int getPosition()
	{
		return position;
	}

	@StyleableRes public int getStyleableAttr()
	{
		return styleableAttr;
	}

	@NonNull public String getAttributeName()
	{
		return attributeName;
	}
}