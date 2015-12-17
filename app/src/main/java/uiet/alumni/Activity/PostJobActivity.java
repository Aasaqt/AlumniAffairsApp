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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import uiet.alumni.R;
import uiet.alumni.adapter.DrawerAdapter;
import uiet.alumni.adapter.JobViewAdapter;

/**
 * Created by aasaqt on 16/12/15.
 */
public class PostJobActivity extends Activity implements View.OnClickListener {
    EditText jobTitle,jobOrganization,jobLocation,jobApply,jobExp,jobCtc,jobDetails;
    Button jobPost;
    ImageView threebar;
    DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView;
    DrawerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    String TITLES[] = {"Home", "News", "Jobs", "Contact Us","Find Alumni","Log Out"};
    int ICONS[] = {R.drawable.profile_icon,  R.drawable.terms_icon,R.drawable.privicy_icon, R.drawable.help_icon,R.drawable.search_icon, R.drawable.setting_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postjob_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_drawer); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new DrawerAdapter(this, TITLES, ICONS, mDrawerLayout);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        jobTitle = (EditText) findViewById(R.id.etJobTitle);
        jobOrganization= (EditText) findViewById(R.id.etJobOrganization);
        jobLocation= (EditText) findViewById(R.id.etJobLocation);
        jobApply= (EditText) findViewById(R.id.etJobApply);
        jobExp= (EditText) findViewById(R.id.etJobExp);
        jobCtc= (EditText) findViewById(R.id.etJobCtc);
        jobDetails= (EditText) findViewById(R.id.etJobOther);
        jobPost = (Button) findViewById(R.id.buttonJobPost);
        jobPost.setOnClickListener(this);
        threebar = (ImageView) findViewById(R.id.threebar);
        threebar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonJobPost:
                if(jobTitle.getText().toString().equals("") || jobOrganization.getText().toString().equals("") || jobLocation.getText().toString().equals("")){
                    Toast.makeText(this,"Please enter first three details",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"Successfully posted.Will be shown after verification by the admin!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this,HomeActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.threebar:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }
}
