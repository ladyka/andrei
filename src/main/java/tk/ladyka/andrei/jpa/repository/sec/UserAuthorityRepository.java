package tk.ladyka.andrei.jpa.repository.sec;

import java.util.List;

import tk.ladyka.andrei.jpa.domain.sec.UserAuthority;
import tk.ladyka.andrei.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("secUserAuthorityRepository")
public interface UserAuthorityRepository extends BaseRepository<UserAuthority> {

	@Query("select ua from sec.UserAuthority ua where ua.user.id = :userId and ua.authorityId = :authorityId")
	public List<UserAuthority> getUserAuthority(@Param("userId") long userId, @Param("authorityId") long authorityId);

	@Query("select ua from sec.UserAuthority ua where ua.user.id = :userId")
	List<UserAuthority> getUserAuthorities(@Param("userId") long userId);
}
