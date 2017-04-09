package uk.co.jamiecruwys;

/**
 * The different states a view can be in
 */
public enum ViewState
{
	/**
	 * Loading content
	 */
	LOADING("loading"),

	/**
	 * Loaded, but has no content
	 */
	EMPTY("empty"),

    /**
	 * Loaded one or more pieces of content
     */
    LOADED("loaded"),

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