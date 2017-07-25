package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The persistent class for the SEC_AUTHORITY database table.
 * 
 */
@Data
@EqualsAndHashCode(exclude = {
    "groupAuthorities"/*, "userAuthorities"*/
})
@Entity(name = "sec.Authority")
@Table(name = "SEC_AUTHORITY")
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEC_AUTHORITY_ID", unique = true, nullable = false, precision = 10)
  private long id;

  @Column(nullable = false, length = 50)
  private String code;


  @Column(name = "CREATED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @Column(length = 4000)
  private String description;


  @Column(name = "MODIFIED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime modifiedDate;

  @Column(length = 200)
  private String name;

  // bi-directional many-to-one association to GroupAuthority
  @OneToMany(mappedBy = "authority")
  private Set<GroupAuthority> groupAuthorities;

//  // bi-directional many-to-one association to UserAuthority
//  @OneToMany(mappedBy = "authority")
//  private Set<UserAuthority> userAuthorities;

  public GroupAuthority addGroupAuthority(GroupAuthority groupAuthority) {
    getGroupAuthorities().add(groupAuthority);
    groupAuthority.setAuthority(this);

    return groupAuthority;
  }

  public GroupAuthority removeGroupAuthority(GroupAuthority groupAuthority) {
    getGroupAuthorities().remove(groupAuthority);
    groupAuthority.setAuthority(null);

    return groupAuthority;
  }

//  public UserAuthority addUserAuthority(UserAuthority userAuthority) {
//    getUserAuthorities().add(userAuthority);
//    userAuthority.setAuthority(this);
//
//    return userAuthority;
//  }
//
//  public UserAuthority removeUserAuthority(UserAuthority userAuthority) {
//    getUserAuthorities().remove(userAuthority);
//    userAuthority.setAuthority(null);
//
//    return userAuthority;
//  }

}
