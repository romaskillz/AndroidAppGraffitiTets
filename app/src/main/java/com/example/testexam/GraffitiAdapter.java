package com.example.testexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testexam.API.Model.Example;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraffitiAdapter extends RecyclerView.Adapter<GraffitiAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Example item);
    }

    private List<Example> mExamples;
    private OnItemClickListener listener;

    public GraffitiAdapter(List<Example> items, OnItemClickListener listener) {
        this.mExamples = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mExamples.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mExamples.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView) ImageView photo;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.author) TextView author;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Example item, final OnItemClickListener listener) {
            String image = item.getPhotos().get(0).getImage();
            String auth = item.getArtists().get(0).getName();
            if (image != null && !image.isEmpty()) {
                Picasso.get().load(image).into(photo);
            } else {
                photo.setImageResource(R.drawable.ic_launcher_background);
            }
            name.setText(item.getName());
            author.setText(auth);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
