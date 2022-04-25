package fr.ktheo.back.rest;

import fr.ktheo.back.model.Tag;
import fr.ktheo.back.model.payload.CreateTagRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.ArtworkRepository;
import fr.ktheo.back.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("TagController")
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    TagRepository tagRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>    getAllTags() {
        return ResponseEntity.ok().body(tagRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getTag(@PathVariable long id) {
        return ResponseEntity.ok().body(tagRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteArtworkTag(@PathVariable long id){
        tagRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createTag(@Valid @RequestBody CreateTagRequest dto) {
        Tag tag;

        tag = dto.toEntity();
        tagRepository.save(tag);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Tag registered succesfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity <?>   updateTag(@RequestBody Tag tag){
        tagRepository.save(tag);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
