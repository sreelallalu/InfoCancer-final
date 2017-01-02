package info.NavAdapter;

/**
 * Created by SLR on 11/20/2016.
 */

import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.NavItem.QuestionItem;
import infocancer.nyesteventure.com.infocancer.R;


public class Question_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener,RecyclerView.OnItemTouchListener {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private static RecyclerView mViewx;
    GestureDetectorCompat gestureDetector;
    private ArrayList<QuestionItem> itemList;
    private static View.OnClickListener onclick=new MyClick();
    private static OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;
    static ActionMode actionMode;
    private boolean isMoreLoading = false;
    private int visibleThreshold = 0;
    private int totalsize;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnLoadMoreListener{
        void onLoadMore();
        void OnClickITEM(int a);
    }



    public Question_Adapter(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener=onLoadMoreListener;
        itemList =new ArrayList<>();
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager){
        this.mLinearLayoutManager=linearLayoutManager;
    }

    public void setRecyclerView(RecyclerView mView, int total){
        this.totalsize=total;
        this.mViewx=mView;
        //   mView.addOnItemTouchListener(this);
        mView.setItemAnimator(new DefaultItemAnimator());
        mView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();

                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
            //   Log.e("visiblecount",""+totalItemCount);
              //  Log.e("totalarr_o",""+totalsize);
                if (!isMoreLoading && (totalItemCount - visibleItemCount)<= (firstVisibleItem + visibleThreshold)&&totalsize!= totalItemCount) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                        // Log.e("onloading","loading");
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {

            return new StudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_question_list, parent, false));
        } else {
            return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false));
        }

    }

    public void addAll(List<QuestionItem> lst){
        itemList.clear();
        itemList.addAll(lst);
        notifyDataSetChanged();
    }

    public void addItemMore(List<QuestionItem> lst){
        itemList.addAll(lst);
        notifyItemRangeChanged(0,itemList.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder) {

            QuestionItem singleItem = (QuestionItem) itemList.get(position);
            ((StudentViewHolder) holder).tvItem1.setText(singleItem.get_Time());
            ((StudentViewHolder) holder).tvItem2.setText(singleItem.get_Content());
            ((StudentViewHolder) holder).tvItem3.setText(singleItem.get_Title());

        }
    }

    public void setMoreLoading(boolean isMoreLoading) {

        this.isMoreLoading=isMoreLoading;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setProgressMore(final boolean isProgress) {
        if (isProgress) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    itemList.add(null);
                    notifyItemInserted(itemList.size() - 1);
                }
            });
        } else {
            itemList.remove(itemList.size() - 1);
            notifyItemRemoved(itemList.size());
        }
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem1;
        public TextView tvItem2;
        public TextView tvItem3;
       // public LayoutRipple riple;

        public StudentViewHolder(View v) {
            super(v);
            tvItem1 = (TextView) v.findViewById(R.id.adapter_timex);
            tvItem2 = (TextView) v.findViewById(R.id.adapter_contentx);
            tvItem3 = (TextView) v.findViewById(R.id.adapter_subjectx);
            v.setOnClickListener(new MyClick());
          //  LayoutRipple layoutRipple= (LayoutRipple) v.findViewById(R.id.rippleo);
            /*setOriginRiple(layoutRipple);
            layoutRipple.setOnClickListener(new MyClick());*/
        }
       /* private void setOriginRiple(final LayoutRipple layoutRipple){

            layoutRipple.post(new Runnable() {

                @Override
                public void run() {
                    View v = layoutRipple.getChildAt(0);
                 *//* layoutRipple.setxRippleOrigin(ViewHelper.getX(v)+v.getWidth()/2);
                    layoutRipple.setyRippleOrigin(ViewHelper.getY(v)+v.getHeight()/2);
*//*
                    // layoutRipple.setRippleColor(Color.parseColor("#1E88E5"));

                    layoutRipple.setRippleSpeed(30);
                }
            });

        }
*/
        private class CliCk implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                int a=mViewx.getChildAdapterPosition(v);
                Log.e("position",""+a);
                onLoadMoreListener.OnClickITEM(a);
            }
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar pBar;
        public ProgressViewHolder(View v) {
            super(v);
            pBar = (ProgressBar) v.findViewById(R.id.pBar);
        }
    }


    private static class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            int a=mViewx.getChildAdapterPosition(v);
            Log.e("position",""+a);
            onLoadMoreListener.OnClickITEM(a);




        }
    }

    private  class RecyclerViewDemoOnGestureListener  extends GestureDetector.SimpleOnGestureListener {
        MyClick onclick;
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = mViewx.findChildViewUnder(e.getX(), e.getY());
            onclick.onClick(view);
            return super.onSingleTapConfirmed(e);
        }

        public void onLongPress(MotionEvent e) {
            View view = mViewx.findChildViewUnder(e.getX(), e.getY());
            if (actionMode != null) {
                return;
            }
            // Start the CAB using the ActionMode.Callback defined above

            super.onLongPress(e);
        }


    }

    private class T {
    }
}