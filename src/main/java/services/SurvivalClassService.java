
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Explorer;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class SurvivalClassService {

	//Managed repository

	@Autowired
	private SurvivalClassRepository	survivalRepository;

	//Supporting services

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private TripService				tripService;

	@Autowired
	private ActorService			actorService;


	//Simple CRUD methods

	public SurvivalClass create(final int tripId) {
		final SurvivalClass s = new SurvivalClass();

		s.setLocation(this.configurationService.findAll().iterator().next().getDefaultLocation());
		final Manager m = (Manager) this.actorService.findByPrincipal();
		s.setManager(m);
		final Trip t = this.tripService.findOne(tripId);
		s.setTrip(t);
		s.setExplorers(new ArrayList<Explorer>());

		return s;
	}
	public SurvivalClass findOne(final int id) {
		Assert.notNull(id);

		return this.survivalRepository.findOne(id);
	}

	public Collection<SurvivalClass> findAll() {
		return this.survivalRepository.findAll();
	}

	public SurvivalClass save(final SurvivalClass s) {
		Assert.notNull(s);

		//Assertion that the user modifying this survival class has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == s.getManager().getId() || s.getExplorers().contains(this.actorService.findByPrincipal()));

		return this.survivalRepository.save(s);
	}

	public void delete(final SurvivalClass s) {
		Assert.notNull(s);

		this.survivalRepository.delete(s);
	}

}
