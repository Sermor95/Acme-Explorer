
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
import domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditServiceTest extends AbstractTest {

	// Service under test

	@Autowired
	private AuditService	auditService;


	// Tests

	@Test
	public void testCreateAudit() {
		// Setting up the authority to execute services.
		this.authenticate("auditor1");

		// Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Audit audit = this.auditService.create();
		audit.setTitle("Tittle1");
		audit.setDescription("This is a description");
		//Saving entity to database and confirming it exists with findAll().
		final Audit saved = this.auditService.save(audit);

		final Collection<Audit> audits = this.auditService.findAll();
		Assert.isTrue(audits.contains(saved));
	}

	@Test
	public void testListDeleteAudit() {
		// Setting up the authority to execute services.
		this.authenticate("auditor1");

		// We retrieve a list of all audits, and obtain the Id of
		// one of them.
		Collection<Audit> audits = this.auditService.findAll();
		final int id = audits.iterator().next().getId();

		// Using findOne() to retrieve a particular entity and verifying it.
		final Audit audit = this.auditService.findOne(id);
		Assert.notNull(audit);

		// Using delete() to delete the entity we retrieved.
		this.auditService.delete(audit);

		// Verifying the entity has been removed from the database.
		audits = this.auditService.findAll();
		Assert.isTrue(!audits.contains(audit));
	}
}
