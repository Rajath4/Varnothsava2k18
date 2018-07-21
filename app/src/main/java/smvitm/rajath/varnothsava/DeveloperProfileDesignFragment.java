package smvitm.rajath.varnothsava;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeveloperProfileDesignFragment extends Fragment {


    public DeveloperProfileDesignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("About Developer");
        View rootView = inflater.inflate(R.layout.fragment_developer_profile_design, container, false);
        LinearLayout devEmail = rootView.findViewById(R.id.devemail);
        devEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ykrajath4@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Varnothsava'18 App");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        LinearLayout devCall = rootView.findViewById(R.id.devcall);
        devCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callPermision()) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "+919591708470"));
                    startActivity(callIntent);
                }
            }
        });
        LinearLayout lin = rootView.findViewById(R.id.lin);
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCollegeWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoCollegeWebsite.putExtra("key", "DeveloperLinkedin");
                startActivity(gotoCollegeWebsite);
            }
        });
        LinearLayout git = rootView.findViewById(R.id.git);
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCollegeWebsite = new Intent(getActivity(), GotoWeb.class);
                gotoCollegeWebsite.putExtra("key", "Developerg");
                startActivity(gotoCollegeWebsite);
            }
        });
        return rootView;
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
