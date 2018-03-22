
package controllers.manager;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StageService;
import services.TripService;
import controllers.AbstractController;
import domain.Stage;
import domain.Trip;

@Controller
@RequestMapping("stage/manager")
public class StageManagerController extends AbstractController {

	//Services

	@Autowired
	private StageService	stageService;

	@Autowired
	private TripService		tripService;

	//Ancillary attributes

	private int				tripId;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;

		Assert.notNull(varId);
		this.setTripId(varId);
		final Trip trip = this.tripService.findOne(varId);
		Assert.notNull(trip);
		final Collection<Stage> stages = trip.getStage();

		result = new ModelAndView("stage/list");
		result.addObject("varId", varId);
		result.addObject("stages", stages);
		result.addObject("requestURI", "stage/manager/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Stage stage;

		stage = this.stageService.create();
		result = this.createEditModelAndView(stage);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Stage stage;

		stage = this.stageService.findOne(varId);
		Assert.notNull(stage);
		result = this.createEditModelAndView(stage);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Stage stage, final BindingResult binding) {
		ModelAndView result;
		final int tripId = this.getTripId();

		if (binding.hasErrors())
			result = this.createEditModelAndView(stage);
		else
			try {
				final Stage saved = this.stageService.save(stage);

				final Trip t = this.tripService.findOne(tripId);
				final Collection<Stage> stages = t.getStage();
				if (!stages.contains(saved)) {
					stages.add(saved);
					t.setStage(stages);
					this.tripService.save(t);
					result = this.list(tripId);
				} else {
					this.tripService.save(t);
					result = this.list(tripId);
				}
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(stage, "stage.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Stage stage, final BindingResult binding) {
		ModelAndView result;
		final int tripId = this.getTripId();

		if (binding.hasErrors())
			result = this.createEditModelAndView(stage, binding.toString());
		else
			try {
				this.stageService.delete(stage);

				//final Trip t = this.tripService.findOne(tripId);
				//final Collection<Stage> stages = t.getStage();
				//stages.remove(stage);
				//t.setStage(stages);
				//this.tripService.save(t);
				result = this.list(tripId);
			} catch (final Throwable oops) {
				//result = this.createEditModelAndView(stage, "stage.commit.error");
				result = this.createEditModelAndView(stage, oops.getMessage());
			}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Stage stage) {
		ModelAndView result;

		result = this.createEditModelAndView(stage, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Stage stage, final String messageCode) {
		ModelAndView result;
		final int tripId = this.getTripId();

		result = new ModelAndView("stage/edit");
		result.addObject("stage", stage);
		result.addObject("tripId", tripId);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "stage/manager/edit.do");

		return result;

	}

	public int getTripId() {
		return this.tripId;
	}

	public void setTripId(final int tripId) {
		this.tripId = tripId;
	}
}
