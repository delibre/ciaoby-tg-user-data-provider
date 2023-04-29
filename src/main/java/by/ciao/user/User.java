package by.ciao.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String phone;

    private String username;

    private String referral;

    private String englishLvl;

    private String numOfCorrectAnswers;

    private LocalDateTime testCompletionDate;

    @NaturalId
    private String chatId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDateTime;

}
