package uiet.alumni.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Calendar;

import uiet.alumni.R;

/**
 * Created by aasaqt on 29/10/15.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    TextView already,admin;
    EditText etRegisterDOB,etRegisterMail,etRegisterFirstName,etRegisterLastName,etRegisterPassword;
    DatePicker datePicker;
    Calendar calendar;
    int year,month,day;
    String[] classes =  {"2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016"};
    String[] programs =  {"BE","ME","MBA","PhD","B.Com","M.Com","BA","MA","B.Sc","M.Sc"};
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        already = (TextView) findViewById(R.id.tvAlready);
        admin = (TextView) findViewById(R.id.tvAdmin);
        etRegisterDOB = (EditText) findViewById(R.id.etRegisterDOB);
        etRegisterMail = (EditText) findViewById(R.id.etRegisterMail);
        etRegisterPassword= (EditText) findViewById(R.id.etRegisterPassword);
        etRegisterFirstName= (EditText) findViewById(R.id.etRegisterFirstName);
        etRegisterLastName= (EditText) findViewById(R.id.etRegisterLastName);
        register = (Button) findViewById(R.id.butRegister);
        already.setOnClickListener(this);
        admin.setOnClickListener(this);
        register.setOnClickListener(this);
        etRegisterDOB.setOnClickListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, classes);
        //Find TextView control
        AutoCompleteTextView registerClass = (AutoCompleteTextView) findViewById(R.id.etRegisterClass);
        //Set the number of characters the user must type before the drop down list is shown
        registerClass.setThreshold(1);
        //Set the adapter
        registerClass.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, programs);
        //Find TextView control
        AutoCompleteTextView registerProgram = (AutoCompleteTextView) findViewById(R.id.etRegisterProgram);
        //Set the number of characters the user must type before the drop down list is shown
        registerProgram.setThreshold(1);
        //Set the adapter
        registerProgram.setAdapter(adapter2);
        //showDate(year,month+1,day);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvAlready:
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.tvAdmin:

                break;
            case R.id.etRegisterDOB:
                showDialog(999);
                break;
            case R.id.butRegister:

                ParseUser registerObject = new ParseUser();
                registerObject.setUsername(etRegisterMail.getText().toString());
                registerObject.setPassword(etRegisterPassword.getText().toString());

                //registerObject.put("Email",etRegisterMail.getText().toString());
                //registerObject.put("Password",etRegisterPassword.getText().toString());
                registerObject.put("FirstName",etRegisterFirstName.getText().toString());
                registerObject.put("LastName", etRegisterLastName.getText().toString());
                registerObject.put("DOB", etRegisterDOB.getText().toString());
                signUpUser(registerObject);
                //registerObject.saveInBackground();


                break;
        }
    }

    private void signUpUser(ParseUser registerObject) {
        registerObject.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Signup successful!
                    Toast.makeText(RegisterActivity.this,"Successfully Registered! Please Login Again!",Toast.LENGTH_LONG).show();
                    Intent p = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(p);
                } else {
                    // Fail!
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage(e.getMessage())
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };
    private void showDate(int year, int month, int day) {
        etRegisterDOB.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
