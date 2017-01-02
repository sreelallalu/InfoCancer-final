package infocancer.nyesteventure.com.infocancer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import info.Constant.CONSTANTS;
import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.NavAdapter.CommentAdapter;
import info.NavItem.CommentItem;


/**
 * Created by SLR on 11/9/2016.
 */
public class ViewQuestion_Doctor extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "tag";
    private TextView q_title,q_content,q_time,ur_name,q_catg;
    private EditText comment_box;
    private ImageView user_image;
    private FloatingActionButton comment_button;
    private SwipeRefreshLayout swipeview;
    private ListView listView;
    private View comment;
    private String _userid;

    private String PPop,PPP;
    private ArrayList<CommentItem> arrayList=new ArrayList<>();
    TextView u_name,u_time,u_comment;


    private String q_id;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent loop=getIntent();

        String time=loop.getStringExtra(CONSTANTS.intent_send.Intent_TIME);
        String content=loop.getStringExtra(CONSTANTS.intent_send.Intent_CONTENT);
        String title=loop.getStringExtra(CONSTANTS.intent_send.Intent_TITLE);
        String catg=loop.getStringExtra(CONSTANTS.intent_send.Intent_CAT);

        q_id=loop.getStringExtra(CONSTANTS.intent_send.Intent_Q_ID);
        String q_image=loop.getStringExtra(CONSTANTS.intent_send.Intent_Q_IMAGE);

        String u_name=loop.getStringExtra(CONSTANTS.intent_send.Intent_U_NAME);
        String u_image=loop.getStringExtra(CONSTANTS.intent_send.Intent_U_IMAGE);
        setContentView(R.layout.viewquestion2);
        find_ids();
        MainDataBAse data=new MainDataBAse(ViewQuestion_Doctor.this);
        data.open();

        _userid=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_userid,1);
        viewquestion(time,content,title,catg,q_image,u_image,u_name);
      /*  View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);*/

    }
    private void find_ids() {

        q_title=(TextView)findViewById(R.id.id_question_view_sub);
        q_content=(TextView)findViewById(R.id.id_question_view_content);
        q_time=(TextView)findViewById(R.id.id_question_view_time);
        q_catg=(TextView)findViewById(R.id.q_category);
        user_image=(ImageView)findViewById(R.id.u_propic_view);
        ur_name=(TextView)findViewById(R.id.u_user_name);

        comment=(View)findViewById(R.id.id_comment_linear_button);

        swipeview=(SwipeRefreshLayout) findViewById(R.id.comment_refresh_opwe);
        swipeview.setOnRefreshListener(this);
        listView=(ListView)findViewById(R.id.xlistview_id);

        comment_box=(EditText)findViewById(R.id.id_question_comment_edittext);
        comment_button=(FloatingActionButton) findViewById(R.id.comment_button_id);
//hideSoftKeyboard();
       /* MainDataBAse maindata=new MainDataBAse(ViewQuestion_Doctor.this);
        maindata.open();
        maindata.*/


        /*View view = ViewQuestion_Doctor.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }*/
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ViewQuestion_Doctor.this.comment_box.length() == 0) {
                    return;
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(ViewQuestion_Doctor.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ViewQuestion_Doctor.this.comment_box.getWindowToken(), 0);




                String com_string=comment_box.getText().toString();
                if(com_string!=null)
                {
                PostCOmment(com_string);
                    comment_box.setText("");

                }
            }
        });
        comment_butt();
        listView.setOnScrollListener(new SCrollL());


    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void PostCOmment(String com_string) {

        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("tag","cmtinsert");
        hashmap.put("comment",com_string);
        hashmap.put("post_id1",q_id);
        hashmap.put("doctor_id",_userid);




        PostResponseAsyncTask task=new PostResponseAsyncTask(ViewQuestion_Doctor.this, hashmap, new AsyncResponse()
        {
            @Override
            public void processFinish(String s) {
                try {
                    JSONObject qrjson =new JSONObject(s);


                    JSONArray jasonJsonArray=qrjson.getJSONArray(CONSTANTS.comment_list.Comment_responce);
                    if(jasonJsonArray==null)
                    {
                        swipeview.setRefreshing(false);
                    }arrayList.clear();


                    for (int i =0; i<jasonJsonArray.length(); i++)
                    {
                        JSONObject c = jasonJsonArray.getJSONObject(i);
                        String comment = c.getString(CONSTANTS.comment_list.ID_C);
                        String name = c.getString(CONSTANTS.comment_list.ID_N);

                        String time = c.getString(CONSTANTS.comment_list.ID_T);
                        String u_image=c.getString(CONSTANTS.comment_list.ID_IMG);
                        String coomentid=c.getString(CONSTANTS.comment_list.ID_Cid);

                        arrayList.add(new CommentItem(name,comment,u_image,time,coomentid));
                        //Log.e("time",subject);
                    }



                    CommentAdapter adaptero=new CommentAdapter(getApplicationContext(), R.layout.view_question_list,arrayList);


                    listView.setAdapter(adaptero);
                    //adaptero.notifyAll();
                    adaptero.notifyDataSetChanged();
                    listView.invalidateViews();
                    swipeview.setRefreshing(false);
                    listView.setOnItemClickListener(new ItemCommentClick());









                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
        task.execute(CONSTANTS.comment_list.WEBURL);
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

    private void comment_butt() {
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment_listview();
            }
        });}

    private void viewquestion(String time, String content, String title, String catg, String q_image, String u_image, String u_name) {

        q_title.setText(title);
        q_content.setText(content);

        q_time.setText(time);
        q_catg.setText(catg);

        ur_name.setText(u_name);
        user_image.setImageResource(R.drawable.save_user1);



    }

  /*  private void picass0_setuserpic(String h) {

        try {
            Picasso picasso=new Picasso.Builder(ViewQuestion_Doctor.this).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                }
            }).build();



            picasso.load(h).placeholder(getResources().getDrawable(R.drawable.babx)).into(user_image, new Callback() {
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

    }*/

    private void comment_listview() {

        PostAsyn(q_id);

    }
    private void PostAsyn(String q_id) {
        arrayList.clear();
        listView.refreshDrawableState();

        listView.invalidateViews();

        swipeview.setRefreshing(true);
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("tag",CONSTANTS.comment_list.TAG_COMMENT);
        hashmap.put(CONSTANTS.comment_list.Comment_TAg_id,q_id);



        PostResponseAsyncTask task=new PostResponseAsyncTask(ViewQuestion_Doctor.this, hashmap, new AsyncResponse()
        {
            @Override
            public void processFinish(String s) {
                try {
                    JSONObject qrjson =new JSONObject(s);


                    JSONArray jasonJsonArray=qrjson.getJSONArray(CONSTANTS.comment_list.Comment_responce);
                    if(jasonJsonArray==null)
                    {
                        swipeview.setRefreshing(false);
                    }


                    for (int i =0; i<jasonJsonArray.length(); i++)
                    {
                        JSONObject c = jasonJsonArray.getJSONObject(i);
                        String comment = c.getString(CONSTANTS.comment_list.ID_C);
                        String name = c.getString(CONSTANTS.comment_list.ID_N);

                        String time = c.getString(CONSTANTS.comment_list.ID_T);
                        String u_image=c.getString(CONSTANTS.comment_list.ID_IMG);
                        String coomentid=c.getString(CONSTANTS.comment_list.ID_Cid);

                        arrayList.add(new CommentItem(name,comment,u_image,time,coomentid));
                        //Log.e("time",subject);
                    }



                    CommentAdapter adaptero=new CommentAdapter(getApplicationContext(), R.layout.view_question_list,arrayList);


                    listView.setAdapter(adaptero);
                    //adaptero.notifyAll();
                    adaptero.notifyDataSetChanged();
                    listView.invalidateViews();
                    swipeview.setRefreshing(false);
                    listView.setOnItemClickListener(new ItemCommentClick());
                } catch (JSONException e) {
                    e.printStackTrace();
                    swipeview.setRefreshing(false);
                }
            }
        });
        task.execute(CONSTANTS.comment_list.WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                swipeview.setRefreshing(false);
            }
            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                swipeview.setRefreshing(false);
            }
            @Override
            public void handleProtocolException(ProtocolException e) {
                swipeview.setRefreshing(false);
            }
            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                swipeview.setRefreshing(false);
            }
        });


    }


    @Override
    public void onRefresh() {

        PostAsyn(q_id);
    }
    private class ItemCommentClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String name=arrayList.get(position).get_u_c_name();
            String comment=arrayList.get(position).get_u_c_comment();
            String time=arrayList.get(position).get_u_c_time();
            String url_o=arrayList.get(position).get_u_c_image();
            String commentid=arrayList.get(position).getComment_id();
            Intent reply=new Intent(ViewQuestion_Doctor.this,Reply.class);
            reply.putExtra("namereply",name);
            reply.putExtra("commentreply",comment);
            reply.putExtra("timereply",time);
            reply.putExtra("urlreply",url_o);
            reply.putExtra("post_id",q_id);
            reply.putExtra("commentid",commentid);
            startActivity(reply);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private class SCrollL implements  AbsListView.OnScrollListener {


        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            int topRowVerticalPosition = (listView == null || listView.getChildCount() == 0) ?
                    0 : listView.getChildAt(0).getTop();

            swipeview.setEnabled((topRowVerticalPosition >= 0));
        }
    }
}
