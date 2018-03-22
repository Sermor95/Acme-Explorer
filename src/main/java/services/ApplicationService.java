
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Explorer;
import domain.Manager;
import domain.Status;
import domain.Trip;

@Service
@Transactional
public class ApplicationService {

	//Managed repository ---------------------------------

	@Autowired
	private ApplicationRepository	applicationRepository;

	//Supporting services --------------------------------

	@Autowired
	private TripService				tripService;

	@Autowired
	private ActorService			actorService;


	//Simple CRUD Methods --------------------------------

	public Application create(final int tripId) {

		final Application a = new Application();

		a.setStatus(Status.PENDING);

		final Trip t = this.tripService.findOne(tripId);
		a.setTrip(t);
		final Explorer e = (Explorer) this.actorService.findByPrincipal();
		a.setExplorer(e);

		a.setMade(new Date(System.currentTimeMillis() - 1));

		return a;
	}

	public Collection<Application> findAll() {

		return this.applicationRepository.findAll();
	}

	public Application findOne(final int id) {
		Assert.notNull(id);

		return this.applicationRepository.findOne(id);
	}

	public Application save(final Application application) {
		Assert.notNull(application);

		//Once an application has been rejected, a reason must be given.
		if (application.getStatus().equals(Status.REJECTED))
			Assert.isTrue(!(application.getReason().isEmpty() || application.getReason() == null));

		//If the explorer provides a valid credit card, the application's status is automatically updated from DUE to ACCEPTED.
		if (application.getStatus().equals(Status.DUE) && application.getCreditCard() != null)
			application.setStatus(Status.ACCEPTED);

		//Assertion that the user modifying this application has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == application.getExplorer().getId() || this.actorService.findByPrincipal().getId() == application.getTrip().getManager().getId());

		final Application saved = this.applicationRepository.save(application);

		return saved;
	}

	public void delete(final Application application) {
		Assert.notNull(application);

		//Assertion that the user deleting this application has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == application.getExplorer().getId() || this.actorService.findByPrincipal().getId() == application.getTrip().getManager().getId());

		this.applicationRepository.delete(application);
	}

	//Other methods

	//The ratio of applications with status PENDING.
	public Double ratioApplicationsPending() {
		return this.applicationRepository.ratioApplicationsPending();
	}

	//The ratio of applications with status DUE.
	public Double ratioApplicationsDue() {
		return this.applicationRepository.ratioApplicationsDue();
	}

	//The ratio of applications with status ACCEPTED.
	public Double ratioApplicationsAccepted() {
		return this.applicationRepository.ratioApplicationsAccepted();
	}

	//The ratio of applications with status CANCELLED.
	public Double ratioApplicationsCancelled() {
		return this.applicationRepository.ratioApplicationsCancelled();
	}

	//Applications which belong to trips published by a certain manager.
	public Collection<Application> applicationsByManager(final Manager m) {
		return this.applicationRepository.applicationsByManager(m);
	}

}
