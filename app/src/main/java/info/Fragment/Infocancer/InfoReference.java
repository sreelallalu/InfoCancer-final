package info.Fragment.Infocancer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.Fragment.Prevension.BackFragment;
import infocancer.nyesteventure.com.infocancer.R;

/**
 * Created by SLR on 9/19/2016.
 */
public class InfoReference extends Fragment implements BackFragment{


      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.inforeference, container, false);
            TextView textView= (TextView) rootView.findViewById(R.id.link_op);

            textView.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='http://www.nyesteventuretech.com/'> InfoCancer </a>";
            textView.setText(Html.fromHtml(text));



            return rootView;
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
