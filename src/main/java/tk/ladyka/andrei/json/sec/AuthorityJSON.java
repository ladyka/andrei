package tk.ladyka.andrei.json.sec;

import java.time.LocalDateTime;

import tk.ladyka.andrei.utils.ComparableHelper;
import tk.ladyka.andrei.jpa.domain.sec.Authority;
import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class AuthorityJSON implements ComparableHelper<AuthorityJSON> {

	private long id;
	private String code;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public static AuthorityJSON fromBean(Authority authority, boolean deep) {
		AuthorityJSON json = new AuthorityJSON();
		BeanUtils.copyProperties(authority, json);
		return json;
	}

	@Override
	public int compareTo(AuthorityJSON o) {
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
