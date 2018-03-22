
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.UserAccount;
import domain.Folder;
import domain.Manager;
import domain.SocialIdentity;
import domain.SurvivalClass;
import domain.Trip;
import domain.Zolet;

@Service
@Transactional
public class ManagerService {

	//Managed repository

	@Autowired
	private ManagerRepository	managerRepository;

	//Supporting services

	@Autowired
	private FolderService		folderService;

	@Autowired
	private ActorService		actorService;


	//Simple CRUD methods

	public Manager create() {
		final Authority a = new Authority();
		a.setAuthority(Authority.MANAGER);
		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		final Manager m = new Manager();

		m.setSocialIdentities(new ArrayList<SocialIdentity>());
		m.setUserAccount(account);
		m.setFolders(new ArrayList<Folder>());

		m.setSurvivalClass(new ArrayList<SurvivalClass>());
		m.setTrip(new ArrayList<Trip>());
		m.setSuspicious(false);
		m.setZolets(new ArrayList<Zolet>());

		return m;
	}

	public Manager findOne(final int id) {
		Assert.notNull(id);

		return this.managerRepository.findOne(id);
	}

	public Collection<Manager> findAll() {
		return this.managerRepository.findAll();
	}
	public Manager save(final Manager m) {
		Assert.notNull(m);

		final Manager saved2;
		//Assertion that the user modifying this explorer has the correct privilege.
		if (m.getId() != 0) {
			Assert.isTrue(this.actorService.findByPrincipal().getId() == m.getId());
			saved2 = this.managerRepository.save(m);
		} else {
			final Manager saved = this.managerRepository.save(m);
			saved.setFolders(this.folderService.generateDefaultFolders(saved));
			saved2 = this.managerRepository.save(saved);
		}

		return saved2;
	}

	public void delete(final Manager m) {
		Assert.notNull(m);

		//Assertion that the user deleting this manager has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == m.getId());

		this.managerRepository.delete(m);
	}

	//Other methods

	//The average, the minimum, the maximum, and the standard deviation of the number of trips managed per manager.
	public Double[] minMaxAvgStddevTripsPerManager() {
		return this.managerRepository.minMaxAvgStddevTripsPerManager();
	}

	//The ratio of suspicious managers.
	public Double ratioSuspiciousManagers() {
		return this.managerRepository.ratioSuspiciousManagers();
	}
	//The suspicious managers.
	public Collection<Manager> suspiciousManagers() {
		return this.managerRepository.suspiciousManagers();
	}
}
