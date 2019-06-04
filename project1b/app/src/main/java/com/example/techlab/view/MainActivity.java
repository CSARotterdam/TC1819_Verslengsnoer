package com.example.techlab.view;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;


public class MainActivity extends AppCompatActivity {
    protected static final String KEY_ACTIVE_USER_ID = "CurrentUserID";
    protected static final String PREFERENCES_FILE = "com.example.techlab.preferences";
    protected static final String KEY_ACTIVE_USER_EMAIL = "keyActiveUser";
    protected static final String KEY_ACTIVE_USER_PASS = "keyActiveUserPass";
    protected static final String KEY_ACTIVE_USER_STATUS = "keyActiveUserStatus";
    protected static final String KEY_PRODUCT_ADMINISTER_SPINNER_STATE = "keyProductAdministerState";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    EditText loginEmailInput;
    EditText loginPasswordInput;
    TextInputLayout textInputLayout;
    CheckBox stayLoggedInCheckBox;
    DataManagement dataManagement;

    Context ctx;
    public Context getCtx() {
        return ctx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);

        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        mSharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        stayLoggedInCheckBox = findViewById(R.id.stayLoggedInCheckBox);
        textInputLayout = findViewById(R.id.textInputLayout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dataManagement = new DataManagement();

        ImageView logo = findViewById(R.id.TechLabLogo);
        int ImageResource = getResources().getIdentifier("@drawable/techlablogo_small", null, this.getPackageName());
        logo.setImageResource(ImageResource);
    }

    @Override
    protected void onDestroy() {
        Log.i("MAINACT", "onDestroy!");
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (dataManagement.ifExists(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""),mSharedPreferences.getString(KEY_ACTIVE_USER_PASS,""))) {
            System.out.println("OnResume>Exist = true");
            if (!(dataManagement.ifBlocked(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL,"")))) {
                System.out.println("OnResume>Exist = true>Blocked check...");
                startActivity(new Intent(this, Product_InventoryActivity.class));
            }
            else{
                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Uitgelogd")
                        .setMessage("Uw account wordt uitgelogd en is geblokkeerd, neem contact met TechLab.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false);
                //Creating dialog box
                RequestItemAlertDialog.create().show();
            }
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        loginPasswordInput.setText("");
        loginEmailInput.setText("");
        mEditor.apply();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }


    public void signUpPageButton(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }

    //    https://www.youtube.com/watch?reload=9&v=ATERxKKORbY
//    This method creates a Notification that shows
    private void addNotification(){
//        int currentuserID = mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1);
        int userID = getIntent().getIntExtra("UserID", -1);
//
//        if (currentuserID==userID){}

//        Here we build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.logo_round)
                .setContentTitle("Te Laat")
                .setContentText("1 of meer producten staan nu op te laat\n Lever deze zo snel mogelijk in");
//  When you click on the intent you go to Student_Geleend_Aangevraagd.class
        Intent notification = new Intent(this,Student_Geleend_Aangevraagd.class);
        PendingIntent pending = PendingIntent.getActivity(this, 0,notification, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pending);

//        Notify the system that there is a notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(userID,builder.build());
    }
    public void loginButton(View view){
        if (dataManagement.ifExists(loginEmailInput.getText().toString().trim().toLowerCase(),loginPasswordInput.getText().toString())) {
            addNotification();
            if (!(dataManagement.ifBlocked(loginEmailInput.getText().toString()))) {
                Users user = dataManagement.getUserWithEmail(loginEmailInput.getText().toString());
                Intent startNewActivity = new Intent(getBaseContext(), Product_InventoryActivity.class);
                mEditor.putString(KEY_ACTIVE_USER_EMAIL, loginEmailInput.getText().toString());
                mEditor.putInt(KEY_ACTIVE_USER_ID, user.getId());
                mEditor.putString(KEY_ACTIVE_USER_STATUS, user.getUserType());
                if (stayLoggedInCheckBox.isChecked()) {
                    mEditor.putString(KEY_ACTIVE_USER_PASS, loginPasswordInput.getText().toString());
                }
                startActivity(startNewActivity);

            }
            else{
                AlertDialog.Builder RequestItemAlertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Inlog mislukt")
                        .setMessage("Uw account is geblokkeerd, neem contact met TechLab.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false);

                //Creating dialog box
                RequestItemAlertDialog.create().show();
            }
        }else{
            textInputLayout.setError("Het e-mailadres of wachtwoord dat je hebt ingevoerd is onjuist");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
