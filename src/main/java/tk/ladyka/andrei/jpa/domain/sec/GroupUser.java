package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The persistent class for the SEC_GROUP_USER database table.
 * 
 */
@Data
@Entity(name = "sec.GroupUser")
@Table(name = "SEC_GROUP_USER")
public class GroupUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEC_GROUP_USER_ID", unique = true, nullable = false, precision = 10)
  private long id;


  @Column(name = "CREATED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime createdDate;


  @Column(name = "MODIFIED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime modifiedDate;

  // bi-directional many-to-one association to Group
  @ManyToOne
  @JoinColumn(name = "SEC_GROUP_ID", nullable = false)
  private Group group;

  // bi-directional many-to-one association to User
  @ManyToOne
  @JoinColumn(name = "SEC_USER_ID", nullable = false)
  private User user;

}
