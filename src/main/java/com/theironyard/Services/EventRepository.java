package com.theironyard.Services;

import com.theironyard.Entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by branden on 3/14/16 at 10:56.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
    List<Event> findByOrderByDateTimeDesc();
}
