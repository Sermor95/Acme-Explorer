
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.TagValueService;
import services.TripService;
import domain.Category;
import domain.Sponsorship;
import domain.TagValue;
import domain.Trip;

@Controller
@RequestMapping("trip")
public class TripController extends AbstractController {

	//Services

	@Autowired
	private TripService		tripService;
	@Autowired
	private CategoryService	categoryService;
	@Autowired
	private TagValueService	tagValueService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findAll();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");

		return result;
	}
	@RequestMapping(value = "/categoryList", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Collection<Trip> tripsByCategory;
		Category c;
		c = this.categoryService.findOne(varId);
		tripsByCategory = c.getTrip();

		result = new ModelAndView("trip/categoryList");
		result.addObject("category", c);
		result.addObject("tripsByCategory", tripsByCategory);
		result.addObject("requestURI", "trip/categoryList.do");

		return result;
	}

	@RequestMapping(value = "/tagValueList", method = RequestMethod.GET)
	public ModelAndView list2(@RequestParam final int tagValueId) {
		final ModelAndView result;
		Collection<Trip> tripsByTagValue;
		TagValue t;
		t = this.tagValueService.findOne(tagValueId);
		tripsByTagValue = t.getTrip();

		result = new ModelAndView("trip/tagValueList");
		result.addObject("tagValue", t);
		result.addObject("tripsByTagValue", tripsByTagValue);
		result.addObject("requestURI", "trip/tagValueList.do");

		return result;
	}

	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int varId) {
		ModelAndView result;
		Trip trip;
		final Sponsorship sponsorship;

		trip = this.tripService.findOne(varId);
		sponsorship = this.tripService.selectRandomSponsorship(varId);
		Assert.notNull(trip);

		result = new ModelAndView("trip/display");
		result.addObject("trip", trip);
		result.addObject("sponsorship", sponsorship);
		result.addObject("requestURI", "trip/display.do");

		return result;
	}

	//Search
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		final ModelAndView result;

		result = new ModelAndView("trip/search");

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "results")
	public ModelAndView results(@RequestParam final String keyword) {
		ModelAndView result;
		Collection<Trip> trips;
		trips = this.tripService.findByKeyword(keyword);

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");

		return result;
	}
}
