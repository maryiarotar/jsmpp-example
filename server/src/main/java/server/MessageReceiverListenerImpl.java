package server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jsmpp.bean.*;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.session.*;
import org.jsmpp.util.MessageIDGenerator;
import org.jsmpp.util.MessageId;
import org.jsmpp.util.RandomMessageIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.configs.AppInjector;
import server.db.dto.MessageDto;
import server.service.MessageService;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MessageReceiverListenerImpl implements ServerMessageReceiverListener {

    Logger logger = LoggerFactory.getLogger(MessageReceiverListenerImpl.class);

    Injector injector = Guice.createInjector(new AppInjector());
    MessageService service = injector.getInstance(MessageService.class);

    MessageIDGenerator messageIDGenerator = new RandomMessageIDGenerator();

    @Override
    public SubmitSmResult onAcceptSubmitSm(SubmitSm submitSm, SMPPServerSession smppServerSession)
            throws ProcessRequestException {

        MessageId messageId = messageIDGenerator.newMessageId();

        byte[] sms_bytes = submitSm.getShortMessage();
        //String sms = IntStream.range(0, sms_bytes.length).mapToObj(i -> Character.toString((char)sms_bytes[i])).collect(Collectors.joining(""));

        MessageDto messageDto = MessageDto.parseToMessage(sms_bytes);

        logger.debug("id={}, message from client received! = {}", messageId, messageDto);

        service.insertMessage(messageDto); //sends to DB (table messages)

        if (SMSCDeliveryReceipt.SUCCESS.containedIn(submitSm.getRegisteredDelivery()) ||
                SMSCDeliveryReceipt.SUCCESS_FAILURE.containedIn(submitSm.getRegisteredDelivery())) {

        }

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
