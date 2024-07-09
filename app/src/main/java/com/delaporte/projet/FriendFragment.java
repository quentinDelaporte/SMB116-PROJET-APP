package com.delaporte.projet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class FriendFragment extends Fragment implements FriendAdapter.OnFriendInteractionListener {
    private int userConnectedId;
    private RecyclerView friendsRecyclerView;
    private EditText searchFriendEditText;
    private Button addFriendButton;
    private Button deleteFriendButton;
    private FriendAdapter friendAdapter;
    private List<Friend> friendList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        Bundle args = getArguments();
        if (args != null && args.containsKey("USER_ID")) {
            userConnectedId = args.getInt("USER_ID");
        } else {
        }

        addFriendButton = view.findViewById(R.id.add_friend_button);
        searchFriendEditText = view.findViewById(R.id.search_friend);

        friendsRecyclerView = view.findViewById(R.id.friends_recycler_view);
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();


        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
            }
        });


        return view;
    }

    private void loadData(){
        String friendJson =FriendApiService.getById(userConnectedId);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Friend>>() {}.getType();
        List<Friend> friends = gson.fromJson(friendJson, listType);

        FriendAdapter adapter = new FriendAdapter(friends, this);
        friendsRecyclerView.setAdapter(adapter);
    }

    private void addFriend() {
        Log.d("searchFriendEditText.getText()",searchFriendEditText.getText()+"");

        String utilisateurJson = UtilisateurApiService.getByUsername(searchFriendEditText.getText()+"");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Utilisateur>>() {}.getType();
        List<Utilisateur> utilisateurs = gson.fromJson(utilisateurJson, listType);

        Log.d("add",FriendApiService.insert(
                userConnectedId, utilisateurs.get(0).getUt_id()
        ));


        loadData();
    }

    @Override
    public void onChatClick(Friend friend) {
        openConversation(friend.getId());
    }

    /**@Override
    public void onChallengeClick(Friend friend) {
    }*/

    @Override
    public void onDeleteClick(Friend friend) {
        FriendApiService.delete(friend.getId(), userConnectedId);
        loadData();
    }

    private void openConversation(int friendId) {
        FragmentManager fragmentManager = getParentFragmentManager();
        Log.d("userConnectedId", userConnectedId+"");
        ConversationFragment conversationFragment = ConversationFragment.newInstance(userConnectedId,friendId);
        fragmentManager.beginTransaction()
                .replace(R.id.flFragment, conversationFragment)
                .addToBackStack(null)
                .commit();
    }
}
