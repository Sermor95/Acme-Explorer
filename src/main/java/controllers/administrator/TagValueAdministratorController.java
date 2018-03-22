
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TagService;
import services.TagValueService;
import controllers.AbstractController;
import domain.Tag;
import domain.TagValue;

@Controller
@RequestMapping("tagValue/administrator")
public class TagValueAdministratorController extends AbstractController {

	//Services

	@Autowired
	private TagValueService	tagValueService;

	@Autowired
	private TagService		tagService;

	//Ancillary attributes

	private Tag				currentTag;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int varId) {
		final ModelAndView result;
		Collection<TagValue> tagValues;
		final Tag tag = this.tagService.findOne(varId);
		this.setCurrentTag(tag);
		tagValues = tag.getTagValue();

		result = new ModelAndView("tagValue/list");
		result.addObject("tagValues", tagValues);
		result.addObject("requestURI", "tagValue/administrator/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		TagValue tagValue;
		tagValue = this.tagValueService.create(this.getCurrentTag().getId());
		result = this.createEditModelAndView(tagValue, null);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		final TagValue tagValue = this.tagValueService.findOne(varId);
		result = this.createEditModelAndView(tagValue);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final TagValue tagValue, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tagValue, binding.toString());
		else
			try {
				tagValue.setTag(this.getCurrentTag());
				this.tagValueService.save(tagValue);
				final String uri = "redirect:/tag/administrator/list.do";
				result = new ModelAndView(uri);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tagValue, "tagValue.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final TagValue tagValue, final Boolean modify, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(tagValue, null);
		else
			try {
				this.tagValueService.delete(tagValue);
				final String uri = "redirect:/tag/administrator/list.do";
				result = new ModelAndView(uri);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(tagValue, "tagValue.commit.error");
			}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final TagValue tagValue) {
		ModelAndView result;

		result = this.createEditModelAndView(tagValue, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final TagValue tagValue, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("tagValue/edit");
		result.addObject("tagValue", tagValue);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "tagValue/administrator/edit.do");

		return result;

	}

	public Tag getCurrentTag() {
		return this.currentTag;
	}

	public void setCurrentTag(final Tag currentTag) {
		this.currentTag = currentTag;
	}
}
