package info.NavAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




import java.util.ArrayList;

import info.NavItem.R_Item;
import infocancer.nyesteventure.com.infocancer.R;


/**
 * Created by SLR on 11/15/2016.
 */
public class ReplyArrayAdapter extends BaseAdapter {
    private static final String TAG ="tag";
    private Context context;
    private ArrayList<R_Item> replyItemses;


    public ReplyArrayAdapter(Context context, ArrayList<R_Item> replyItemses) {
        this.context = context;
        this.replyItemses = replyItemses;
    }

    @Override
    public int getCount() {
        return replyItemses.size();
    }

    @Override
    public Object getItem(int position) {
        return replyItemses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       final  ViewHolder mHolder;
        if(convertView==null)
        {
            mHolder = new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
             convertView=inflater.inflate(R.layout.reply_adapterbody,null);
           mHolder.mTime=(TextView)convertView.findViewById(R.id.reply_time);
            mHolder.mName=(TextView)convertView.findViewById(R.id.reply_name);
            mHolder.mcontent=(TextView)convertView.findViewById(R.id.reply_comment_id);



            mHolder.imageView=(ImageView)convertView.findViewById(R.id.reply_headerimage);
            mHolder.id_img=(ImageView)convertView.findViewById(R.id.id_re_doct);
            convertView.setTag(mHolder);

        }
        else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        R_Item item = replyItemses.get(position);

        mHolder.mName.setText(item.get_name());
        mHolder.mTime.setText(item.get_time());
        mHolder.mcontent.setText(item.get_comments());
        if(item.is_isvisible())
         {
             mHolder.imageView.setImageResource(R.drawable.save_doctor1);
             mHolder.id_img.setImageResource(R.drawable.stethoscope);
         }
        else{
             mHolder.imageView.setImageResource(R.drawable.save_user1);
            mHolder.id_img.setImageResource(android.R.color.transparent);
         }


        return convertView;
    }


    private class ViewHolder {
        private TextView mName;
        private TextView mTime;
       private ImageView imageView,id_img;
        private TextView mcontent;
    }
}
