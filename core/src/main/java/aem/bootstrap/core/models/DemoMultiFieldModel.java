package aem.bootstrap.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

//import org.json.JSONObject;

/**
 * Created by gechhabr on 7/25/2016.
 */
@Model(adaptables = Resource.class)
public class DemoMultiFieldModel {

    Logger logger = LoggerFactory.getLogger(DemoMultiFieldModel.class);

    @Inject
    @Named("links")
    private String[] links;

    private List<Link> list;

    @PostConstruct
    protected void init() throws JSONException {
        logger.info("links" + links);
        this.list = new ArrayList<Link>();
        for (String linkString : links) {
            JSONObject jsonObject = new JSONObject(linkString);
            logger.info("json object is " + jsonObject);
            list.add(new Link(jsonObject.getString("linktext"), jsonObject.getString("linkURL")));
        }
        logger.info("linkList is" + list);
    }

    public List<Link> getList() {
        return list;
    }
}
