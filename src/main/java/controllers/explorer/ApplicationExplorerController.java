
package controllers.explorer;

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
import domain.CreditCard;
import domain.Explorer;
import domain.Status;

@Controller
@RequestMapping("application/explorer")
public class ApplicationExplorerController extends AbstractController {

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
		Collection<Application> applications;
		final Date futureDate = LocalDate.now().plusMonths(1).toDate();

		applications = ((Explorer) this.actorService.findByPrincipal()).getApplication();

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("futureDate", futureDate);
		result.addObject("requestURI", "application/explorer/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int varId) {
		final ModelAndView result;
		Application application;

		application = this.applicationService.create(varId);
		result = this.createEditModelAndView(application);

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

				this.mailMessageService.applicationStatusNotification(saved.getExplorer().getId(), saved.getTrip().getManager().getId());

				result = new ModelAndView("redirect:/trip/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(application, "application.commit.error");
			}
		return result;
	}

	//Cancellation

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int varId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(varId);

		try {
			application.setStatus(Status.CANCELLED);

			this.mailMessageService.applicationStatusNotification(application.getExplorer().getId(), application.getTrip().getManager().getId());

			this.applicationService.save(application);
			result = new ModelAndView("redirect:/application/explorer/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/application/explorer/list.do");
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
		final Collection<CreditCard> creditCards = ((Explorer) this.actorService.findByPrincipal()).getCreditCard();

		result = new ModelAndView("application/edit");
		result.addObject("application", application);
		result.addObject("creditCards", creditCards);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "application/explorer/edit.do");

		return result;

	}
}
