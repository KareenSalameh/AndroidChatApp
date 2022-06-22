package com.example.androidchatapp.messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidchatapp.R;
import com.example.androidchatapp.chat.Chat;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {

    private List<MessagesList> messagesLists;
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
        holder.nickname.setText(list2.getUser());
        holder.lastm.setText(list2.getLastMessage());

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chat.class);
               // intent.putExtra("name", list2.g)
              ////////////  intent.putExtra("nickname", list2.getUser());
                intent.putExtra("nickname", list2.getNickname());
                intent.putExtra("chat_key" , list2.getChatKey());

                context.startActivity(intent);
            }
        });
    }

    public void updateData(List<MessagesList> messagesLists){
        this.messagesLists = messagesLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messagesLists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView profilePic;
        private TextView nickname;
        //private TextView nickname;
        private TextView lastm;
        private LinearLayout rootLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.nickname);
            //nickname = itemView.findViewById(R.id.nickName);
            lastm = itemView.findViewById(R.id.lastMessage);
            profilePic = itemView.findViewById(R.id.profilepic);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }
    }
}
