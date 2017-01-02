package info.NavItem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by SLR on 11/23/2016.
 */

public class AlertBox_Inner {
    Context c;

    public AlertBox_Inner(Context c) {
        this.c = c;
		
		
        AlertDialog.Builder alert = new AlertDialog.Builder(
                c);
        alert.setMessage("network error");
        alert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                    }
                });
        alert.show();
    }
}
