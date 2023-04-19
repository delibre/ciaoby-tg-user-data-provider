package by.ciao.userdatasender.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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

}
