package pl.adreszler.textstats;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class TextStatsController {

    private final TextStatsService statsService;

    TextStatsController(TextStatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/calculate")
    String calculateStats(@RequestParam String text,
                          @RequestParam boolean lengthFlag,
                          @RequestParam boolean wordCountFlag,
                          @RequestParam boolean palindromeFlag,
                          @RequestParam boolean mostPopularWordFlag,
                          Model model) {
        if (text.equals("")) {
            return "error";
        }
        TextStatsDto stats = statsService.calculateStats(text, lengthFlag, wordCountFlag, palindromeFlag, mostPopularWordFlag);
        model.addAttribute("stats", stats);
        model.addAttribute("text", text);

        return "success";
    }
}