package info.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;

/**
 * Created by SLR on 12/1/2016.
 */

public class BackgroundService extends Service {
    private String _userid;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Timer mTimer;

    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
         PostCheck();

            // StringNotification();

        }
    };

    private void PostCheck() {
        MainDataBAse data = new MainDataBAse(getApplication());
        String PPP = data.getNameUser(DATABASE_C.TABLE_NAME.T_USER, DATABASE_C.COLUMN_NAME_USER.U_PPP, 1);
        String userid = data.getNameUser(DATABASE_C.TABLE_NAME.T_USER, DATABASE_C.COLUMN_NAME_USER.U_userid, 1);
        data.close();

        if (userid!=null) {
            try {
                HashMap<String, String> hashmap = new HashMap<>();
                hashmap.put("tag", "service");
                hashmap.put("type", PPP);
                hashmap.put("user_id", userid);

                PostResponseAsyncTask task = new PostResponseAsyncTask(getApplicationContext(), hashmap, new AsyncResponse() {
                    @Override
                    public void processFinish(String s)
                    {


                    }
                });
                task.setEachExceptionsHandler(new EachExceptionsHandler() {
                    @Override
                    public void handleIOException(IOException e) {

                    }

                    @Override
                    public void handleMalformedURLException(MalformedURLException e) {

                    }

                    @Override
                    public void handleProtocolException(ProtocolException e) {

                    }

                    @Override
                    public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {

                    }
                });


            } catch (Exception e) {
            }
            ;
        }

    }








    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        mTimer.schedule(timerTask, 2000, 10 * 1000);



    }

}
