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
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    public LinearLayoutManager mLinearLayoutManager;
    public Context mContext;
    private static final int TYPE_ITEM = 1;
    private String[] mHeadingNews,mContentNews;

    public CardViewAdapter(Context pContext,String[] pHeadingNews,String[] pContentNews, LinearLayoutManager pLayoutManager) {
        mLinearLayoutManager = pLayoutManager;
        mContext = pContext;
        mHeadingNews = pHeadingNews;
        mContentNews = pContentNews;
    }

    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType, mContext);
            return vhItem;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {
            holder.heading.setText(mHeadingNews[position]);
            holder.content.setText(mContentNews[position]);

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
        TextView heading,content;
        ImageView imageView;
        RelativeLayout layout;
        Context mCtx;

        public ViewHolder(View itemView, int ViewType, Context pCtx) {
            super(itemView);
            mCtx = pCtx;

            if (ViewType == TYPE_ITEM) {
                heading = (TextView) itemView.findViewById(R.id.tvHeading);
                content= (TextView) itemView.findViewById(R.id.tvContent);
                imageView = (ImageView) itemView.findViewById(R.id.ivNews);
                layout = (RelativeLayout) itemView.findViewById(R.id.homeNews);
                layout.setOnClickListener(this);
                Holderid = 1;
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}