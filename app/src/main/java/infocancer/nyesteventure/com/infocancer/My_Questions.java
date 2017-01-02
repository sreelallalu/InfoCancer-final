package infocancer.nyesteventure.com.infocancer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

import info.Constant.CONSTANTS;
import info.Database.DATABASE_C;
import info.Database.MainDataBAse;
import info.Holder.DataHolderClass;
import info.NavAdapter.Question_Adapter;
import info.NavItem.QuestionItem;


/**
 * Created by SLR on 11/22/2016.
 */

public class My_Questions extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        Question_Adapter.OnLoadMoreListener {
    SwipeRefreshLayout swipeView;
    ImageView user_pic,editpic;
    TextView name_u,email_u;
    ListView listView;

    RecyclerView.LayoutManager layoutManager;

    private ArrayList<QuestionItem> itemList=new ArrayList<>();
    private ArrayList<QuestionItem> arrayList=new ArrayList<>();
    private TextView category_text;
    private FloatingActionButton fab;


    private String categoryString;
    private String category_temp;
    RecyclerView recyclerView;
    boolean refreshToggle = true;
    Question_Adapter mAdapter;
    int totalsize, end_value;
    private Toolbar toolbar;
    String userid;


    private Spinner spinner;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainDataBAse data=new MainDataBAse(My_Questions.this);
        data.open();
       userid=data.getNameUser(DATABASE_C.TABLE_NAME.T_USER,DATABASE_C.COLUMN_NAME_USER.U_userid,1);
        data.close();
        setContentView(R.layout.question_include);
        toolbar = (Toolbar) findViewById(R.id.toolbarkk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinner= (Spinner) findViewById(R.id.questionmode_spinner);
        recyclerView = (RecyclerView) findViewById(R.id.rvListuser);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Question_Adapter(My_Questions.this);
        mAdapter.setLinearLayoutManager(mLayoutManager);
        category_text=(TextView)findViewById(R.id.infoText);
        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.CYAN);
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);

        recyclerView.setAdapter(mAdapter);
        fab = (FloatingActionButton) findViewById(R.id.question_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(My_Questions.this,Ask_question_User.class);
                startActivity(intent);
            }
        });
        spinnersetting();



    }
    private void loadData() {

        totalsize=arrayList.size();
        mAdapter.setRecyclerView(recyclerView,totalsize);
        itemList.clear();
        if (totalsize >= 20) {
            for (int i = 0; i < 20; i++) {
                itemList.add(arrayList.get(i));
            }
        } else {
            for (int i = 0; i < totalsize; i++) {
                itemList.add(arrayList.get(i));
            }
        }
        mAdapter.addAll(itemList);
    }
    private void spinnersetting() {
        List<String> list= DataHolderClass.getInstance().getDistributor_id();

        ArrayAdapter dri=new ArrayAdapter<String>(My_Questions.this,
                R.layout.spinner_item,list);

        dri.setDropDownViewResource(R.layout.spinner_compo);
        spinner.setAdapter(dri);
        categoryString=spinner.getSelectedItem().toString();
        category_text.setText(categoryString);
        spinner.setOnItemSelectedListener(new SelectedSpinner());


    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                PostAsyn(category_temp,userid);
            }
        },1000);

    }
    private ArrayList<QuestionItem> PostAsyn(String position, String userid) {
        // final ArrayList<QuestionItem> arrayList=new ArrayList<>();
        arrayList.clear();
        final ArrayList<QuestionItem> temp=new ArrayList<>();
        swipeView.setRefreshing(true);
        HashMap<String,String> hashmap=new HashMap<>();

        hashmap.put("tag", CONSTANTS.questions.Question_TAG_IDUSER);
        hashmap.put(CONSTANTS.questions.CAT_TAG,position);
        hashmap.put(CONSTANTS.questions.CAT_UERqU,userid);

        PostResponseAsyncTask task=new PostResponseAsyncTask(My_Questions.this, hashmap, new AsyncResponse()
        {
            @Override
            public void processFinish(String s) {
                try {
                    JSONObject qrjson =new JSONObject(s);
                    JSONArray jasonJsonArray=qrjson.getJSONArray(CONSTANTS.questions.TAG_NAME);
                    if(jasonJsonArray==null){
                        swipeView.setRefreshing(false);
                    }
                    for (int i =0; i<jasonJsonArray.length(); i++)
                    {
                        JSONObject c = jasonJsonArray.getJSONObject(i);
                        String u_imag= c.getString(CONSTANTS.questions.ID_U_URL);
                        String u_name= c.getString(CONSTANTS.questions.ID_U_name);
                        String q_imag= c.getString(CONSTANTS.questions.ID_Q_urL);
                        String q_id= c.getString(CONSTANTS.questions.ID_Q_id);
                        String content = c.getString(CONSTANTS.questions.ID_C);
                        String title = c.getString(CONSTANTS.questions.ID_S);
                        String time = c.getString(CONSTANTS.questions.ID_T);
                        String catgr = c.getString(CONSTANTS.questions.ID_CAT);

                        //Log.e("catego",catgr);
                        temp.add(new QuestionItem(title,content,time,catgr,q_id,q_imag,u_name,u_imag));
                        // Log.e("time",subject);
                    }

                    swipeView.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    swipeView.setRefreshing(false);
                }finally {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            My_Questions.this.arrayList=temp;
                            loadData();
                        }
                    },300);
                }
            }
        });
        task.execute(CONSTANTS.questions.WEBURL);
        task.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                swipeView.setRefreshing(false);
            }
            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                swipeView.setRefreshing(false);
            }
            @Override
            public void handleProtocolException(ProtocolException e) {
                swipeView.setRefreshing(false);
            }
            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                swipeView.setRefreshing(false);
            }
        });
        return temp;
    }



    public void onLoadMore() {

        Log.d("MainActivity_","onLoadMore");
        int start = mAdapter.getItemCount();
        mAdapter.setProgressMore(true);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {

                itemList.clear();
                mAdapter.setProgressMore(false);

                int start = mAdapter.getItemCount();

                Log.e("loop","loppp");

                int end =endvalue(start);


                for (int i = start ; i <end; i++) {
                    itemList.add(arrayList.get(i));

                }

                mAdapter.addItemMore(itemList);
                mAdapter.setMoreLoading(false);
            }

        },300);



    }
    private int endvalue(int start) {

        int first_check=totalsize-start;
        if(first_check<20)
        {
            end_value=start+first_check;
        }
        else
        {
            end_value=start+20;
        }
        Log.e("startvalue",""+start);
        Log.e("endvalue",""+end_value);
        return end_value;

    }

    @Override
    public void OnClickITEM(final int a) {

        Log.e("int a",""+a);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Next_page(a);

            }
        });




    }



    private void Next_page(int position) {
        String title=arrayList.get(position).get_Title();
        String times= arrayList.get(position).get_Time();

        String content=arrayList.get(position).get_Content();
        String catg=arrayList.get(position).get_catg();

        String q_image= arrayList.get(position).get_q_image();
        String q_id= arrayList.get(position).get_q_id();

        String u_image= arrayList.get(position).get_u_image();
        String u_name= arrayList.get(position).get_u_name();

        Intent ui=new Intent(My_Questions.this,ViewListQuetion.class);

        ui.putExtra(CONSTANTS.intent_send.Intent_TITLE,title);
        ui.putExtra(CONSTANTS.intent_send.Intent_TIME,times);

        ui.putExtra(CONSTANTS.intent_send.Intent_CONTENT,content);
        ui.putExtra(CONSTANTS.intent_send.Intent_CAT,catg);

        ui.putExtra(CONSTANTS.intent_send.Intent_U_NAME,u_name);
        ui.putExtra(CONSTANTS.intent_send.Intent_U_IMAGE,u_image);

        ui.putExtra(CONSTANTS.intent_send.Intent_Q_ID,q_id);
        ui.putExtra(CONSTANTS.intent_send.Intent_Q_IMAGE,q_image);

        startActivity(ui);
    }
    private class SelectedSpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            final String selectedItem = parent.getItemAtPosition(position).toString();

            category_text.setText(selectedItem);
            My_Questions.this.category_temp=selectedItem;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    PostAsyn(selectedItem,userid);
                }
            },600);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(My_Questions.this,Questions.class);
        startActivity(intent);
        this.finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(My_Questions.this,Questions.class);
                startActivity(intent);
                this.finish();
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}



