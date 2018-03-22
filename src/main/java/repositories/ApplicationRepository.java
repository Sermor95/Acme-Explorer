
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Manager;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	//The ratio of applications with status “PENDING”.
	@Query("select (select count(a) from Application a where a.status=0)*1.0/count(a) from Application a")
	Double ratioApplicationsPending();

	//The ratio of applications with status “DUE”.
	@Query("select (select count(a) from Application a where a.status=2)*1.0/count(a) from Application a")
	Double ratioApplicationsDue();

	//The ratio of applications with status “ACCEPTED”.
	@Query("select (select count(a) from Application a where a.status=3)*1.0/count(a) from Application a")
	Double ratioApplicationsAccepted();

	//The ratio of applications with status “CANCELLED”.
	@Query("select (select count(a) from Application a where a.status=4)*1.0/count(a) from Application a")
	Double ratioApplicationsCancelled();

	//Applications which belong to trips published by a certain manager.
	@Query("select a from Application a where a.trip.manager = ?1")
	Collection<Application> applicationsByManager(Manager m);
}
