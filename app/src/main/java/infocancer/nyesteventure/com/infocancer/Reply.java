package infocancer.nyesteventure.com.infocancer;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;

import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.NavAdapter.ReplyArrayAdapter;
import info.NavItem.R_Item;


/**
 * Created by SLR on 11/13/2016.
 */
public class Reply extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{


    private static final String WEBURL = "http://infocancer.nyesteventuretech.com/Service/replycomment.php";
    private static final String TAG ="tag" ;
    private ImageView imageView;
    private TextView _name,_postid,_time,_comment;
    private ListView listView;
    private EditText etx;
    private FloatingActionButton _send;
    private ArrayList<R_Item> _arryalists=new ArrayList<>();
    SwipeRefreshLayout swip;

String name,comment,timerreplay,urlreplay,postid,commentid,sshareid,ssharetype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent reply=getIntent();



         name=reply.getStringExtra("namereply");
        comment=reply.getStringExtra("commentreply");
        timerreplay=reply.getStringExtra("timereply");
        urlreplay=reply.getStringExtra("urlreply");
        postid=reply.getStringExtra("post_id");
        commentid=reply.getStringExtra("commentid");

        MainDataBAse data=new MainDataBAse(Reply.this);
        data.open();
        ssharetype=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_PPP,1);
        sshareid=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_userid,1);
        data.close();
        setContentView(R.layout.replymessage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarkk);
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        _name=(TextView)findViewById(R.id.reply_name);
        _postid=(TextView)findViewById(R.id.reply_postid);
        _time=(TextView)findViewById(R.id.replay_idtime);
        _comment=(TextView)findViewById(R.id.reply_content);
        swip=(SwipeRefreshLayout)findViewById(R.id.comment_refresh_replay);
       swip.setOnRefreshListener(this);
        listView=(ListView)findViewById(R.id.reply_listview);

        imageView=(ImageView)findViewById(R.id.reply_image);
        etx=(EditText) findViewById(R.id.reply_edittext);
        _send=(FloatingActionButton) findViewById(R.id.reply_sendbu);


        _name.setText(name);
        _comment.setText(comment);
       _time.setText(timerreplay);
        _postid.setText(postid);


  picassa(urlreplay);


       final HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("tag","selectallreply");
        hashMap.put("allcomment_id",commentid);
        //Log.e("user_replay_id",commentid);

     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             Post_sendComment(hashMap);
         }
    },400);


        _send.setOnClickListener(new Click());








    }

    private void sendMessage() {
        if (this.etx.length() == 0) {
            return;
        }
        ((InputMethodManager)getApplicationContext().getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.etx.getWindowToken(), 0);
        String str = this.etx.getText().toString();

        if(str!=null)
        {
            _send.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#490101")));
         final HashMap<String,String> hashmap=new HashMap();
            hashmap.put("tag","reply");
            hashmap.put("message",str);
            hashmap.put("comment_id",commentid);
            hashmap.put("post_Id",postid);
            hashmap.put("user_id",sshareid);
            hashmap.put("type",ssharetype);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Post_sendComment(hashmap);
                }
            },400);




        }





    }

    private void Post_sendComment(HashMap<String,String> hashmap) {
         //Log.e("sharetype",str +" "+postid);
swip.setRefreshing(true);
_arryalists.clear();
        PostResponseAsyncTask task=new PostResponseAsyncTask(Reply.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

                try {
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("replyuser");
                    int jleng=jsonArray.length();

                    Log.e("total",""+jleng);

                    for(int ik=0;ik<jsonArray.length();ik++)
                    {
                        JSONObject c=jsonArray.getJSONObject(ik);

                        String _type=c.getString("type");
                     String _name = null,_comment = null,_time = null,_url;
                   if(_type.contains("userlogin"))
                        {
                          _comment=c.getString("reply");
                          _time=c.getString("time");
                           _name=c.getString("username");
                            _arryalists.add(new R_Item(_name,_time,_comment));
                        }
                        else
                        if(_type.contains("doctorlogin"))
                        {
                         _comment=c.getString("reply");
                            _time=c.getString("time");
                            _url=c.getString("doctorphoto");
                           _name=c.getString("doctorname");

                            _arryalists.add(new R_Item(_name,_time,_comment,true,_url));
 //
                        }




                    }
                    swip.setRefreshing(false);

   ReplyArrayAdapter adapter=new ReplyArrayAdapter(Reply.this,_arryalists);
                    listView.setAdapter(adapter);
                    etx.setText("");
                    _send.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#04109b")));




                } catch (JSONException e) {
                    e.printStackTrace();
                    swip.setRefreshing(false);
                }


            }
        });
        task.execute(WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                swip.setRefreshing(false);
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                swip.setRefreshing(false);
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                swip.setRefreshing(false);
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                swip.setRefreshing(false);
            }
        });


    }

    private void picassa(String h) {
        try {

            Picasso picasso=new Picasso.Builder(Reply.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                }
            }).build();




            picasso.load(h)
                    .placeholder(R.drawable.save_doctor1)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG,"sucseess");
                        }

                        @Override
                        public void onError() {
                            Log.d(TAG,"error");
                        }
                    });
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void Post_Send(String commnetid) {
        HashMap<String,String> hashmap=new HashMap();
        hashmap.put("tag","");
     //   hashmap.put("postid",postid);
        hashmap.put("commentid",commnetid);

        PostResponseAsyncTask task=new PostResponseAsyncTask(Reply.this, hashmap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {




            }
        });
        task.execute(WEBURL);
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

    }

    @Override
    public void onRefresh() {
        final HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("tag","selectallreply");
        hashMap.put("allcomment_id",commentid);
       // Log.e("user_replay_id",commentid);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Post_sendComment(hashMap);
            }
        },400);

    }

    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            sendMessage();

        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
       // Log.d("BACK_BUTTON_DOESNT_WORK", "I will never execute and you will never see me :(");
        super.onBackPressed();
        this.finish();

    }
}
