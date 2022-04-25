package fr.ktheo.back.repository;

import fr.ktheo.back.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
