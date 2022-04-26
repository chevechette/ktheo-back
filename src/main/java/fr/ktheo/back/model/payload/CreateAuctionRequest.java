package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAuctionRequest {
    private long seller;

    private long artwork;

    private LocalDateTime startsOn;

    private LocalDateTime endsOn;

    public Auction toEntity() {
        Auction auction;

        auction = new Auction();
        auction.setStartsOn(this.startsOn);
        auction.setEndsOn(this.endsOn);
        return auction;
    }
}
