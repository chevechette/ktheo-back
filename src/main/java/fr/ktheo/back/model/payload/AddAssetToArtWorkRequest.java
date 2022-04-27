package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Asset;
import fr.ktheo.back.model.Tag;
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
public class AddAssetToArtWorkRequest {
    private String          path;
    private long            uploader;

    public Asset toEntity() {
        Asset asset;

        asset = new Asset();
        asset.setPath(this.path);
        asset.setArtworks(new HashSet<>());
        return asset;
    }
}
