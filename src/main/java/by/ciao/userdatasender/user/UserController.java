package by.ciao.userdatasender.user;

import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        user.setAdditionDateTime(new Date());
        userRepository.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/last-hour")
    public List<User> getUsersForTheLastHour() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        Date previousHour = cal.getTime();

        return userRepository.findAllByAdditionDateTimeBetween(previousHour, new Date());
    }

    @GetMapping("/last-day")
    public List<User> getUsersForTheLastDay() {
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

        return userRepository.findAllByAdditionDateTimeBetween(yesterdayStart, yesterdayEnd);
    }



}
