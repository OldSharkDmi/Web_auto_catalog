package ru.rutmiit.services;


import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.AddOfferDto;
import ru.rutmiit.dto.ShowDetailedOfferInfoDto;
import ru.rutmiit.dto.ShowOfferInfoDto;
import ru.rutmiit.models.Offer;
import ru.rutmiit.repositories.ModelRepository;
import ru.rutmiit.repositories.OfferRepository;
import ru.rutmiit.repositories.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    private final UserRepository userRepository;

    public OfferService(ModelMapper modelMapper, OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }


    public void register(AddOfferDto offer) {
        Offer of = modelMapper.map(offer, Offer.class);
        of.setModel(modelRepository.findByName(offer.getModel()).orElse(null));
        of.setUsers(userRepository.findByUserName(offer.getUsers()).orElse(null));
        offerRepository.saveAndFlush(of);
    }

    public List<ShowOfferInfoDto> getAll() {
        return offerRepository.findAll().stream().map((offer) -> modelMapper.map(offer, ShowOfferInfoDto.class)).collect(Collectors.toList());
    }


    public Optional<AddOfferDto> findOffer(String id) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(id), AddOfferDto.class));
    }

    public ShowDetailedOfferInfoDto offerDetails(String id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), ShowDetailedOfferInfoDto.class);
    }

    public void removeOffer(String id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getTop3CheapestOffers() {
        // Здесь вызывайте метод репозитория для получения топ 3 заказов
        return offerRepository.findTop3ByOrderByPrice();
    }

    public List<ShowOfferInfoDto> getAllSortedByPrice(String sortOrder) {
        List<Offer> offers = offerRepository.findAll();

        if ("asc".equals(sortOrder)) {
            Collections.sort(offers, (o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
        } else {
            Collections.sort(offers, (o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        }

        return offers.stream().map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class)).collect(Collectors.toList());
    }
    public List<ShowOfferInfoDto> getAllSortedByDate(String sortOrder) {
        List<Offer> offers = offerRepository.findAll();

        if ("asc".equals(sortOrder)) {
            Collections.sort(offers, Comparator.comparing(Offer::getCreated));
        } else {
            Collections.sort(offers, Comparator.comparing(Offer::getCreated).reversed());
        }

        return offers.stream()
                .map(offer -> modelMapper.map(offer, ShowOfferInfoDto.class))
                .collect(Collectors.toList());
    }

//    public AddOfferDto update(AddOfferDto offer) {
//        Optional<Offer> existingOffer = offerRepository.findById(offer.getId());
//        if (existingOffer.isPresent()) {
//            return modelMapper.map(offerRepository.save(modelMapper.map(offer, Offer.class)), AddOfferDto.class);
//        } else {
//            throw new DataIntegrityViolationException("Exception of update");
//        }
//    }
}