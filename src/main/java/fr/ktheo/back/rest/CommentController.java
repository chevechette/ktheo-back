package fr.ktheo.back.rest;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Comment;
import fr.ktheo.back.model.Tag;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.CreateCommentRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.ArtworkRepository;
import fr.ktheo.back.repository.CommentRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController("CommentController")
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    CommentRepository   commentRepository;

    @Autowired
    UserRepository      userRepository;

    @Autowired
    ArtworkRepository   artworkRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>    getAllComments() {
        return ResponseEntity.ok().body(commentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getComment(@PathVariable long id) {
        return ResponseEntity.ok().body(commentRepository.findById(id));
    }

    @GetMapping("/artwork/{id}")
    public ResponseEntity<?>    getAllCommentByArtwork(@PathVariable long id) {
        Artwork art;

        art = artworkRepository.findById(id).orElseThrow(()->new EntityNotFoundException("id not found :"+id));
        return ResponseEntity.ok().body(commentRepository.findAllByTopic(art));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteComment(@PathVariable long id){
        commentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createComment(@Valid @RequestBody CreateCommentRequest dto) {
        Comment comment;
        Artwork artwork;
        User    usr;

        artwork = artworkRepository.findById(dto.getTopic()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getTopic()));
        usr = userRepository.findById(dto.getUser()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getUser()));
        comment = dto.toEntity();
        comment.setAuthor(usr);
        comment.setTopic(artwork);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Comment registered succesfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity <?>   updateComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
