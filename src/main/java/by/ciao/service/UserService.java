package by.ciao.service;

import by.ciao.repository.UserRepository;
import by.ciao.user.User;
import by.ciao.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final Function<User, UserDto> mappingFunction = (user) -> {
        UserDto userDto = new UserDto();

        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setUsername(user.getUsername());
        userDto.setReferral(user.getReferral());
        userDto.setChatId(user.getChatId());
        userDto.setEnglishLvl(user.getEnglishLvl());
        userDto.setNumOfCorrectAnswers(user.getNumOfCorrectAnswers());

        return userDto;
    };

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private List<UserDto> createDtoListFromUsers(final List<User> users) {
        return users.stream()
                .map(mappingFunction)
                .collect(Collectors.toList());
    }

    public UserDto addUser(final UserDto userDto) {
        User user = new User();
        user.setChatId(userDto.getChatId());
        user.setUsername(userDto.getUsername());
        user.setAdditionDateTime(new Date());

        return mappingFunction.apply(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return createDtoListFromUsers(userRepository.findAll());
    }

    public UserDto getUserByChatId(final String chatId) {
        return userRepository.getUserByChatId(chatId)
                .map(mappingFunction)
                .orElse(null);
    }

    public List<UserDto> getUsersForTheLastHour() {
        LocalDateTime previousHour = LocalDateTime.now().minusHours(1);
        return createDtoListFromUsers(userRepository.findAllByTestCompletionDateBetween(previousHour, LocalDateTime.now()));
    }

    public List<UserDto> getUsersForTheLastDay() {
        LocalDateTime previousDayStart = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime previousDayEnd = LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        return createDtoListFromUsers(userRepository.findAllByTestCompletionDateBetween(previousDayStart, previousDayEnd));
    }

    public UserDto updateContactInfo(String chatId, UserDto updateUser) {
        User user = userRepository.getUserByChatId(chatId)
                .orElseThrow(() -> {
                    log.error("User with such chatId does not exist " + chatId);
                    return new NoSuchElementException("User with such chatId does not exist " + chatId);
                });

        BeanUtils.copyProperties(updateUser, user, "id", "chatId", "additionDateTime");

        return mappingFunction.apply(userRepository.save(user));
    }

    public UserDto updateTestInfo(String chatId, UserDto updateUser) {
        User user = userRepository.getUserByChatId(chatId)
                .orElseThrow(() -> {
                    log.error("User with such chatId does not exist " + chatId);
                    return new NoSuchElementException("User with such chatId does not exist " + chatId);
                });

        BeanUtils.copyProperties(updateUser, user, "id", "chatId", "additionDateTime");

        return mappingFunction.apply(userRepository.save(user));
    }

}
