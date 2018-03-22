
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
import domain.CreditCard;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorshipServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private SponsorshipService	sponsorshipService;


	//Tests

	@Test
	public void testCreateListDeleteSponsorship() {
		//Setting up the authority to execute services.
		this.authenticate("sponsor2");

		//Using create() to initialise a new entity.
		final Sponsorship sponsorship = this.sponsorshipService.create();

		sponsorship.setLink("https://www.testsponsorship1.com/");
		sponsorship.setUrl("https://www.testsponsorship2.com/");
		final CreditCard cc = new CreditCard();
		cc.setHolder("Luisete");
		cc.setBrand("asdas");
		cc.setCvv(222);
		cc.setExpMonth(8);
		cc.setExpYear(2019);
		cc.setNumber("5555555555554444");
		sponsorship.setCreditcard(cc);

		//Saving entity to database and verifying it exists with findAll().
		final Sponsorship saved = this.sponsorshipService.save(sponsorship);

		final Collection<Sponsorship> sponsors = this.sponsorshipService.findAll();
		Assert.isTrue(sponsors.contains(saved));

		//Using delete() to delete the entity we retrieved.
		this.sponsorshipService.delete(saved);
	}
}
