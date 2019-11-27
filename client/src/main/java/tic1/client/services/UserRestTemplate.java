package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.User;
import tic1.client.models.UserAdmin;
import tic1.client.models.UserClient;
import tic1.client.models.UserManager;
import tic1.commons.transfers.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRestTemplate {

    public User showUser(long id) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                "http://localhost:8080/user/" + id, HttpMethod.GET, null, UserDTO.class);
        UserDTO userDTO = response.getBody();
        switch (userDTO.getType()) {
            case "Admin":
                return new UserAdmin(userDTO);

            case "Manager":
                return new UserManager(userDTO);

            case "Client":
                return new UserClient(userDTO);

            default:
                return new User(userDTO);

        }
    }


    @Deprecated
    public List<Object> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                "http://localhost:8080/user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>() {
                });
        List<UserDTO> users = response.getBody();

        List<Object> usersMix = new ArrayList<>();
        for (UserDTO user : users) {
            switch (user.getType()) {

                case "Admin":
                    usersMix.add(new UserAdmin(user));

                    break;
                case "Manager":
                    usersMix.add(new UserManager(user));

                    break;
                case "Client":
                    usersMix.add(new UserClient(user));
                    break;
                default:
                    usersMix.add(new User(user));

            }
        }
        return usersMix;


    }


    public void deleteUser(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/user/" + id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    public void createUser(User user) {
        UserDTO userDTO = user.toDTO();
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<UserDTO> body = new HttpEntity<>(
                userDTO);
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/user", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public Object findByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                "http://localhost:8080/user/name/" + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UserDTO>() {
                });
        UserDTO user = response.getBody();
        switch (user.getType()) {

            case "Admin":
                return new UserAdmin(user);
            case "Manager":
                return new UserManager(user);
            case "Client":
                return new UserClient(user);
            default:
                return new User(user);
        }
    }

    public Boolean userExists(String name) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
                "http://localhost:8080/user/exist/" + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                });

        return response.getBody();
    }


}
