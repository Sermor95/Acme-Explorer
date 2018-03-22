
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.EducationRecord;
import domain.EndorserRecord;
import domain.MiscellaneousRecord;
import domain.ProfessionalRecord;
import domain.Ranger;

@Service
@Transactional
public class CurriculumService {

	//Managed repository

	@Autowired
	private CurriculumRepository		curriculumRepository;

	//Supporting services

	@Autowired
	private ActorService				actorService;

	@Autowired
	private TripService					tripService;

	@Autowired
	private PersonalRecordService		personalRecordService;

	@Autowired
	private EducationRecordService		educationRecordService;

	@Autowired
	private EndorserRecordService		endorserRecordService;

	@Autowired
	private ProfessionalRecordService	professionalRecordService;

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//Simple CRUD methods

	public Curriculum create() {
		final Curriculum c = new Curriculum();

		c.setTicker(this.tripService.generateTicker()); //Using the method from TripService to generate a unique ticker.

		final Ranger r = (Ranger) this.actorService.findByPrincipal();
		c.setRanger(r);
		c.setEducationRecord(new ArrayList<EducationRecord>());
		c.setEndorserRecord(new ArrayList<EndorserRecord>());
		c.setMiscellaneousRecord(new ArrayList<MiscellaneousRecord>());
		c.setProfessionalRecord(new ArrayList<ProfessionalRecord>());

		return c;
	}

	public Curriculum findOne(final int id) {
		Assert.notNull(id);

		return this.curriculumRepository.findOne(id);
	}

	public Collection<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	public Curriculum save(final Curriculum c) {
		Assert.notNull(c);

		//Assertion that the user modifying this curriculum has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == c.getRanger().getId());

		final Curriculum saved = this.curriculumRepository.save(c);

		return saved;
	}

	public void delete(final Curriculum c) {
		Assert.notNull(c);

		//Assertion that the user deleting this curriculum has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == c.getRanger().getId());

		if (c.getPersonalRecord().getName() != null)
			this.personalRecordService.delete(c.getPersonalRecord());

		if (!(c.getEducationRecord().isEmpty()))
			for (final EducationRecord er : c.getEducationRecord())
				this.educationRecordService.delete(er);

		if (!(c.getEndorserRecord().isEmpty()))
			for (final EndorserRecord er : c.getEndorserRecord())
				this.endorserRecordService.delete(er);

		if (!(c.getProfessionalRecord().isEmpty()))
			for (final ProfessionalRecord pr : c.getProfessionalRecord())
				this.professionalRecordService.delete(pr);

		if (!(c.getMiscellaneousRecord().isEmpty()))
			for (final MiscellaneousRecord mr : c.getMiscellaneousRecord())
				this.miscellaneousRecordService.delete(mr);

		this.curriculumRepository.delete(c);
	}
}
