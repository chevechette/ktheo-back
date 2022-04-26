package fr.ktheo.back.repository;

import fr.ktheo.back.model.AuctionStatus;
import fr.ktheo.back.model.EAuctionStatus;
import fr.ktheo.back.model.ETransactionStatus;
import fr.ktheo.back.model.TransactionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuctionStatusRepository extends CrudRepository<AuctionStatus, Long> {
    Optional<AuctionStatus> findByAuctionStatus(EAuctionStatus status);

}
