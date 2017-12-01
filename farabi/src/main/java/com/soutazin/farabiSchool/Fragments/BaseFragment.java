package com.soutazin.farabiSchool.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    private BackHandlerInterface backHandlerInterface = null;
    private String fragmentTitle = "Telenium";
    final static String ARG_TITLE= "title";
    public boolean readyToStart = false;

    public boolean onBackPressed() {
        return false;
    }



    public interface BackHandlerInterface {
        public void setSelectedFragment(BaseFragment backHandledFragment);
    }
    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(getActivity()  instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    public void updateUI() {}

    public void filterFragment(String filterText){}

    @Override
    public void onStart()
    {
        super.onStart();
        try {
            backHandlerInterface.setSelectedFragment(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (this instanceof  InstantMessagingFragment) {
//            ((MainActivity) getActivity()).changeActionBar(true);
//        }
//        else
//        {
//            ((MainActivity)getActivity()).changeActionBar(false);
//            ((MainActivity)getActivity()).setActionBarLabels(fragmentTitle);
//        }
    }


}
