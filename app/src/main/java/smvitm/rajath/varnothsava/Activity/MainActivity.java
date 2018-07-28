package smvitm.rajath.varnothsava.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import smvitm.rajath.varnothsava.Fragment.AboutSMVITMFragment;
import smvitm.rajath.varnothsava.Fragment.AboutUsFragment;
import smvitm.rajath.varnothsava.Fragment.AnnoncementFragment;
import smvitm.rajath.varnothsava.Fragment.DeveloperProfileDesignFragment;
import smvitm.rajath.varnothsava.Fragment.EventScheduleTabFragment;
import smvitm.rajath.varnothsava.Fragment.EventTabFragment;
import smvitm.rajath.varnothsava.Fragment.HomePageFragment;
import smvitm.rajath.varnothsava.R;

/*
Created by Rajath
For more details contact me at
Name : Rajath
Email : ykrajath4@gmail.com
WhatsApp : +91 9591708470
Phone : +91 9591708470
*/

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean back = false;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();


        for (int i = 4; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4081")), 0, spanString.length(), 0); //fix the color to white
            item.setTitle(spanString);
        }
        navigationView.setNavigationItemSelectedListener(this);

        HomePageFragment homePageFragment = new HomePageFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContent, homePageFragment);
        fragmentTransaction.commit();
        back = false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (!back) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                HomePageFragment homePageFragment = new HomePageFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flContent, homePageFragment);
                fragmentTransaction.commit();
                back = false;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_smvitm) {
            AboutSMVITMFragment aboutSMVITMFragment = new AboutSMVITMFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, aboutSMVITMFragment);
            fragmentTransaction.commit();
            back = true;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //To load Home page
            HomePageFragment monday = new HomePageFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, monday);
            fragmentTransaction.commit();
            back = false;

        } else if (id == R.id.nav_annocement) {
            //To load announcement page
            AnnoncementFragment annoncementFragment = new AnnoncementFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, annoncementFragment);
            fragmentTransaction.commit();
            back = true;

        } else if (id == R.id.nav_schedule) {
            //To load Varnothsava Schedule
            EventScheduleTabFragment eventScheduleTabFragment = new EventScheduleTabFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, eventScheduleTabFragment);
            fragmentTransaction.commit();
            back = true;

        } else if (id == R.id.nav_events) {
            //To load Varnothsava Events
            setTheme(R.style.AppThemeA);
            EventTabFragment eventTabFragment = new EventTabFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, eventTabFragment);
            fragmentTransaction.commit();
            back = true;

        } else if (id == R.id.nav_about) {
            //To load About Us
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, aboutUsFragment);
            fragmentTransaction.commit();
            back = true;

        } else if (id == R.id.nav_dev) {
            //To load Developer Info Page
            DeveloperProfileDesignFragment developerProfileDesignFragment = new DeveloperProfileDesignFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flContent, developerProfileDesignFragment);
            fragmentTransaction.commit();
            back = true;

        } else if (id == R.id.nav_register) {
            Intent gotoRegistrationWebsite = new Intent(MainActivity.this, GotoWeb.class);
            gotoRegistrationWebsite.putExtra("key", "reg");
            startActivity(gotoRegistrationWebsite);
        } else if (id == R.id.nav_register_talent_expo) {
            Intent gotoRegistrationWebsite = new Intent(MainActivity.this, GotoWeb.class);
            gotoRegistrationWebsite.putExtra("key", "regT");
            startActivity(gotoRegistrationWebsite);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
