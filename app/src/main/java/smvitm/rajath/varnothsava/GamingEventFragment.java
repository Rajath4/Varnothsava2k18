package smvitm.rajath.varnothsava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GamingEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GamingEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamingEventFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<RecyclerViewModel> pname = new ArrayList<RecyclerViewModel>();
        try {
            InputStream is;
            String filename;
            is = getActivity().getAssets().open("games_events.json");
            filename = "game_event";


            int size = is.available();
            byte[] byteArra = new byte[size];
            is.read(byteArra);
            is.close();
            String buffer = new String(byteArra, "UTF-8");

            JSONObject jsnfil = new JSONObject(buffer);

            JSONArray array = jsnfil.getJSONArray(filename);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);


                String Sid = object.optString("id");
                String SEventName = object.optString("EventName");
                String SRules = object.optString("Rules");
                String SStudentCoordinator = object.optString("StudentCoordinator");
                String SFacultyCoordinator = object.optString("FacultyCoordinator");
                String SContactDetailsOfStudentCoordinator = object.optString("ContactDetailsOfStudentCoordinator");
                String SContactDeatailOfFacCoordi = object.optString("ContactDeatailOfFacCoordi");
                String SVenue = object.optString("Venue");
                String SEventShortDetail = object.optString("EventShortDetail");
                String SStudentCordinatorDetail = object.optString("StudentCordinatorDetail");
                String SFacCoordinatorDetail = object.optString("FacCoordinatorDetail");
                String SEventTime = object.optString("EventTime");
                String SEventDay = object.optString("EventDay");
                String SOtherRules = object.optString("OtherRules");
                RecyclerViewModel bean1 = new RecyclerViewModel();
                bean1.setId(Sid);
                bean1.setEventName(SEventName);
                bean1.setRules(SRules);
                bean1.setStudentCoordinator(SStudentCoordinator);
                bean1.setFacultyCoordinator(SFacultyCoordinator);
                bean1.setFacCoordinatorDetail(SFacCoordinatorDetail);
                bean1.setContactDetailsOfStudentCoordinator(SContactDetailsOfStudentCoordinator);
                bean1.setContactDeatailOfFacCoordi(SContactDeatailOfFacCoordi);
                bean1.setVenue(SVenue);
                bean1.setEventShortDetail(SEventShortDetail);
                bean1.setStudentCordinatorDetail(SStudentCordinatorDetail);
                bean1.setEventTime(SEventTime);
                bean1.setEventDay(SEventDay);
                bean1.setOtherRules(SOtherRules);

                pname.add(bean1);

            }
        } catch (Exception e) {

        }


        final EventRecyclerViewAdapter itemsAdapter = new EventRecyclerViewAdapter(GamingEventFragment.this.getActivity(), pname, null);
        final RecyclerView clv = view.findViewById(R.id.clist);
        clv.setLayoutManager(new LinearLayoutManager(GamingEventFragment.this.getActivity()));
        //clv.setLayoutManager(new VegaLayoutManager());
        clv.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        clv.setHasFixedSize(true);
        clv.setAdapter(itemsAdapter);

    }
}