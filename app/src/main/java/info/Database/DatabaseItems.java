package info.Database;

/**
 * Created by SLR on 8/31/2016.
 */
public class DatabaseItems {
    int _id;
    String _Title;
    String _Url;
    String _Stringcontent;
  public DatabaseItems(){}

    public DatabaseItems(String _Title, String _Stringcontent, String _Url) {
        this._Url = _Url;

        this._Stringcontent = _Stringcontent;
        this._Title = _Title;
    }


    public int get_id() {
        return _id;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_Title() {
        return _Title;
    }

    public void set_Title(String _Title) {
        this._Title = _Title;
    }

    public String get_Url() {
        return _Url;
    }

    public void set_Url(String _Url) {
        this._Url = _Url;
    }

    public String get_Stringcontent() {
        return _Stringcontent;
    }

    public void set_Stringcontent(String _Stringcontent) {
        this._Stringcontent = _Stringcontent;
    }

}
