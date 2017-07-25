package tk.ladyka.andrei.jpa.repository.sec;
//
//import java.util.Set;
//
//import tk.ladyka.andrei.jpa.repository.BaseRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;
import tk.ladyka.andrei.jpa.domain.sec.Authority;
import tk.ladyka.andrei.jpa.repository.BaseRepository;

@Repository("secAuthorityRepository")
public interface AuthorityRepository extends BaseRepository<Authority> {
//
//  public Authority findByCodeIgnoreCase(String code);
//
//  @Query("select ua.authority from sec.UserAuthority ua where ua.user.username = :username")
//  public Set<Authority> findAllForUser(@Param("username") String username);
//
//  @Query("select distinct a from sec.Authority a "
//      + "where a in (select ua.authority from sec.UserAuthority ua where ua.user.username = :username) "
//      + "or a in (select ga.authority from sec.GroupAuthority ga, sec.GroupUser gu where ga.group = gu.group and gu.user.username = :username)")
//  public Set<Authority> findAllForUserAndGroups(@Param("username") String username);
//
//  @Query("select ga.authority from sec.GroupAuthority ga where ga.group.code = :code")
//  public Set<Authority> findAllForGroup(@Param("code") String code);
//
//  @Query("select distinct ga.authority from sec.GroupAuthority ga where ga.group.code in (:codes)")
//  public Set<Authority> findAllForGroups(@Param("codes") Set<String> codes);

}
