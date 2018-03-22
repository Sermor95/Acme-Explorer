
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.Curriculum;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	//Managed repository

	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	//Supporting services

	@Autowired
	private CurriculumService			curriculumService;
	@Autowired
	private ActorService				actorService;


	//Simple CRUD methods

	public EndorserRecord create(final int curriculumId) {
		final EndorserRecord en = new EndorserRecord();

		final Curriculum c = this.curriculumService.findOne(curriculumId);
		en.setCurriculum(c);

		return en;
	}

	public EndorserRecord findOne(final int id) {
		Assert.notNull(id);

		return this.endorserRecordRepository.findOne(id);
	}

	public Collection<EndorserRecord> findAll() {
		return this.endorserRecordRepository.findAll();
	}

	public EndorserRecord save(final EndorserRecord er) {
		Assert.notNull(er);

		//Assertion that the user modifying this endorser record has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == er.getCurriculum().getRanger().getId());

		final EndorserRecord saved = this.endorserRecordRepository.save(er);

		return saved;
	}

	public void delete(final EndorserRecord er) {
		Assert.notNull(er);

		//Assertion that the user deleting this endorser record has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == er.getCurriculum().getRanger().getId());

		this.endorserRecordRepository.delete(er);
	}

}
