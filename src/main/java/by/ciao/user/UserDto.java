package by.ciao.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserDto {
    private String fullName;
    private String phone;
    private String username;
    private String referral;
    private String chatId;
    private String englishLvl;
    private String numOfCorrectAnswers;
}
