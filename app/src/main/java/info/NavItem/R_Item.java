package info.NavItem;

/**
 * Created by SLR on 11/16/2016.
 */
public class R_Item {
    private String _name;
    private String _time;
    private String _comments;
    private boolean _isvisible=false;
    private String _imgurl;

    public String get_imgurl() {
        return _imgurl;
    }

    public void set_imgurl(String _imgurl) {
        this._imgurl = _imgurl;
    }

    public boolean is_isvisible() {
        return _isvisible;
    }

    public void set_isvisible(boolean _isvisible) {
        this._isvisible = _isvisible;
    }

    public R_Item() {
    }

    public R_Item(String _name, String _time, String _comments, boolean _isvisible, String _imgurl) {
        this._name = _name;
        this._time = _time;
        this._comments = _comments;
        this._isvisible = _isvisible;
        this._imgurl = _imgurl;
    }

    public R_Item(String _name, String _time, String _comments) {
        this._name = _name;
        this._time = _time;
        this._comments = _comments;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_comments() {
        return _comments;
    }

    public void set_comments(String _comments) {
        this._comments = _comments;
    }
}
