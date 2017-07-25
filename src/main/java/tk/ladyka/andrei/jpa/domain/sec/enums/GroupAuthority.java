package tk.ladyka.andrei.jpa.domain.sec.enums;

import lombok.Getter;
import lombok.Setter;

public enum GroupAuthority {

  NOTHING(0,null,null);

  @Getter
  @Setter
  private long id;

  @Getter
  @Setter
  private Authority authority;

  @Getter
  @Setter
  private Group group;

  GroupAuthority(long id, Authority authority, Group group) {
    this.id = id;
    this.authority = authority;
    this.group = group;
  }
}
