package org.example.server;


import org.jsmpp.session.MessageReceiverListener;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.SMPPServerSessionListener;
import org.jsmpp.session.BindRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Server implements Runnable{

    Logger logger = LoggerFactory.getLogger(Server.class);

    private static final int SMPP_PORT = 8088;

    public void run() {

        SMPPServerSessionListener sessionListener;
        //TODO: оформить в один try и разные catch-и
        try{
            sessionListener = new SMPPServerSessionListener(SMPP_PORT);
            sessionListener.setSessionStateListener(new SessionStateListenerImpl()); //for state changing
            sessionListener.setMessageReceiverListener(new MessageReceiverListenerImpl()); //receive messages
            logger.info("Server session listener is created!");



        } catch (IOException e) {
            sessionListener = null;
            logger.info("IOException when creating sessionListener:::{}", e);
        }

        if (sessionListener != null) {
            logger.info("...waiting for connection...");
            try {
                while (true) {
                    SMPPServerSession session = sessionListener.accept();
                    BindRequest bindRequest = session.waitForBind(1000);
                    logger.info("session accepted and bound! id:{}", session.getSessionId());
                    logger.info("bind request type = {}, password = {}", bindRequest.getBindType(),
                            bindRequest.getPassword());
                    logger.info("bind sysId = {}", bindRequest.getSystemId());
                }


            } catch (Exception e) {
                logger.info("Something wrong when accepting or binding session request from client:::{}", e);
            }


        }
        try {
            sessionListener.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
