package infocancer.nyesteventure.com.infocancer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Holder.DataCheckHolder;
import info.Holder.DataHolderClass;
import info.HttpRequest.PostResponseAsyncTk;
import info.NavItem.AlertBox_;
import info.NavItem.AlertBox_Inner;
import info.NavItem.AlertBox_outer;

import static info.NetWorkCheck.NetworkChecker.isConnected;
import static info.NetWorkCheck.NetworkChecker.isConnectedMobile;
import static info.NetWorkCheck.NetworkChecker.isConnectedWifi;


public class SplashScreen extends AppCompatActivity {

 List<String> arrylisList;
    private TextView text;
   private Snackbar snackbar;
    ObjectAnimator animation;
   private ProgressBar progressBar;
   private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spashscreen);
// set the drawable as progress drawable
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .cordinated1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
      animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration (3000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());


      /*  coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkpass();
            }
        });*/
        text=(TextView)findViewById(R.id.info_splashcv) ;
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/georgia.ttf");
        text.setTypeface(face);

        arrylisList=new ArrayList<>();

        checkpass();


    }

    private void checkpass() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(isConnectedWifi(SplashScreen.this)||isConnectedMobile(SplashScreen.this))
                {
                    if(isConnected(SplashScreen.this)) {

                        check_password();
                    }
                    else
                    {
                        snakbar();
                    }

                }else
                {
                    snakbar();
                }
            }
        },400) ;
    }
    private void exit_f() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }

    private void check_password(){
        MainDataBAse data=new MainDataBAse(SplashScreen.this);
        data.open();
        boolean check=  data.check(DATABASE_C.TABLE_NAME.T_USER);
        if(check==false) {


         String PPP = data.getNameUser(DATABASE_C.TABLE_NAME.T_USER, DATABASE_C.COLUMN_NAME_USER.U_PPP, 1);
            String userpass = data.getNameUser(DATABASE_C.TABLE_NAME.T_USER, DATABASE_C.COLUMN_NAME_USER.U_pass, 1);
            String useremail = data.getNameUser(DATABASE_C.TABLE_NAME.T_USER, DATABASE_C.COLUMN_NAME_USER.U_email, 1);
            data.close();
            Post_Send(PPP,useremail,userpass);



        }
        else {
            post_catg();

        }

    }

    private void snakbar() {

         snackbar = Snackbar
                .make(coordinatorLayout,"No internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                        checkpass();

                    }
                });
       /* snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                checkpass();

            }
        });*/


        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    private void post_catg() {
        animation.start ();

     arrylisList.clear();
        HashMap<String ,String> hashmap=new HashMap<>();
        hashmap.put("tag","userchoice");
        PostResponseAsyncTk task=new PostResponseAsyncTk(SplashScreen.this, hashmap, false,new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                try{

                    JSONObject qrjson =new JSONObject(s);
                    if(qrjson.getInt("succ")==1)
                    {
                    JSONArray jasonJsonArray=qrjson.getJSONArray("cat");

                    for (int i =0; i<jasonJsonArray.length(); i++) {
                        JSONObject c = jasonJsonArray.getJSONObject(i);
                            String u_string= c.getString("category");
                            arrylisList.add(u_string);


                        }
                        DataHolderClass.getInstance().setDistributor_id(arrylisList);
                        loginActivity();

                    }

                        else
                        if(qrjson.getInt("succ")==303){
                            snakbar();
                        }


                }
                catch (Exception e)
                {
                    snakbar();
                }


                //  DataHolderClass.getInstance().setDistributor_id(arrayList);

            }
        });
        task.execute("http://infocancer.nyesteventuretech.com/Service/check.php");
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                snakbar();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                new AlertBox_outer(SplashScreen.this);
                snakbar();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                snakbar();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                snakbar();
            }
        });
    }


    private void Post_Send(String ppp, String useremail, String userpass) {
        animation.start ();
        arrylisList.clear();
        HashMap<String ,String> hashmap=new HashMap<>();
        hashmap.put("tag","check");
        hashmap.put("usertype",ppp);
        hashmap.put("email",useremail);
        hashmap.put("password",userpass);



        PostResponseAsyncTk task=new PostResponseAsyncTk(SplashScreen.this, hashmap,false, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

                   try{
                       JSONObject qrjson =new JSONObject(s);
                       if(qrjson.getInt("succ")==1)
                       {
                            JSONArray jasonJsonArray=qrjson.getJSONArray("cat");
                            for (int i =0; i<jasonJsonArray.length(); i++) {
                                JSONObject c = jasonJsonArray.getJSONObject(i);
                                    String u_string= c.getString("category");
                                    arrylisList.add(u_string);
                            }
                           DataHolderClass.getInstance().setDistributor_id(arrylisList);
                           DataCheckHolder.getInstance().setDistributor_id(true);
                           loginActivity();
                    }else
                           if(qrjson.getInt("succ")==303)
                           {
                              loginActivity();
                           }
                   }
                   catch (JSONException e) {

                     snakbar();
                   }

                   catch (Exception e)
                   {
                       snakbar();

                   }
              //  DataHolderClass.getInstance().setDistributor_id(arrayList);

            }
        });
        task.execute("http://infocancer.nyesteventuretech.com/Service/check.php");
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                snakbar();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                snakbar();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                snakbar();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                snakbar();
            }
        });
    }

    private void loginActivity() {
        progressBar.clearAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },100);
    }


}
