package fr.ktheo.back.rest;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.model.payload.CreateArtworkRequest;
import fr.ktheo.back.repository.ArtworkRepository;
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

    @PostMapping("/new")
    public ResponseEntity<?>    createArtwork(@Valid @RequestBody CreateArtworkRequest dto) {
        User    usr;
        Artwork wrk;

        usr = userRepository.findById(dto.getOwner()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getOwner()));
        wrk = dto.toEntity();
        wrk.setOwner(usr);
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
