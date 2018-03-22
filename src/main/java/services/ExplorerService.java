
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ExplorerRepository;
import security.Authority;
import security.UserAccount;
import domain.Application;
import domain.Contact;
import domain.CreditCard;
import domain.Explorer;
import domain.Folder;
import domain.SocialIdentity;
import domain.Story;
import domain.SurvivalClass;

@Service
@Transactional
public class ExplorerService {

	//Managed repository

	@Autowired
	private ExplorerRepository	explorerRepository;

	//Supporting services

	@Autowired
	private FolderService		folderService;

	@Autowired
	private ActorService		actorService;


	//Simple CRUD methods

	public Explorer create() {
		final Authority a = new Authority();
		a.setAuthority(Authority.EXPLORER);
		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		final Explorer e = new Explorer();

		e.setCreditCard(new ArrayList<CreditCard>());

		e.setSocialIdentities(new ArrayList<SocialIdentity>());
		e.setUserAccount(account);
		e.setFolders(new ArrayList<Folder>());
		e.setSuspicious(false);

		e.setStory(new ArrayList<Story>());
		e.setApplication(new ArrayList<Application>());
		e.setContact(new ArrayList<Contact>());
		e.setSurvivalClass(new ArrayList<SurvivalClass>());

		return e;
	}
	public Explorer findOne(final int id) {
		Assert.notNull(id);

		return this.explorerRepository.findOne(id);
	}

	public Collection<Explorer> findAll() {
		return this.explorerRepository.findAll();
	}

	public Explorer save(final Explorer e) {
		Assert.notNull(e);

		final Integer username = e.getUserAccount().getUsername().length();
		final Integer password = e.getUserAccount().getPassword().length();

		Assert.isTrue(username >= 5 && username <= 32);
		Assert.isTrue(password >= 5 && password <= 32);

		final Explorer saved2;
		//Assertion that the user modifying this explorer has the correct privilege.
		if (e.getId() != 0) {
			Assert.isTrue(this.actorService.findByPrincipal().getId() == e.getId());
			saved2 = this.explorerRepository.save(e);
		} else {
			final Explorer saved = this.explorerRepository.save(e);
			saved.setFolders(this.folderService.generateDefaultFolders(saved));
			saved2 = this.explorerRepository.save(saved);
		}
		//final Finder f = this.finderService.save(e.getFinder());
		//		Assert.notNull(f);

		return saved2;
	}

	public void delete(final Explorer e) {
		Assert.notNull(e);

		//Assertion that the user deleting this explorer has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == e.getId());

		this.explorerRepository.delete(e);
	}

}
