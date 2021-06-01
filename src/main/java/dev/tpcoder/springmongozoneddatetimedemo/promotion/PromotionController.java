package dev.tpcoder.springmongozoneddatetimedemo.promotion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class PromotionController {

    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping("/promotions")
    public Flux<Promotion> findAllPromotion() {
        return promotionRepository.findAll();
    }

    @PostMapping("/promotions")
    public Mono<Promotion> createPromotion(@RequestBody Promotion promotion) {
        return promotionRepository.save(promotion);
    }
}
