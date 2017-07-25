package tk.ladyka.andrei.jpa.domain.sec;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The persistent class for the SEC_USER database table.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {
		"groupUsers", "userAuthorities"
})
@Entity(name = "sec.User")
@Table(name = "SEC_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEC_USER_ID", unique = true, nullable = false, precision = 10)
	private long id;


	@Column(name = "ACCOUNT_EXPIRATION")
	private LocalDateTime accountExpiration;


	@Column(name = "CREATED_DATE", insertable = false, updatable = false, nullable = false)
	private LocalDateTime createdDate;


	@Column(name = "CREDENTIALS_EXPIRATION")
	private LocalDateTime credentialsExpiration;

	@Column(nullable = false, precision = 1)
	private Boolean enabled;

	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;

	@Column(name = "LAST_NAME", length = 100)
	private String lastName;

	@Column(nullable = false, precision = 1)
	private Boolean locked;


	@Column(name = "MODIFIED_DATE", insertable = false, updatable = false, nullable = false)
	private LocalDateTime modifiedDate;

	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 255)
	private String username;

	// bi-directional many-to-one association to GroupUser
	@OneToMany(mappedBy = "user")
	private Set<GroupUser> groupUsers;

	// bi-directional many-to-one association to UserAuthority
	@OneToMany(mappedBy = "user")
	private Set<UserAuthority> userAuthorities;

	public GroupUser addGroupUser(GroupUser groupUser) {
		getGroupUsers().add(groupUser);
		groupUser.setUser(this);

		return groupUser;
	}

	public GroupUser removeGroupUser(GroupUser groupUser) {
		getGroupUsers().remove(groupUser);
		groupUser.setUser(null);

		return groupUser;
	}

	public UserAuthority addUserAuthority(UserAuthority userAuthority) {
		getUserAuthorities().add(userAuthority);
		userAuthority.setUser(this);

		return userAuthority;
	}

	public UserAuthority removeUserAuthority(UserAuthority userAuthority) {
		getUserAuthorities().remove(userAuthority);
		userAuthority.setUser(null);

		return userAuthority;
	}

}
