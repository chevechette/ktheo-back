package fr.ktheo.back.rest;

import fr.ktheo.back.model.*;
import fr.ktheo.back.model.payload.CreateArtworkRequest;
import fr.ktheo.back.model.payload.CreateAuctionRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.ArtworkRepository;
import fr.ktheo.back.repository.AuctionRepository;
import fr.ktheo.back.repository.AuctionStatusRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController("AuctionController")
@RequestMapping("/api/auction")
@CrossOrigin(value = "*")
public class AuctionController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AuctionStatusRepository auctionStatusRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAuction() {
        return ResponseEntity.ok().body(auctionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getAuction(@PathVariable long id){
        return ResponseEntity.ok().body(auctionRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteAuction(@PathVariable long id){
        auctionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createArtwork(@Valid @RequestBody CreateAuctionRequest dto) {
        User usr;
        Artwork wrk;
        Auction auction;

        usr = userRepository.findById(dto.getSeller()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getSeller()));
        wrk = artworkRepository.findById(dto.getArtwork()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getArtwork()));
        auction = dto.toEntity();
        auction.setArtwork(wrk);
        auction.setSeller(usr);
        auction.setStatus(auctionStatusRepository
                .findByAuctionStatus(EAuctionStatus.AUCTION_OPEN)
                .orElseThrow(()->new EntityNotFoundException("Transaction Type not found :"+ "PENDING" )));
        auction.getArtwork().setAuction(auction);
        auction.getSeller().getAuctions().add(auction);
        auctionRepository.save(auction);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Auction registered succesfully"));
    }


    @PutMapping("/{id}")
    public ResponseEntity <?>   updateArtwork(@RequestBody Auction auction){
        auctionRepository.save(auction);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
