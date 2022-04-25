package fr.ktheo.back.repository;

import fr.ktheo.back.model.Asset;
import org.springframework.data.repository.CrudRepository;

public interface AssetRepository extends CrudRepository<Asset, Long> {
}
