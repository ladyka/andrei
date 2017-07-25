package tk.ladyka.andrei.json.sec;

import java.util.ArrayList;

import java.time.LocalDateTime;

import tk.ladyka.andrei.jpa.domain.sec.GroupAuthority;
import tk.ladyka.andrei.utils.ComparableHelper;
import org.springframework.beans.BeanUtils;

import tk.ladyka.andrei.jpa.domain.sec.Group;
import tk.ladyka.andrei.jpa.domain.sec.GroupUser;

import lombok.Data;

@Data
public class GroupJSON implements ComparableHelper<GroupJSON> {

	private long id;
	private String code;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private ArrayList<UserJSON> users = new ArrayList<>();
	private ArrayList<AuthorityJSON> roles = new ArrayList<>();

	public static GroupJSON fromBean(Group group, boolean deep) {
		GroupJSON json = new GroupJSON();
		BeanUtils.copyProperties(group, json, "users", "roles");
		if (deep) {
			for (GroupUser groupUser : group.getGroupUsers()) {
				UserJSON userJSON = UserJSON.fromBean(groupUser.getUser(), false);
				json.getUsers().add(userJSON);
			}
			for (GroupAuthority groupAuth : group.getGroupAuthorities()) {
				AuthorityJSON roleJSON = AuthorityJSON.fromBean(groupAuth.getAuthority(), false);
				json.getRoles().add(roleJSON);
			}
		}
		return json;
	}

	@Override
	public int compareTo(GroupJSON o) {
		if (o == null) {
			return BEFORE;
		}
		if (this == o) {
			return EQUAL;
		}
		if (this.equals(o)) {
			return EQUAL;
		}

		if (this.name.compareToIgnoreCase(o.name) != 0) {
			return this.name.compareToIgnoreCase(o.name);
		}

		return this.code.compareToIgnoreCase(o.code);
	}

}
