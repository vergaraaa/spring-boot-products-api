package com.example.exam.Product.ProductValidation;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.exam.Exceptions.ProfanityFilterException;

public class ProfanityValidator {
    private static final String API_KEY = "uc5muGEB+U+SWcTWkfAFrw==hxSe0tnsBoBr35Ma";
    
    public static boolean containsProfanity(String name, String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ProfanityFilterAPIResponse> nameResponseEntity = restTemplate.exchange(
                "https://api.api-ninjas.com/v1/profanityfilter?text=" + name,
                HttpMethod.GET,
                entity, 
                ProfanityFilterAPIResponse.class
            );
            
            ResponseEntity<ProfanityFilterAPIResponse> descriptionResponseEntity = restTemplate.exchange(
                "https://api.api-ninjas.com/v1/profanityfilter?text=" + description,
                HttpMethod.GET,
                entity, 
                ProfanityFilterAPIResponse.class
            );
    
            return nameResponseEntity.getBody().isHas_profanity() || descriptionResponseEntity.getBody().isHas_profanity();
        } catch (Exception e) {
            throw new ProfanityFilterException();
        }

    }
}
