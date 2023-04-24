package by.ciao.service;

import by.ciao.controller.UserController;
import by.ciao.repository.UserRepository;
import by.ciao.user.User;
import by.ciao.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDto newDto(final User user) {
        return new UserDto(
                user.getFullName(),
                user.getPhone(),
                user.getUsername(),
                user.getReferral(),
                user.getChatId().toString(),
                user.getEnglishLvl(),
                user.getNumOfCorrectAnswers().toString()
        );
    }

    private List<UserDto> createDtoList(final List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(newDto(user));
        }

        return userDtos;
    }

    public UserDto addUser(final User user) {
        user.setAdditionDateTime(new Date());
        return newDto(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return createDtoList(userRepository.findAll());
    }

    public UserDto getUserByChatId(final Long chatId) {
        User user = userRepository.getUserByChatId(chatId)
                .orElse( null);
        if (user == null) return null;

        return newDto(user);
    }

    public List<UserDto> getUsersForTheLastHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        Date previousHour = cal.getTime();

        return createDtoList(userRepository.findAllByAdditionDateTimeBetween(previousHour, new Date()));
    }

    public List<UserDto> getUsersForTheLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Date yesterdayStart = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        Date yesterdayEnd = cal.getTime();

        return createDtoList(userRepository.findAllByAdditionDateTimeBetween(yesterdayStart, yesterdayEnd));
    }

    public UserDto updateContactInfo(Long chatId, User updateUser) {
        User user = userRepository.getUserByChatId(chatId)
                .orElseThrow(() -> {
                    log.error("User with such chatId does not exist " + chatId);
                    return new NoSuchElementException("User with such chatId does not exist " + chatId);
                });

        user.setFullName(updateUser.getFullName());
        user.setPhone(updateUser.getPhone());
        user.setUsername(updateUser.getUsername());
        user.setReferral(updateUser.getReferral());

        return newDto(userRepository.save(user));
    }

    public UserDto updateTestInfo(Long chatId, User updateUser) {
        User user = userRepository.getUserByChatId(chatId)
                .orElseThrow(() -> {
                    log.error("User with such chatId does not exist " + chatId);
                    return new NoSuchElementException("User with such chatId does not exist " + chatId);
                });

        user.setEnglishLvl(updateUser.getEnglishLvl());
        user.setNumOfCorrectAnswers(updateUser.getNumOfCorrectAnswers());
        user.setTestCompletionDate(new Date());

        return newDto(userRepository.save(user));
    }


}
