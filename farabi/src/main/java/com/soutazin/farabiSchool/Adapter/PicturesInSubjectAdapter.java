package com.soutazin.farabiSchool.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.androidquery.AQuery;
import com.soutazin.farabiSchool.Model.PictureInSubject;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.SchoolApp;
import com.soutazin.farabiSchool.ui.DPTextView;
import com.soutazin.farabiSchool.utilities.AppConstants;

import java.util.ArrayList;


public class PicturesInSubjectAdapter extends RecyclerView.Adapter<PicturesInSubjectAdapter.ViewHolder> {

    private final Context _context;
    private ArrayList<PictureInSubject> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public PicturesInSubjectAdapter(Context context, ArrayList<PictureInSubject> data, ItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this._context = context;
        this.mClickListener = listener;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.picture_in_subject_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PictureInSubject pictureInSubject = mData.get(position);
        holder.txtDescription.setText(pictureInSubject.description);
//        holder.mainImage.setImageResource(0);
        if (pictureInSubject.description.equals(""))
        {
            holder.layDescriptionViewer.setVisibility(View.GONE);
        }
        else
        {
            holder.layDescriptionViewer.setVisibility(View.VISIBLE);
            holder.txtDescription.setText(pictureInSubject.description);

        }
        SchoolApp.aQuery
                .id(holder.mainImage)
                .progress(holder.prgImageLoading)
                .image(
                        AppConstants.baseURL + pictureInSubject.url + pictureInSubject.id + ".jpg"
                        , true
                        , true
                        , 0
                        , 0
                        , null
                        , AQuery.FADE_IN);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ProgressBar prgImageLoading;
        public LinearLayout layDescriptionViewer;
        public DPTextView txtDescription;
        public ImageView mainImage;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDescription = (DPTextView) itemView.findViewById(R.id.txt_picture_in_subject_description);
            mainImage = (ImageView) itemView.findViewById(R.id.img_picture_in_subject);
            layDescriptionViewer = (LinearLayout) itemView.findViewById(R.id.lay_picture_in_subject_description);
            prgImageLoading = (ProgressBar) itemView.findViewById(R.id.image_loading_progress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(
                    view,
                    mData,
                    getAdapterPosition()
//                    AppConstants.baseURL + (mData.get(getAdapterPosition())).url + (mData.get(getAdapterPosition())).id + ".jpg",
//                    mData.get(getAdapterPosition()).description
            );
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, ArrayList<PictureInSubject> pictures, int postition);
    }
}
