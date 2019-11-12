package tic1.client.services.alert;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ImageRestTemplate {



    public void createImage(File image) {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new ClassPathResource(""));
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<LinkedMultiValueMap<String, Object>> body = new HttpEntity<>(
                map);
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
