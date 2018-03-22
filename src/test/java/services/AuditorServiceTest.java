
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest {

	// Service under test

	@Autowired
	private AuditorService	auditorService;


	// Test

	@Test
	public void testCreateAuditor() {
		// Setting up the authority to execute services.
		//this.authenticate("auditor2");

		// Using create() to initialise a new entity.
		final Auditor auditor = this.auditorService.create();
		auditor.setName("testName4");
		auditor.setSurname("testSurname4");
		auditor.setEmail("test4@mail.com");
		auditor.setPhone("944488877");
		auditor.setAddress("c/test4, 1");
		auditor.getUserAccount().setUsername("OSKIRA");
		auditor.getUserAccount().setPassword("12345679");

		final Auditor saved = this.auditorService.save(auditor);
		final Auditor bbdd = this.auditorService.findOne(saved.getId());
		Assert.notNull(bbdd);

		// Saving entity to database and confirming it exists with findAll().
		//		final Auditor saved = this.auditorService
		//				.save(auditor);
		//		final Collection<Auditor> auditors = this.auditorService
		//				.findAll();
		//		Assert.isTrue(auditors.contains(saved));
	}

}
