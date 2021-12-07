package sergey.ermakov.bazeofusers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByAge(int age);
    List<User> findByOrderByAgeAsc();
    List<User> findByOrderByAgeDesc();
    List<User> findByOrderByLastnameAsc();
    List<User> findByOrderByLastnameDesc();


}
