
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TagValueService;
import services.TripService;
import controllers.AbstractController;
import domain.TagValue;
import domain.Trip;

@Controller
@RequestMapping("tagValue/manager")
public class TagValueManagerController extends AbstractController {

	//Services

	@Autowired
	private TagValueService	tagValueService;

	@Autowired
	private TripService		tripService;

	//Ancillary attributes

	private int				tripId;


	//Listing

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView manage(@RequestParam final int varId) {
		final ModelAndView result;
		final Collection<TagValue> tags = this.tagValueService.findAll();
		this.setTripId(varId);
		final Trip trip = this.tripService.findOne(varId);
		final Collection<TagValue> values = trip.getTagValue();

		result = new ModelAndView("tagValue/manage");
		result.addObject("tags", tags);
		result.addObject("values", values);
		result.addObject("requestURI", "tagValue/manager/manage.do");

		return result;
	}

	//Setting the trip's category

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public ModelAndView set(@RequestParam final int varId) {
		ModelAndView result;
		final TagValue tag = this.tagValueService.findOne(varId);
		final int id = this.getTripId();
		final Trip trip = this.tripService.findOne(id);

		try {
			final Collection<Trip> trips = tag.getTrip();
			if (!trips.contains(trip)) {
				trips.add(trip);
				tag.setTrip(trips);
				this.tagValueService.save(tag);
			} else {
				trips.remove(trip);
				tag.setTrip(trips);
				this.tagValueService.save(tag);
			}

			//final String uri = "redirect:/category/manager/manage.do?varId=" + id;
			result = this.manage(id);
		} catch (final Throwable oops) {
			//final String uri = "redirect:/category/manager/manage.do?varId=" + id;
			result = this.manage(id);
		}

		return result;
	}

	//Ancillary methods

	public int getTripId() {
		return this.tripId;
	}

	public void setTripId(final int tripId) {
		this.tripId = tripId;
	}
}
