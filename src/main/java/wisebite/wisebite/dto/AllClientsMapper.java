package wisebite.wisebite.dto;

import wisebite.wisebite.model.Client;

import java.util.ArrayList;
import java.util.List;

public class AllClientsMapper {
    public List<AllClientsDTO> toDto(List<Client> clients) {
        List<AllClientsDTO> dtos = new ArrayList<>();
        for (Client client : clients) {
            String firstName = client.getFirstName();
            String infix;
            if (client.getInfix() == null){
                 infix = " ";
            } else {
                 infix = client.getInfix();
            }
            String lastName = client.getLastName();
            dtos.add(new AllClientsDTO(firstName, infix, lastName));
        }
        return dtos;
    }
}

