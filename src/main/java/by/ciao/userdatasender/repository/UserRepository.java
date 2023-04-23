package by.ciao.userdatasender.repository;

import by.ciao.userdatasender.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByAdditionDateTimeBetween(Date additionTimeStart, Date additionTimeEnd);
    Optional<User> getUserByChatId(Long chatId);
}
