package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The persistent class for the SEC_USER_AUTHORITY database table.
 * 
 */
@Data
@Entity(name = "sec.UserAuthority")
@Table(name = "SEC_USER_AUTHORITY")
public class UserAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEC_USER_AUTHORITY_ID", unique = true, nullable = false, precision = 10)
  private long id;


  @Column(name = "CREATED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime createdDate;


  @Column(name = "MODIFIED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime modifiedDate;

  @Column(name = "SEC_AUTHORITY_ID", nullable = false)
  private long authorityId;

  // bi-directional many-to-one association to User
  @ManyToOne
  @JoinColumn(name = "SEC_USER_ID", nullable = false)
  private User user;

}
