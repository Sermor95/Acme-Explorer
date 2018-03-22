
package controllers.explorer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConfigurationService;
import services.FinderService;
import services.TripService;
import controllers.AbstractController;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@Controller
@RequestMapping("finder/explorer")
public class FinderExplorerController extends AbstractController {

	//Services

	@Autowired
	private FinderService			finderService;

	@Autowired
	private TripService				tripService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ConfigurationService	configurationService;

	//Ancillary attributes

	private Collection<Trip>		tripList;
	private Long					lastSaved;


	//Listing the results of a finder

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Trip> trips = this.getTripList();
		final Long millis = this.configurationService.findAll().iterator().next().getCacheHours() * 3600000L;

		if (System.currentTimeMillis() - this.getLastSaved() > millis)
			trips = this.tripService.findAll();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "finder/explorer/list.do");

		return result;
	}

	//Edition of parameters

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Finder finder;

		finder = ((Explorer) this.actorService.findByPrincipal()).getFinder();
		Assert.notNull(finder);
		result = this.createEditModelAndView(finder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;
		ArrayList<Trip> trips = new ArrayList<Trip>();

		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				final Finder saved = this.finderService.save(finder);

				this.setLastSaved(System.currentTimeMillis());

				if (saved.getKeyWord().isEmpty() && saved.getMinPrice() == null && saved.getMaxPrice() == null && saved.getDateStart() == null && saved.getDateEnd() == null)
					trips = (ArrayList<Trip>) this.tripService.findAll();
				else
					trips = (ArrayList<Trip>) this.finderService.find(saved);

				this.setTripList(trips);

				if (trips.size() > this.configurationService.findAll().iterator().next().getCacheResults()) {
					final ArrayList<Trip> results = new ArrayList<Trip>();
					for (int i = 0; i <= trips.size(); i++)
						results.add(trips.get(i));
					this.setTripList(results);
				}

				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "finder/explorer/edit.do");

		return result;

	}

	public Collection<Trip> getTripList() {
		return this.tripList;
	}

	public void setTripList(final Collection<Trip> tripList) {
		this.tripList = tripList;
	}

	public Long getLastSaved() {
		return this.lastSaved;
	}

	public void setLastSaved(final Long lastSaved) {
		this.lastSaved = lastSaved;
	}
}
