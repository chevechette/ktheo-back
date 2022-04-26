package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.ArtworkReport;
import fr.ktheo.back.model.CommentReport;
import fr.ktheo.back.model.UserReport;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReportRequest {
    private String  reason;

    private Long reporter;

    private Long reportee;

    public UserReport toUserReport() {
        UserReport rep;

        rep = new UserReport();
        toUserReport().setReason(this.reason);
        return rep;
    }

    public CommentReport toCommentReport() {
        CommentReport rep;

        rep = new CommentReport();
        toUserReport().setReason(this.reason);
        return rep;
    }

    public ArtworkReport toArtworkReport() {
        ArtworkReport rep;

        rep = new ArtworkReport();
        toUserReport().setReason(this.reason);
        return rep;
    }
}
