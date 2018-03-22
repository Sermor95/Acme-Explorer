
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
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private FinderService	finderService;


	//Tests

	@Test
	public void testCreateFinder() {

		//Setting up the authority to execute services.

		this.authenticate("explorer1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Finder finder = this.finderService.create();

		//Saving entity to database and confirming it exists with findAll().
		final Finder saved = this.finderService.save(finder);

		final Collection<Finder> finders = this.finderService.findAll();
		Assert.isTrue(finders.contains(saved));
	}

	@Test
	public void testListDeleteFinder() {

		//Setting up the authority to execute services.

		//this.authenticate("explorer1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		final Collection<Finder> finders = this.finderService.findAll();
		final int id = finders.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Finder finder = this.finderService.findOne(id);
		Assert.notNull(finder);

		//Using delete() to delete the entity we retrieved.
		this.finderService.delete(finder);

		//Verifying the entity has been removed from the database.		
		final Finder bbdd = this.finderService.findOne(finder.getId());
		Assert.isNull(bbdd);
	}
}
