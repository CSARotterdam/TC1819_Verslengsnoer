package com.example.techlab.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.techlab.R;
import com.example.techlab.db.DataManagement;
import com.example.techlab.model.Users;

public class MainActivity extends DrawerMenu {
    protected static final String KEY_ACTIVE_USER_ID = "CurrentUserID";
    protected static final String PREFERENCES_FILE = "com.example.techlab.preferences";
    protected static final String KEY_ACTIVE_USER_EMAIL = "keyActiveUser";
    protected static final String KEY_STAY_LOGGED_IN = "keyStayLoggedInStatus";
    protected static final String KEY_ACTIVE_USER_STATUS = "keyActiveUserStatus";
    protected static final String KEY_PRODUCT_ADMINISTER_SPINNER_STATE = "keyProductAdministerState";
    protected static final String KEY_ACTIVE_USER_NAME = "keyActiveUserName";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    EditText loginEmailInput;
    EditText loginPasswordInput;
    TextInputLayout userNameInputLayout, passwordInputLayout;
    CheckBox stayLoggedInCheckBox;
    DataManagement dataManagement;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Menu for Contact page:
        FrameLayout frameLayout = findViewById(R.id.content_frame);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityview = layoutInflater.inflate(R.layout.activity_main, null,false);
        frameLayout.addView(activityview);

        ctx = this;
        loginEmailInput = findViewById(R.id.loginEmailInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        mSharedPreferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        stayLoggedInCheckBox = findViewById(R.id.stayLoggedInCheckBox);
        userNameInputLayout = findViewById(R.id.textInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        dataManagement = new DataManagement();

        ImageView logo = findViewById(R.id.TechLabLogo);
        int ImageResource = getResources().getIdentifier("@drawable/techlablogo_small", null, this.getPackageName());
        logo.setImageResource(ImageResource);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mSharedPreferences.getString(KEY_STAY_LOGGED_IN, "").matches("on")) {
            System.out.println("OnResume>Exist = true");
            blockfunc blocked = new blockfunc(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""),ctx);
            if (!blocked.ifblocked()) {
                System.out.println("OnResume>Exist = true>Blocked check...");
                startActivity(new Intent(this, Product_InventoryActivity.class));
            }
            else{
                mEditor.putString(KEY_STAY_LOGGED_IN,"").apply();
                blocked.ShowBlockDialog("Uitgelogd");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        loginPasswordInput.setText("");
        loginEmailInput.setText("");
        mEditor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void signUpPageButton(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    // https://www.youtube.com/watch?reload=9&v=ATERxKKORbY
    // This method creates a Notification that shows
    private void addNotification() {
        // int currentuserID =
        // mSharedPreferences.getInt(MainActivity.KEY_ACTIVE_USER_ID,-1);
        // if one of the product status == "te laat" send notification

        // int userID = getIntent().getIntExtra("UserID", -1);
        // if(getIntent().getTe){
        // }

        // Here we build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.logo_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_round))
                .setContentTitle("Product(en) Te Laat").setContentText("Lever deze zo snel mogelijk in")
                .setOngoing(true) // You can see the notification in lockscreen
                .setVibrate(new long[] { 0, 10000 })
                // .setWhen
                // .setColor(Color.BLUE)
                .setAutoCancel(true); // clear notification after click
        // When you click on the notification you go to
        // Student_Geleend_Aangevraagd.class
        Intent notification = new Intent(this, Student_Geleend_Aangevraagd.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, notification, PendingIntent.FLAG_UPDATE_CURRENT));

        // Notify the system that there is a notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

    public void loginButton(View view) {
        userNameInputLayout.setError(null);
        if (dataManagement.ifExists(loginEmailInput.getText().toString().trim())) {
            if (dataManagement.ifExists(loginEmailInput.getText().toString().trim().toLowerCase(),
                loginPasswordInput.getText().toString().trim())) {
                    addNotification();

                    if (!(dataManagement.ifBlocked(loginEmailInput.getText().toString()))) {
                        Users user = dataManagement.getUserWithEmail(loginEmailInput.getText().toString());
                        Intent startNewActivity = new Intent(getBaseContext(), Product_InventoryActivity.class);
                        mEditor.putString(KEY_ACTIVE_USER_EMAIL, loginEmailInput.getText().toString());
                        mEditor.putInt(KEY_ACTIVE_USER_ID, user.getId());
                        mEditor.putString(KEY_ACTIVE_USER_STATUS, user.getUserType());
                        mEditor.putString(KEY_ACTIVE_USER_NAME, user.getFirstName());
                        if (stayLoggedInCheckBox.isChecked()) {
                            mEditor.putString(KEY_STAY_LOGGED_IN, "on");
                        }
                        startActivity(startNewActivity);
                    }
                    else {
                        blockfunc blocked = new blockfunc(mSharedPreferences.getString(KEY_ACTIVE_USER_EMAIL, ""),ctx);
                        blocked.ShowBlockDialog("Inlog mislukt!");
                    }
                }
            else {
                passwordInputLayout.setError("het wachtwoord dat je hebt ingevoerd is onjuist");
            }
        }
        else {
            userNameInputLayout.setError("Het e-mailadres dat je hebt ingevoerd is onjuist");
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
