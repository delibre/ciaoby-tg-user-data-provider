package by.ciao.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @Column(unique=true)
    private String phone;

    private String username;

    private String referral;

    private String englishLvl;

    private String numOfCorrectAnswers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date testCompletionDate;

    @Column(unique=true)
    private String chatId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDateTime;

}
