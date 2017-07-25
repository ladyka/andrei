package tk.ladyka.andrei.jpa.repository.sec;

import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository("secUserRepository")
public interface UserRepository extends BaseRepository<User> {

  public User findByUsernameIgnoreCase(String username);

}
