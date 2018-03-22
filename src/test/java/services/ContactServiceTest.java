
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ContactServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private ContactService	contactService;


	//Tests

	@Test
	public void testCreateContact() {

		//Setting up the authority to execute services.
		this.authenticate("explorer1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Contact contact = this.contactService.create();

		contact.setName("Luis Candelario");
		contact.setEmail("luicanlun@hotmail.com");
		contact.setPhone("924553536");

		//Saving entity to database and confirming it exists with findAll().
		final Contact saved = this.contactService.save(contact);

		final Collection<Contact> contacts = this.contactService.findAll();
		Assert.isTrue(contacts.contains(saved));
	}

	@Test
	public void testListDeleteContact() {
		//Setting up the authority to execute services.
		this.authenticate("explorer1");

		//We retrieve a list of all contacts, and obtain the Id of one of them.
		Collection<Contact> contacts = this.contactService.findAll();
		final int id = contacts.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Contact contact = this.contactService.findOne(id);
		Assert.notNull(contact);

		//Using delete() to delete the entity we retrieved.
		this.contactService.delete(contact);

		//Verifying the entity has been removed from the database.
		contacts = this.contactService.findAll();
		Assert.isTrue(!contacts.contains(contact));
	}
}
