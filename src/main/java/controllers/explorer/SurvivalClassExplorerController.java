
package controllers.explorer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ExplorerService;
import services.SurvivalClassService;
import services.TripService;
import controllers.AbstractController;
import domain.Explorer;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Controller
@RequestMapping("survivalClass/explorer")
public class SurvivalClassExplorerController extends AbstractController {

	//Services

	@Autowired
	private SurvivalClassService	survivalClassService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ExplorerService			explorerService;

	@Autowired
	private TripService				tripService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Trip trip;
		Collection<SurvivalClass> list = new ArrayList<SurvivalClass>();
		final Collection<SurvivalClass> survivalClasses = new ArrayList<SurvivalClass>();
		Manager manager;

		trip = this.tripService.findOne(varId);
		manager = this.tripService.findOne(varId).getManager();
		list = manager.getSurvivalClass();

		for (final SurvivalClass s : list)
			if (s.getTrip().equals(trip))
				survivalClasses.add(s);
		result = new ModelAndView("survivalClass/list");
		result.addObject("survivalClasses", survivalClasses);
		result.addObject("requestURI", "survivalClass/list.do");

		return result;
	}

	//Enrol

	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView enrol(@RequestParam final int varId) {
		ModelAndView result;
		final int explorerId;
		Explorer explorer;
		SurvivalClass survivalClass = new SurvivalClass();
		explorerId = this.actorService.findByPrincipal().getId();
		explorer = this.explorerService.findOne(explorerId);
		survivalClass = this.survivalClassService.findOne(varId);
		survivalClass.getExplorers().add(explorer);

		try {

			this.survivalClassService.save(survivalClass);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(survivalClass, "survivalClass.commit.error");
		}
		return result;
	}

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
		result.addObject("requestURI", "survivalClass/edit.do");

		return result;

	}
}
