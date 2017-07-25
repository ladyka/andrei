package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * The persistent class for the SEC_GROUP_AUTHORITY database table.
 * 
 */
@Data
@Entity(name = "sec.GroupAuthority")
@Table(name = "SEC_GROUP_AUTHORITY")
public class GroupAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SEC_GROUP_AUTHORITY_ID", unique = true, nullable = false, precision = 10)
  private long id;


  @Column(name = "CREATED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime createdDate;


  @Column(name = "MODIFIED_DATE", insertable = false, updatable = false, nullable = false)
  private LocalDateTime modifiedDate;

  // bi-directional many-to-one association to Authority
  @ManyToOne
  @JoinColumn(name = "SEC_AUTHORITY_ID", nullable = false)
  private Authority authority;

  // bi-directional many-to-one association to Group
  @ManyToOne
  @JoinColumn(name = "SEC_GROUP_ID", nullable = false)
  private Group group;

}
