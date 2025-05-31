package dk.sdu.mmmi.cbse.common.data;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScoreClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081/score";

    public ScoreClient() {
        this.restTemplate = new RestTemplate();
    }

    public int getScore() {
        try {
            return restTemplate.getForObject(baseUrl, Integer.class);
        } catch (Exception e) {
            return -1; // fallback on failure
        }
    }

    public void addScore(int points) {
        try {
            restTemplate.postForObject(baseUrl + "/add?points=" + points, null, Void.class);
        } catch (Exception e) {
            System.err.println("Failed to add score: " + e.getMessage());
        }
    }

    public void resetScore() {
        try {
            restTemplate.postForObject(baseUrl + "/reset", null, Void.class);
        } catch (Exception e) {
            System.err.println("Failed to reset score: " + e.getMessage());
        }
    }
}
