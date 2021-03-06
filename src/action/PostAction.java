package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.*;
import util.Pager;
import vo.*;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

@Controller("postAction")
@Scope("prototype")
public class PostAction extends ActionSupport {
    private int postid;
    @Autowired
    private PostService postService;
    @Autowired
    private SubForumService subForumService;
    @Autowired
    private FollowpostService followpostService;
    @Autowired
    private UserService userService;
    private Post post;
    private int subforumid;
    private SubForum subForum;
    private List followposts;
    private int page=1;
    private String order="asc";
    private Collection collection;
    private String searchKeyWord;
    private List posts;
    private String search_order="desc";
    private String search_info;

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public SubForum getSubForum() {
        return subForum;
    }

    public void setSubForum(SubForum subForum) {
        this.subForum = subForum;
    }

    public SubForumService getSubForumService() {
        return subForumService;
    }

    public void setSubForumService(SubForumService subForumService) {
        this.subForumService = subForumService;
    }

    public FollowpostService getFollowpostService() {
        return followpostService;
    }

    public void setFollowpostService(FollowpostService followpostService) {
        this.followpostService = followpostService;
    }

    public List getFollowposts() {
        return followposts;
    }

    public void setFollowposts(List followposts) {
        this.followposts = followposts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getSubforumid() {
        return subforumid;
    }

    public void setSubforumid(int subforumid) {
        this.subforumid = subforumid;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }

    public String getSearch_order() {
        return search_order;
    }

    public void setSearch_order(String search_order) {
        this.search_order = search_order;
    }

    public String getSearch_info() {
        return search_info;
    }

    public void setSearch_info(String search_info) {
        this.search_info = search_info;
    }

    public String browserPost() throws Exception {
        if(postid==0)
            return ERROR;
        post=postService.getPostById(postid);
        int totalFollowpostsNum=followpostService.getFollowpostsNumByPostId(postid);

        //读取web.xml获取ShowFollowpostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowFollowpostsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowFollowpostsPerPageNum"));

        followposts=followpostService.getFollowpostsByPostId(postid,page, ShowFollowpostsPerPageNum,order);
        Map request=(Map)ActionContext.getContext().get("request");

        //读取web.xml获取ShowPostsPerPageNum参数
        final int ShowPostsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowPostsPerPageNum"));

        Pager pager=new Pager(page,ShowFollowpostsPerPageNum,totalFollowpostsNum);
        request.put("pager",pager);

        Map session=ActionContext.getContext().getSession();
        User user=(User) session.get("user");
        if(user!=null)
            collection=userService.getCollection(user.getId(),postid);
        return SUCCESS;
    }

    public String getAddPostPageNeedUserLogin() throws Exception
    {
        if(subforumid==0)
            return ERROR;
        subForum=subForumService.getSubForumById(subforumid);
        return SUCCESS;
    }

    public String commitAddPostNeedUserLogin() throws Exception
    {
        if(post==null||post.getTitle().equals("")||post.getContent().equals(""))
            return ERROR;
        post.setSubForum(subForumService.getSubForumById(subforumid));
        Map session=ActionContext.getContext().getSession();
        post.setUser((User)session.get("user"));
        postService.createPost(post);
        return SUCCESS;
    }

    public String getUpdatePostPageNeedUserLogin() throws Exception
    {
        post=postService.getPostById(postid);
        return SUCCESS;
    }

    public String commitUpdatePostNeedUserLogin() throws Exception
    {
        postService.updatePost(post);
        post=postService.getPostById(post.getId());
        return SUCCESS;
    }

    public String commitDeletePostNeedUserLogin() throws Exception
    {
        post=postService.getPostById(postid);
        postService.deletePost(post);
        return SUCCESS;
    }

    public String searchPosts() throws Exception
    {
        //读取web.xml获取ShowPostsPerPageNum参数
        ServletContext servletContext =ServletActionContext.getServletContext();
        final int ShowSearchResultsPerPageNum=Integer.valueOf(servletContext.getInitParameter("ShowSearchResultsPerPageNum"));
        if(searchKeyWord.equals(""))
        {
            search_info="请输入关键字";
            return SUCCESS;
        }
        Map request=(Map)ActionContext.getContext().get("request");
        posts=postService.getSearchResults(searchKeyWord,page,ShowSearchResultsPerPageNum,search_order);
        Pager pager=new Pager(page,ShowSearchResultsPerPageNum,postService.getSearchResultNum(searchKeyWord,search_order));
        request.put("pager",pager);
        return SUCCESS;
    }
}
