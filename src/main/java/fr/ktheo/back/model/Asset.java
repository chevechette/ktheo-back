package fr.ktheo.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "asset")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;

    @Column(unique=true)
    private String          path;

    @Column(name = "uploaded_on")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private LocalDateTime   uploadedOn;

    /*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User            uploader;
     */
}
