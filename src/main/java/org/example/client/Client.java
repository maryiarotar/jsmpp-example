package org.example.client;

import org.jsmpp.bean.BindType;
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

            BindParameter bindParametr = new BindParameter(
                    BindType.BIND_TRX,
            );

            session.connectAndBind(HOST, PORT, )

        } catch (Exception e) {
            logger.info("Exception in client code::{}", e);
        }

    }



}
