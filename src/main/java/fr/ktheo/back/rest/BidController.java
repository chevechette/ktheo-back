package fr.ktheo.back.rest;

import fr.ktheo.back.model.Artwork;
import fr.ktheo.back.model.Auction;
import fr.ktheo.back.model.Bid;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.CreateBidRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.AuctionRepository;
import fr.ktheo.back.repository.AuctionStatusRepository;
import fr.ktheo.back.repository.BidRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController("BidController")
@RequestMapping("/api/bid")
@CrossOrigin(value = "*")
public class BidController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    BidRepository   bidRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllBid() {
        return ResponseEntity.ok().body(bidRepository.findAll());
    }

    @GetMapping("/auction/{id}")
    public ResponseEntity<?>    getAuctionAllBid(@PathVariable long id){
        Auction auction;

        auction = auctionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("id not found :"+id));
        return ResponseEntity.ok().body(bidRepository.findByAuction(auction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getBid(@PathVariable long id){
        return ResponseEntity.ok().body(bidRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteArtwork(@PathVariable long id){
        bidRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createArtwork(@Valid @RequestBody CreateBidRequest dto) {
        User    usr;
        Auction auction;
        Bid     bid;

        usr = userRepository.findById(dto.getBidder()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getBidder()));
        auction = auctionRepository.findById(dto.getAuction()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getAuction()));
        bid = dto.toEntity();
        bid.setBidder(usr);
        bid.setAuction(auction);
        bidRepository.save(bid);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Bid registered succesfully"));
    }


    @PutMapping("/{id}")
    public ResponseEntity <?>   updateArtwork(@RequestBody Bid bid){
        bidRepository.save(bid);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
