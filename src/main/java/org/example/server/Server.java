package org.example.server;


import org.jsmpp.session.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Server implements Runnable{

    Logger logger = LoggerFactory.getLogger(Server.class);

    private static final int SMPP_PORT = 8088;

    private ServerMessageReceiverListener messageReceiverListener = new MessageReceiverListenerImpl();

    public void run() {

        SMPPServerSessionListener sessionListener;

        try{
            sessionListener = new SMPPServerSessionListener(SMPP_PORT);
            sessionListener.setSessionStateListener(new SessionStateListenerImpl()); //for state changing
            sessionListener.setMessageReceiverListener(messageReceiverListener); //receive messages
            logger.info("Server session listener is created!");

            logger.info("...waiting for connection...");

            try {
                while (true) {
                    SMPPServerSession session = sessionListener.accept();
                    BindRequest bindRequest = session.waitForBind(5000);
                    logger.info("session accepted and bound! id:{}", session.getSessionId());
                    logger.info("bind request: type = {}, sysId = {}", bindRequest.getBindType(),
                            bindRequest.getSystemId());

                    session.setMessageReceiverListener(messageReceiverListener);



                }
                } catch (IOException | TimeoutException e) {
                    sessionListener = null;
                    logger.info("Something wrong when accepting or binding session request from client:::{}", e);
                }

            sessionListener.close();

            } catch (IOException e) {
                logger.info("IOException when creating sessionListener:::{}", e);
            }



    }
}
