package tk.ladyka.andrei.jpa.repository.sec;

import java.util.List;

import tk.ladyka.andrei.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tk.ladyka.andrei.jpa.domain.sec.GroupUser;

@Repository("secGroupUserRepository")
public interface GroupUserRepository extends BaseRepository<GroupUser> {

	@Query("select gu from sec.GroupUser gu where gu.user.id = :userId and gu.group.id = :groupId")
	public List<GroupUser> getGroupUser(@Param("userId") long userId, @Param("groupId") long groupId);

}
