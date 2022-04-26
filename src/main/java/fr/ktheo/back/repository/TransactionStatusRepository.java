package fr.ktheo.back.repository;

import fr.ktheo.back.model.ETransactionStatus;
import fr.ktheo.back.model.TransactionStatus;
import fr.ktheo.back.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionStatusRepository extends CrudRepository<TransactionStatus, Long> {
    Optional<TransactionStatus> findByTransactionStatus(ETransactionStatus status);

}
