package com.digital.signage.service.impl;


import com.digital.signage.models.MovingWallModel.VASTModel;
import com.digital.signage.util.MovingWallUtil.XmlTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;


@Service
public class movngWallService {

    private static final String API_URL = "https://pub.lmx.ai/ssp-project/api/v2/rest/dv/vast";
    public VASTModel getData() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Token", "eyJhbGcMTY5NzE4NzIwOQ");
        String param1 = "GD-00000-02491";
        String param2 = "IND-PAN-D-00000-96526";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        String apiUrlWithParams = String.format("%s?deal=%s&uuid=%s", API_URL, param1, param2);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrlWithParams,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        String xml = null;
        if (response.getStatusCode().is2xxSuccessful()) {
             xml = response.getBody();
            System.out.println("Response: " + xml);
        } else {
            System.err.println("Error - HTTP Status Code: " + response.getStatusCode());
        }

        if(xml==null)
            return null;

//        XmlMapper xmlMapper = new XmlMapper();
//        // Convert XML to JsonNode
//        JsonNode jsonNode = xmlMapper.readTree(xml);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonOutput = objectMapper.writeValueAsString(jsonNode);

        Document mainDoc = XmlTools.stringToDocument(xml);
        VASTModel vastModel = new VASTModel(mainDoc);
        System.out.println(vastModel.getDuration());
        System.out.println(vastModel.getImpressions());
        System.out.println(vastModel.getMediaFiles().get(0));
        System.out.println(vastModel.getSkipOffset());
        return vastModel;
    }
}
