
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
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private CurriculumService	curriculumService;


	//Tests

	@Test
	public void testCreateCurriculum() {
		//Setting up the authority to execute services.
		this.authenticate("ranger2");

		//Using create() to initialise a new entity. Necessary Id's taken from populated database.
		final Curriculum curriculum = this.curriculumService.create();

		//Saving entity to database and confirming it exists with findAll().		
		final Curriculum saved = this.curriculumService.save(curriculum);
		final Curriculum bbdd = this.curriculumService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

	@Test
	public void testListDeleteCurriculum() {
		//Setting up the authority to execute services.
		this.authenticate("ranger1");

		//We retrieve a list of all notes, and obtain the Id of one of them.
		final Collection<Curriculum> curriculums = this.curriculumService.findAll();
		final int id = curriculums.iterator().next().getId();

		//Using findOne() to retrieve a particular entity and verifying it.
		final Curriculum curriculum = this.curriculumService.findOne(id);

		this.curriculumService.delete(curriculum);

		final Curriculum bbdd = this.curriculumService.findOne(curriculum.getId());
		Assert.isNull(bbdd);
	}
}
