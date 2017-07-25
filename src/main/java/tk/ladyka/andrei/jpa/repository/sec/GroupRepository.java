package tk.ladyka.andrei.jpa.repository.sec;

import java.util.Set;

import tk.ladyka.andrei.jpa.domain.sec.Group;
import tk.ladyka.andrei.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("secGroupRepository")
public interface GroupRepository extends BaseRepository<Group> {

  public Group findByCodeIgnoreCase(String code);

  @Query("select gu.group from sec.GroupUser gu where gu.user.username = :username")
  public Set<Group> findAllForUser(@Param("username") String username);

}
