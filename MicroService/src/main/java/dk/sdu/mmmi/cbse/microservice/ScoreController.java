package dk.sdu.mmmi.cbse.microservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private int score = 0;

    @GetMapping
    public int getScore() {
        return score;
    }

    @PostMapping("/add")
    public void addScore(@RequestParam int points) {
        score += points;
    }

    @PostMapping("/reset")
    public void resetScore() {
        score = 0;
    }
}
