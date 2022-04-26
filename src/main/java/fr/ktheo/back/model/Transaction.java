package fr.ktheo.back.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;

    @ManyToOne
    private User            owner;

    @ManyToOne
    private User            buyer;

    @OneToOne
    private Artwork         artwork;

    private double          price;

    @ManyToOne
    private TransactionStatus   status;

    @Column(name = "bought_on")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private LocalDateTime   boughtOn;
}
