package com.example.vivify_technocrats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
private List<VideoItem> videoList;
private RecyclerView homeRecyclerView;
private VideoAdapter videoAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
     View rootView = inflater.inflate(R.layout.fragment_home,container,false);

  videoList = generateVideoItem();
  homeRecyclerView = rootView.findViewById(R.id.homeRecyclerView);
  homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  videoAdapter =  new VideoAdapter(videoList);
  homeRecyclerView.setAdapter(videoAdapter);
  return rootView;
}

private List<VideoItem> generateVideoItem(){
        List<VideoItem> videoItems = new ArrayList<>();
       // videoItems.add(new VideoItem(R.drawable.dgcumminsgeneratorbye,"Vivify technocrats", "Vivify",R.drawable.vttech));
    videoItems.add(new VideoItem("vivify","vivify tech",R.drawable.vttech));
    videoItems.add(new VideoItem("vivify","vivify tech",R.drawable.vttech));

   // videoItems.add(new VideoItem(R.drawable.wirelesscctvbye,"Vivify technocrats","Vivify",R.drawable.vttech));
   return videoItems;
    }

}