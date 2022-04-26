package fr.ktheo.back.repository;

import fr.ktheo.back.model.Auction;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, Long> {
}
