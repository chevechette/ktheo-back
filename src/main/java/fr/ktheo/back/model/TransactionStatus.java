package fr.ktheo.back.model;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transaction_status")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TransactionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, name = "transaction_status")
    @NonNull
    private ETransactionStatus transactionStatus;
}
