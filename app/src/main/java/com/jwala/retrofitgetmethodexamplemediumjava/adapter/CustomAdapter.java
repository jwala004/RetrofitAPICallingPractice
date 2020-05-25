package com.jwala.retrofitgetmethodexamplemediumjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PhotoModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<PhotoModel> dataList;
    private Context context;

    public CustomAdapter(Context context, List<PhotoModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.albumId.setText("Album Id : "+dataList.get(position).getAlbumId());
        holder.id.setText("Id : "+String.valueOf(dataList.get(position).getId()));
        holder.title.setText("Title : "+dataList.get(position).getTitle());
// this one is also working
//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(dataList.get(position).getThumbnailUrl())
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.url);

        Picasso.get().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.url);

//        not working
//        Glide.with(context).load(dataList.get(position).getThumbnailUrl())
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.url);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView albumId;
        TextView id;
        TextView title;
        private ImageView url;

        CustomViewHolder(View itemView) {
            super(itemView);

            albumId = itemView.findViewById(R.id.tvAlbumId);
            id = itemView.findViewById(R.id.tvUserId);
            title = itemView.findViewById(R.id.tvTitle);
            url = itemView.findViewById(R.id.imageUrl);
        }
    }
}