
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
import services.RangerService;
import domain.Ranger;

@Controller
@RequestMapping("ranger")
public class RangerController extends AbstractController {

	//Services

	@Autowired
	private RangerService	rangerService;

	@Autowired
	private ActorService	actorService;


	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Ranger ranger;
		ranger = (Ranger) this.actorService.findByPrincipal();
		Assert.notNull(ranger);
		result = this.createEditModelAndView(ranger);

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Ranger ranger;

		ranger = this.rangerService.create();
		result = this.createEditModelAndView(ranger);

		return result;
	}

	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int varId) {
		ModelAndView result;
		Ranger ranger;

		ranger = this.rangerService.findOne(varId);

		result = new ModelAndView("ranger/display");
		result.addObject("ranger", ranger);
		result.addObject("requestURI", "ranger/display.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Ranger ranger, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(ranger);
		else
			try {
				this.actorService.hashPassword(ranger);
				this.rangerService.save(ranger);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(ranger, "ranger.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Ranger ranger) {
		ModelAndView result;

		result = this.createEditModelAndView(ranger, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Ranger ranger, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("ranger/edit");
		result.addObject("ranger", ranger);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "ranger/edit.do");

		return result;

	}
}
