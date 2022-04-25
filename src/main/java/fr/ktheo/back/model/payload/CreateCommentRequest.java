package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCommentRequest {
    private long user;

    private long topic;

    public Comment toEntity() {
        return new Comment();
    }
}