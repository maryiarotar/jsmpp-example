package server;

import org.jsmpp.extra.SessionState;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.Session;
import org.jsmpp.session.SessionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
*  Class to process state changes (for example, bound, unbound, close states)
* */
public class SessionStateListenerImpl implements SessionStateListener {

    Logger logger = LoggerFactory.getLogger(SessionStateListenerImpl.class);

    @Override
    public void onStateChange(SessionState newState, SessionState oldState, Session session) {

        logger.info("State changed from " + oldState + " to " + newState +
                ", in session with id=" + session.getSessionId());

        if (newState == SessionState.OPEN) {
            logger.info("State of session is:::OPENED");
        } else if (newState == SessionState.BOUND_RX || newState == SessionState.BOUND_TX
                                || newState == SessionState.BOUND_TRX) {
            logger.info("State of session is:::{}", newState);
        } else if (newState == SessionState.UNBOUND){
            logger.info("State of session is:::UNBOUND");
        } else if (newState == SessionState.CLOSED) {
            logger.info("State of session is:::CLOSED");
        }

    }
}
