package com.delaporte.projet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private List<Friend> friendList;
    private OnFriendInteractionListener listener;

    public interface OnFriendInteractionListener {
        void onChatClick(Friend friend);
        //void onChallengeClick(Friend friend);
        void onDeleteClick(Friend friend);
    }

    public FriendAdapter(List<Friend> friendList, OnFriendInteractionListener listener) {
        this.friendList = friendList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        Log.d("friend", friend.toString());
        holder.friendName.setText(friend.getName());
        holder.chatButton.setOnClickListener(v -> listener.onChatClick(friend));
        //holder.challengeButton.setOnClickListener(v -> listener.onChallengeClick(friend));
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(friend));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onDeleteClick(friend);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {

        TextView friendName;
        Button chatButton;
        //Button challengeButton;
        Button deleteButton;

        FriendViewHolder(View itemView) {
            super(itemView);
            friendName = itemView.findViewById(R.id.friend_name);
            chatButton = itemView.findViewById(R.id.chat_button);
            //challengeButton = itemView.findViewById(R.id.challenge_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
