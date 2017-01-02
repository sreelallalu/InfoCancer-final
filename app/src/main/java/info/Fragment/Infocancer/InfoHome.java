package info.Fragment.Infocancer;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import info.Fragment.Prevension.BackFragment;
import infocancer.nyesteventure.com.infocancer.R;

/**
 * Created by SLR on 9/19/2016.
 */
public class InfoHome extends Fragment implements BackFragment {

  ImageView image1,image2,image3;
    int simage1[]={R.drawable.babx,R.drawable.cccc};
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {



       String simage2[]={"","",""};
       String simage3[]={"",""," "};
       int i=0;
         View rootView = inflater.inflate(R.layout.info_home, container, false);

           image1=(ImageView)rootView.findViewById(R.id.info_front_image1);
       image2=(ImageView)rootView.findViewById(R.id.info_front_image2);
       image3=(ImageView)rootView.findViewById(R.id.info_front_image3);

      setimage();
         return rootView;

         }

    private void setimage() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                image1.setImageResource(simage1[i]);
                i++;
                if(i>simage1.length-1)
                {
                    i=0;
                }
                handler.postDelayed(this, 5000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 100); //for initial delay..
    }


    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public int getBackPriority() {
        return 0;
    }
}
