package server;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import org.jsmpp.bean.*;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.session.*;
import org.jsmpp.util.MessageIDGenerator;
import org.jsmpp.util.MessageId;
import org.jsmpp.util.RandomMessageIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MessageReceiverListenerImpl implements ServerMessageReceiverListener {

    Logger logger =LoggerFactory.getLogger(MessageReceiverListenerImpl.class);

    

    MessageIDGenerator messageIDGenerator = new RandomMessageIDGenerator();

    @Override
    public SubmitSmResult onAcceptSubmitSm(SubmitSm submitSm, SMPPServerSession smppServerSession)
            throws ProcessRequestException {

        MessageId messageId = messageIDGenerator.newMessageId();

        byte[] sms_bytes = submitSm.getShortMessage();
        String sms = IntStream.range(0, sms_bytes.length).mapToObj(i -> Character.toString((char)sms_bytes[i])).collect(Collectors.joining(""));
        System.out.println("short messages received ----> " + Arrays.toString(sms_bytes));

        logger.info("message from client received! =" + sms);

        //TODO: send to DB


        return new SubmitSmResult(messageId, new OptionalParameter[0]);
    }

    @Override
    public SubmitMultiResult onAcceptSubmitMulti(SubmitMulti submitMulti, SMPPServerSession smppServerSession) throws ProcessRequestException {
        return null;
    }

    @Override
    public QuerySmResult onAcceptQuerySm(QuerySm querySm, SMPPServerSession smppServerSession) throws ProcessRequestException {
        return null;
    }

    @Override
    public void onAcceptReplaceSm(ReplaceSm replaceSm, SMPPServerSession smppServerSession) throws ProcessRequestException {

    }

    @Override
    public void onAcceptCancelSm(CancelSm cancelSm, SMPPServerSession smppServerSession) throws ProcessRequestException {

    }

    @Override
    public BroadcastSmResult onAcceptBroadcastSm(BroadcastSm broadcastSm, SMPPServerSession smppServerSession) throws ProcessRequestException {
        return null;
    }

    @Override
    public void onAcceptCancelBroadcastSm(CancelBroadcastSm cancelBroadcastSm, SMPPServerSession smppServerSession) throws ProcessRequestException {

    }

    @Override
    public QueryBroadcastSmResult onAcceptQueryBroadcastSm(QueryBroadcastSm queryBroadcastSm, SMPPServerSession smppServerSession) throws ProcessRequestException {
        return null;
    }

    @Override
    public DataSmResult onAcceptDataSm(DataSm dataSm, Session session) throws ProcessRequestException {
        return null;
    }
}
