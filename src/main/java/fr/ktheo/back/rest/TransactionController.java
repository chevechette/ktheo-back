package fr.ktheo.back.rest;

import fr.ktheo.back.model.*;
import fr.ktheo.back.model.payload.CreateAuctionRequest;
import fr.ktheo.back.model.payload.CreateTransactionRequest;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController("TransactionController")
@RequestMapping("/api/transaction")
@CrossOrigin(value = "*")
public class TransactionController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArtworkRepository artworkRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    TransactionRepository   transactionRepository;

    @Autowired
    TransactionStatusRepository transactionStatusRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTransaction() {
        return ResponseEntity.ok().body(transactionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>    getTransaction(@PathVariable long id){
        return ResponseEntity.ok().body(transactionRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>    deleteAuction(@PathVariable long id){
        transactionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/new")
    public ResponseEntity<?>    createArtwork(@Valid @RequestBody CreateTransactionRequest dto) {
        User buyer;
        User owner;
        Artwork wrk;
        Transaction trans;

        owner = userRepository.findById(dto.getOwner()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getOwner()));
        buyer = userRepository.findById(dto.getBuyer()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getBuyer()));
        wrk = artworkRepository.findById(dto.getArtwork()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getArtwork()));

        trans = dto.toEntity();
        trans.setOwner(owner);
        trans.setBuyer(buyer);
        trans.setArtwork(wrk);
        trans.setStatus(transactionStatusRepository
                .findByTransactionStatus(ETransactionStatus.TRANSACTION_PENDING)
                .orElseThrow(()->new EntityNotFoundException("Transaction Type not found :"+ "PENDING" )));
        transactionRepository.save(trans);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Transaction registered succesfully"));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity <?>   updateTransaction(@RequestBody Transaction transaction){
        transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
