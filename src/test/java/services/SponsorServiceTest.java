
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private SponsorService	sponsorService;


	//Tests

	@Test
	public void testCreateListSponsor() {
		//Setting up the authority to execute services.

		this.authenticate("sponsor2");

		//Using create() to initialise a new entity.
		final Sponsor sponsor = this.sponsorService.create();

		sponsor.setName("testSponsor");
		sponsor.setSurname("testSponsorSurname");
		sponsor.setEmail("sponsor@mail.com");
		sponsor.setPhone("912321821");
		sponsor.setAddress("c/test2, 13");
		sponsor.getUserAccount().setUsername("Sixto");
		sponsor.getUserAccount().setPassword("12345678");

		final Sponsor saved = this.sponsorService.save(sponsor);
		final Sponsor bbdd = this.sponsorService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

}
