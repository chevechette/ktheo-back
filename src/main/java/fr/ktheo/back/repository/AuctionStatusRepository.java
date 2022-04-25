package fr.ktheo.back.repository;

import fr.ktheo.back.model.AuctionStatus;
import org.springframework.data.repository.CrudRepository;

public interface AuctionStatusRepository extends CrudRepository<AuctionStatus, Long> {
}
