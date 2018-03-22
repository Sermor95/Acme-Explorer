
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ranger;

@Repository
public interface RangerRepository extends JpaRepository<Ranger, Integer> {

	//The average, the minimum, the maximum, and the standard deviation of the number trips guided per ranger.
	@Query("select min(r.trip.size), max(r.trip.size), avg(r.trip.size),stddev(r.trip.size) from Ranger r")
	Double[] minMaxAvgStddevTripsPerRanger();

	//The ratio of rangers who have registered their curricula.
	@Query("select (select count(r) from Ranger r where r.curriculum != null)*1.0/count(r) from Ranger r")
	Double ratioRangersWithCurriculum();

	//The ratio of rangers whose curriculums been endorsed.
	@Query("select (select count(r) from Ranger r join r.curriculum c where c.endorserRecord is not empty)*1.0/count(r) from Ranger r")
	Double ratioRangersEndorsed();

	//The ratio of suspicious rangers.
	@Query("select (select count(r) from Ranger r where r.suspicious = true)*1.0/count(r) from Ranger r")
	Double ratioSuspiciousRangers();
	//Suspicious rangers
	@Query("select r from Ranger r where r.suspicious = true)")
	Collection<Ranger> suspiciousRangers();

}
