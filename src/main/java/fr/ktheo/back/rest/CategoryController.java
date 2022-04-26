package fr.ktheo.back.rest;

import fr.ktheo.back.model.EAuctionStatus;
import fr.ktheo.back.model.ECategory;
import fr.ktheo.back.model.ETransactionStatus;
import fr.ktheo.back.repository.AuctionStatusRepository;
import fr.ktheo.back.repository.CategoryRepository;
import fr.ktheo.back.repository.TransactionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CategoryController")
@RequestMapping("/api/category")
@CrossOrigin(value = "*")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok().body(categoryRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable long id) {
        return ResponseEntity.ok().body(categoryRepository.findById(id));
    }

    @GetMapping("/name/{id}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String id) {
        return ResponseEntity.ok().body(categoryRepository.findByCategory(ECategory.valueOf(id)));
    }
}
