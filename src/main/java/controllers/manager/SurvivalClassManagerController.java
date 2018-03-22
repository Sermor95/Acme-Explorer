
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
import services.SurvivalClassService;
import controllers.AbstractController;
import domain.Manager;
import domain.SurvivalClass;

@Controller
@RequestMapping("survivalClass/manager")
public class SurvivalClassManagerController extends AbstractController {

	//Services

	@Autowired
	private SurvivalClassService	survivalClassService;

	@Autowired
	private ActorService			actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<SurvivalClass> survivalClasses;
		Manager manager;

		manager = (Manager) this.actorService.findByPrincipal();
		survivalClasses = manager.getSurvivalClass();

		result = new ModelAndView("survivalClass/list");
		result.addObject("survivalClasses", survivalClasses);
		result.addObject("requestURI", "survivalClass/manager/list.do");

		return result;
	}
	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int varId) {
		final ModelAndView result;
		SurvivalClass survivalClass;

		survivalClass = this.survivalClassService.create(varId);
		result = this.createEditModelAndView(survivalClass);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		ModelAndView result;
		SurvivalClass survivalClass;

		survivalClass = this.survivalClassService.findOne(varId);
		Assert.notNull(survivalClass);
		result = this.createEditModelAndView(survivalClass);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SurvivalClass survivalClass, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(survivalClass);
		else
			try {
				this.survivalClassService.save(survivalClass);
				result = new ModelAndView("redirect:/trip/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(survivalClass, "survivalClass.commit.error");
			}
		return result;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int varId) {
		ModelAndView result;
		SurvivalClass survivalClass;

		survivalClass = this.survivalClassService.findOne(varId);
		Assert.notNull(survivalClass);
		this.survivalClassService.delete(survivalClass);

		try {
			this.survivalClassService.delete(survivalClass);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(survivalClass, "creditCard.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final SurvivalClass survivalClass) {
		ModelAndView result;

		result = this.createEditModelAndView(survivalClass, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SurvivalClass survivalClass, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("survivalClass/edit");
		result.addObject("survivalClass", survivalClass);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "survivalClass/manager/edit.do");

		return result;

	}
}
