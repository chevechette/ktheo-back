package fr.ktheo.back.repository;

import fr.ktheo.back.model.UserReport;
import org.springframework.data.repository.CrudRepository;

public interface UserReportRepository extends CrudRepository<UserReport, Long> {
}
