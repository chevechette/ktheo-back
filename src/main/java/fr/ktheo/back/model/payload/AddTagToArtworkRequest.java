package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddTagToArtworkRequest {
    private String          tag;
    private boolean         isTrigger = false;

    public Tag toEntity() {
        Tag tag;

        tag = new Tag();
        tag.setTrigger(this.isTrigger);
        tag.setTag(this.tag);
        return tag;
    }
}
