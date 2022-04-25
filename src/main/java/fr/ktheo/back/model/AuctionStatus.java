package fr.ktheo.back.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "auction_status")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AuctionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @NonNull
    private EAuctionStatus auctionStatus;
}
