
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	//The average, the minimum, the maximum, and the standard deviation of the number of applications per trip.
	@Query("select min(t.application.size), max(t.application.size), avg(t.application.size), stddev(t.application.size) from Trip t")
	Double[] minMaxAvgStddevApplicationsPerTrip();

	//The average, the minimum, the maximum, and the standard deviation of the price of the trips.
	@Query("select min(t.price), max(t.price), avg(t.price), stddev(t.price) from Trip t")
	Double[] minMaxAvgStddevPricePerTrip();

	//The ratio of trips that have been cancelled versus the total number of trips that have been organised.
	@Query("select (select count(t) from Trip t where t.reason != null)*1.0/count(t) from Trip t")
	Double ratioTripsCancelled();

	//The listing of trips that have got at least 10% more applications than the average, ordered by number of applications.
	@Query("select t from Trip t where t.application.size >= (select avg(t.application.size)*1.1 from Trip t) order by t.application.size")
	Collection<Trip> tripsWithAboveAverageApplications();

	//The minimum, the maximum, the average, and the standard deviation of the number of notes per trip.
	@Query("select min(t.note.size), max(t.note.size), avg(t.note.size),stddev(t.note.size) from Trip t")
	Double[] minMaxAvgStddevNotesPerTrip();

	//The minimum, the maximum, the average, and the standard deviation of the number of audit records per trip.
	@Query("select min(t.audit.size), max(t.audit.size), avg(t.audit.size),stddev(t.audit.size) from Trip t")
	Double[] minMaxAvgStddevAuditsPerTrip();

	//The ratio of trips with an audit record.
	@Query("select (select count(t) from Trip t where t.audit is not empty)*1.0/count(t) from Trip t")
	Double ratioTripsWithAudit();
}
