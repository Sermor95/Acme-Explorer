
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;
import domain.Zolet;

@Repository
public interface ZoletRepository extends JpaRepository<Zolet, Integer> {

	@Query("select (select count(t) from Trip t where t.zolets.size!=0)*1.0/ count(t) from Trip t")
	Double ratioTripsWithAtLeastOneZolet();

	@Query("select m from Trip t join t.manager m where t.zolets.size >=(select max(t1.zolets.size) from Trip t1))")
	Manager findManagerWithMoreZolets();

}
