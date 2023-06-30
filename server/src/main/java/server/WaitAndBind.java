package server;

import org.jsmpp.session.AbstractSession;
import org.jsmpp.session.BindRequest;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitAndBind implements Runnable{

    Logger logger = LoggerFactory.getLogger(WaitAndBind.class);

    private SMPPServerSession session;

    public WaitAndBind(SMPPServerSession session){
        this.session = session;
    }


    @Override
    public void run() {
        try {
            BindRequest bindRequest = session.waitForBind(5000);


            //TODO -------------------------check system_id of client in DB (and access?)
            String clientId = bindRequest.getSystemId(); //it gets id of client system


            //if (repository.getMessageById())
            // * if (checkPassword(bindReq)) { //TODO: checkPassword() - получить из ДБ пароль и сверить
            // *     bindReq.accept(&quot;sys&quot;);
            // * } else {
            // *     bindReq.reject(SMPPConstant.STAT_ESME_RINVPASWD);
            // * }

            //systemid = id of server
            bindRequest.accept(Server.getServerId()); //set id of current server

            logger.info("session accepted and bound! id:{}", session.getSessionId());
            logger.info("bind request: type = {}, sysId = {}", bindRequest.getBindType(), bindRequest.getSystemId());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //re-interrupt the current thread
                Thread.currentThread().interrupt();
            }

            session.unbindAndClose();
            logger.info("server session closed");



        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: {}", e.getMessage());

        }

    }
}
