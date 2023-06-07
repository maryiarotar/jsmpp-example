package org.example.client;

import org.jsmpp.bean.BindType;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    Logger logger = LoggerFactory.getLogger(Client.class);

    private static final String HOST = "localhost";
    private static final int PORT = 8088;

    //systemId & password предоставляются провайдером для идентификации клиента на сервере
    private static final String DEFAULT_SYSID = "dflt";
    private static final String DEFAULT_PASS = "1234";


    public void sendFromClient(String str){

        try {

            SMPPSession session = new SMPPSession();

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

        } catch (Exception e) {
            logger.info("Exception in client code::{}", e);
        }

    }



}
