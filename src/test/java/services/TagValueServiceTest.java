
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
import domain.TagValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TagValueServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private TagValueService	tagValueService;


	//Setting up the authority to execute services.
	@Test
	public void testCreateTagValue() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final TagValue tagValue = this.tagValueService.create(3017);

		tagValue.setValue("Value");

		//Saving entity to database and confirming it exists with findAll().
		final TagValue saved = this.tagValueService.save(tagValue);

		final Collection<TagValue> tagValues = this.tagValueService.findAll();
		Assert.isTrue(tagValues.contains(saved));
	}

	@Test
	public void testListDeleteTagValue() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<TagValue> tagValues = this.tagValueService.findAll();
		final int id = tagValues.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final TagValue tagValue = this.tagValueService.findOne(id);
		Assert.notNull(id);

		//Using delete() to delete the entity we retrieved.
		this.tagValueService.delete(tagValue);

		//Verifying the entity has been removed from the database.
		tagValues = this.tagValueService.findAll();
		Assert.isTrue(!tagValues.contains(tagValue));
	}
}
