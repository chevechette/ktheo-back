package fr.ktheo.back.rest;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Tag;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.AddTagToArtworkRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.model.payload.CreateArtworkRequest;
import fr.ktheo.back.repository.ArtworkRepository;
import fr.ktheo.back.repository.TagRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController("ArtworkController")
@RequestMapping("/api/artwork")
@CrossOrigin(value = "*")
public class ArtworkController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository   tagRepository;

    @Autowired
    ArtworkRepository   artworkRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>    getAllArtworks() {
        return ResponseEntity.ok().body(artworkRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getArtwork(@PathVariable long id){
        return ResponseEntity.ok().body(artworkRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteArtwork(@PathVariable long id){
        artworkRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{id}/tag")
    public ResponseEntity<?>    addTagForArtwork(@Valid @RequestBody AddTagToArtworkRequest dto, @PathVariable long id) {
        Artwork artwork;
        Tag     tag;

        artwork = artworkRepository.findById(id).orElseThrow(()->new EntityNotFoundException("id not found :"+id));
        tag = tagRepository.findByTag(dto.getTag()).orElse(dto.toEntity());
        artwork.getTags().add(tag);
        tagRepository.save(tag);
        artworkRepository.save(artwork);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Artwork succesfully tagged"));
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createArtwork(@Valid @RequestBody CreateArtworkRequest dto) {
        User    usr;
        Artwork wrk;

        usr = userRepository.findById(dto.getOwner()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getOwner()));
        wrk = dto.toEntity();
        wrk.setOwner(usr);
        wrk.getOwner().getArtworks().add(wrk);
        artworkRepository.save(wrk);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Artwork registered succesfully"));
    }


    @PutMapping("/{id}")
    public ResponseEntity <?>   updateArtwork(@RequestBody Artwork artwork){
        artworkRepository.save(artwork);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
