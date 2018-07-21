package smvitm.rajath.varnothsava;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
import java.util.List;


/*
Created by Rajath
For more details contact me at
Name : Rajath
Email : ykrajath4@gmail.com
WhatsApp : +91 9591708470
Phone : +91 9591708470
*/

public class EventTabFragment extends Fragment {


    private MaterialViewPager mViewPager;

    public EventTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);


        getActivity().setTitle("Events");


        getActivity().setTheme(R.style.AppTheme);


        // Setting ViewPager for each Tabs
           /* ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            // Set Tabs inside Toolbar
            TabLayout tabs = (TabLayout) view.findViewById(R.id.fixture_tabs);
            tabs.setupWithViewPager(viewPager);*/
        mViewPager = view.findViewById(R.id.materialViewPager);

        Toolbar toolbar = mViewPager.getToolbar();

        ViewPager viewPager = mViewPager.getViewPager();
        setupViewPager(viewPager);
//After set an adapter to the ViewPager
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


        NestedScrollView mScrollView = view.findViewById(R.id.scrollView);
        //MaterialViewPagerHelper.registerScrollView(, mScrollView, null);

//

        final Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.bg_technical);
        final Drawable bg2 = ContextCompat.getDrawable(getActivity(), R.drawable.bg_cultural);
        final Drawable bg3 = ContextCompat.getDrawable(getActivity(), R.drawable.bg_gaming);
        final Drawable bg4 = ContextCompat.getDrawable(getActivity(), R.drawable.bg_general);

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                d);
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green,
                                bg2);
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan,
                                bg3);
                    //https://cdn-article-1.papertostone.com/q-article/content/images/2015/07/nara_jpg_1184016f.jpg
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.red,
                                bg4);
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });
        return view;

    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new TechnicalEventFragment(), "Technical");
        adapter.addFragment(new CulturalEventFragment(), "Cultural");
        adapter.addFragment(new GamingEventFragment(), "Gaming");
        adapter.addFragment(new GeneralEventFragment(), "General");
        viewPager.setAdapter(adapter);


    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
