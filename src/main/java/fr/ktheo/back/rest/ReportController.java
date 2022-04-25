package fr.ktheo.back.rest;

import fr.ktheo.back.model.*;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.model.payload.CreateReportRequest;
import fr.ktheo.back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController("ReportController")
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArtworkRepository       artworkRepository;

    @Autowired
    CommentRepository       commentRepository;

    @Autowired
    ArtworkReportRepository artworkReportRepository;

    @Autowired
    UserReportRepository    userReportRepository;

    @Autowired
    CommentReportRepository commentReportRepository;

    @GetMapping(value="/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCommentReport() {
        return ResponseEntity.ok().body(commentReportRepository.findAll());
    }

    @GetMapping(value="/artwork", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllArtworkReport() {
        return ResponseEntity.ok().body(artworkReportRepository.findAll());
    }

    @GetMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUserReport() {
        return ResponseEntity.ok().body(userReportRepository.findAll());
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentReport(@PathVariable long id) {
        return ResponseEntity.ok().body(commentReportRepository.findById(id));
    }

    @GetMapping("/artwork/{id}")
    public ResponseEntity<?> getArtworkReport(@PathVariable long id) {
        return ResponseEntity.ok().body(artworkReportRepository.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserReport(@PathVariable long id) {
        return ResponseEntity.ok().body(userReportRepository.findById(id));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?>    deleteCommentReport(@PathVariable long id){
        commentReportRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/artwork/{id}")
    public ResponseEntity<?>    deleteArtworkReport(@PathVariable long id){
        artworkReportRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?>    deleteUserReport(@PathVariable long id){
        userReportRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/comment/new")
    public ResponseEntity<?>    createCommentReport(@Valid @RequestBody CreateReportRequest dto) {
        User            usr;
        Comment         comment;
        CommentReport   rep;

        usr = userRepository.findById(dto.getReporter()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        comment = commentRepository.findById(dto.getReportee()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        rep = dto.toCommentReport();

        rep.setReporter(usr);
        rep.setReportedComment(comment);
        commentReportRepository.save(rep);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Comment Report registered succesfully"));
    }

    @PostMapping("/artwork/new")
    public ResponseEntity<?>    createArtworkReport(@Valid @RequestBody CreateReportRequest dto) {
        User            usr;
        Artwork         artwork;
        ArtworkReport   rep;

        usr = userRepository.findById(dto.getReporter()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        artwork = artworkRepository.findById(dto.getReportee()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        rep = dto.toArtworkReport();

        rep.setReporter(usr);
        rep.setReportedArtwork(artwork);
        artworkReportRepository.save(rep);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Artwork Report registered succesfully"));
    }


    @PostMapping("/user/new")
    public ResponseEntity<?>    createUserReport(@Valid @RequestBody CreateReportRequest dto) {
        User         usr;
        User         criminal;
        UserReport   rep;

        usr = userRepository.findById(dto.getReporter()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        criminal = userRepository.findById(dto.getReportee()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getReporter()));
        rep = dto.toUserReport();

        rep.setReporter(usr);
        rep.setReportedUser(criminal);
        userReportRepository.save(rep);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("User report registered succesfully"));
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity <?>   updateCommentReport(@RequestBody CommentReport rep){
        commentReportRepository.save(rep);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/artwork/{id}")
    public ResponseEntity <?>   updateCommentReport(@RequestBody ArtworkReport rep){
        artworkReportRepository.save(rep);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity <?>   updateCommentReport(@RequestBody UserReport rep){
        userReportRepository.save(rep);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
