package server.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    public Long id = null;
    public String systemId;

    public String password;



    public static ClientDto parseToClientDto(ResultSet resultSet) throws Exception{

        ClientDto client = null;

        try {
            Long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);

            client = new ClientDto(id, name, password);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return client;
    }


}
