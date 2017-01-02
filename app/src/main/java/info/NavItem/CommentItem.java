package info.NavItem;

/**
 * Created by SLR on 10/21/2016.
 */
public class CommentItem {

    private String _u_c_name;
    private String _u_c_comment;
    private String _u_c_image;
    private String _u_c_time;
    private String comment_id;

    public CommentItem(String _u_c_name, String _u_c_comment, String _u_c_image, String _u_c_time, String comment_id) {
        this._u_c_name = _u_c_name;
        this._u_c_comment = _u_c_comment;
        this._u_c_image = _u_c_image;
        this._u_c_time = _u_c_time;
        this.comment_id = comment_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String get_u_c_comment() {
        return _u_c_comment;
    }

    public void set_u_c_comment(String _u_c_comment) {
        this._u_c_comment = _u_c_comment;
    }

    public String get_u_c_image() {
        return _u_c_image;
    }

    public void set_u_c_image(String _u_c_image) {
        this._u_c_image = _u_c_image;
    }

    public String get_u_c_name() {
        return _u_c_name;
    }

    public void set_u_c_name(String _u_c_name) {
        this._u_c_name = _u_c_name;
    }

    public String get_u_c_time() {
        return _u_c_time;
    }

    public void set_u_c_time(String _u_c_time) {
        this._u_c_time = _u_c_time;
    }

    public CommentItem()
    {


    }
}
