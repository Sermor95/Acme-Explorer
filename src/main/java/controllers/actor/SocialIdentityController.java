
package controllers.actor;

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
import services.SocialIdentityService;
import controllers.AbstractController;
import domain.SocialIdentity;

@Controller
@RequestMapping("socialIdentity")
public class SocialIdentityController extends AbstractController {

	//Services

	@Autowired
	private SocialIdentityService	socialIdentityService;

	@Autowired
	private ActorService			actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<SocialIdentity> socialIdentities;

		socialIdentities = this.actorService.findByPrincipal().getSocialIdentities();

		result = new ModelAndView("socialIdentity/list");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("requestURI", "socialIdentity/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = this.socialIdentityService.create();
		result = this.createEditModelAndView(socialIdentity);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialIdentityId) {
		final ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = this.socialIdentityService.findOne(socialIdentityId);
		Assert.notNull(socialIdentity);
		result = this.createEditModelAndView(socialIdentity);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialIdentity socialIdentity, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(socialIdentity);
		else
			try {
				this.socialIdentityService.save(socialIdentity);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(socialIdentity, "socialIdentity.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SocialIdentity socialIdentity, final BindingResult binding) {
		ModelAndView result;

		try {
			this.socialIdentityService.delete(socialIdentity);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(socialIdentity, "socialIdentity.commit.error");
		}
		return result;
	}

	//Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int socialIdentityId) {
		final ModelAndView result;
		Collection<SocialIdentity> socialIdentities;
		SocialIdentity socialIdentity;

		result = new ModelAndView("socialIdentity/list");
		socialIdentities = this.actorService.findByPrincipal().getSocialIdentities();

		socialIdentity = this.socialIdentityService.findOne(socialIdentityId);

		this.socialIdentityService.delete(socialIdentity);
		socialIdentities = this.actorService.findByPrincipal().getSocialIdentities();

		result.addObject("socialIdentities", socialIdentities);
		result.addObject("requestURI", "socialIdentity/list.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final SocialIdentity socialIdentity) {
		ModelAndView result;

		result = this.createEditModelAndView(socialIdentity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SocialIdentity socialIdentity, final String messageCode) {
		ModelAndView result;
		final Collection<SocialIdentity> socialIdentities = this.actorService.findByPrincipal().getSocialIdentities();

		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "socialIdentity/edit.do");

		return result;

	}
}
