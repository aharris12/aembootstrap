package aem.bootstrap.core.collab;

import com.adobe.cq.social.commons.comments.listing.CommentSocialComponentListProviderManager;
import com.adobe.cq.social.forum.client.api.AbstractPost;
import com.adobe.cq.social.forum.client.api.ForumConfiguration;
import com.adobe.cq.social.forum.client.api.Post;
import com.adobe.cq.social.scf.ClientUtilities;
import com.adobe.cq.social.scf.QueryRequestInfo;
import org.apache.sling.api.resource.Resource;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gechhabr on 6/17/2016.
 */
public class IdeaSocialComponent extends AbstractPost<ForumConfiguration> implements Post<ForumConfiguration>{
    private Tag statusTag;
    private List<Tag> tags;
    /**
     *
     * @param resource
     * @param clientUtilities
     * @param commentSocialComponentListProviderManager
     * @throws RepositoryException
     */
    public IdeaSocialComponent(Resource resource, ClientUtilities clientUtilities, CommentSocialComponentListProviderManager commentSocialComponentListProviderManager) throws RepositoryException {
        super(resource, clientUtilities, commentSocialComponentListProviderManager);
        filterTags();
    }

    /**
     *
     * @param resource
     * @param clientUtilities
     * @param queryRequestInfo
     * @param commentSocialComponentListProviderManager
     * @throws RepositoryException
     */
    public IdeaSocialComponent(Resource resource, ClientUtilities clientUtilities, QueryRequestInfo queryRequestInfo, CommentSocialComponentListProviderManager commentSocialComponentListProviderManager) throws RepositoryException {
        super(resource, clientUtilities, queryRequestInfo, commentSocialComponentListProviderManager);
        filterTags();
    }

    /**
     *
     * @param resource
     * @param clientUtilities
     * @param queryRequestInfo
     * @param resource1
     * @param i
     * @param commentSocialComponentListProviderManager
     * @throws RepositoryException
     */
    public IdeaSocialComponent(Resource resource, ClientUtilities clientUtilities, QueryRequestInfo queryRequestInfo, Resource resource1, int i, CommentSocialComponentListProviderManager commentSocialComponentListProviderManager) throws RepositoryException {
        super(resource, clientUtilities, queryRequestInfo, resource1, i, commentSocialComponentListProviderManager);
        filterTags();
    }

    @Override
    public List<com.adobe.cq.social.commons.comments.api.Comment.Tag> getTags() {
        return this.tags;
    }

    private void filterTags() {
        this.tags = new ArrayList<Tag>();
        for(Tag tag:super.getTags()) {
            if(tag.getTagId().startsWith("acmeideas:")) {
                statusTag = tag;
            } else {
                tags.add(tag);
            }
        }
    }

    public String getStatus() {
        return null == this.statusTag ? null : this.statusTag.getTitle();
    }

    public boolean isNewIdea() {
        if(this.statusTag != null && "acmeideas:new".equals(statusTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isAccepted() {
        if(this.statusTag != null && "acmeideas:accepted".equals(statusTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isReviewed() {
        if(this.statusTag != null && "acmeideas:reviewed".equals(statusTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isUnderReview() {
        if(this.statusTag != null && "acmeideas:under-review".equals(statusTag.getTagId())){
            return true;
        }
        return false;
    }

    public boolean isLaunched() {
        if(this.statusTag != null && "acmeideas:launched".equals(statusTag.getTagId())){
            return true;
        }
        return false;
    }

}
