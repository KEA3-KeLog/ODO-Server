package odo.server.post;


import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateService {
    // [내 애플리케이션] > [앱 키] 에서 확인한 REST API 키 값 입력
    private static final String REST_API_KEY = "3bb30f6d9eab61add4b056721668725f";

    public static String summary(String args) {
        // KoGPT API 호출을 위한 메서드 호출
        args = args + "\n한줄 요약:"; 
        Map<String, Object> response = kogptApi(args, 60, 0.1, 0.3);
        String sum = "";

        // response에서 generations 키의 값을 추출
        List<Map<String, Object>> generations = (List<Map<String, Object>>) response.get("generations");

        // generations 리스트에서 첫 번째 요소를 가져와 text 키의 값을 추출
        if (generations != null && !generations.isEmpty()) {
            Map<String, Object> firstGeneration = generations.get(0);
            Object textValue = firstGeneration.get("text");

            // textValue가 String 타입인 경우에만 sum에 저장
            if (textValue instanceof String) {
                sum = (String) textValue;
            }
        }
        System.out.println(sum);
        return sum;
    }

    public static Map<String, Object> kogptApi(String prompt, int maxTokens, double temperature, double topP) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + REST_API_KEY);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("prompt", prompt);
        requestMap.put("max_tokens", maxTokens);
        requestMap.put("temperature", temperature);
        requestMap.put("top_p", topP);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestMap, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                "https://api.kakaobrain.com/v1/inference/kogpt/generation",
                requestEntity,
                Map.class);

        return responseEntity.getBody();
    }

}
