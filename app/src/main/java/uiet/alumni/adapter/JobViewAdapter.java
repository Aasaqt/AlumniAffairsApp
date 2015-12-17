package uiet.alumni.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import uiet.alumni.R;

/**
 * Created by aasaqt on 16/12/15.
 */
public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.ViewHolder>{
    public LinearLayoutManager mLinearLayoutManager;
    public Context mContext;
    private static final int TYPE_ITEM = 1;
    private String[] mHeadingNews,mContentNews,mJobCompany,mJobDate;

    public JobViewAdapter(Context pContext,String[] pHeadingNews,String[] pContentNews,String[] pJobCompany,String[] pJobDate, LinearLayoutManager pLayoutManager) {
        mLinearLayoutManager = pLayoutManager;
        mContext = pContext;
        mHeadingNews = pHeadingNews;
        mContentNews = pContentNews;
        mJobCompany = pJobCompany;
        mJobDate = pJobDate;
    }

    @Override
    public JobViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.job_data, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType, mContext);
            return vhItem;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(JobViewAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {
            holder.heading.setText(mHeadingNews[position]);
            holder.content.setText(mContentNews[position]);
            holder.jobCompany.setText(mJobCompany[position]);
            holder.jobDate.setText(mJobDate[position]);
            holder.layout.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mContentNews.length;
    }
    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        TextView heading,content,jobCompany,jobDate;
        RelativeLayout layout;
        Context mCtx;

        public ViewHolder(View itemView, int ViewType, Context pCtx) {
            super(itemView);
            mCtx = pCtx;

            if (ViewType == TYPE_ITEM) {
                heading = (TextView) itemView.findViewById(R.id.tvJobHeading);
                content= (TextView) itemView.findViewById(R.id.tvJobLocation);
                jobCompany = (TextView) itemView.findViewById(R.id.tvJobCompany);
                jobDate = (TextView) itemView.findViewById(R.id.tvDate);
                layout = (RelativeLayout) itemView.findViewById(R.id.job);
                layout.setOnClickListener(this);
                Holderid = 1;
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
