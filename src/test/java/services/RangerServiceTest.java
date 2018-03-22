
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Ranger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RangerServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private RangerService	rangerService;


	//Tests

	@Test
	public void testCreateListRanger() {
		//Using create() to initialise a new entity.
		final Ranger ranger = this.rangerService.create();

		ranger.setName("Name1");
		ranger.setSurname("Surname1");
		ranger.setEmail("test@mail.com");
		ranger.setPhone("653492923");
		ranger.setAddress("c/test, 10");
		ranger.getUserAccount().setUsername("Fren");
		ranger.getUserAccount().setPassword("12345678");

		final Ranger saved = this.rangerService.save(ranger);
		final Ranger bbdd = this.rangerService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

}
