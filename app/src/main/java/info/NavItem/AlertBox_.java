package info.NavItem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by SLR on 11/23/2016.
 */

public class AlertBox_ {
    private String _title;
    private Context c;

    public AlertBox_(String _title, Context c) {
        this._title = _title;
        this.c = c;

        AlertDialog.Builder alert = new AlertDialog.Builder(
                c);
        alert.setMessage(_title);
        alert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                    }
                });
        alert.show();
    }
}
