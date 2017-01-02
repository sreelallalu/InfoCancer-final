package info.Database;

/**
 * Created by SLR on 10/26/2016.
 */
public class UserDbItem {
   private String _name;
    private String _email;
    private String _pass;
    private String _userid;
    private String _phone;
    private String _age;
    private String _url;

    public String get_userid() {
        return _userid;
    }

    public void set_userid(String _userid) {
        this._userid = _userid;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_age() {
        return _age;
    }

    public void set_age(String _age) {
        this._age = _age;
    }

    public UserDbItem(String _name, String _email, String _pass, String _userid, String _phone, String _age, String _url) {
        this._name = _name;
        this._email = _email;
        this._pass = _pass;
        this._userid = _userid;
        this._phone = _phone;
        this._age = _age;
        this._url = _url;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {

        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String _pass) {
        this._pass = _pass;
    }





}
