package com.jwala.retrofitgetmethodexamplemediumjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.model.CommentModel;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolderClass> {

    Context context;
    List<CommentModel> commentModelArrayList;

    public CommentsAdapter(Context context, List<CommentModel> commentModelArrayList) {
        this.context = context;
        this.commentModelArrayList = commentModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_url_manipulation, viewGroup, false);
        return new MyViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {

        holder.setData("" + commentModelArrayList.get(position).getPostId(),
                "Id: " + commentModelArrayList.get(position).getId(),
                "Name: " +commentModelArrayList.get(position).getName(),
                "Email: " +commentModelArrayList.get(position).getEmail(),
                "Body: " + commentModelArrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }

    public class MyViewHolderClass extends RecyclerView.ViewHolder {

        TextView postId, tvId, tvName, tvEmail, tvBody;

        public MyViewHolderClass(@NonNull View itemView) {
            super(itemView);

            postId = itemView.findViewById(R.id.postId);
            tvId = itemView.findViewById(R.id.tvUserId);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvBody = itemView.findViewById(R.id.tvBody);


        }

        private void setData(String postCommonId,
                             String id,
                             String name,
                             String email,
                             String body) { // for imageview use  ( int resource and then imageview.setImageResource() )

            postId.setText(postCommonId);
            tvId.setText(id);
            tvName.setText(name);
            tvEmail.setText(email);
            tvBody.setText(body);

        }

    }
}
