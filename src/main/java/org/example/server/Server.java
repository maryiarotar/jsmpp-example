package org.example.server;

import org.jsmpp.session.MessageReceiverListener;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.SMPPServerSessionListener;
import org.jsmpp.session.BindRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Server {

    Logger logger = LoggerFactory.getLogger(Server.class);

    private static final int SMPP_PORT = 8088;

    public void startServer() throws IOException {

        SMPPServerSessionListener sessionListener;

        try{
            sessionListener = new SMPPServerSessionListener(SMPP_PORT);
            sessionListener.setSessionStateListener(new SessionStateListenerImpl());
            sessionListener.setMessageReceiverListener(new MessageReceiverListenerImpl());
            logger.info("Server session listener is created!");



        } catch (IOException e) {
            sessionListener = null;
            logger.info("IOException when creating sessionListener:::{}", e);
        }

        if (sessionListener != null) {
            logger.info("...waiting for connection...");
            try {

                SMPPServerSession session = sessionListener.accept();
                BindRequest bindRequest = session.waitForBind(1000);
                bindRequest.
                logger.info("session accepted and bound! id:{}", session.getSessionId());



            } catch (Exception e) {
                logger.info("Something wrong when accepting or binding session request from client:::{}", e);
            }


        }
        sessionListener.close();


    }
}
