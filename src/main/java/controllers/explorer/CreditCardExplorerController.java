
package controllers.explorer;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ExplorerService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Explorer;

@Controller
@RequestMapping("creditCard/explorer")
public class CreditCardExplorerController extends AbstractController {

	//Services

	@Autowired
	private ExplorerService	explorerService;

	@Autowired
	private ActorService	actorService;


	//Listing

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final CreditCard creditcard = new CreditCard();

		result = this.createEditModelAndView(creditcard);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;

		Collection<CreditCard> creditCards;
		Explorer explorer;

		explorer = (Explorer) this.actorService.findByPrincipal();
		creditCards = explorer.getCreditCard();

		result = new ModelAndView("creditCard/list");
		result.addObject("explorer", explorer);
		result.addObject("creditCards", creditCards);
		result.addObject("requestURI", "creditCard/explorer/list.do");

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CreditCard creditCard, final BindingResult binding) {
		ModelAndView result;

		Explorer explorer;

		Collection<CreditCard> creditCards;

		explorer = (Explorer) this.actorService.findByPrincipal();

		creditCards = explorer.getCreditCard();

		final Calendar now = Calendar.getInstance();

		if (creditCard.getExpYear() < now.get(Calendar.YEAR))

			result = this.createEditModelAndView(creditCard, "creditCard.date.error");

		else if (creditCard.getExpMonth() < now.get(Calendar.MONTH) && creditCard.getExpYear() == now.get(Calendar.YEAR))

			result = this.createEditModelAndView(creditCard, "creditCard.date.error");

		else if (binding.hasErrors())

			result = this.createEditModelAndView(creditCard);

		else

			try {

				creditCards.add(creditCard);

				explorer.setCreditCard(creditCards);

				this.explorerService.save(explorer);

				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {

				result = this.createEditModelAndView(creditCard, "creditCard.commit.error");

			}

		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final String varId) {

		ModelAndView result;
		Explorer explorer;
		Collection<CreditCard> creditCards;
		CreditCard creditcard = new CreditCard();

		explorer = (Explorer) this.actorService.findByPrincipal();
		creditCards = explorer.getCreditCard();

		for (final CreditCard c : creditCards)
			if (c.getNumber().equals(varId))
				creditcard = c;

		try {
			creditCards.remove(creditcard);
			explorer.setCreditCard(creditCards);
			this.explorerService.save(explorer);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(creditcard, "creditCard.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final CreditCard creditCard) {
		ModelAndView result;

		result = this.createEditModelAndView(creditCard, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CreditCard creditCard, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("creditCard/edit");
		result.addObject("creditCard", creditCard);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "creditCard/explorer/edit.do");

		return result;

	}
}
