
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
import domain.MailMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MailMessageServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private MailMessageService	mailMessageService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private FolderService		folderService;


	//Tests

	//In this instance, it's easier to check all methods in a single test, because there aren't any existing MailMessage entities in the database.
	@Test
	public void testCreateBroadcastDeleteMailMessage() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		final Folder folder = this.folderService.getSystemFolderByName(this.actorService.findByPrincipal().getId(), "In box");

		//Using create() to initialise a new entity.
		final MailMessage mailMessage = this.mailMessageService.create();

		mailMessage.setSubject("testMessage");
		mailMessage.setBody("This is a test message");
		mailMessage.setReceiver(this.actorService.findOne(2937));
		mailMessage.setFolder(folder);

		//Saving entity to database and sending it to the receiver.
		final MailMessage saved = this.mailMessageService.save(mailMessage);
		final MailMessage sentMsg = this.mailMessageService.send(saved);
		final MailMessage sentSaved = this.mailMessageService.save(sentMsg);

		//Confirming the messages exist using findAll().
		Collection<MailMessage> mailMessages = this.mailMessageService.findAll();
		Assert.isTrue(mailMessages.contains(saved));
		Assert.isTrue(mailMessages.contains(sentSaved));

		//Testing the broadcastNotification() method since we have already created a message.
		this.mailMessageService.broadcastNotification(mailMessage);

		//Using delete() to delete the entity we created.
		this.mailMessageService.delete(mailMessage);

		//Verifying the entity has been removed from the database.
		mailMessages = this.mailMessageService.findAll();
		Assert.isTrue(!mailMessages.contains(mailMessage));
	}

	@Test
	public void testApplicationStatusNotification() {
		//Setting up the authority to execute services.
		this.authenticate("admin");

		//Sending a notification to the two necessary actors.
		this.mailMessageService.applicationStatusNotification(2937, 2932);

		//Verifying there are messages in the database.
		Assert.notEmpty(this.mailMessageService.findAll());
	}
}
