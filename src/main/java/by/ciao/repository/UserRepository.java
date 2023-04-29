package by.ciao.repository;

import by.ciao.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByTestCompletionDateBetween(LocalDateTime additionDateTime, LocalDateTime additionDateTime2);
    Optional<User> getUserByChatId(String chatId);
}
