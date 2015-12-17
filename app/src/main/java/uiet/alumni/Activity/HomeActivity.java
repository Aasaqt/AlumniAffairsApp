package uiet.alumni.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import uiet.alumni.R;
import uiet.alumni.adapter.CardViewAdapter;
import uiet.alumni.adapter.DrawerAdapter;

/**
 * Created by aasaqt on 31/10/15.
 */
public class HomeActivity extends Activity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView,recyclerView;
    DrawerAdapter mAdapter;
    CardViewAdapter adapter;
    ImageView threebar;
    RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    String TITLES[] = {"Home", "News", "Jobs", "Contact Us","Find Alumni","Log Out"};
    int ICONS[] = {R.drawable.profile_icon, R.drawable.terms_icon,R.drawable.privicy_icon, R.drawable.help_icon,R.drawable.search_icon,R.drawable.setting_icon};
    String HeadingNews[] = {"How IIT Grads helped a Mechanic's son in Kanpur","Bajaj Auto extends financial support of Rs. 30 cr to ISB",
    "Indian Alumni are showing love for their alma mater","Industry interaction: IIT- M alumni to go Yale,Harvard"};
    String ContentNews[] = {"A mechanic's son, Ayush is one of three people from India who will b....","The Indian School of Business(IBS), with campuses at Mohali an....",
    "While private investments,loans and grants to students have been...","Housed in a quiet,leafy residential neighbourhodd in Chennai,the IIT..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_drawer); // Assigning the RecyclerView Object to the xml View
        recyclerView = (RecyclerView) findViewById(R.id.rv_step);
        recyclerView.setHasFixedSize(false);
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new DrawerAdapter(this, TITLES, ICONS, mDrawerLayout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new CardViewAdapter(this,HeadingNews,ContentNews, layoutManager);
        recyclerView.setAdapter(adapter);
        mRecyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        threebar = (ImageView) findViewById(R.id.threebar);
        threebar.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.threebar:
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }
}
