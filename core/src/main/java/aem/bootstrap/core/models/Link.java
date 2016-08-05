package aem.bootstrap.core.models;

/**
 * Created by gechhabr on 8/5/2016.
 */
public class Link {
    private String linktext;

    private String linkURL;

    Link(String linktext, String linkURL) {
        this.linktext = linktext;
        this.linkURL = linkURL;
    }

    public String getLinktext() {
        return linktext;
    }

    public String getLinkURL() {
        return linkURL;
    }
}
