/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ActorService;
import services.ApplicationService;
import services.LegalTextService;
import services.ManagerService;
import services.RangerService;
import services.TripService;
import controllers.AbstractController;
import domain.Actor;
import domain.Administrator;
import domain.LegalTextTable;
import domain.Manager;
import domain.Ranger;
import domain.Trip;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	//Services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private LegalTextService	legalTextService;

	@Autowired
	private ManagerService		managerService;

	@Autowired
	private RangerService		rangerService;

	@Autowired
	private TripService			tripService;


	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		final ModelAndView result;
		Administrator administrator;
		administrator = (Administrator) this.actorService.findByPrincipal();
		Assert.notNull(administrator);
		result = this.createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				this.actorService.hashPassword(administrator);
				this.actorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator, "actor.commit.error");
			}
		return result;
	}

	//Dashboard

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		final Collection<String> tripsWithAboveAverageApplications = new ArrayList<String>();
		final Map<String, Long> legalTextTable = new HashMap<String, Long>();

		//Parse the collection to display the trips' names.
		final Collection<Trip> trips = this.tripService.tripsWithAboveAverageApplications();
		for (final Trip t : trips)
			tripsWithAboveAverageApplications.add(t.getTitle());

		//Parse the map to show the legal texts' names.
		for (final LegalTextTable ltt : this.legalTextService.legalTextTable())
			legalTextTable.put(ltt.getText().getTitle(), ltt.getCount());

		result = new ModelAndView("administrator/dashboard");

		result.addObject("minMaxAvgStddevApplicationsPerTrip", Arrays.toString(this.tripService.minMaxAvgStddevApplicationsPerTrip()));
		result.addObject("minMaxAvgStddevTripsPerManager", Arrays.toString(this.managerService.minMaxAvgStddevTripsPerManager()));
		result.addObject("minMaxAvgStddevPricePerTrip", Arrays.toString(this.tripService.minMaxAvgStddevPricePerTrip()));
		result.addObject("minMaxAvgStddevTripsPerRanger", Arrays.toString(this.rangerService.minMaxAvgStddevTripsPerRanger()));
		result.addObject("ratioApplicationsPending", this.applicationService.ratioApplicationsPending());
		result.addObject("ratioApplicationsDue", this.applicationService.ratioApplicationsDue());
		result.addObject("ratioApplicationsAccepted", this.applicationService.ratioApplicationsAccepted());
		result.addObject("ratioApplicationsCancelled", this.applicationService.ratioApplicationsCancelled());
		result.addObject("ratioTripsCancelled", this.tripService.ratioTripsCancelled());
		result.addObject("tripsWithAboveAverageApplications", tripsWithAboveAverageApplications);
		result.addObject("legalTextTable", legalTextTable);

		result.addObject("minMaxAvgStddevNotesPerTrip", Arrays.toString(this.tripService.minMaxAvgStddevNotesPerTrip()));
		result.addObject("minMaxAvgStddevAuditsPerTrip", Arrays.toString(this.tripService.minMaxAvgStddevAuditsPerTrip()));
		result.addObject("ratioTripsWithAudit", this.tripService.ratioTripsWithAudit());
		result.addObject("ratioRangersWithCurriculum", this.rangerService.ratioRangersWithCurriculum());
		result.addObject("ratioRangersEndorsed", this.rangerService.ratioRangersEndorsed());
		result.addObject("ratioSuspiciousManagers", this.managerService.ratioSuspiciousManagers());
		result.addObject("ratioSuspiciousRangers", this.rangerService.ratioSuspiciousRangers());

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

	//Listing suspicious actors

	@RequestMapping(value = "/suspiciousList", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Actor> actors = new ArrayList<Actor>();
		final Collection<Manager> managers = this.managerService.suspiciousManagers();
		actors.addAll(managers);
		final Collection<Ranger> rangers = this.rangerService.suspiciousRangers();
		actors.addAll(rangers);

		result = new ModelAndView("administrator/suspiciousList");
		result.addObject("actors", actors);
		result.addObject("requestURI", "administrator/suspiciousList.do");

		return result;
	}

	//Ban and unban actors

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int varId) {
		final ModelAndView result;
		final Actor actor = this.actorService.findOne(varId);
		final UserAccount userAccount = actor.getUserAccount();
		;

		if (!userAccount.getAuthorities().isEmpty())
			userAccount.removeAuthority(userAccount.getAuthorities().iterator().next());
		else if (actor.getClass().toString().contains("Manager"))
			actor.getUserAccount().addAuthority((Authority) Authority.listAuthorities().toArray()[1]);
		else
			actor.getUserAccount().addAuthority((Authority) Authority.listAuthorities().toArray()[2]);
		this.actorService.save(actor);
		result = new ModelAndView("redirect:/administrator/suspiciousList.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "administrator/edit.do");

		return result;

	}
}
