package smvitm.rajath.varnothsava;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
Created by Rajath
For more details contact me at
Name : Rajath
Email : ykrajath4@gmail.com
WhatsApp : +91 9591708470
Phone : +91 9591708470
*/

public class EventInfoInBriefActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    static String id;
    static String EventName;
    static String Rules;
    static String StudentCoordinator;
    static String FacultyCoordinator;
    static String ContactDetailsOfStudentCoordinator;
    static String ContactDeatailOfFacCoordi;
    static String Venue;
    static String EventShortDetail;
    static String StudentCordinatorDetail;
    static String FacCoordinatorDetail;
    static String EventTime;
    static String EventDay;
    static String OtherRules;
    static String callNumber = "";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        Intent eventDetails = getIntent();
        EventName = eventDetails.getStringExtra("EventName");
        Rules = eventDetails.getStringExtra("Rules");
        EventShortDetail = eventDetails.getStringExtra("EventShortDetail");
        Venue = eventDetails.getStringExtra("Venue");
        StudentCoordinator = eventDetails.getStringExtra("StudentCoordinator");
        FacultyCoordinator = eventDetails.getStringExtra("FacultyCoordinator");
        StudentCordinatorDetail = eventDetails.getStringExtra("StudentCordinatorDetail");
        FacCoordinatorDetail = eventDetails.getStringExtra("FacCoordinatorDetail");
        ContactDetailsOfStudentCoordinator = eventDetails.getStringExtra("ContactDetailsOfStudentCoordinator");
        ContactDeatailOfFacCoordi = eventDetails.getStringExtra("ContactDeatailOfFacCoordi");
        id = eventDetails.getStringExtra("id");
        EventDay = eventDetails.getStringExtra("EventDay");
        EventTime = eventDetails.getStringExtra("EventTime");
        OtherRules = eventDetails.getStringExtra("OtherRules");


        setTitle(EventName);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + callNumber));
                    if (ActivityCompat.checkSelfPermission(EventInfoInBriefActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                } else {
                    Toast.makeText(this, "Please give permision to call the coordinator", Toast.LENGTH_SHORT).show();
                    return;
                }
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                View rootView = inflater.inflate(R.layout.fragment_event_info, container, false);
                TextView otherTextView = rootView.findViewById(R.id.other);
                TextView rulesTextView = rootView.findViewById(R.id.rules);
                TextView venueTextView = rootView.findViewById(R.id.venue);
                TextView dateTextView = rootView.findViewById(R.id.date);
                TextView timeTextView = rootView.findViewById(R.id.eventtime);
                TextView nameTextView = rootView.findViewById(R.id.name);
                TextView shortDetailTextView = rootView.findViewById(R.id.shortd);
                otherTextView.setText(OtherRules);
                rulesTextView.setText(Rules);
                venueTextView.setText(Venue);
                timeTextView.setText(EventTime);
                dateTextView.setText(EventDay);
                nameTextView.setText(EventName);
                shortDetailTextView.setText(EventShortDetail);


                return rootView;

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {

                if (EventName.equalsIgnoreCase("Te-presento")) {
                    View rootView = inflater.inflate(R.layout.tech_presento_layout, container, false);
                    final ImageView callCVS = rootView.findViewById(R.id.call_civ_s);
                    callCVS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("7026202155");
                        }
                    });
                    final ImageView callCVF = rootView.findViewById(R.id.call_civ_f);
                    callCVF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9916823370");
                        }
                    });


                    final ImageView callCSS = rootView.findViewById(R.id.call_cs_s);
                    callCSS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9449731365");
                        }
                    });
                    final ImageView callCSF = rootView.findViewById(R.id.call_cs_f);
                    callCSF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9620488077");
                        }
                    });


                    final ImageView callECS = rootView.findViewById(R.id.call_ec_s);
                    callECS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("8861939472");
                        }
                    });
                    final ImageView callECF = rootView.findViewById(R.id.call_ec_f);
                    callECF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9739319656");
                        }
                    });


                    final ImageView callMES = rootView.findViewById(R.id.call_mech_s);
                    callMES.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9480713892");
                        }
                    });
                    final ImageView callMEF = rootView.findViewById(R.id.call_mech_f);
                    callMEF.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe("9449700900");
                        }
                    });
                    return rootView;

                } else {

                    View rootView = inflater.inflate(R.layout.fragment_event_coordinator, container, false);

                    TextView sNameTextView = rootView.findViewById(R.id.sconame);
                    TextView sDetailTextView = rootView.findViewById(R.id.scodetail);
                    TextView sContactTextView = rootView.findViewById(R.id.scocontact);
                    TextView fNameTextView = rootView.findViewById(R.id.facname);
                    TextView fDetailTextView = rootView.findViewById(R.id.facdetail);
                    TextView fContactTextView = rootView.findViewById(R.id.faccontact);
                    TextView nameTextView = rootView.findViewById(R.id.name);
                    TextView shortDetailTextView = rootView.findViewById(R.id.shortd);
                    sNameTextView.setText(StudentCoordinator);
                    sDetailTextView.setText(StudentCordinatorDetail);
                    sContactTextView.setText(ContactDetailsOfStudentCoordinator);
                    fNameTextView.setText(FacultyCoordinator);
                    fDetailTextView.setText(FacCoordinatorDetail);
                    fContactTextView.setText(ContactDeatailOfFacCoordi);
                    nameTextView.setText(EventName);
                    shortDetailTextView.setText(EventShortDetail);


                    final ImageView call1 = rootView.findViewById(R.id.call1);
                    call1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe(ContactDetailsOfStudentCoordinator);
                        }
                    });
                    final ImageView call2 = rootView.findViewById(R.id.call2);
                    call2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callMe(ContactDeatailOfFacCoordi);
                        }
                    });
                    return rootView;
                }
            }
            return null;

        }

        public void callMe(String phoneNumber) {
            if (callPermision()) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            }
        }

        public boolean callPermision() {

            if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {

                    //permission is granted


                    return true;
                } else {

                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);

                    return false;
                }
            } else

            { //permission is automatically granted on sdk<23 upon installation

                return true;
            }
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

}
