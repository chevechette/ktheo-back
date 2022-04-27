package fr.ktheo.back.rest;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Asset;
import fr.ktheo.back.model.Tag;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.CreateAssetRequest;
import fr.ktheo.back.model.payload.CreateTagRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.ArtworkRepository;
import fr.ktheo.back.repository.AssetRepository;
import fr.ktheo.back.repository.TagRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;

@RestController("AssetController")
@RequestMapping("/api/asset")
@CrossOrigin(value = "*")
public class AssetController {
    @Autowired
    UserRepository      userRepository;

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    AssetRepository assetRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAssets() {
        return ResponseEntity.ok().body(assetRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getTag(@PathVariable long id) {
        return ResponseEntity.ok().body(assetRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteArtworkTag(@PathVariable long id){
        assetRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createTag(@Valid @RequestBody CreateAssetRequest dto) {
        Asset   asset;
        User    usr;

        usr = userRepository.findById(dto.getUploader()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getUploader()));
        asset = dto.toEntity();
        asset.setUploader(usr);
        for (Long artId : dto.getArtworks()) {
            Artwork artwork;

            artwork = artworkRepository.findById(artId).orElseThrow(()->new EntityNotFoundException("id not found :"+artId));
            artwork.getPhotos().add(asset);
            asset.getArtworks().add(artwork);
        }
        usr.getAssets().add(asset);
        assetRepository.save(asset);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Asset registered succesfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity <?>   updateTag(@RequestBody Asset asset){
        assetRepository.save(asset);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
