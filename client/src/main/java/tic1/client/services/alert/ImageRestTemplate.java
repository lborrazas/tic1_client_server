package tic1.client.services.alert;

import javafx.scene.image.Image;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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

    public Image showImage(String path) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Resource> response = restTemplate.exchange(
                "http://localhost:8080/files/" + path, HttpMethod.GET, null, Resource.class);
        Resource file = response.getBody();
        Image image =  new Image(file.getInputStream());
        return image;

    }




}
