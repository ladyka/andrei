package tk.ladyka.andrei.json.sec;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import tk.ladyka.andrei.jpa.domain.sec.GroupUser;
import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.domain.sec.UserAuthority;
import tk.ladyka.andrei.jpa.domain.sec.enums.Authority;
import tk.ladyka.andrei.utils.ComparableHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class UserJSON implements ComparableHelper<UserJSON> {

	private long id;
	private String username;
	private String password = null;
	private String firstName;
	private String lastName;
	private Boolean enabled;
	private Boolean locked;

	@JsonFormat
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime accountExpiration;

	@JsonFormat
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime credentialsExpiration;

	@JsonFormat
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime createdDate;

	@JsonFormat
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime modifiedDate;

	private ArrayList<GroupJSON> groups = new ArrayList<>();
	private ArrayList<Authority> roles = new ArrayList<>();

	public static UserJSON fromBean(User user, boolean deep) {
		UserJSON json = new UserJSON();
		BeanUtils.copyProperties(user, json, "password", "groups", "roles");
		if (deep) {
			for (GroupUser groupUser : user.getGroupUsers()) {
				GroupJSON groupJSON = GroupJSON.fromBean(groupUser.getGroup(), false);
				json.getGroups().add(groupJSON);
			}
			for (UserAuthority userAuth : user.getUserAuthorities()) {
				Authority roleJSON = Authority.findOne(userAuth.getAuthorityId());
				json.getRoles().add(roleJSON);
			}
		}
		return json;
	}

	@Override
	public int compareTo(UserJSON o) {
		if (o == null) {
			return BEFORE;
		}
		if (this == o) {
			return EQUAL;
		}
		if (this.equals(o)) {
			return EQUAL;
		}

		return this.username.compareToIgnoreCase(o.username);
	}

}
