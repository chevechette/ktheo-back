package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Asset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAssetRequest {
    private String          path;
    private long            uploader;
    private Set<Long> artworks;

    public Asset toEntity() {
        Asset asset;

        asset = new Asset();
        asset.setPath(this.path);
        asset.setArtworks(new HashSet<>());
        return asset;
    }
}