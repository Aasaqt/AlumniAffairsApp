package uiet.alumni;

import android.app.Application;

import com.parse.Parse;

import uiet.alumni.utils.PrefUtils;

/**
 * Created by aasaqt on 31/10/15.
 */
public class AlumniApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrefUtils.init(this);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "RlL8DS87fyxGGlnrCQJ2LKSP0wrK5aFTciEhSARS", "zRn4dq9CLZ7lroIqTEIH7yKiBjfgyOwbmimEwsne");
    }
}
