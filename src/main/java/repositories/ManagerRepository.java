
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	//The average, the minimum, the maximum, and the standard deviation of the number of trips managed per manager.
	@Query("select min(m.trip.size), max(m.trip.size), avg(m.trip.size), stddev(m.trip.size) from Manager m")
	Double[] minMaxAvgStddevTripsPerManager();

	//The ratio of suspicious managers.
	@Query("select (select count(m) from Manager m where m.suspicious = true)*1.0/count(m) from Manager m")
	Double ratioSuspiciousManagers();
	//Suspicious managers
	@Query("select m from Manager m where m.suspicious = true)")
	Collection<Manager> suspiciousManagers();

}
