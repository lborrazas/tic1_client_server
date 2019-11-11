package tic1.client.services.alert;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class ImageRestTemplate {



    public void createImage(MultipartFile image) {
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<MultipartFile> body = new HttpEntity<>(
                image);
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
