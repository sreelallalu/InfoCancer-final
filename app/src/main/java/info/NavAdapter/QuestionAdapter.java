package info.NavAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.NavItem.QuestionItem;
import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 10/11/2016.
 */
//SELECT usrcmt_id,comment,datetime,fname,photo FROM tblusercomments AS tm INNER JOIN tbluser as c on tm.user_Id=c.user_Id WHERE post_Id='27'
public class QuestionAdapter extends ArrayAdapter<QuestionItem> {


    private Context context;
    ArrayList<QuestionItem> navDrawerItemss;
    int layoutResourceId;
    public QuestionAdapter(Context context, int layoutid, ArrayList<QuestionItem> navDrawerItems) {
        super(context,layoutid,navDrawerItems);
        this.context = context;
        this.layoutResourceId=layoutid;
        this.navDrawerItemss = navDrawerItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(layoutResourceId, null);

            mHolder = new ViewHolder();

            mHolder.mtitle = (TextView) convertView.findViewById(R.id.adapter_subjectx);
           mHolder.mcontent= (TextView) convertView.findViewById(R.id.adapter_contentx);
           mHolder.mTime  = (TextView) convertView.findViewById(R.id.adapter_timex);

            convertView.setTag(mHolder);






        }
        else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        QuestionItem item = navDrawerItemss.get(position);

        mHolder.mtitle.setText(item.get_Title());
        mHolder.mTime.setText(item.get_Time());
        mHolder.mcontent.setText(item.get_Content());

        return convertView;

    }
    private class ViewHolder {
        private TextView mtitle;
        private TextView mTime;
       // private TextView murl;
        private TextView mcontent;
    }
}
