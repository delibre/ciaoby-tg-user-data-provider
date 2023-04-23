package by.ciao.userdatasender.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
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

    private int numOfCorrectAnswers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date testCompletionDate;

    @Column(unique=true)
    private Long chatId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDateTime;

}
