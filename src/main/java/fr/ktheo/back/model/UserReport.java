package fr.ktheo.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_report")
@Getter
@Setter
public class UserReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_user_id")
    private User    reportedUser;
}
