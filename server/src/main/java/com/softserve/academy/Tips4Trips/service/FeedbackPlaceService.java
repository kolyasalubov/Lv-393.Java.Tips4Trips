package com.softserve.academy.Tips4Trips.service;

        import com.softserve.academy.Tips4Trips.entity.feedback.FeedbackPlace;
        import com.softserve.academy.Tips4Trips.entity.place.Place;
        import com.softserve.academy.Tips4Trips.repository.FeedbackPlaceRepository;
        import org.apache.log4j.Logger;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.stereotype.Service;

        import java.util.NoSuchElementException;
        import java.util.Optional;

@Service
public class FeedbackPlaceService {

    private static final Logger logger = Logger.getLogger(FeedbackPlaceService.class);

    private FeedbackPlaceRepository repository;

    @Autowired
    public FeedbackPlaceService(FeedbackPlaceRepository repository) {
        this.repository = repository;
    }


    public FeedbackPlace createFeedbackPlace(FeedbackPlace feedbackPlace) {
        return repository.save(feedbackPlace);
    }

    public void deleteFeedbackPlaceById(Long id) {
        repository.deleteById(id);
    }

    public FeedbackPlace updateFeedbackPlace(FeedbackPlace feedbackPlace) {
        return repository.save(feedbackPlace);
    }

    public Page<FeedbackPlace> getByPalce(Long place, PageRequest pageable) {
        return repository.findAllByPlaceIdOrderByDateDesc(place,pageable);
    }
}
