
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RangerRepository;
import security.Authority;
import security.UserAccount;
import domain.Folder;
import domain.Ranger;
import domain.SocialIdentity;
import domain.Trip;

@Service
@Transactional
public class RangerService {

	//Managed repository

	@Autowired
	private RangerRepository	rangerRepository;

	//Supporting services

	@Autowired
	private FolderService		folderService;

	@Autowired
	private ActorService		actorService;


	//Simple CRUD methods

	public Ranger create() {
		final Authority a = new Authority();
		a.setAuthority(Authority.RANGER);

		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		final Ranger r = new Ranger();
		r.setSocialIdentities(new ArrayList<SocialIdentity>());
		r.setUserAccount(account);
		r.setSuspicious(false);
		r.setFolders(new ArrayList<Folder>());
		r.setTrip(new ArrayList<Trip>());

		return r;
	}

	public Ranger findOne(final int id) {
		Assert.notNull(id);

		return this.rangerRepository.findOne(id);
	}

	public Collection<Ranger> findAll() {
		return this.rangerRepository.findAll();
	}

	public Ranger save(final Ranger r) {
		Assert.notNull(r);

		final Ranger saved2;
		//Assertion that the user modifying this explorer has the correct privilege.
		if (r.getId() != 0) {
			Assert.isTrue(this.actorService.findByPrincipal().getId() == r.getId());
			saved2 = this.rangerRepository.save(r);
		} else {
			final Ranger saved = this.rangerRepository.save(r);
			saved.setFolders(this.folderService.generateDefaultFolders(saved));
			saved2 = this.rangerRepository.save(saved);
		}

		return saved2;
	}

	public void delete(final Ranger r) {
		Assert.notNull(r);

		this.rangerRepository.delete(r);
	}

	//Other methods

	//The average, the minimum, the maximum, and the standard deviation of the number trips guided per ranger.
	public Double[] minMaxAvgStddevTripsPerRanger() {
		return this.rangerRepository.minMaxAvgStddevTripsPerRanger();
	}

	//The ratio of rangers who have registered their curricula.
	public Double ratioRangersWithCurriculum() {
		return this.rangerRepository.ratioRangersWithCurriculum();
	}

	//The ratio of rangers whose curriculums been endorsed.
	public Double ratioRangersEndorsed() {
		return this.rangerRepository.ratioRangersEndorsed();
	}

	//The ratio of suspicious rangers.
	public Double ratioSuspiciousRangers() {
		return this.rangerRepository.ratioSuspiciousRangers();
	}
	//The of suspicious rangers.
	public Collection<Ranger> suspiciousRangers() {
		return this.rangerRepository.suspiciousRangers();
	}

}
