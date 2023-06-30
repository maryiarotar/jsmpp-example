package server.db.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.MessageReceiverListenerImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageDto implements Comparable {

    public Long id = null;
    public String message;
    public String date;

    public static MessageDto parseToMessage(ResultSet resultSet) throws Exception{

        MessageDto messageDto = null;

        try {
            Long id = resultSet.getLong(1);
            String message = resultSet.getString(2);
            String date = resultSet.getString(3);

            messageDto = createInstance(id, message, date);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return messageDto;
    }


    public static MessageDto parseToMessage(byte[] smsInBytes){

        String message = IntStream.range(0, smsInBytes.length).mapToObj(i -> Character.toString((char)smsInBytes[i])).collect(Collectors.joining(""));
        Date rawDate = new Date(System.currentTimeMillis());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rawDate);

        return createInstance(null, message, date);
    }


    public static MessageDto createInstance(Long id, String message, String date){
        return new MessageDto(id, message, date);
    }

    public java.util.Date parseToDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        try {
            java.util.Date date = dateFormat.parse(this.date);
            return date;
        } catch (ParseException e) {
            LoggerFactory.getLogger(MessageDto.class).warn("Can not parse String to Date, {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int compareTo(Object o) {
        java.util.Date date1 = this.parseToDate();
        java.util.Date date2 = ((MessageDto) o).parseToDate();

        return date1.compareTo(date2);
    }

    //String clientId; //to make foreign key to clients who send messages

}
