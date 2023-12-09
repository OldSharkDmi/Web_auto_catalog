package ru.rutmiit.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import ru.rutmiit.models.Offer;
import ru.rutmiit.services.OfferService;

import java.util.List;

@Controller
public class HomeController {

    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Offer> top3Offers = offerService.getTop3CheapestOffers();
        model.addAttribute("topOffers", top3Offers);
        return "index";
    }
}
