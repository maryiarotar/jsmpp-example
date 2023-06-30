package server.workers;

import server.db.dto.MessageDto;

import java.util.concurrent.PriorityBlockingQueue;

public class SubmitSmWorker {

    public static PriorityBlockingQueue<MessageDto> queue;

    public SubmitSmWorker(){
        queue = new PriorityBlockingQueue<>();
    }

    public void addMessage(MessageDto message){
        queue.add(message);
    }

    //TODO add queue to DB when limit of messages (to set limit, then insertBatch)

}
