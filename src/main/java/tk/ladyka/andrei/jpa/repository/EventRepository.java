package tk.ladyka.andrei.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.ladyka.andrei.dto.EventDTO;
import tk.ladyka.andrei.jpa.domain.Event;
import org.springframework.stereotype.Repository;
import tk.ladyka.andrei.jpa.domain.sec.GroupUser;

import java.util.List;

@Repository
public interface EventRepository extends BaseRepository<Event> {
//
//    @Query(value = "INSERT INTO `event_has_man` (`event_id`,`man_id`) VALUES (,<{man_id: }>);")
//    EventDTO connectEventWithMan(@Param("eventId") long eventId, @Param("manId") long manId);
//
//    @Query("select gu from sec.GroupUser gu where gu.user.id = :userId and gu.group.id = :groupId")
//    public List<GroupUser> getGroupUser(@Param("userId") long userId, @Param("groupId") long groupId);

}
