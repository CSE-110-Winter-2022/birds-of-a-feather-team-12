package com.example.team12bof;

import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FakedMessageListener extends MessageListener{

    private final MessageListener messageListener;
    private final ArrayList<Message> MessageList;

    public FakedMessageListener(MessageListener realMessageListener, ArrayList<String> messages) {
        this.messageListener = realMessageListener;
       MessageList = new ArrayList<>();
        for (String s : messages) {
            Message newMessage = new Message(s.getBytes(StandardCharsets.UTF_8));
            MessageList.add(newMessage);
        }
    }
    public void getMessage() {
        for (Message msg : MessageList) {
            this.messageListener.onFound(msg);
        }
        return;
    }

}