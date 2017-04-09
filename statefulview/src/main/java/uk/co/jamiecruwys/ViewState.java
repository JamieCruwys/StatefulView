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
	LOADING(0, R.styleable.StatefulView_loadingLayout, "loading"),

	/**
	 * Loaded, but has no content
	 */
	EMPTY(1, R.styleable.StatefulView_emptyLayout, "empty"),

    /**
	 * Loaded one or more pieces of content
     */
    LOADED(2, R.styleable.StatefulView_loadedLayout, "loaded"),

	/**
	 * Error while loading content
	 */
    ERROR(3, R.styleable.StatefulView_errorLayout, "error");

	@IntRange(from=0) private int position;
	@StyleableRes private int styleableAttr;
    @NonNull private String name;

    ViewState(@IntRange(from=0) int position, @StyleableRes int styleableAttr, @NonNull String name)
    {
		this.position = position;
		this.styleableAttr = styleableAttr;
        this.name = name;
    }

	@IntRange(from=0) public int getPosition()
	{
		return position;
	}

	@StyleableRes public int getStyleableAttr()
	{
		return styleableAttr;
	}

    @NonNull public String getName()
    {
        return name;
    }
}