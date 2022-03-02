package com.example.team12bof;

import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class FakedMessageListener extends MessageListener{

    private final MessageListener messageListener;
    private final ArrayList<Message> allMessages;

    public FakedMessageListener(MessageListener realMessageListener, ArrayList<String> messages) {
        this.messageListener = realMessageListener;
        allMessages = new ArrayList<>();
        for (String s : messages) {
            Message newMessage = new Message(s.getBytes(StandardCharsets.UTF_8));
            allMessages.add(newMessage);
        }
    }
    public void getMessage() {
        for (Message m : allMessages) {
            this.messageListener.onFound(m);
        }
        return;
    }

}
