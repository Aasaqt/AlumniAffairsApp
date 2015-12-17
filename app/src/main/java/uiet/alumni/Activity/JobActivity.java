package uiet.alumni.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import uiet.alumni.R;
import uiet.alumni.adapter.DrawerAdapter;
import uiet.alumni.adapter.JobViewAdapter;

/**
 * Created by aasaqt on 16/12/15.
 */
public class JobActivity extends Activity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView,recyclerView;
    DrawerAdapter mAdapter;
    JobViewAdapter adapter;
    ImageView threebar;
    RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    String TITLES[] = {"Home", "News", "Jobs", "Contact Us","Find Alumni","Log Out"};
    int ICONS[] = {R.drawable.profile_icon,  R.drawable.terms_icon,R.drawable.privicy_icon, R.drawable.help_icon,R.drawable.search_icon, R.drawable.setting_icon};
    String HeadingNews[] = {"Software Developer Job","Business Development Associate Internship"};
    String ContentNews[] = {"Location:Gurgaon","Location:Chandigarh"};
    String JobCompany[] = {"Company:Zomato","Company:LocalMistri"};
    String JobDate[] = {"21 December,2015","22 December,2015"};
    Button postJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_drawer); // Assigning the RecyclerView Object to the xml View
        recyclerView = (RecyclerView) findViewById(R.id.rv_step);
        recyclerView.setHasFixedSize(false);
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new DrawerAdapter(this, TITLES, ICONS, mDrawerLayout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new JobViewAdapter(this,HeadingNews,ContentNews,JobCompany,JobDate,layoutManager);
        recyclerView.setAdapter(adapter);
        mRecyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        threebar = (ImageView) findViewById(R.id.threebar);
        threebar.setOnClickListener(this);
        postJob = (Button) findViewById(R.id.buttonPostJob);
        postJob.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.threebar:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.buttonPostJob:
                Intent i = new Intent(this,PostJobActivity.class);
                startActivity(i);
                break;
        }
    }
}
