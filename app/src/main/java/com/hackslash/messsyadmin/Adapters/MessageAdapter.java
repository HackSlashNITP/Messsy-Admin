package com.hackslash.messsyadmin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.hackslash.messsyadmin.Model.MessageClass;
import com.hackslash.messsyadmin.R;

import java.util.ArrayList;
import java.util.Objects;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {
    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    Context context;
    ArrayList<MessageClass> messages;

    public MessageAdapter(Context context, ArrayList<MessageClass> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_sent_layout_2_persons, parent, false);
            return new viewHolder(view);

        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_receive_layout_2_persons, parent, false);
            return new viewHolder(view);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.messageTV.setText(messages.get(position).getMessage());
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView messageTV;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            messageTV = itemView.findViewById(R.id.senderMessage);

        }
    }


    @Override
    public int getItemViewType(int position) {
        MessageClass message = messages.get(position);

        if (Objects.requireNonNull(FirebaseAuth.getInstance().getUid()).equalsIgnoreCase(message.getSenderId())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }
}
