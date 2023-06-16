package client;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.*;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.session.SubmitSmResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable {

    Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String HOST = "localhost";
    private static final int PORT = 8011;

    //systemId & password предоставляются провайдером для идентификации клиента на сервере
    private static final String DEFAULT_SYSID = "sns3";
    private static final String DEFAULT_PASS = "jvSzfAMc";

    private SMPPSession session;

    @Override
    public void run(){

    }

    public void sendFromClient(String str){

        try {

            session = new SMPPSession();

            //    bindType - is the bind type. (rx, tx, trx)
            //    systemId - is the system id. (from provider)
            //    password - is the password. (from provider)
            //    systemType - is the system type. (C-Octet String ., “EMAIL”, “WWW”, cmt, vms, ota, etc)
            //    addrTon - is the address TON.
            //    addrNpi - is the address NPI.
            //    addressRange - is the address range to which client can send/get messages
            BindParameter bindParameter = new BindParameter(
                    BindType.BIND_TRX, DEFAULT_SYSID, DEFAULT_PASS, null,
                    TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null);


            String SMSCSystemId = session.connectAndBind(HOST, PORT, bindParameter);
            logger.info("session success, id::{}", SMSCSystemId);

            session.setMessageReceiverListener(new MessageReceiverListenerImpl());


            SubmitSmResult res = session.submitShortMessage("CMT", TypeOfNumber.NATIONAL, NumberingPlanIndicator.NATIONAL,
                    "1234", TypeOfNumber.NATIONAL, NumberingPlanIndicator.NATIONAL, "4321", new ESMClass(67),
                    (byte) 0, (byte) 3, "000000013500000R",  "000000050000000R", new RegisteredDelivery(5), (byte) 1,
                    new GeneralDataCoding(Alphabet.ALPHA_LATIN1), (byte) 0, str.getBytes(StandardCharsets.UTF_8));

            if (res != null ) { logger.info("message is sended...-> sms_id: ", res); }
            else { logger.warn("message is sended...-> sms_id: ", res); }



        } catch (IOException |ResponseTimeoutException | PDUException | InvalidResponseException | NegativeResponseException e) {
            logger.info("Exception while binding in client code::{}", e.getMessage());
        }

    }

    /*
    private Runnable sendMessage(String message){

        return new Runnable() {
            @Override
            public void run() {

                session.submitShortMessage(


                );

            }
        }

    }
*/


}
