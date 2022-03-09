package com.example.team12bof;


import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class FakedMessageListener extends MessageListener {

    private final MessageListener messageListener;


    public FakedMessageListener(MessageListener messageListener, String msgString) {
        this.messageListener = messageListener;

            Message message = new Message(msgString.getBytes(StandardCharsets.UTF_8));
            this.messageListener.onFound(message);
            this.messageListener.onLost(message);

    }

}
