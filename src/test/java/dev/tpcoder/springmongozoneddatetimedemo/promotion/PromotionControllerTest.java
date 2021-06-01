package dev.tpcoder.springmongozoneddatetimedemo.promotion;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class PromotionControllerTest {

    @InjectMocks
    private PromotionController promotionController;

    @Mock
    private PromotionRepository promotionRepository;

    @Test
    @DisplayName("Find all promotion")
    void findAllPromotion_success_returnFlux() {
        var promotion1 = new Promotion();
        promotion1.setPromotionId(new ObjectId());
        promotion1.setName("promotion-1");

        var promotion2 = new Promotion();
        promotion2.setPromotionId(new ObjectId());
        promotion2.setName("promotion-2");
        promotion2
                .setStartDate(ZonedDateTime.parse("2021-01-01T10:05:10+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        promotion2.setEndDate(ZonedDateTime.parse("2021-01-31T10:05:10+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        var promotionList = List.of(promotion1, promotion2);

        Mockito.when(promotionRepository.findAll()).thenReturn(Flux.fromIterable(promotionList));

        StepVerifier.create(promotionController.findAllPromotion())
                .expectSubscription()
                .assertNext(promotion -> {
                    Assertions.assertEquals(promotion1.getPromotionId(), promotion.getPromotionId());
                    Assertions.assertEquals(promotion1.getName(), promotion.getName());
                    Assertions.assertNull(promotion1.getStartDate());
                    Assertions.assertNull(promotion1.getEndDate());
                })
                .assertNext(promotion -> {
                    Assertions.assertEquals(promotion2.getPromotionId(), promotion.getPromotionId());
                    Assertions.assertEquals(promotion2.getName(), promotion.getName());
                    Assertions.assertEquals(promotion2.getStartDate().toEpochSecond(),
                            promotion.getStartDate().toEpochSecond());
                    Assertions.assertEquals(promotion2.getEndDate().toEpochSecond(),
                            promotion.getEndDate().toEpochSecond());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Create promotion")
    void createPromotion_success_returnMono() {
        var promotion = new Promotion();
        promotion.setPromotionId(new ObjectId());
        promotion.setName("promotion");
        promotion.setStartDate(
                ZonedDateTime.parse("2021-01-01T10:05:10+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
        promotion.setEndDate(
                ZonedDateTime.parse("2021-01-31T10:05:10+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );

        Mockito.when(promotionRepository.save(ArgumentMatchers.any(Promotion.class))).thenReturn(Mono.just(promotion));

        StepVerifier.create(promotionController.createPromotion(promotion))
                .expectSubscription()
                .assertNext(promo -> {
                    Assertions.assertEquals(promotion.getPromotionId(), promo.getPromotionId());
                    Assertions.assertEquals(promotion.getName(), promo.getName());
                    Assertions.assertEquals(promotion.getStartDate().toEpochSecond(),
                            promo.getStartDate().toEpochSecond());
                    Assertions.assertEquals(promotion.getEndDate().toEpochSecond(), promo.getEndDate().toEpochSecond());
                })
                .verifyComplete();
    }
}