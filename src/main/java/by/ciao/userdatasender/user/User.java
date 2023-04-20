package by.ciao.userdatasender.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String phone;

    private String username;

    private String referral;

    private String englishLvl;

    private int numOfCorrectAnswers;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Date additionDateTime;

}
