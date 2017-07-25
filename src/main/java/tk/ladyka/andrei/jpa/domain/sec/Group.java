package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The persistent class for the SEC_GROUP database table.
 * 
 */
@Data
@EqualsAndHashCode(exclude = {
    "groupAuthorities", "groupUsers"
})
@Entity(name = "sec.Group")
@Table(name = "SEC_GROUP")
public class Group {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEC_GROUP_ID", unique = true, nullable = false, precision = 10)
  private long id;

  @Column(nullable = false, length = 100)
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
  @OneToMany(mappedBy = "group")
  private Set<GroupAuthority> groupAuthorities;

  // bi-directional many-to-one association to GroupUser
  @OneToMany(mappedBy = "group")
  private Set<GroupUser> groupUsers;

  public GroupAuthority addGroupAuthority(GroupAuthority groupAuthority) {
    getGroupAuthorities().add(groupAuthority);
    groupAuthority.setGroup(this);

    return groupAuthority;
  }

  public GroupAuthority removeGroupAuthority(GroupAuthority groupAuthority) {
    getGroupAuthorities().remove(groupAuthority);
    groupAuthority.setGroup(null);

    return groupAuthority;
  }

  public GroupUser addGroupUser(GroupUser groupUser) {
    getGroupUsers().add(groupUser);
    groupUser.setGroup(this);

    return groupUser;
  }

  public GroupUser removeGroupUser(GroupUser groupUser) {
    getGroupUsers().remove(groupUser);
    groupUser.setGroup(null);

    return groupUser;
  }

}
