
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.Authority;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Folder;
import domain.Note;
import domain.SocialIdentity;

@Service
@Transactional
public class AuditorService {

	//Managed repository ---------------------------------

	@Autowired
	private AuditorRepository	auditorRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FolderService		folderService;


	//Simple CRUD Methods --------------------------------

	public Auditor create() {
		final Authority a = new Authority();
		a.setAuthority(Authority.AUDITOR);
		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));

		final Auditor auditor = new Auditor();
		auditor.setSocialIdentities(new ArrayList<SocialIdentity>());
		auditor.setUserAccount(account);

		auditor.setFolders(new ArrayList<Folder>());
		auditor.setNote(new ArrayList<Note>());
		auditor.setAudit(new ArrayList<Audit>());
		auditor.setSuspicious(false);
		return auditor;
	}

	public Collection<Auditor> findAll() {
		return this.auditorRepository.findAll();
	}

	public Auditor findOne(final int id) {
		Assert.notNull(id);

		return this.auditorRepository.findOne(id);
	}

	public Auditor save(final Auditor a) {
		Assert.notNull(a);

		final Auditor saved2;
		//Assertion that the user modifying this explorer has the correct privilege.
		if (a.getId() != 0) {
			Assert.isTrue(this.actorService.findByPrincipal().getId() == a.getId());
			saved2 = this.auditorRepository.save(a);
		} else {
			final Auditor saved = this.auditorRepository.save(a);
			saved.setFolders(this.folderService.generateDefaultFolders(saved));
			saved2 = this.auditorRepository.save(saved);
		}
		//		final Finder f = this.finderService.save(e.getFinder());
		//		Assert.notNull(f);

		return saved2;
	}

	public void delete(final Auditor auditor) {
		Assert.notNull(auditor);

		//Assertion that the user deleting this auditor has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == auditor.getId());

		this.auditorRepository.delete(auditor);
	}

}
