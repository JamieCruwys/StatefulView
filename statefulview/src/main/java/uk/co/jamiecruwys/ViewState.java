package uk.co.jamiecruwys;

/**
 * The different states a view can be in
 */
public enum ViewState
{
    /**
	 * Loaded one or more pieces of content
     */
    SHOWING_CONTENT("content"),

	/**
	 * Loaded, but has no content
	 */
    EMPTY("empty"),

	/**
	 * Loading content
	 */
    LOADING("loading"),

	/**
	 * Error while loading content
	 */
    ERROR("error");

    private String name;

    ViewState(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}