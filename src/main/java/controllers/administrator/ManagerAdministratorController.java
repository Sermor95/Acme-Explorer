
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ManagerService;
import controllers.AbstractController;
import domain.Manager;

@Controller
@RequestMapping("manager/administrator")
public class ManagerAdministratorController extends AbstractController {

	//Services

	@Autowired
	private ManagerService	managerService;


	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Manager manager;

		manager = this.managerService.create();
		result = this.createEditModelAndView(manager);

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Manager manager) {
		ModelAndView result;

		result = this.createEditModelAndView(manager, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Manager manager, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("manager/edit");
		result.addObject("manager", manager);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "manager/edit.do");

		return result;

	}
}
