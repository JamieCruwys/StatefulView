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
	LOADING(R.styleable.StatefulView_loadingLayout, "loadingLayout"),

	/**
	 * Loaded, but has no content
	 */
	EMPTY(R.styleable.StatefulView_emptyLayout, "emptyLayout"),

	/**
	 * Loaded one or more pieces of content
	 */
	LOADED(R.styleable.StatefulView_loadedLayout, "loadedLayout"),

	/**
	 * Error while loading content
	 */
	ERROR(R.styleable.StatefulView_errorLayout, "errorLayout");

	/**
	 * The styleable resource attribute for this view, derived from attrs.xml
	 */
	@StyleableRes private int styleableAttr;

	/**
	 * Name of the styleable resource attribute from attrs.xml
	 */
	@NonNull private String attributeName;

	ViewState(@StyleableRes int styleableAttr, @NonNull String attributeName)
	{
		this.styleableAttr = styleableAttr;
		this.attributeName = attributeName;
	}

	@IntRange(from = 0) public int getPosition()
	{
		return ordinal();
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