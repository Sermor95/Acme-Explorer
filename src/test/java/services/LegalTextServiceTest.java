
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
import domain.LegalText;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class LegalTextServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private LegalTextService	legalTextService;


	//Tests

	//In this instance, a single check for all methods is necessary, as a legal text cannot be deleted if it's assigned to a trip.
	@Test
	public void testCreateListDeleteLegalText() {
		this.authenticate("admin");
		//Using create() to initialise a new entity.
		final LegalText legalText = this.legalTextService.create();

		legalText.setTitle("testTitle");
		legalText.setBody("This is a test body.");
		legalText.setLaws("These are test laws.");

		//Saving entity to database and confirming it exists with findAll().
		final LegalText saved = this.legalTextService.save(legalText);

		Collection<LegalText> legalTexts = this.legalTextService.findAll();
		Assert.isTrue(legalTexts.contains(saved));

		//Using delete() to delete the entity we created.
		this.legalTextService.delete(legalText);

		//Verifying the entity has been removed from the database.
		legalTexts = this.legalTextService.findAll();
		Assert.isTrue(!legalTexts.contains(legalText));
	}
}
