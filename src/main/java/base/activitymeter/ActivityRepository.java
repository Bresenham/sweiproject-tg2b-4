package base.activitymeter;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
	@Query("SELECT id FROM Activity where key = :key")
	List<Activity> findIdByKey(@Param("key") long key);
	
//	 @Query("SELECT COUNT(u) FROM activity u WHERE u.key=:key")
//	    Long count(@Param("key") long key);
}
