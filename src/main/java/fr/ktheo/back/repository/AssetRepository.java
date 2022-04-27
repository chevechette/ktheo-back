package fr.ktheo.back.repository;

import fr.ktheo.back.model.Asset;
import fr.ktheo.back.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssetRepository extends CrudRepository<Asset, Long> {
    Optional<Asset> findByPath(String path);
}
