package smvitm.rajath.varnothsava;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnnoncementFragment extends Fragment {


    public AnnoncementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_annoncement, container, false);
        getActivity().setTitle("Announcements");
        Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_SHORT).show();
        return rootview;
    }

}
