package server;


import org.jsmpp.PDUStringException;
import org.jsmpp.session.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.db.MySqlRepository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Server implements Runnable{

    Logger logger = LoggerFactory.getLogger(Server.class);

    private MySqlRepository repository = new MySqlRepository();

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

            logger.info("Server session listener is created!");
            logger.info("...waiting for connection...");

            try {
                while (true) {
                    SMPPServerSession session = sessionListener.accept();
                    BindRequest bindRequest = session.waitForBind(5000);
                    //cheking system_id of client in DB
                    String clientId = bindRequest.getSystemId();



                    bindRequest.accept("sys");
                    logger.info("session accepted and bound! id:{}", session.getSessionId());
                    logger.info("bind request: type = {}, sysId = {}", bindRequest.getBindType(),
                            bindRequest.getSystemId());

                    session.setMessageReceiverListener(messageReceiverListener);

                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        //re-interrupt the current thread
                        Thread.currentThread().interrupt();
                    }
                    session.unbindAndClose();
                    logger.info("server session closed");
                }
                } catch (IOException | TimeoutException e) {
                    sessionListener = null;
                    logger.info("Something wrong when accepting or binding session request from client:::{}", e);
                } catch (PDUStringException e) {
                throw new RuntimeException(e);
            }
            sessionListener.close();
            logger.info("server connectionListener closed");
            } catch (IOException e) {
                logger.info("IOException when creating sessionListener:::{}", e);
            }



    }
}
