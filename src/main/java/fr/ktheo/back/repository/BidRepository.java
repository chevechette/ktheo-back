package fr.ktheo.back.repository;

import fr.ktheo.back.model.Auction;
import fr.ktheo.back.model.Bid;
import fr.ktheo.back.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BidRepository extends CrudRepository<Bid, Long> {
    Optional<Iterable<Bid>> findAllByAuction(Auction auction);
}
