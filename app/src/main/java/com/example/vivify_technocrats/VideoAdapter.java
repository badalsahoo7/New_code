package com.example.vivify_technocrats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> videoList;

    public VideoAdapter(List<VideoItem> videoList) {
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
VideoItem videoItem = videoList.get(position);
//holder.videoThumbnail.setImageResource(videoItem.getVideoThumbnail());
holder.videoTitle.setText(videoItem.getVideoTitle());
holder.channelName.setText(videoItem.getChannelName());
holder.channelImage.setImageResource(videoItem.getChannelImage());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder{
        //ImageView videoThumbnail;
        TextView videoTitle;
        TextView channelName;
        ImageView channelImage;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            //videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            channelName = itemView.findViewById(R.id.channelName);
            channelImage = itemView.findViewById(R.id.channelImage);
        }
    }
}
