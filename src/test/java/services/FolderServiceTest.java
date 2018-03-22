
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
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FolderServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private FolderService	folderService;
	@Autowired
	private ActorService	actorService;


	//Tests

	//In this instance, it's easier to check all methods in a single test, because every default folder in the database cannot be modified or deleted.
	@Test
	public void testCreateListDeletefolder() {
		//Setting up the authority to execute services.
		this.authenticate("manager1");

		//Using create() to initialise a new entity.
		final Folder folder = this.folderService.create(this.actorService.findByPrincipal());

		folder.setName("testFolder");

		//Saving entity to database and confirming it exists with findAll().
		final Folder saved = this.folderService.save(folder);

		Collection<Folder> folders = this.folderService.findAll();
		Assert.isTrue(folders.contains(saved));

		//Using delete() to delete the entity we created.
		this.folderService.delete(folder);

		//Verifying the entity has been removed from the database.
		folders = this.folderService.findAll();
		Assert.isTrue(!folders.contains(folder));
	}
}
