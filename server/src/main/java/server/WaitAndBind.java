package server;

import org.jsmpp.session.AbstractSession;
import org.jsmpp.session.BindRequest;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.Session;

public class WaitAndBind implements Runnable{

    private SMPPServerSession session;

    public WaitAndBind(SMPPServerSession session){
        this.session = session;
    }


    @Override
    public void run() {
        try {
            BindRequest bindRequest = session.waitForBind(5000);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //cheking system_id of client in DB
        String clientId = bindRequest.getSystemId();
//TODO -------------------------
        //if (repository.getMessageById())
        // * if (checkPassword(bindReq)) { //TODO: checkPassword() - получить из ДБ пароль и сверить
        // *     bindReq.accept(&quot;sys&quot;);
        // * } else {
        // *     bindReq.reject(SMPPConstant.STAT_ESME_RINVPASWD);
        // * }

        //systemid = id of server
        bindRequest.accept(SERVER_ID);
        logger.info("session accepted and bound! id:{}", session.getSessionId());
        logger.info("bind request: type = {}, sysId = {}", bindRequest.getBindType(),
                bindRequest.getSystemId());

    }
}
