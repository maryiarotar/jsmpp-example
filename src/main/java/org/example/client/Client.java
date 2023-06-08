package org.example.client;

import org.jsmpp.bean.BindType;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Client implements Runnable {

    Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String HOST = "localhost";
    private static final int PORT = 8000;

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






        } catch (IOException e) {
            logger.info("Exception while binding in client code::{}", e.getMessage());
        }

    }

    private Runnable sendMessage(String message){

        return new Runnable() {
            @Override
            public void run() {

                session.submitShortMessage(


                );

            }
        }

    }



}
