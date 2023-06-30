package server;


import com.google.inject.Inject;
import org.jsmpp.PDUStringException;
import org.jsmpp.session.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Server implements Runnable{

    private static final String SERVER_ID = "my-Server";
    private static final int howLongToLive = 60000;
    Logger logger = LoggerFactory.getLogger(Server.class);
    ExecutorService executor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());

    private static int SMPP_PORT = 8011;

    public Server(){}

    public Server(int port){
        SMPP_PORT = port;
    }

    private ServerMessageReceiverListener messageReceiverListener = new MessageReceiverListenerImpl();

    public void run() {

        SMPPServerSessionListener sessionListener;

        try{
            sessionListener = new SMPPServerSessionListener(SMPP_PORT);
            sessionListener.setSessionStateListener(new SessionStateListenerImpl()); //for state changing
            sessionListener.setMessageReceiverListener(messageReceiverListener); //receive messages

            logger.info("Server session listener is created! ...waiting for connection...");

            while (true) {
                    SMPPServerSession session = sessionListener.accept();

                    executor.execute(new WaitAndBind(session));

                    try {
                        Thread.sleep(60000);
                        executor.shutdown();
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

            }
            sessionListener.close();
            logger.info("server connectionListener closed");
            } catch (IOException e) {
                logger.info("IOException when creating sessionListener:::{}", e);
            }



    }


    //TODO: для имени серверов, каждый из которых запускается в новом потоке, добавить аргумент -int в конец имени его запихнуть
    public static String getServerId(){
        return SERVER_ID;
    }




}
