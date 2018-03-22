
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Explorer;
import domain.Story;
import domain.Trip;

@Service
@Transactional
public class StoryService {

	//Managed repository ---------------------------------

	@Autowired
	private StoryRepository	storyRepository;

	//Supporting services --------------------------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private TripService		tripService;

	@Autowired
	private AuditService	auditService;


	//Simple CRUD Methods --------------------------------

	public Story create(final int tripId) {

		final Story s = new Story();

		final Explorer e = (Explorer) this.actorService.findByPrincipal();
		s.setExplorer(e);

		final Trip t = this.tripService.findOne(tripId);
		s.setTrip(t);

		return s;
	}

	public Collection<Story> findAll() {
		return this.storyRepository.findAll();
	}

	public Story findOne(final int id) {
		Assert.notNull(id);

		return this.storyRepository.findOne(id);
	}

	public Story save(final Story story) {
		Assert.notNull(story);

		//Assertion that every attachment is an URL. Uses method from AuditService.
		final String attachments = story.getAttachments();
		if (attachments != null && !attachments.isEmpty())
			Assert.isTrue(this.auditService.isValidURLCollection(attachments));

		//Assertion that the user modifying this story has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == story.getExplorer().getId());

		final Story saved = this.storyRepository.save(story);

		return saved;
	}

	public void delete(final Story story) {
		Assert.notNull(story);

		//Assertion that the user deleting this story has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == story.getExplorer().getId());

		this.storyRepository.delete(story);
	}

}
