package smvitm.rajath.varnothsava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.balysv.materialripple.MaterialRippleLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home_page, container, false);

        getActivity().setTitle("Home");

        Button registerButton = rootview.findViewById(R.id.registerbutton);
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        registerButton.startAnimation(myAnim);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegistrationWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoRegistrationWebsite.putExtra("key", "reg");
                startActivity(gotoRegistrationWebsite);
            }
        });

        MaterialRippleLayout collegewebaddress = rootview.findViewById(R.id.rippleweb);
        collegewebaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCollegeWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoCollegeWebsite.putExtra("key", "Website");
                startActivity(gotoCollegeWebsite);
            }
        });

        MaterialRippleLayout e = rootview.findViewById(R.id.rippleemail);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"varnothsava@sode-edu.in"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Varnothsava'18 App");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        MaterialRippleLayout fb = rootview.findViewById(R.id.ripplefb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCollegeWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoCollegeWebsite.putExtra("key", "fb");
                startActivity(gotoCollegeWebsite);
            }
        });
        MaterialRippleLayout instaP = rootview.findViewById(R.id.rippleinsta);
        instaP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoinstaeWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoinstaeWebsite.putExtra("key", "insta");
                startActivity(gotoinstaeWebsite);
            }
        });


        return rootview;
    }

}
