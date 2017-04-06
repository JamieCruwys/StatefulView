package uk.co.jamiecruwys;

/**
 * Enum for common states that the view can be in
 */
public enum State
{
    /**
     * Showing one or more pieces of content
     */
    SHOWING_CONTENT("content"),

    /**
     * No content to show
     */
    EMPTY("empty"),

    /**
     * Waiting for data to be retrieved
     */
    LOADING("loading"),

    /**
     * Failed to retrieve content
     */
    ERROR("error");

    private String name;

    State(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}