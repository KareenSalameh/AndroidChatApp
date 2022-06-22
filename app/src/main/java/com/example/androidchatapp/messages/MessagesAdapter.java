package com.example.androidchatapp.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidchatapp.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {

    private final List<MessagesList> messagesLists;
    private final Context context;

    public MessagesAdapter(List<MessagesList> messagesLists, Context context) {
        this.messagesLists = messagesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layput, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {

        MessagesList list2 = messagesLists.get(position);

       // holder.name.setText(list2.getUser());
        holder.nickname.setText(list2.getNickname());
        holder.lastm.setText(list2.getLastMessage());
    }

    @Override
    public int getItemCount() {
        return messagesLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView profilePic;
       // private TextView name;
        private TextView nickname;
        private TextView lastm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //name = itemView.findViewById(R.id.username);
            nickname = itemView.findViewById(R.id.nickName);
            lastm = itemView.findViewById(R.id.lastmessage);
            profilePic = itemView.findViewById(R.id.profilepic);
        }
    }
}
