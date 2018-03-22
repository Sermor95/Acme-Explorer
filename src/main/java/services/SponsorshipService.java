
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.CreditCard;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	// Managed service
	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	// Supporting service

	@Autowired
	private ActorService			actorService;


	// Simple CRUD methods

	public Sponsorship create() {
		final Sponsorship ss = new Sponsorship();
		final Sponsor s = (Sponsor) this.actorService.findByPrincipal();
		ss.setSponsor(s);
		ss.setCreditcard(new CreditCard());
		return ss;
	}

	public Sponsorship findOne(final int id) {
		Assert.notNull(id);

		return this.sponsorshipRepository.findOne(id);
	}

	public Collection<Sponsorship> findAll() {
		return this.sponsorshipRepository.findAll();
	}

	public Sponsorship save(final Sponsorship ss) {
		Assert.notNull(ss);

		//Assertion that the user modifying this sponsorship has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == ss.getSponsor().getId());

		final Sponsorship saved = this.sponsorshipRepository.save(ss);
		return saved;
	}

	public void delete(final Sponsorship ss) {
		Assert.notNull(ss);

		//Assertion that the user deleting this sponsorship has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == ss.getSponsor().getId());

		this.sponsorshipRepository.delete(ss);
	}
}
