
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
import services.AuditorService;
import domain.Auditor;

@Controller
@RequestMapping("auditor")
public class AuditorController extends AbstractController {

	//Services

	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private ActorService	actorService;


	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Auditor auditor;
		auditor = (Auditor) this.actorService.findByPrincipal();

		System.out.println("1.-----------" + auditor);

		Assert.notNull(auditor);
		result = this.createEditModelAndView(auditor);

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Auditor auditor;

		auditor = this.auditorService.create();
		result = this.createEditModelAndView(auditor);

		return result;
	}

	//Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int auditorId) {
		ModelAndView result;
		Auditor auditor;

		auditor = this.auditorService.findOne(auditorId);

		result = new ModelAndView("auditor/display");
		result.addObject("auditor", auditor);
		result.addObject("requestURI", "auditor/display.do");

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Auditor auditor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(auditor);
		else
			try {
				this.actorService.hashPassword(auditor);
				this.auditorService.save(auditor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(auditor, "actor.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Auditor auditor) {
		ModelAndView result;

		result = this.createEditModelAndView(auditor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Auditor auditor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("auditor/edit");
		result.addObject("auditor", auditor);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "auditor/edit.do");

		return result;

	}
}
