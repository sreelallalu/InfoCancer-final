package info.Fragment.ImgCancer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

import info.Constant.CONSTANTS;
import info.Database.DATABASE_C;
import info.Database.DatabaseItems;
import info.Database.MainDataBAse;
import info.Fragment.ImageViewPopUpHelper;
import info.Fragment.Prevension.BackFragment;
import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 9/19/2016.
 */
public class ImgSkin extends Fragment implements BackFragment{

      //  change
      private String Db_name=DATABASE_C.TABLE_NAME.T_IMGAGE+4;
      private static final String C_CANCER =IMGCON.SKIN;




      private static final String TAG ="picassa";

      private String title_i[],content_i[],url_imag[];
      private String temp_var[];
      private Button back,next;
      int count;
      int cnt=0;
      String _title[],_content[],_urlimg[];
      private TextView Header,cuntertext,contentext;
      private ImageView imgaView;



      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.img_main, container,    false);
            findidz(rootView);

            //Toast.makeText(getActivity(), Db_name, Toast.LENGTH_SHORT).show();
            try {
                  checkdatabase();
            } catch (Exception e) {
                  e.printStackTrace();
            }


            back.setOnClickListener(backbu);

            next.setOnClickListener(nextbu);
            imgaView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        ImageViewPopUpHelper.enablePopUpOnClick(getActivity(), imgaView);
                  }
            });


            return rootView;
      }



      private void findidz(View rootView) {
            back=(Button)rootView.findViewById(R.id.imgback0er);
            next=(Button)rootView.findViewById(R.id.imgnext0er);
            Header=(TextView) rootView.findViewById(R.id.imghead0er);
            cuntertext=(TextView) rootView.findViewById(R.id.imgcount0er);
            contentext=(TextView) rootView.findViewById(R.id.imgtextstring0er);
            imgaView=(ImageView) rootView.findViewById(R.id.imgdisplay0e);

      }

      @Override
      public boolean onBackPressed() {
            return true;
      }

      @Override
      public int getBackPriority() {
            return 0;
      }


      private void checkdatabase() throws Exception {

            MainDataBAse batabase=new MainDataBAse(getActivity());
            batabase.open();

            boolean check=batabase.check(Db_name);
            // Toast.makeText(getActivity(), ""+check, Toast.LENGTH_SHORT).show();
            batabase.close();

            if(check==true)
            {
                  new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                              try {
                                    serverup();
                              } catch (Exception e) {
                                    e.printStackTrace();
                              }
                        }
                  });



            }
            else
            if(check==false)
            {
                  AllgetfromDatabase();
                  //Toast.makeText(getActivity(),""+check, Toast.LENGTH_SHORT).show();

            }

      }

      private void AllgetfromDatabase()throws Exception {
            String _title[]=new String[50];
            String _content[]=new String[50];
            String _url[]=new String[50];

            _title= StringgetFromdatabase(Db_name,DATABASE_C.COLUMN_NAME_IMAGE.I_title);
            _content=StringgetFromdatabase(Db_name,DATABASE_C.COLUMN_NAME_IMAGE.I_text);
            _url=StringgetFromdatabase(Db_name,DATABASE_C.COLUMN_NAME_IMAGE.I_url);
            int count_url=_content.length;

            Log.e("read title",_title[0]);
            Log.e("read content",_content[0]);
            Log.e("read url",_url[0]);


            frontpagesetting(_title,_content,_url,count_url);
      }



      private void frontpagesetting(String[] h, String[] h1, String[] h2, int a) {
            this._title=h;
            this._content=h1;
            this._urlimg=h2;
            this.count=a;

            Header.setText(_title[0]);
            contentext.setText(_content[0]);
            picassaurl(_urlimg[0]);
            cuntertext.setText("1/"+count);







      }

      private void picassaurl(String h) {
            try {

                  Picasso picasso=new Picasso.Builder(getActivity()).listener(new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                        }
                  }).build();




                  picasso.load(h)
                      .placeholder(R.drawable.ef)
                          .into(imgaView, new Callback() {
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

      private String[] StringgetFromdatabase(String tablename, String columnname) {
            String cv[]=new String[50];
            MainDataBAse newdata=new MainDataBAse(getActivity());
            newdata.open();
            try {
                  // newdata.exportDB("urlcancer11db");
                  cv =newdata. getImageTable(tablename,columnname);
                  Log.e("read Data",cv[0]);

            } catch (Exception e) {
                  e.printStackTrace();
            }
            newdata.close();
            return cv;
      }


      private void serverup() throws Exception {


            try {
                  final String _title[] = new String[50];
                  final String _content[] = new String[50];
                  final String _url[] = new String[50];

                  final MainDataBAse dataBAse = new MainDataBAse(getActivity());
                  dataBAse.open();


                  HashMap<String, String> hashmap = new HashMap<>();
                  hashmap.put("tag", CONSTANTS.Image_URL.TAG);
                  hashmap.put(CONSTANTS.Image_URL.CatG, C_CANCER);


                  PostResponseAsyncTask task = new PostResponseAsyncTask(getActivity(), hashmap, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {

                              Log.e("json_Response", s);

                              try {
                                    int i;
                                    JSONObject main = null;

                                    main = new JSONObject(s);

                                    JSONArray matchFixturec = main.getJSONArray(CONSTANTS.Image_URL.I_JsonOb);
                                    int j = matchFixturec.length();

                                    for (i = 0; i < j; i++) {

                                          JSONObject c = matchFixturec.getJSONObject(i);
                                          _title[i] = c.getString(CONSTANTS.Image_URL.I_HEAD);
                                          _content[i] = c.getString(CONSTANTS.Image_URL.I_TEXT);
                                          _url[i] = c.getString(CONSTANTS.Image_URL.I_PHOTO);


                                          dataBAse.createImageTable(new DatabaseItems(_title[i], _content[i], _url[i]), Db_name);

                                    }
                                    dataBAse.close();




                              } catch (JSONException e) {
                                    e.printStackTrace();
                              }finally {
                                    new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                                try {
                                                      AllgetfromDatabase();
                                                } catch (Exception e) {
                                                      e.printStackTrace();
                                                }
                                          }
                                    },100);
                              }

                        }
                  });
                  task.execute(CONSTANTS.Image_URL.WEBURL);
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

                  e.printStackTrace();
            } finally {

            }
      }


      View.OnClickListener nextbu=new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                  cnt++;
                  if(cnt>count-1){cnt=0;}

                  Header.setText(_title[cnt]);
                  contentext.setText(_content[cnt]);
                  picassaurl(_urlimg[cnt]);
                  cuntertext.setText((cnt+1)+"/"+count);


            }
      };
      View.OnClickListener backbu=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  cnt--;
                  if(cnt<0){cnt=count-1;}

                  Header.setText(_title[cnt]);
                  contentext.setText(_content[cnt]);
                  picassaurl(_urlimg[cnt]);
                  cuntertext.setText((cnt+1)+"/"+count);

            }
      };






}
