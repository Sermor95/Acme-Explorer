
package controllers;

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
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("sponsor")
public class SponsorController extends AbstractController {

	//Services

	@Autowired
	private SponsorService	sponsorService;

	@Autowired
	private ActorService	actorService;


	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Sponsor sponsor;
		sponsor = (Sponsor) this.actorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = this.createEditModelAndView(sponsor);

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.create();
		result = this.createEditModelAndView(sponsor);

		return result;
	}

	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int sponsorId) {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.findOne(sponsorId);

		result = new ModelAndView("sponsor/display");
		result.addObject("sponsor", sponsor);
		result.addObject("requestURI", "sponsor/display.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(sponsor);
		else
			try {
				this.actorService.hashPassword(sponsor);
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(sponsor, "sponsor.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "sponsor/edit.do");

		return result;

	}
}
