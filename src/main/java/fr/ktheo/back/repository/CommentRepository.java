package fr.ktheo.back.repository;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Comment;
import fr.ktheo.back.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Iterable<Comment>> findAllByTopic(Artwork topic);

}
