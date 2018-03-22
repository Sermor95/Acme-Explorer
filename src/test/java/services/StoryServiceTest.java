
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
import domain.Story;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class StoryServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private StoryService	storyService;


	//Setting up the authority to execute services.
	@Test
	public void testCreateStory() {
		//Setting up the authority to execute services.
		this.authenticate("explorer1");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Story story = this.storyService.create(3031);

		story.setTitle("Title");
		story.setText("This is the text");
		story.setAttachments("Attachment1");

		//Saving entity to database and confirming it exists with findAll().
		final Story saved = this.storyService.save(story);

		final Collection<Story> stories = this.storyService.findAll();
		Assert.isTrue(stories.contains(saved));
	}

	@Test
	public void testListDeleteNote() {
		//Setting up the authority to execute services.
		this.authenticate("explorer1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		Collection<Story> stories = this.storyService.findAll();
		final int id = stories.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Story story = this.storyService.findOne(id);
		Assert.notNull(story);

		//Using delete() to delete the entity we retrieved.
		this.storyService.delete(story);

		//Verifying the entity has been removed from the database.
		stories = this.storyService.findAll();
		Assert.isTrue(!stories.contains(story));
	}
}
