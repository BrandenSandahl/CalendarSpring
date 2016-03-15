package com.theironyard.Services;

import com.theironyard.Entities.Event;
import com.theironyard.Entities.Favorite;
import com.theironyard.Entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by branden on 3/14/16 at 11:40.
 */
public interface FavRepository extends CrudRepository<Favorite, Integer> {
    Favorite findByUserAndEvent(User user, Event event);


}
