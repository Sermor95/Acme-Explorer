
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.TripService;
import controllers.AbstractController;
import domain.Category;
import domain.Trip;

@Controller
@RequestMapping("category/manager")
public class CategoryManagerController extends AbstractController {

	//Services

	@Autowired
	private CategoryService	categoryService;

	@Autowired
	private TripService		tripService;

	//Ancillary attributes

	private int				tripId;


	//Listing

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView manage(@RequestParam final int varId) {
		final ModelAndView result;
		final Collection<Category> categories = this.categoryService.findAll();
		this.setTripId(varId);

		result = new ModelAndView("category/manage");
		result.addObject("categories", categories);
		result.addObject("requestURI", "category/manager/manage.do");

		return result;
	}

	//Setting the trip's category

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public ModelAndView set(@RequestParam final int varId) {
		ModelAndView result;
		final Category category = this.categoryService.findOne(varId);
		final int id = this.getTripId();
		final Trip trip = this.tripService.findOne(id);

		try {
			final Category old = this.categoryService.categoryOfTrip(trip);
			if (old != null) {
				final Collection<Trip> trips = old.getTrip();
				trips.remove(trip);
				old.setTrip(trips);
				this.categoryService.save(old);
			}

			final Collection<Trip> trips = category.getTrip();
			trips.add(trip);
			category.setTrip(trips);
			this.categoryService.save(category);

			result = this.manage(id);
		} catch (final Throwable oops) {
			result = this.manage(id);
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;

		result = this.createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "category/manager/edit.do");

		return result;

	}

	public int getTripId() {
		return this.tripId;
	}

	public void setTripId(final int tripId) {
		this.tripId = tripId;
	}
}
