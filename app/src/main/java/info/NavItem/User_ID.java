package info.NavItem;

import android.content.Context;

/**
 * Created by SLR on 11/18/2016.
 */
public class User_ID
{

    private String userid;
    Context content;

    public User_ID(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public User_ID(Context content) {
        this.content = content;
    }

    public User_ID() {
    }

    public User_ID(String userid, Context content) {
        this.userid = userid;
        this.content = content;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
