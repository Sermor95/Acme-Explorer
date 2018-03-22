
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TripService;
import services.ZoletService;
import domain.Trip;
import domain.Zolet;

@Controller
@RequestMapping("zolet")
public class ZoletController extends AbstractController {

	//Services

	@Autowired
	private ZoletService	zoletService;

	@Autowired
	private TripService		tripService;


	//List

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Zolet> zolets;

		zolets = this.zoletService.findAll();

		result = new ModelAndView("zolet/list");
		result.addObject("zolets", zolets);
		result.addObject("requestURI", "zolet/list.do");

		return result;
	}

	@RequestMapping(value = "/trip/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Collection<Zolet> zolets;
		Trip c;
		c = this.tripService.findOne(varId);
		zolets = c.getZolets();

		result = new ModelAndView("zolet/list");
		result.addObject("trip", c);
		result.addObject("zolets", zolets);
		result.addObject("requestURI", "zolet/list.do");

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int varId) {
		ModelAndView result;
		Zolet zolet;

		zolet = this.zoletService.findOne(varId);
		Assert.notNull(zolet);

		result = new ModelAndView("zolet/display");
		result.addObject("zolet", zolet);
		result.addObject("requestURI", "zolet/display.do");

		return result;
	}
}
