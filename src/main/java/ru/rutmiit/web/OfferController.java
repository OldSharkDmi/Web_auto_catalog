package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddOfferDto;
import ru.rutmiit.dto.ShowOfferInfoDto;
import ru.rutmiit.services.ModelService;
import ru.rutmiit.services.OfferService;
import ru.rutmiit.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {


    @Autowired
    private final OfferService offerService;
    @Autowired
    private final ModelService modelService;
    @Autowired
    private final UserService userService;
    public OfferController(OfferService offerService, ModelService modelService, UserService userService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAll());
        return "offer-add";}

    @ModelAttribute("offerModel")
    public AddOfferDto initModel() {return new AddOfferDto();}

    @PostMapping("/create")
    public String createOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.register(offerModel);

        return "redirect:/";
    }


    @GetMapping("/all")
    public String showAllOffers(Model model) {
        model.addAttribute("offerInfos", offerService.getAll());
        return "offer-all";
    }


    @GetMapping("/offer-details/{offer-id}")
    public String OfferDetails(@PathVariable("offer-id") String id, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(id));

        return "offer-details";
    }

    @GetMapping("/offer-delete/{offer-id}")
    public String deleteOffer(@PathVariable("offer-id") String id) {
        offerService.removeOffer(id);

        return "redirect:/";
    }

    @GetMapping("/all-sorted")
    public String showAllOffers(Model model, @RequestParam(required = false) String sortByPrice) {
        List<ShowOfferInfoDto> offerInfos;

        if ("asc".equals(sortByPrice) || "desc".equals(sortByPrice)) {
            offerInfos = offerService.getAllSortedByPrice(sortByPrice);
        } else {
            offerInfos = offerService.getAll();
        }

        model.addAttribute("offerInfos", offerInfos);
        return "offer-all";
    }
    @GetMapping("/all-sortedbyDate")
    public String showAllOffersbyDate(Model model, @RequestParam(required = false) String sortBy) {
        List<ShowOfferInfoDto> offerInfos;

        if ("asc".equals(sortBy) || "desc".equals(sortBy)) {
            offerInfos = offerService.getAllSortedByDate(sortBy);
        } else {
            offerInfos = offerService.getAll();
        }

        model.addAttribute("offerInfos", offerInfos);
        return "offer-all";
    }



}
