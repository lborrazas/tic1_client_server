package tic1.client.services.alert;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ImageRestTemplate {

    public void createImage(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FileSystemResource value = new FileSystemResource(file);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", value);
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<MultiValueMap> ent = new HttpEntity<>(
                body, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity("http://localhost:8080/", ent, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public File showImage(String path){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<File> response = restTemplate.exchange(
                "http://localhost:8080/files/" + path, HttpMethod.GET, null, File.class);
        File file = response.getBody();
        return file;
    }




}
