package fr.ktheo.back.repository;

import fr.ktheo.back.model.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
