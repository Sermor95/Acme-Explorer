
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
import services.ManagerService;
import services.NoteService;
import controllers.AbstractController;
import domain.Manager;
import domain.Note;

@Controller
@RequestMapping("note/manager")
public class NoteManagerController extends AbstractController {

	//Services

	@Autowired
	private NoteService		noteService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ManagerService	managerService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Note> notes;
		int managerId;

		managerId = this.actorService.findByPrincipal().getId();
		final Manager manager = this.managerService.findOne(managerId);

		notes = this.noteService.notesOfTrips(manager);

		result = new ModelAndView("note/list");
		result.addObject("notes", notes);
		result.addObject("requestURI", "note/manager/list.do");

		return result;
	}
	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int noteId) {
		final ModelAndView result;
		Note note;

		note = this.noteService.findOne(noteId);
		Assert.notNull(note);
		result = this.createEditModelAndView(note);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Note note, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(note);
		else
			try {
				this.noteService.save(note);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(note, "note.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Note note) {
		ModelAndView result;

		result = this.createEditModelAndView(note, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Note note, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("note/edit");
		result.addObject("note", note);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "note/manager/edit.do");

		return result;

	}
}
