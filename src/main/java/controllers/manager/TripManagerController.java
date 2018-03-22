
package controllers.manager;

import java.util.ArrayList;
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

import services.ActorService;
import services.LegalTextService;
import services.RangerService;
import services.StageService;
import services.TripService;
import controllers.AbstractController;
import domain.LegalText;
import domain.Manager;
import domain.Ranger;
import domain.Stage;
import domain.Trip;

@Controller
@RequestMapping("trip/manager")
public class TripManagerController extends AbstractController {

	//Services

	@Autowired
	private TripService			tripService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private RangerService		rangerService;

	@Autowired
	private LegalTextService	legalTextService;

	@Autowired
	private StageService		stageService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Trip> trips;

		trips = ((Manager) this.actorService.findByPrincipal()).getTrip();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/manager/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Trip trip;

		trip = this.tripService.create();
		result = this.createEditModelAndView(trip);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Trip trip;

		trip = this.tripService.findOne(varId);
		Assert.notNull(trip);
		result = this.createEditModelAndView(trip);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.save(trip);
				result = new ModelAndView("redirect:/trip/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trip, "trip.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Trip trip, final BindingResult binding) {
		ModelAndView result;

		try {
			for (final Stage s : trip.getStage())
				this.stageService.delete(s);

			this.tripService.delete(trip);
			result = new ModelAndView("redirect:/trip/manager/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(trip, "trip.commit.error");
		}
		return result;
	}

	//Cancellation

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int varId) {
		final ModelAndView result;
		Trip trip;

		trip = this.tripService.findOne(varId);
		Assert.notNull(trip);
		result = this.createCancelModelAndView(trip);

		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, params = "cancel")
	public ModelAndView saveCancel(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCancelModelAndView(trip);
		else
			try {
				this.tripService.cancel(trip);
				result = new ModelAndView("redirect:/trip/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createCancelModelAndView(trip, "trip.cancel.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Trip trip, final String messageCode) {
		ModelAndView result;
		Collection<Ranger> rangers;
		final Collection<LegalText> legalTexts = new ArrayList<LegalText>();

		rangers = this.rangerService.findAll();
		for (final LegalText e : this.legalTextService.findAll())
			if (e.isFinalMode())
				legalTexts.add(e);

		result = new ModelAndView("trip/edit");
		result.addObject("trip", trip);
		result.addObject("rangers", rangers);
		result.addObject("legalTexts", legalTexts);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "trip/manager/edit.do");

		return result;

	}

	protected ModelAndView createCancelModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createCancelModelAndView(trip, null);

		return result;
	}

	protected ModelAndView createCancelModelAndView(final Trip trip, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("trip/cancel");
		result.addObject("trip", trip);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "trip/manager/cancel.do");

		return result;
	}
}
