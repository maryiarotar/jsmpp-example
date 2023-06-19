package server.db.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageDto {

    public Long id = null;
    public String message;
    public String date;

    public MessageDto parseToMessage(ResultSet resultSet){
        return null;
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

    //String clientId; //to make foreign key to clients who send messages

}
