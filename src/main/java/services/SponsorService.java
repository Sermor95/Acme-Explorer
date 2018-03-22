
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import domain.Folder;
import domain.SocialIdentity;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	//Managed repository ---------------------------------

	@Autowired
	private SponsorRepository	sponsorRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FolderService		folderService;


	//Simple CRUD Methods --------------------------------

	public Sponsor create() {

		final Authority a = new Authority();
		a.setAuthority(Authority.SPONSOR);
		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		final Sponsor sponsor = new Sponsor();
		sponsor.setSocialIdentities(new ArrayList<SocialIdentity>());
		sponsor.setUserAccount(account);
		sponsor.setFolders(new ArrayList<Folder>());
		sponsor.setSuspicious(false);

		sponsor.setSponsorship(new ArrayList<Sponsorship>());

		return sponsor;
	}

	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Sponsor findOne(final int id) {
		Assert.notNull(id);

		return this.sponsorRepository.findOne(id);
	}

	public Sponsor save(final Sponsor s) {
		Assert.notNull(s);

		final Sponsor saved2;
		//Assertion that the user modifying this explorer has the correct privilege.
		if (s.getId() != 0) {
			Assert.isTrue(this.actorService.findByPrincipal().getId() == s.getId());
			saved2 = this.sponsorRepository.save(s);
		} else {
			final Sponsor saved = this.sponsorRepository.save(s);
			saved.setFolders(this.folderService.generateDefaultFolders(saved));
			saved2 = this.sponsorRepository.save(saved);
		}
		//		final Finder f = this.finderService.save(e.getFinder());
		//		Assert.notNull(f);

		return saved2;
	}

	public void delete(final Sponsor sponsor) {
		Assert.notNull(sponsor);

		//Assertion that the user deleting this sponsor has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == sponsor.getId());

		this.sponsorRepository.delete(sponsor);
	}

}
