package uiet.alumni.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import org.w3c.dom.Text;

import uiet.alumni.R;
import uiet.alumni.adapter.DrawerAdapter;

/**
 * Created by aasaqt on 16/12/15.
 */
public class ProfileActivity extends Activity implements View.OnClickListener {
    TextView email,lastname,firstname;
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
        setContentView(R.layout.profile_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rl_drawer); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new DrawerAdapter(this, TITLES, ICONS, mDrawerLayout);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);
        firstname = (TextView) findViewById(R.id.tvFirstDesc);
        lastname = (TextView) findViewById(R.id.tvLastDesc);
        email = (TextView) findViewById(R.id.tvEmailDesc);
        threebar = (ImageView) findViewById(R.id.threebar);
        threebar.setOnClickListener(this);
        String firstnam = ParseUser.getCurrentUser().getUsername();

        //firstname.setText(firstnam);
        email.setText(firstnam);
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
