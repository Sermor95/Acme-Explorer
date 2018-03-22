
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MailMessageRepository;
import domain.Actor;
import domain.Folder;
import domain.MailMessage;
import domain.Priority;

@Service
@Transactional
public class MailMessageService {

	//Managed repository --------------------------------

	@Autowired
	private MailMessageRepository	mailMessageRepository;

	//Supporting services  ----------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private FolderService			folderService;

	@Autowired
	private ConfigurationService	configurationService;


	//Simple CRUD methods --------------------------------

	public MailMessage create() {

		final MailMessage mailMessage = new MailMessage();
		mailMessage.setPriority(Priority.NEUTRAL);

		final Actor receiver = this.actorService.findAll().iterator().next();
		mailMessage.setReceiver(receiver);
		mailMessage.setSender(this.actorService.findByPrincipal());
		mailMessage.setFolder(this.folderService.findAll().iterator().next());

		return mailMessage;
	}

	public Collection<MailMessage> findAll() {

		return this.mailMessageRepository.findAll();
	}

	public MailMessage findOne(final int id) {
		Assert.notNull(id);

		return this.mailMessageRepository.findOne(id);
	}

	public MailMessage save(final MailMessage mailMessage) {
		Assert.notNull(mailMessage);

		//Assertion that the user modifying this mail message has the correct privilege, that is, he or she is the sender or receiver.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == mailMessage.getSender().getId() || this.actorService.findByPrincipal().getId() == mailMessage.getReceiver().getId());

		mailMessage.setSent(new Date(System.currentTimeMillis() - 1));

		return this.mailMessageRepository.save(mailMessage);
	}

	public void delete(final MailMessage mailMessage) {
		Assert.notNull(mailMessage);

		//Assertion that the user deleting this mail message has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == mailMessage.getFolder().getActor().getId());

		this.mailMessageRepository.delete(mailMessage);
	}

	//Other business methods ----------------------------

	//Creates a copy of a message and sends it to the receiver of the original message. Necessary to save outside this method to avoid complications.
	public MailMessage send(final MailMessage m) {
		Assert.notNull(m);

		//Parsing the message's subject and body for spam words.
		final boolean isSpamSubject = this.configurationService.isSpam(m.getSubject());
		final boolean isSpamBody = this.configurationService.isSpam(m.getBody());
		String folderName = "in box";
		if (isSpamSubject == true || isSpamBody == true)
			folderName = "spam box";

		//Finds the system folder where the message must be sent to.
		final Folder f = this.folderService.getSystemFolderByName(m.getReceiver().getId(), folderName);

		final MailMessage sentMsg = this.create();

		sentMsg.setSubject(m.getSubject());
		sentMsg.setBody(m.getBody());
		sentMsg.setPriority(m.getPriority());
		sentMsg.setReceiver(m.getReceiver());
		sentMsg.setFolder(f);

		return sentMsg;
	}

	//Moves a message from one folder to another.
	public void move(final MailMessage message, final Folder newOne) {
		Assert.notNull(message);
		Assert.notNull(newOne);
		final Folder folder = message.getFolder();
		folder.getMailMessage().remove(message);
		this.folderService.save(folder);
		newOne.getMailMessage().add(message);
		message.setFolder(newOne);
		this.save(message);
		this.folderService.save(newOne);
	}

	//Sends a message to every notification box in the system.
	public void broadcastNotification(final MailMessage m) {
		Assert.notNull(m);

		for (final Actor a : this.actorService.findAll()) {
			final Folder notificationBox = this.folderService.getSystemFolderByName(a.getId(), "notification box");
			final MailMessage sentMsg = this.create();

			sentMsg.setSubject(m.getSubject());
			sentMsg.setBody(m.getBody());
			sentMsg.setPriority(m.getPriority());
			sentMsg.setReceiver(a);
			sentMsg.setFolder(notificationBox);

			this.save(sentMsg);
		}
	}

	//Sends a message to the explorer and manager associated to an application.
	public void applicationStatusNotification(final int explorerId, final int managerId) {
		Assert.notNull(explorerId);
		Assert.notNull(managerId);

		final Folder folder = this.folderService.getSystemFolderByName(explorerId, "notification box");

		final MailMessage n1 = this.create();
		n1.setSubject("Application status changed");
		n1.setBody("The status in one of your applications has been changed");
		n1.setReceiver(this.actorService.findOne(explorerId));
		n1.setFolder(folder);
		this.save(n1);

		//Should work correctly, instead chooses in box.
		final Folder folder2 = this.folderService.getSystemFolderByName(managerId, "notification box");

		final MailMessage n2 = this.create();
		n2.setSubject("Application status changed");
		n2.setBody("The status in one of your applications has been changed");
		n1.setReceiver(this.actorService.findOne(managerId));
		n1.setFolder(folder2);
		this.save(n2);
	}

	public Collection<MailMessage> mailMessagesFromFolder(final int id) {
		return this.mailMessageRepository.mailMessagesFromFolder(id);
	}
}
