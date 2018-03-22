
package controllers.administrator;

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

import services.TagService;
import controllers.AbstractController;
import domain.Tag;

@Controller
@RequestMapping("tag/administrator")
public class TagAdministratorController extends AbstractController {

	//Services

	@Autowired
	private TagService	tagService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Tag> tags;

		tags = this.tagService.findAll();
		result = new ModelAndView("tag/list");
		result.addObject("tags", tags);
		result.addObject("requestURI", "tag/administrator/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Tag tag;

		tag = this.tagService.create();
		result = this.createEditModelAndView(tag);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Tag tag;

		tag = this.tagService.findOne(varId);
		Assert.notNull(tag);
		result = this.createEditModelAndView(tag);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Tag tag, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tag);
		else
			try {
				this.tagService.save(tag);
				result = new ModelAndView("redirect:/tag/administrator/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tag, "tag.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Tag tag, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tag, binding.toString());
		else
			try {
				this.tagService.delete(tag);
				result = new ModelAndView("redirect:/tag/administrator/list.do");
			} catch (final Throwable oops) {
				//result = this.createEditModelAndView(tag, "tag.commit.error");
				result = this.createEditModelAndView(tag, oops.toString());
			}
		return result;
	}

	//Delete

	//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	//	public ModelAndView delete(@RequestParam final int varId) {
	//		final ModelAndView result;
	//
	//		final Tag tag = this.tagService.findOne(varId);
	//		this.tagService.delete(tag);
	//
	//		result = new ModelAndView("tag/list");
	//
	//		result.addObject("requestURI", "/tag/administrator/list.do");
	//
	//		return result;
	//	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Tag tag) {
		ModelAndView result;

		result = this.createEditModelAndView(tag, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Tag tag, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("tag/edit");
		result.addObject("tag", tag);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "tag/administrator/edit.do");

		return result;

	}
}
