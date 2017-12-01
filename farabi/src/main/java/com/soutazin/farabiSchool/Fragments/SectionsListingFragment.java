package com.soutazin.farabiSchool.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.soutazin.farabiSchool.Activity.MainActivity;
import com.soutazin.farabiSchool.Activity.PictureViewerActivity;
import com.soutazin.farabiSchool.Adapter.PicturesInSubjectAdapter;
import com.soutazin.farabiSchool.Adapter.SectionsListAdapter;
import com.soutazin.farabiSchool.Adapter.SubjectsListAdapter;
import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.Model.Section;
import com.soutazin.farabiSchool.Model.Subject;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;

import java.util.ArrayList;
import java.util.Collections;


public class SectionsListingFragment extends BaseFragment implements View.OnClickListener, SectionsListAdapter.ItemClickListener, PicturesInSubjectAdapter.ItemClickListener {
    final static String ARG_POSITION = "position";
    private static final String PARENT_PART_ID_KEY = "PARENT_PART_ID";
    int mCurrentPosition = -1;
    private View view;
    private RecyclerView lstSectionsListing;
    private SectionsListAdapter adapter;
    private int parentPartId = -1;
    private ArrayList<Section> allSections = new ArrayList<>();

    @Override
    public void filterFragment(String filter)
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        parentPartId = getArguments().getInt(PARENT_PART_ID_KEY, -1);

        view = inflater.inflate(R.layout.fragment_sections_listing, container, false);

        lstSectionsListing = (RecyclerView) view.findViewById(R.id.lst_listing_sections);
        allSections =  SchoolApp.farabiDB.getSections(parentPartId);
        if (allSections.size() == 12)
        // for instruments
        {
            lstSectionsListing.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        }
        else
        {
            lstSectionsListing.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        }
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = new SectionsListAdapter(getActivity(), allSections);
                adapter.setClickListener(SectionsListingFragment.this);
                lstSectionsListing.setAdapter(adapter);

                lstSectionsListing.getViewTreeObserver().addOnPreDrawListener(
                        new ViewTreeObserver.OnPreDrawListener() {

                            @Override
                            public boolean onPreDraw() {
                                lstSectionsListing.getViewTreeObserver().removeOnPreDrawListener(this);
                                for (int i = 0; i < lstSectionsListing.getChildCount(); i++) {
                                    View v = lstSectionsListing.getChildAt(i);
                                    v.setAlpha(0.0f);
                                    v.animate().alpha(1.0f)
                                            .setDuration(300)
                                            .setStartDelay(i * 50)
                                            .start();
                                }

                                return true;
                            }
                        });


            }
        }, 200);

        return view;
    }


    public static SectionsListingFragment newInstance(int partId) {
        SectionsListingFragment myFragment = new SectionsListingFragment();

        Bundle args = new Bundle();
        args.putInt(PARENT_PART_ID_KEY, partId);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onStart() {
        super.onStart();



    }

    @Override
    public void updateUI() {
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
        outState.putInt(PARENT_PART_ID_KEY, parentPartId);
    }

    @Override
    public void onClick(View view) {
    }


    @Override
    public void onItemClick(View view, int position) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyTheme);
        dialog.setContentView(R.layout.dialog_subject_list);

        final RecyclerView lstSubjects = (RecyclerView) dialog.findViewById(R.id.lst_subjects_list);
        final ArrayList<Subject> allSubjects = SchoolApp.farabiDB.getSubjectsOfSection(allSections.get(position).id);
        final SubjectsListAdapter adapter = new SubjectsListAdapter(getActivity(), allSubjects , this);
        final GridLayoutManager layManager = new GridLayoutManager(getActivity(), 1);
        layManager.setSmoothScrollbarEnabled(false);
        lstSubjects.setLayoutManager(layManager);
        lstSubjects.setAdapter(adapter);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                lstSubjects.scrollToPosition(0);
            }
        }, 10);



        dialog.show();


//        Intent intent = new Intent(getActivity(), SubjectsListActivity.class);
//        startActivity(intent);
    }


    @Override
    public void onItemClick(View view, ArrayList<PictureInSubject> pictures, int postition) {
        SchoolApp.currentPicturesArrayList = pictures;
        Bundle b = new Bundle();
        b.putInt("position", postition);
        Intent intent = new Intent(getActivity(), PictureViewerActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}