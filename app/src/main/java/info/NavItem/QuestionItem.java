package info.NavItem;

/**
 * Created by SLR on 10/11/2016.
 */
public class QuestionItem {

   private String _Title;
  private String _Content;

   private String _Time;
   private String _catg;

    private String _q_id;
    private String _q_image;

    private String _u_name;
    private String _u_image;

    public QuestionItem(String _Title, String _Content, String _Time, String _catg, String _q_id, String _q_image, String _u_name, String _u_image) {
        this._Title = _Title;
        this._Content = _Content;
        this._Time = _Time;
        this._catg = _catg;
        this._q_id = _q_id;
        this._q_image = _q_image;
        this._u_name = _u_name;
        this._u_image = _u_image;
    }

    public String get_Title() {
        return _Title;
    }

    public void set_Title(String _Title) {
        this._Title = _Title;
    }

    public String get_Content() {
        return _Content;
    }

    public void set_Content(String _Content) {
        this._Content = _Content;
    }

    public String get_Time() {
        return _Time;
    }

    public void set_Time(String _Time) {
        this._Time = _Time;
    }

    public String get_catg() {
        return _catg;
    }

    public void set_catg(String _catg) {
        this._catg = _catg;
    }

    public String get_q_id() {
        return _q_id;
    }

    public void set_q_id(String _q_id) {
        this._q_id = _q_id;
    }

    public String get_q_image() {
        return _q_image;
    }

    public void set_q_image(String _q_image) {
        this._q_image = _q_image;
    }

    public String get_u_name() {
        return _u_name;
    }

    public void set_u_name(String _u_name) {
        this._u_name = _u_name;
    }

    public String get_u_image() {
        return _u_image;
    }

    public void set_u_image(String _u_image) {
        this._u_image = _u_image;
    }
}
