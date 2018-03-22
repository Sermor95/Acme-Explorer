
package controllers.manager;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.MailMessageService;
import controllers.AbstractController;
import domain.Application;
import domain.Manager;
import domain.Status;

@Controller
@RequestMapping("application/manager")
public class ApplicationManagerController extends AbstractController {

	//Services

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MailMessageService	mailMessageService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Application> applications;
		Manager manager;
		final Date futureDate = LocalDate.now().plusMonths(1).toDate();

		manager = (Manager) this.actorService.findByPrincipal();
		applications = this.applicationService.applicationsByManager(manager);

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("futureDate", futureDate);
		result.addObject("requestURI", "application/manager/list.do");

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Application application;

		application = this.applicationService.findOne(varId);
		Assert.notNull(application);
		result = this.createEditModelAndView(application);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(application);
		else
			try {
				final Application saved = this.applicationService.save(application);

				if (saved.getStatus() != Status.PENDING)
					this.mailMessageService.applicationStatusNotification(saved.getExplorer().getId(), saved.getTrip().getManager().getId());

				result = new ModelAndView("redirect:/application/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application, "application.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("application/edit");
		result.addObject("application", application);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "application/manager/edit.do");

		return result;

	}
}
