package by.ciao.userdatasender.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByAdditionDateTimeBetween(Date additionTimeStart, Date additionTimeEnd);
}
