package com.soutazin.farabiSchool.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soutazin.farabiSchool.Model.Section;
import com.soutazin.farabiSchool.R;
import com.soutazin.farabiSchool.ui.DPTextView;

import java.util.ArrayList;

/**
 * Created by iman on 3/23/2017.
 */

public class SectionsListAdapter extends RecyclerView.Adapter<SectionsListAdapter.ViewHolder> {

    private ArrayList<Section> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public SectionsListAdapter(Context context, ArrayList<Section> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.section_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Section section = mData.get(position);
        holder.myTextView.setText(section.title);
        if (section.picResId != -1)
        {
            holder.sectionImage.setImageResource(section.picResId);
        }
        else
        {
            holder.sectionImage.setVisibility(View.GONE);
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public DPTextView myTextView;
        public ImageView sectionImage;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (DPTextView) itemView.findViewById(R.id.txt_instrument_title);
            sectionImage = (ImageView) itemView.findViewById(R.id.img_instrument_section_picture_viewer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
