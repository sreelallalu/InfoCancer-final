package info.NavAdapter;

/**
 * Created by SLR on 10/21/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.NavItem.CommentItem;
import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 10/11/2016.
 */
public class CommentAdapter extends ArrayAdapter<CommentItem> {


    private Context context;
    ArrayList<CommentItem> navDrawerItemss;
    int layoutResourceId;
    public CommentAdapter(Context context, int layoutid, ArrayList<CommentItem> navDrawerItems) {
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

            mHolder.mName = (TextView) convertView.findViewById(R.id.adapter_subjectx);
            mHolder.mcontent= (TextView) convertView.findViewById(R.id.adapter_contentx);
            mHolder.mTime  = (TextView) convertView.findViewById(R.id.adapter_timex);
            mHolder.murl  = (ImageView) convertView.findViewById(R.id.adapter_pro_dp);
            convertView.setTag(mHolder);




        }
        else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        CommentItem item = navDrawerItemss.get(position);

        mHolder.mName.setText(item.get_u_c_name());
        mHolder.mTime.setText(item.get_u_c_time());
        mHolder.mcontent.setText(item.get_u_c_comment());
      mHolder.murl.setImageResource(R.drawable.save_doctor1);


        return convertView;

    }
    private class ViewHolder {
        private TextView mName;
        private TextView mTime;
    private ImageView murl;
        private TextView mcontent;
    }
}
