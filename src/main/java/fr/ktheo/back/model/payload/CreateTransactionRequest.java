package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Transaction;
import fr.ktheo.back.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTransactionRequest {
    private long owner;

    private long buyer;

    private long artwork;

    private double price;

    @Column(name = "bought_on")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private LocalDateTime boughtOn;

    public Transaction toEntity() {
        Transaction trans;

        trans = new Transaction();
        trans.setPrice(price);
        trans.setBoughtOn(this.boughtOn);
        return trans;
    }
}
