package tk.ladyka.andrei.jpa.repository.sec;

import java.util.List;

import tk.ladyka.andrei.jpa.domain.sec.GroupAuthority;
import tk.ladyka.andrei.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("secGroupAuthorityRepository")
public interface GroupAuthorityRepository extends BaseRepository<GroupAuthority> {

	@Query("select ga from sec.GroupAuthority ga where ga.group.id = :groupId and ga.authority.id = :authorityId")
	public List<GroupAuthority> getGroupAuthority(@Param("groupId") long groupId, @Param("authorityId") long authorityId);

}
