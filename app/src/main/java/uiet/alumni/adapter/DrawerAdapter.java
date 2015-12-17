package uiet.alumni.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import uiet.alumni.Activity.HomeActivity;
import uiet.alumni.Activity.JobActivity;
import uiet.alumni.Activity.LoginActivity;
import uiet.alumni.Activity.MainActivity;
import uiet.alumni.Activity.ProfileActivity;
import uiet.alumni.R;
import uiet.alumni.utils.MyUtils;
import uiet.alumni.utils.PrefUtils;
import uiet.alumni.utils.ShareUtils;

/**
 * Created by aasaqt on 31/10/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 0;
    private String mNavTitles[];
    private int mIcons[];
    private Context mCtx;

    private DrawerLayout mDrawer;


    public DrawerAdapter(Context pCtx, String Titles[], int Icons[],DrawerLayout pDrawer) {
        mCtx = pCtx;
        mDrawer = pDrawer;
        mNavTitles = Titles;
        mIcons = Icons;

    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(mCtx).inflate(R.layout.drawer_row, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType, mCtx,mDrawer);
            return vhItem;
        }else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(mCtx).inflate(R.layout.drawer_header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType, mCtx,mDrawer);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final DrawerAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {
            holder.textView.setText(mNavTitles[position-1]);
            holder.imageView.setImageResource(mIcons[position-1]);
            if (position % 2 == 0) {
                holder.layout.setBackgroundColor(Color.parseColor("#e6e9f0"));
            } else {
                holder.layout.setBackgroundColor(Color.parseColor("#edf0f7"));
            }
            holder.layout.setTag(position);
        }else {
            holder.Name.setText("Aasaqt");




        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;
        TextView textView,Name;
        ImageView imageView,ivCover;
        CircleImageView profile;
        LinearLayout layout;
        RelativeLayout lay;
        Context mCtx;
        DrawerLayout mDrawer;

        public ViewHolder(View itemView, int ViewType, Context pCtx,DrawerLayout pDrawerLayout) {
            super(itemView);
            mCtx = pCtx;
            mDrawer = pDrawerLayout;
            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                layout = (LinearLayout) itemView.findViewById(R.id.ll_row);
                layout.setOnClickListener(this);
                Holderid = 1;
            }else {
                Name = (TextView) itemView.findViewById(R.id.name);
                profile = (CircleImageView) itemView.findViewById(R.id.circleView);
                profile.setBorderColor(Color.parseColor("#ffffff"));
                profile.setBorderWidth(MyUtils.dpToPx(mCtx, 2));
                ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
                lay = (RelativeLayout) itemView.findViewById(R.id.drawerHeader);
                lay.setOnClickListener(this);
                Holderid = 0;
            }
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.drawerHeader:
                    Intent n = new Intent(mCtx, ProfileActivity.class);
                    mCtx.startActivity(n);
                    break;
                case R.id.ll_row : {
                    int position = (int)v.getTag();
                    switch(position){

                        case 1 : {
                            Intent intent = new Intent(mCtx, HomeActivity.class);
                            mCtx.startActivity(intent);
                            break;
                        }

                        case 2 : {
                            Intent p = new Intent(mCtx, HomeActivity.class);
                            //p.putExtra("url", "http://api.flatabout.com/misc/privacy.html");
                            mCtx.startActivity(p);
                            break;
                        }
                        case 3 : {
                            Intent p = new Intent(mCtx, JobActivity.class);
                            //p.putExtra("url", "http://api.flatabout.com/misc/terms.html");
                            mCtx.startActivity(p);
                            break;
                        }
                        case 4 : {
                            ShareUtils.sendEmail(mCtx, "");
                            break;
                        }
                        case 5:{
                            break;
                        }
                        case 6 : {
                            ParseUser.logOut();
                            PrefUtils.setUserLogin(false);
                            Intent i = new Intent(mCtx, MainActivity.class);
                            mCtx.startActivity(i);

                            //Intent intent = new Intent(mCtx, ThreeBarActivity.class);
                            //mCtx.startActivity(intent);
                            break;
                        }
                    }
                }
            }
            mDrawer.closeDrawer(Gravity.LEFT);
        }
    }


}

