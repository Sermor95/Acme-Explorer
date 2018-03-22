
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

import services.ActorService;
import services.TripService;
import services.ZoletService;
import controllers.AbstractController;
import domain.Manager;
import domain.Trip;
import domain.Zolet;

@Controller
@RequestMapping("zolet/manager")
public class ZoletManagerController extends AbstractController {

	//Services

	@Autowired
	private ZoletService	zoletService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private TripService		tripService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Zolet> zolets;

		zolets = ((Manager) this.actorService.findByPrincipal()).getZolets();

		result = new ModelAndView("zolet/list");
		result.addObject("zolets", zolets);
		result.addObject("requestURI", "zolets/manager/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Zolet zolet;
		Collection<Trip> trips;
		trips = this.tripService.findAll();
		zolet = this.zoletService.create();
		result = this.createEditModelAndView(zolet);
		result.addObject("trips", trips);
		return result;
	}

	//Editing

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Zolet zolet;

		zolet = this.zoletService.findOne(varId);
		Assert.notNull(zolet);
		Collection<Trip> trips;
		trips = this.tripService.findAll();
		result = this.createEditModelAndView(zolet);
		result.addObject("trips", trips);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Zolet zolet, final BindingResult binding) {
		ModelAndView result;
		Collection<Trip> trips;
		trips = this.tripService.findAll();

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = this.createEditModelAndView(zolet);

		} else
			try {
				this.zoletService.save(zolet);
				result = new ModelAndView("redirect:/zolet/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(zolet, "zolet.commit.error");
			}
		result.addObject("trips", trips);
		return result;
	}

	//Deleting

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int varId) {
		ModelAndView result;
		Zolet zolet;

		zolet = this.zoletService.findOne(varId);
		Assert.notNull(zolet);
		this.zoletService.delete(zolet);

		try {
			this.zoletService.delete(zolet);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(zolet, "zolet.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Zolet zolet) {
		ModelAndView result;

		result = this.createEditModelAndView(zolet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Zolet zolet, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("zolet/edit");
		result.addObject("zolet", zolet);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "zolet/manager/edit.do");

		return result;

	}
}
