package wisebite.wisebite.dto;
import wisebite.wisebite.model.Client;
import java.util.Date;

public class MapperClient {

    public ClientDTO toDto(Client client) {
        String firstName = client.getFirstName();
        String infix = client.getInfix();
        String lastName = client.getLastName();
        double weight = client.getWeight();
        int height = client.getHeight();
        Date startDate = client.getStartDate();
        return new ClientDTO(firstName, infix, lastName, weight, height, startDate);
    }
}
