package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.Comment;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCommentRequest {
    private long user;

    private long topic;

    private String          content;

    public Comment toEntity() {
        Comment comment;

        comment = new Comment();
        comment.setContent(this.content);
        return comment;
    }
}