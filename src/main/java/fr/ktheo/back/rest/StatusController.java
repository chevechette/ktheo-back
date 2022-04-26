package fr.ktheo.back.rest;

import fr.ktheo.back.model.AuctionStatus;
import fr.ktheo.back.model.Role;
import fr.ktheo.back.repository.AuctionStatusRepository;
import fr.ktheo.back.repository.TransactionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleStatus;

@RestController("StatusController")
@RequestMapping("/api/status")
@CrossOrigin(value = "*")
public class StatusController {
    @Autowired
    TransactionStatusRepository transactionStatusRepository;

    @Autowired
    AuctionStatusRepository auctionStatusRepository;

    @GetMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTransactionStatus() {
        return ResponseEntity.ok().body(transactionStatusRepository.findAll());
    }

    @GetMapping(value = "/auction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAuctionStatus() {
        return ResponseEntity.ok().body(auctionStatusRepository.findAll());
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<?> getTransactionStatus(@PathVariable long id) {
        return ResponseEntity.ok().body(transactionStatusRepository.findById(id));
    }

    @GetMapping("/auction/{id}")
    public ResponseEntity<?> getAutionStatus(@PathVariable long id) {
        return ResponseEntity.ok().body(auctionStatusRepository.findById(id));
    }
    
}
