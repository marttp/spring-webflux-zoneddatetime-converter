package dev.tpcoder.springmongozoneddatetimedemo.promotion;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends ReactiveMongoRepository<Promotion, ObjectId> {

}
