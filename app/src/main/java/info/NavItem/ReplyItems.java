package info.NavItem;

/**
 * Created by SLR on 11/14/2016.
 */
public class ReplyItems {

    private String _name;
    private String _comment;
    private String _time;
    private String _urlimg;

    public ReplyItems(String _name, String _comment, String _time) {
        this._comment = _comment;
        this._name = _name;
        this._time = _time;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
