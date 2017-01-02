package info.NetWorkCheck;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by SLR on 11/14/2016.
 */
public class NoInternetConn {


    public static  boolean notConnected(Context context)
    {
        boolean valid = true;
        if(NetworkChecker.isConnectedWifi(context)||NetworkChecker.isConnectedMobile(context))
        {
            if(NetworkChecker.isConnected(context))
            {


            }
            else
            {
                valid=false;
                AlertBuilderBox("network error",context);
                //networkerror
            }

        }else{
            valid=false;
            AlertBuilderBox("please enable your network connection",context);
            //please enable your net work connection
        }


return valid;
    }
    private static void AlertBuilderBox(String s, Context c) {
        AlertDialog.Builder showAlert=new AlertDialog.Builder(c);
        showAlert.setTitle(s);

        showAlert.setNegativeButton("ok" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        AlertDialog alertDialog =showAlert.create();
        alertDialog.show();



    }
}
