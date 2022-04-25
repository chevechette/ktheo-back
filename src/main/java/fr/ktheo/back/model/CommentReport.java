package fr.ktheo.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment_report")
@Getter
@Setter
public class CommentReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_comment_id")
    private Comment     reportedComment;
}
