package aem.bootstrap.core.collab;

import com.adobe.cq.social.forum.client.api.Post;
import com.adobe.cq.social.forum.client.endpoints.ForumOperationExtension;
import com.adobe.cq.social.scf.Operation;
import com.adobe.cq.social.scf.OperationException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;

import javax.jcr.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import com.adobe.cq.social.commons.client.endpoints.Operation;
//import com.adobe.cq.social.commons.client.endpoints.OperationException;

@Component(name = "Ideation Status Extension", immediate = true, metatype = true)
@Service
public class IdeationStatusExtension implements ForumOperationExtension {


    @Override
    public void afterAction(Operation operation, Session session, Post post, Map<String, Object> map) throws OperationException {

    }

    @Override
    public void beforeAction(Operation op, Session sessionUsed, Resource requestResource, Map<String, Object> props)
        throws OperationException {
        if (ResourceUtil.isA(requestResource, "acme/components/ideation/forum")) {
            List<String> tags = new ArrayList<String>();
            if (props.containsKey("tags")) {
                final Object v = props.get("tags");
                if (!(v instanceof String[])) {
                    if (v instanceof String) {
                        tags.add((String) v);
                    }
                } else {
                    for (String t : (String[]) v) {
                        tags.add(t);
                    }
                }
            }
            tags.add("acmeideas:new");
            props.put("tags", tags.toArray(new String[]{}));
        }
    }

    @Override
    public String getName() {
        return "ideation status";
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public List<ForumOperation> getOperationsToHookInto() {
        return Arrays.asList(ForumOperation.CREATE);
    }

}
