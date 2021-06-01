package dev.tpcoder.springmongozoneddatetimedemo.promotion;

import java.time.ZonedDateTime;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "promotion")
public class Promotion {

    @Id
    private ObjectId promotionId;

    private String name;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;
}
