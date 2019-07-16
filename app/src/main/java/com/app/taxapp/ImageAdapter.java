package com.app.taxapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Complaintdetails> mUploads;
    DataSnapshot ds;

    public ImageAdapter(Context context, List<Complaintdetails> complaintdetails) {
        mContext = context;
        mUploads = complaintdetails;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(mContext).inflate(R.layout.image_item, viewGroup,false);
        return  new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {


        //mUploads = new ArrayList<>();
        //Complaintdetails uploadCur = mUploads.get(i);
        Complaintdetails uploadCur = new Complaintdetails();
        //Complaintdetails uploadCur = ds.getValue(Complaintdetails.class);

        //imageViewHolder.image_view1.setImageURI(data.getAddpicleft());
        Picasso.with(mContext)
                .load(uploadCur.getAddpicleft())
                .placeholder(R.drawable.ic_action_image)
                .fit()
                .centerCrop()
                .into(imageViewHolder.image_view1);

        Picasso.with(mContext)
                .load(uploadCur.getAddpic())
                .placeholder(R.drawable.ic_action_image)
                .fit()
                .centerCrop()
                .into(imageViewHolder.image_view2);

        Picasso.with(mContext)
                .load(uploadCur.getAddpicright())
                .placeholder(R.drawable.ic_action_image)
                .fit()
                .centerCrop()
                .into(imageViewHolder.image_view3);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        //public TextView img_description;
        public ImageView image_view1;
        public ImageView image_view2;
        public ImageView image_view3;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            //img_description=itemView.findViewById(R.id.img_description);
            image_view1=itemView.findViewById(R.id.image_view1);
            image_view2=itemView.findViewById(R.id.image_view2);
            image_view3=itemView.findViewById(R.id.image_view3);
        }
    }
}