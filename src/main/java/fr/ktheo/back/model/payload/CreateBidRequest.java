package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Auction;
import fr.ktheo.back.model.Bid;
import fr.ktheo.back.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBidRequest {
    private long bidder;

    private double              bidAmount;

    private String              currency;

    @Column(name = "datetime")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private LocalDateTime datetime;

    private long auction;

    public Bid  toEntity() {
        Bid bid;

        bid = new Bid();
        bid.setBidAmount(this.bidAmount);
        bid.setCurrency(this.currency);
        bid.setDatetime(this.datetime);
        return bid;
    }
}
