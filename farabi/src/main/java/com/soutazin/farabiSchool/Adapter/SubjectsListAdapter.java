package com.soutazin.farabiSchool.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.Model.Subject;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.ui.DPTextView;

import java.util.ArrayList;

/**
 * Created by iman on 3/23/2017.
 */

public class SubjectsListAdapter extends RecyclerView.Adapter<SubjectsListAdapter.ViewHolder> {

    private ArrayList<Subject> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private PicturesInSubjectAdapter.ItemClickListener mClickListener;
    private Context _context = null;
    private static Subject subject = null;

    // data is passed into the constructor
    public SubjectsListAdapter(Context context, ArrayList<Subject> data, PicturesInSubjectAdapter.ItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        _context = context;
        this.mClickListener = listener;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.subject_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        subject = mData.get(position);
        holder.setPictureInThisSubject(subject.pictures);
        holder.txtDescription.setText(Html.fromHtml(subject.text));
        holder.txtTitle.setText(subject.title);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        public DPTextView txtTitle;
        private PicturesInSubjectAdapter picturesInSubjectAdapter = null;
        private ArrayList<PictureInSubject> _picturesInSubject = new ArrayList<>();
        public DPTextView txtDescription;
        public LinearLayout laySubjectPicturesHolder;
        public RecyclerView lstSubjectPicturesHolder;

        public ViewHolder(View itemView) {
            super(itemView);

            txtDescription = (DPTextView) itemView.findViewById(R.id.txt_subject_description);
            txtTitle = (DPTextView) itemView.findViewById(R.id.txt_subject_title);
            laySubjectPicturesHolder = (LinearLayout) itemView.findViewById(R.id.lay_subjects_pictures_holder);
            lstSubjectPicturesHolder = (RecyclerView) itemView.findViewById(R.id.lst_picture_items);



//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }

        public void setPictureInThisSubject(ArrayList<PictureInSubject> pictures) {
            _picturesInSubject = pictures;
            try {
                lstSubjectPicturesHolder.setLayoutManager(new LinearLayoutManager(_context, LinearLayoutManager.HORIZONTAL , false));
                picturesInSubjectAdapter = new PicturesInSubjectAdapter(_context, _picturesInSubject, mClickListener);
                lstSubjectPicturesHolder.setAdapter(picturesInSubjectAdapter);
            } catch (Exception e) {

            }
        }
    }

}
