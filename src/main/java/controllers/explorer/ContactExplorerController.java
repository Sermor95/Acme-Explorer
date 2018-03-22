
package controllers.explorer;

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
import services.ContactService;
import controllers.AbstractController;
import domain.Contact;
import domain.Explorer;

@Controller
@RequestMapping("contact/explorer")
public class ContactExplorerController extends AbstractController {

	//Services

	@Autowired
	private ContactService	contactService;

	@Autowired
	private ActorService	actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Contact> contacts;
		Explorer explorer;

		explorer = (Explorer) this.actorService.findByPrincipal();
		contacts = explorer.getContact();

		result = new ModelAndView("contact/list");
		result.addObject("contacts", contacts);
		result.addObject("requestURI", "contact/explorer/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Contact contact;

		contact = this.contactService.create();
		result = this.createEditModelAndView(contact);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int contactId) {
		ModelAndView result;
		Contact contact;

		contact = this.contactService.findOne(contactId);
		Assert.notNull(contact);
		result = this.createEditModelAndView(contact);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Contact contact, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(contact);
		else
			try {
				this.contactService.save(contact);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(contact, "contact.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Contact contact, final BindingResult binding) {
		ModelAndView result;

		try {
			this.contactService.delete(contact);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(contact, "contact.commit.error");
		}
		return result;
	}

	//Delete

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int contactId) {
		final ModelAndView result;
		Collection<Contact> contacts;
		final Contact contact;
		Explorer explorer;

		result = new ModelAndView("contact/list");
		explorer = (Explorer) this.actorService.findByPrincipal();
		contacts = explorer.getContact();

		contact = this.contactService.findOne(contactId);

		this.contactService.delete(contact);
		contacts = explorer.getContact();

		result.addObject("contacts", contacts);
		result.addObject("requestURI", "contact/explorer/list.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Contact contact) {
		ModelAndView result;

		result = this.createEditModelAndView(contact, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Contact contact, final String messageCode) {
		ModelAndView result;
		final Explorer explorer = (Explorer) this.actorService.findByPrincipal();
		final Collection<Contact> contacts = explorer.getContact();

		result = new ModelAndView("contact/edit");
		result.addObject("contact", contact);
		result.addObject("contacts", contacts);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "contact/explorer/edit.do");

		return result;

	}
}
