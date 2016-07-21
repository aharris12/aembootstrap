package aem.bootstrap.core.collab;

import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.scf.ClientUtilities;
import com.adobe.cq.social.scf.QueryRequestInfo;
import com.adobe.cq.social.scf.SocialComponent;
import com.adobe.cq.social.scf.SocialComponentFactory;
import com.adobe.cq.social.scf.core.AbstractSocialComponentFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import javax.jcr.RepositoryException;

/**
 * Created by gechhabr on 6/17/2016.
 */
@Component(name = "Custom Idea Social Component Factory")
@Service
public class IdeaSocialComponentFactory extends AbstractSocialComponentFactory implements SocialComponentFactory {

    @Reference
    private CommentSocialComponentListProviderManager commentListProviderManager;


    @Override
    public SocialComponent getSocialComponent(Resource resource) {
        try {
            return new IdeaSocialComponent(resource,this.getClientUtilities(resource.getResourceResolver()),commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(Resource resource, SlingHttpServletRequest slingHttpServletRequest) {
        try {
            return new IdeaSocialComponent(resource,this.getClientUtilities(slingHttpServletRequest),this.getQueryRequestInfo(slingHttpServletRequest),commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public SocialComponent getSocialComponent(Resource resource, ClientUtilities clientUtilities, QueryRequestInfo queryRequestInfo) {
        try {
            return new IdeaSocialComponent(resource,clientUtilities,queryRequestInfo,commentListProviderManager);
        } catch (RepositoryException e) {
            return null;
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public String getSupportedResourceType() {
        return "AEMBootstrap/components/ideation/topic";
    }
}
