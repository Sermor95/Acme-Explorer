
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RangerService;
import controllers.AbstractController;
import domain.Ranger;

@Controller
@RequestMapping("ranger/administrator")
public class RangerAdministratorController extends AbstractController {

	//Services

	@Autowired
	private RangerService	rangerService;

	@Autowired
	private ActorService	actorService;


	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Ranger ranger;

		ranger = this.rangerService.create();
		result = this.createEditModelAndView(ranger);

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
				//result = this.createEditModelAndView(ranger, "ranger.commit.error");
				result = this.createEditModelAndView(ranger, oops.getMessage());
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
