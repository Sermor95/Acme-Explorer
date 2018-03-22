
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StageRepository;
import domain.Manager;
import domain.Stage;

@Service
@Transactional
public class StageService {

	//Managed repository ----------------------

	@Autowired
	private StageRepository	stageRepository;

	//Supporting Services ---------------------

	@Autowired
	private ActorService	actorService;


	//Simple CRUD Methods ---------------------

	public Stage create() {

		final Stage s = new Stage();

		final Manager m = (Manager) this.actorService.findByPrincipal();
		s.setManager(m);

		return s;
	}
	public Collection<Stage> findAll() {
		return this.stageRepository.findAll();
	}

	public Stage findOne(final int id) {
		Assert.notNull(id);

		return this.stageRepository.findOne(id);
	}

	public Stage save(final Stage stage) {
		Assert.notNull(stage);

		//Assertion that the user modifying this stage has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == stage.getManager().getId());

		return this.stageRepository.save(stage);
	}

	public void delete(final Stage stage) {
		Assert.notNull(stage);

		//Assertion that the user modifying this stage has the correct privilege.
		Assert.isTrue(this.actorService.findByPrincipal().getId() == stage.getManager().getId());

		this.stageRepository.delete(stage);
	}

}
