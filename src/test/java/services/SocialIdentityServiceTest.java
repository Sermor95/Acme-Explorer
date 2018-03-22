
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
import domain.SocialIdentity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SocialIdentityServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private SocialIdentityService	socialIdentityService;


	//Tests

	@Test
	public void testCreateListSocialIdentity() {

		this.authenticate("manager1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final SocialIdentity socialIdentity = this.socialIdentityService.create();

		socialIdentity.setLink("https://www.testlink.com/");
		socialIdentity.setNick("Testnick");
		socialIdentity.setPhoto("https://www.img.com/");
		socialIdentity.setSocialNetwork("SocialNetwork");
		//Saving entity to database and confirming it exists with findAll().
		final SocialIdentity saved = this.socialIdentityService.save(socialIdentity);

		final Collection<SocialIdentity> socialIdentities = this.socialIdentityService.findAll();
		Assert.isTrue(socialIdentities.contains(saved));
	}

	@Test
	public void testListDeleteSocialIdentity() {

		this.authenticate("manager1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		final Collection<SocialIdentity> socialIdentities = this.socialIdentityService.findAll();
		final int id = socialIdentities.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final SocialIdentity socialIdentity = this.socialIdentityService.findOne(id);
		Assert.notNull(socialIdentity);

		//Using delete() to delete the entity we retrieved.
		this.socialIdentityService.delete(socialIdentity);

		//Verifying the entity has been removed from the database.
		final SocialIdentity bbdd = this.socialIdentityService.findOne(socialIdentity.getId());
		Assert.isNull(bbdd);
	}
}
