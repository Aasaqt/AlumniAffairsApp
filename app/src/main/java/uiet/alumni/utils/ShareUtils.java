package uiet.alumni.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uiet.alumni.R;

/**
 * Created by Adi on 7/14/2015.
 */
public class ShareUtils {

    public static final String PKG_TWITTER = "com.twitter.android";

    public static final String PKG_WHATSAPP = "com.whatsapp";
    public static final String PKG_FACEBOOK = "com.facebook.katana";
    public static final String PKG_KIK = "kik.android";
    public static final String PKG_LINE = "jp.naver.line.android";
    public static final String PKG_FB_MESSANGER = "com.facebook.orca";
    public static final String PKG_TUMBLR = "com.tumblr";
    public static final String PKG_WECHAT = "com.tencent.mm";
    public static final String PKG_HIKE = "com.bsb.hike";
    public static final String PKG_INSTAGRAM = "com.instagram.android";
    public static final String PKG_HANGOUTS = "com.google.android.talk";
    public static final String PKG_GOOGLE_PLUS = "com.google.android.apps.plus";
    public static final String PKG_VIBER = "com.viber.voip";
    public static final String PKG_GMAIL = "com.google.android.gm";

    public static ArrayList<String> prioritylist = new ArrayList<String>(Arrays.asList(PKG_WHATSAPP, PKG_HIKE, PKG_FB_MESSANGER, PKG_LINE, PKG_VIBER, PKG_KIK, PKG_WECHAT, PKG_INSTAGRAM, PKG_TWITTER, PKG_TUMBLR, PKG_FACEBOOK, PKG_HANGOUTS, PKG_GMAIL, PKG_GOOGLE_PLUS));


    public static void share(Activity pActivity) {
        // make an implicit intent for text sharing
        Intent implicitIntent = new Intent(Intent.ACTION_SEND);
        implicitIntent.setType("text/plain");
        implicitIntent.putExtra(Intent.EXTRA_SUBJECT,
                pActivity.getString(R.string.app_name));
        implicitIntent.putExtra(Intent.EXTRA_TEXT, "Please Enter your problem");
        ArrayList<LabeledIntent> explicitIntentsList = getExplicitActivityIntentsList(pActivity, implicitIntent);
        if (explicitIntentsList.size() > 0) {
            explicitIntentsList = reOrderShareList(explicitIntentsList);
            Intent chooserIntent = Intent.createChooser(
                    explicitIntentsList.remove(explicitIntentsList.size() - 1), "Share with");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    explicitIntentsList.toArray(new Parcelable[]{}));
            pActivity.startActivity(chooserIntent);
        } else {
            Toast.makeText(pActivity, "No share application found",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public static ArrayList<LabeledIntent> getExplicitActivityIntentsList(
            Context pActivity, Intent pImplicitIntent) {
        PackageManager packageManager = pActivity.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(
                pImplicitIntent, 0);
        ArrayList<LabeledIntent> targetIntentsList = new ArrayList<LabeledIntent>();
        for (ResolveInfo anInfo : activities) {
            String packageName = anInfo.activityInfo.packageName;
            Intent explicitIntent = new Intent();
            explicitIntent.fillIn(pImplicitIntent, 0);
            explicitIntent.setComponent(new ComponentName(packageName,
                    anInfo.activityInfo.name));
            LabeledIntent explicitLabeledIntent = new LabeledIntent(explicitIntent,
                    packageName, anInfo.labelRes, anInfo.icon);
            targetIntentsList.add(explicitLabeledIntent);
        }
        return targetIntentsList;
    }

    public static ArrayList<LabeledIntent> reOrderShareList(ArrayList<LabeledIntent> explicitIntent) {
        int j = 0;
        int priorityCount = 0;
        while (j < prioritylist.size()) {
            for (int i = 0; i < explicitIntent.size(); i++) {
                if (j < prioritylist.size() && explicitIntent.get(i).getSourcePackage().equals(prioritylist.get(j))) {
                    LabeledIntent tempLI = explicitIntent.get(i);
                    explicitIntent.remove(i);
                    explicitIntent.add(priorityCount, tempLI);
                    priorityCount++;
                    break;
                }
            }
            j++;
        }
        return explicitIntent;
    }

    public static void sendEmail(Context activity,String message) {
        String[] emails = null;
        String subject = null;
        Intent email = new Intent(Intent.ACTION_SEND);
        emails = new String[] {"aasaqt@gmail.com"};
        subject = "Help : ";
        if(message == null)
            message = "";
        email.putExtra(Intent.EXTRA_EMAIL, emails);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        /*activity.startActivity(Intent.createChooser(email,
                "Choose an Email client :"));*/
        ArrayList<LabeledIntent> explicitIntentsList = getExplicitActivityIntentsList(activity, email);
        LabeledIntent removeIntent = null;
        if (explicitIntentsList.size() > 0) {
            for(LabeledIntent labeledIntent : explicitIntentsList){
                if(labeledIntent.getSourcePackage().equals("com.skype.raider")||labeledIntent.getSourcePackage().equals("com.skype.android")||labeledIntent.getSourcePackage().equals("com.skype.android.lite")||labeledIntent.getSourcePackage().equals("com.skype.main")||labeledIntent.getSourcePackage().equals("com.skype.com"));
                    removeIntent = labeledIntent;
            }
            if(removeIntent!=null)
                explicitIntentsList.remove(removeIntent);
            Intent chooserIntent = Intent.createChooser(
                    explicitIntentsList.remove(explicitIntentsList.size() - 1), "Choose an Email client :");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    explicitIntentsList.toArray(new Parcelable[]{}));
            activity.startActivity(chooserIntent);
        } else {
            Toast.makeText(activity, "No share application found",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
