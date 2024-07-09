package com.delaporte.projet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConversationFragment extends Fragment {
    private static final String ARG_LOGGED_ID ="loggedUserId";
    private static final String ARG_FRIEND_ID = "friend_id";
    private int friendId;
    private int loggedUserId;
    private List<String> messageList;
    private EditText messageEditText;
    private Button sendMessageButton;
    private TextView conversationTextView;

    public static ConversationFragment newInstance(int loggedUserId, int friendId) {
        ConversationFragment fragment = new ConversationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LOGGED_ID, loggedUserId);
        args.putInt(ARG_FRIEND_ID, friendId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            friendId = getArguments().getInt(ARG_FRIEND_ID);
            loggedUserId = getArguments().getInt(ARG_LOGGED_ID);
        }

        Log.d("loggedUserId", loggedUserId+"");

        messageList = new ArrayList<>();

        String destinataireJson = UtilisateurApiService.getById(friendId);
        Gson gsonDestinataire = new Gson();
        Type listTypeDestinataire = new TypeToken<List<Utilisateur>>() {}.getType();
        List<Utilisateur> destinataireList =gsonDestinataire.fromJson(destinataireJson, listTypeDestinataire);
        Utilisateur destinataire = destinataireList.get(0);

        String emetteurJson = UtilisateurApiService.getById(loggedUserId);
        Gson gsonEmetteur = new Gson();
        Type listTypeEmetteur = new TypeToken<List<Utilisateur>>() {}.getType();
        List<Utilisateur> emetteurList =gsonEmetteur.fromJson(emetteurJson, listTypeEmetteur);
        Utilisateur emetteur = emetteurList.get(0);

        String msgJson = MessagerieApiService.getMessagesByEmetteurAndReceveurId(loggedUserId,friendId);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Message>>() {}.getType();
        List<Message> msgList = gson.fromJson(msgJson, listType);
        Log.d("size", msgList.size()+"");
        for (Message message : msgList) {
            Log.d("emetteur/recepteur", message.getUt_id_emeteur() + " / " + message.getUt_id_receveur() + " / " + emetteur.getUt_id() + " / " + destinataire.getUt_id());
            messageList.add(
                    (
                            message.getUt_id_emeteur() == destinataire.getUt_id() ?
                                    ( destinataire.getUt_nom_utilisateur() + " :") :
                                    ( emetteur.getUt_nom_utilisateur() + " :")
                    )+ message.getMes_message());
        }


        //TODO ici
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);

        messageEditText = view.findViewById(R.id.message_edit_text);
        sendMessageButton = view.findViewById(R.id.send_message_button);
        conversationTextView = view.findViewById(R.id.conversation_text_view);

        updateConversation();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }

    private void updateConversation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message : messageList) {
            stringBuilder.append(message).append("\n");
        }
        conversationTextView.setText(stringBuilder.toString());
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            messageList.add("Vous : " + message);
            Log.d("sendMsg", loggedUserId+" - "+friendId);
            MessagerieApiService.insert(loggedUserId,friendId,message);
            updateConversation();
            messageEditText.setText("");
        }
    }
}
