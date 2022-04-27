package fr.ktheo.back.repository;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.ETransactionStatus;
import fr.ktheo.back.model.Tag;
import fr.ktheo.back.model.TransactionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Optional<Tag> findByTag(String tag);
    Optional<Iterable<Tag>> findByArtworksContains(Artwork artwork);
}
