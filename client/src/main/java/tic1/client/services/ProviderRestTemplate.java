package tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tic1.client.models.Provider;
import tic1.commons.transfers.ProviderDTO;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderRestTemplate {
    public void createProvider(Provider provider) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<ProviderDTO> body = new HttpEntity<>(
                provider.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/provider", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
//provider/name/{name}
    public void deleteProvider(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/provider/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
    public List<Provider> get(){
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<List<ProviderDTO>> response =
                restTemplate.exchange("http://localhost:8080/provider/", HttpMethod.GET, null,new ParameterizedTypeReference<List<ProviderDTO>>(){});
        System.out.println("RestTemplate response : " + response.getBody());

        List<ProviderDTO> providers = response.getBody();
        return providers.stream()
                .map(Provider::new)
                .collect(Collectors.toList());

    }
    public List<Provider> getByName(String name){
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<List<ProviderDTO>> response =
                restTemplate.exchange("http://localhost:8080/provider/name/"+name, HttpMethod.GET, null,new ParameterizedTypeReference<List<ProviderDTO>>(){});
        System.out.println("RestTemplate response : " + response.getBody());

        List<ProviderDTO> providers = response.getBody();
        return providers.stream()
                .map(Provider::new)
                .collect(Collectors.toList());

    }

    //(get)    update

}
