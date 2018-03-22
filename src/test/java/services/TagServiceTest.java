
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
import domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TagServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private TagService	tagService;


	//Setting up the authority to execute services.
	@Test
	public void testCreateTag() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Tag tag = this.tagService.create();

		tag.setName("Tag");

		//Saving entity to database and confirming it exists with findAll().
		final Tag saved = this.tagService.save(tag);

		final Collection<Tag> tags = this.tagService.findAll();
		Assert.isTrue(tags.contains(saved));
	}

	@Test
	public void testListDeleteTag() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<Tag> tags = this.tagService.findAll();
		final int id = tags.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Tag tag = this.tagService.findOne(id);
		Assert.notNull(id);

		//Using delete() to delete the entity we retrieved.
		this.tagService.delete(tag);

		//Verifying the entity has been removed from the database.
		tags = this.tagService.findAll();
		Assert.isTrue(!tags.contains(tag));
	}
}
