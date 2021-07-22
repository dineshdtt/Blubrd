package com.example.macl6.mydesign.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.renderscript.Short4;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macl6.mydesign.Fragment.CatalogueFragment;
import com.example.macl6.mydesign.Fragment.ContactFragment;
import com.example.macl6.mydesign.Fragment.HomeFragment;
import com.example.macl6.mydesign.Fragment.VideoFragment;
import com.example.macl6.mydesign.R;
import com.example.macl6.mydesign.Utils.BottomNavigationViewBehavior;
import com.example.macl6.mydesign.Utils.FingerprintHandler;
import com.example.macl6.mydesign.Utils.SharedPref;


public class Main2Activity extends AppCompatActivity implements FingerprintHandler.FingerprintHelperListener,NavigationView.OnNavigationItemSelectedListener{

    //  Drawer Layout..........
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private BottomNavigationView bottomNavigationView;
    public NavigationView navigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String FLAG = "flagKey";
    SharedPref sharedpreferences;
    TextView tv_finger;
    private FingerprintHandler fingerprintHelper;
    private FingerprintManager fingerprintManager;
    FingerprintHandler.FingerprintHelperListener fingerprintHelperListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPref.init(Main2Activity.this);

        Resources res = getResources();
        boolean screenIsSmall = res.getBoolean(R.bool.screen_xlarge);

        //Drawer Layout.............
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //  drawer navigation View..............
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        boolean flag = SharedPref.read(SharedPref.FLAG, false);
        if (flag) {
            if (navigationView != null) {
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.nav_finger).setTitle("Disable FingerPrint");
            }
        } else {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_finger).setTitle("Enable FingerPrint");
        }


        // Toolbar code....
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Bottom navigation View;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        replacePage(new HomeFragment());

        //  bottom Navigation View..........................
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                toolbar.setTitle("Blubrd");
                                replacePage(new HomeFragment());
                                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                                break;
                            case R.id.action_item2:
                                toolbar.setTitle("Catalogue");
                                replacePage(new CatalogueFragment());
                                break;
                            case R.id.action_item3:
                                toolbar.setTitle("Products");
                                replacePage(new VideoFragment());
                                break;
                        }
                        return false;
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            switch (item.getItemId()) {

                case R.id.share:
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, "I suggest this app for you : https://play.google.com/store/apps/details?id=com.android.chrome");
                    intent.setType("text/plain");
                    this.startActivity(intent);
                    break;

                case R.id.feedback:
                    startActivity(new Intent(Main2Activity.this, FeedbackActivity.class));
                    break;

                case R.id.notification:
                    startActivity(new Intent(Main2Activity.this, NotificationActivity.class));
                    break;

                default:
                    break;
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {

            case R.id.nav_home: {
                replacePage(new HomeFragment());
                toolbar.setTitle("blubrd");
                break;
            }
            case R.id.nav_Catalogue: {
//                replacePage(new CatalogueFragment());
//                toolbar.setTitle(getResources().getString(R.string.str_catalogue));
                break;
            }
            case R.id.nav_Contact: {
//                replacePage(new ContactFragment());
//                toolbar.setTitle(getResources().getString(R.string.str_contact));
                break;
            }
            case R.id.nav_finger: {
                boolean flag = SharedPref.read(SharedPref.FLAG, false);
                if (flag) {

                    showToast(Main2Activity.this,"you want to disable setting?");

//                    Toast toast = Toast.makeText(Main2Activity.this, R.string.google_api_key, Toast.LENGTH_SHORT);

//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(R.dimen.toast_size);
//                    toast.show();
                    showDisableFinger();
                } else {
                    showEnableFinger();
                }
                break;
            }
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.frame);
        if (fragment instanceof HomeFragment) {
            doExit();
        } else {
            replacePage(new HomeFragment());
            toolbar.setTitle("blubrd");
        }
    }

    // Fragment Replace Page
    public void replacePage(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void doExit() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setPositiveButton(getResources().getString(R.string.str_lbl_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertDialog.setNegativeButton(getResources().getString(R.string.str_lbl_no), null);
            alertDialog.setMessage(getResources().getString(R.string.str_lbl_exit_from_app));
            alertDialog.setTitle(getResources().getString(R.string.app_name));
            alertDialog.show();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
    public static void showToast(Context context, CharSequence msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);

        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                context.getResources().getDimension(R.dimen.toast_size));
        toast.show();
    }

    private void showEnableFinger() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    SharedPref.write(SharedPref.FLAG, true);
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_finger).setTitle("Disable FingerPrint");

//                    startActivity(new Intent(Main2Activity.this,FingerPrintActivity.class));
//                    finish();
                }
            });
            alertDialog.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPref.write(SharedPref.FLAG, false);
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_finger).setTitle("Enable FingerPrint");
                }
            });
            alertDialog.setMessage("Quick login by placing your finger on fingerprint sensor of your device.");
            alertDialog.setTitle("Enable FingerPrint");
            alertDialog.setCancelable(false);
            alertDialog.show();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    private void showDisableFinger() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPref.write(SharedPref.FLAG, false);
                    if (navigationView != null) {
                        Menu menu = navigationView.getMenu();
                        menu.findItem(R.id.nav_finger).setTitle("Enable FingerPrint");

                        Toast.makeText(Main2Activity.this, "FingerPrint Disable", Toast.LENGTH_LONG).show();
                    }
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPref.write(SharedPref.FLAG, true);
                    Menu menu = navigationView.getMenu();
                    menu.findItem(R.id.nav_finger).setTitle("Disable FingerPrint");
                }
            });
            alertDialog.setMessage("Do you want to disable Fingerprint Authentication.");
            alertDialog.setTitle("Disable FingerPrint");
            alertDialog.setCancelable(false);
            alertDialog.show();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    public void showCustomDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.dialog_finger_enable, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(deleteDialogView);
        alertDialog.setCancelable(false);

        deleteDialogView.findViewById(R.id.img_fingerprint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        deleteDialogView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    alertDialog.cancel();
            }
        });
        alertDialog.show();

    }


    private void showCongrats() {
        try {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Main2Activity.this, FingerPrintActivity.class));
                }
            });
            alertDialog.setMessage("You have Successfully enabled Fingerprint based authentication.");
            alertDialog.setTitle("Success");
            alertDialog.setCancelable(false);
            alertDialog.show();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }

    @Override
    public void authenticationFailed(String error) {
        Toast.makeText(Main2Activity.this,"FingerPrint Authentication Failed",Toast.LENGTH_LONG).show();

    }

    @Override
    public void authenticationSuccess(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(Main2Activity.this,"FingerPrint Authentication Successful",Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        //Check for the fingerprint permission and listen for fingerprint
        //add additional checks along with this condition based on your logic
            //Fingerprint is available, update the UI to ask user for Fingerprint auth
            //start listening for Fingerprint
            fingerprintHelper = new FingerprintHandler(fingerprintHelperListener);
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            fingerprintHelper.startAuth(fingerprintManager, null);
    }
}
