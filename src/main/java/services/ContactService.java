
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContactRepository;
import domain.Contact;
import domain.Explorer;

@Service
@Transactional
public class ContactService {

	//Managed repository

	@Autowired
	private ContactRepository	contactRepository;

	//Supporting services

	@Autowired
	private ActorService		actorService;


	//Simple CRUD methods

	public Contact create() {

		final Contact c = new Contact();
		final Explorer e = (Explorer) this.actorService.findByPrincipal();
		c.setExplorer(e);

		return c;
	}

	public Contact findOne(final int id) {
		Assert.notNull(id);

		return this.contactRepository.findOne(id);
	}

	public Collection<Contact> findAll() {
		return this.contactRepository.findAll();
	}

	public Contact save(final Contact c) {
		Assert.notNull(c);

		//Business rule: a contact must have one email, one phone number or both.
		Assert.isTrue(!(c.getEmail().isEmpty() && c.getPhone().isEmpty()));

		//Assertion that the user modifying this contact has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == c.getExplorer().getId());

		final Contact saved = this.contactRepository.save(c);

		return saved;
	}

	public void delete(final Contact c) {
		Assert.notNull(c);

		//Assertion that the user deleting this contact has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == c.getExplorer().getId());

		this.contactRepository.delete(c);
	}

}
