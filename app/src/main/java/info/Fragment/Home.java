package info.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.HashMap;

import info.Database.DatabaseItems;
import info.Database.MainDataBAse;
import info.Database.data;
import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 8/25/2016.
 */
public class Home extends Fragment {
    private static final String TAG ="logcat" ;
    private static final String TAG_USER ="user";
    private static final String URL ="url" ;
    private static final String TITLE ="title" ;
    private static final String STRING ="stringcontent";
    private ImageView imageView;
    private Button back,forward;
    private TextView Header,Stringcontent,counter;
    private JSONArray matchFixturec = null;
   int ik=0;
private String type[]=new String[100];
    private String type2[]=new String[100];
    private String type3[]=new String[100];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home, container, false);
        imageView= (ImageView) rootView.findViewById(R.id.imagslide3);

        back= (Button) rootView.findViewById(R.id.back3);
        forward= (Button) rootView.findViewById(R.id.next3);
        Header= (TextView) rootView.findViewById(R.id.slidetextH3);
        Stringcontent= (TextView) rootView.findViewById(R.id.slidetext3);
        counter= (TextView) rootView.findViewById(R.id.count3);


        back.setOnClickListener(firstxx);
        forward.setOnClickListener(secondx);

        Stringjsons();


        return rootView;

    }
    View.OnClickListener firstxx=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ik--;
            ik= (ik + type.length) % type.length;
            try {

                Picasso picasso=new Picasso.Builder(getActivity()).listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                    }
                }).build();



                picasso.load(type[ik]).into(imageView, new Callback() {
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
    };
    View.OnClickListener secondx =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          ik++;
            ik= ik % type.length;
            //Toast.makeText(getActivity(),type[i],Toast.LENGTH_SHORT).show();
            try {

                Picasso picasso=new Picasso.Builder(getActivity()).listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                    }
                }).build();



                picasso.load(type[ik]).into(imageView, new Callback() {
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
    };






    private String[] Stringjsons() {
        final String face[]=new String[100];
        final String face1[]=new String[100];
        final String face2[]=new String[100];
           HashMap<String, String> hashmap = new HashMap<String, String>();
           hashmap.put("tag","skincancer");

           final PostResponseAsyncTask task=new PostResponseAsyncTask(getActivity(), hashmap, new AsyncResponse() {@Override
               public void processFinish(String s) {

              // Toast.makeText(getActivity(),s.toString(),Toast.LENGTH_SHORT).show();
               int i=0;


               try {

                   JSONObject main=new JSONObject(s);
                   matchFixturec =main.getJSONArray(TAG_USER);
                   int j=matchFixturec.length();

                      for(i=0;i<j;i++){

                       JSONObject c =matchFixturec.getJSONObject(i);
                  face[i]=c.getString(URL);
                       face1[i]=c.getString(TITLE);
                       face2[i]=c.getString(STRING);

                       type [i]=face[i];
                       type2[i]=face1[i];
                       type3[i]=face2[i];
                        //Toast.makeText(getActivity(),face[i],Toast.LENGTH_SHORT).show();




                  //  Toast.makeText(getActivity(),face[i],Toast.LENGTH_SHORT).show();
                   }


                   data db=new data(getActivity());
                   db.open();
                   db.createEntry(new DatabaseItems("sector","kool","halo"));
                   db.close();
               } catch (JSONException e) {
                   e.printStackTrace();
               }
                  /*   try {






                   } catch (JSONException e) {
                       e.printStackTrace();
                   }*/


               }
           });
           task.execute("http://rto.venturesoftwares.org/testjson/testimage.php");
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



     return type;

        }
}
