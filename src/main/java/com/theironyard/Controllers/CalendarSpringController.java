package com.theironyard.Controllers;

import com.theironyard.Entities.Event;
import com.theironyard.Services.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by branden on 3/14/16 at 10:18.
 */
@Controller
public class CalendarSpringController {

    @Autowired
    EventRepository eventRepository;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("events", eventRepository.findByOrderByDateTimeDesc());

        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(String description, String dateTime) {

//        DateTimeFormatter formatter = new DateTimeFormatter("")
        Event event = new Event(description, LocalDateTime.parse(dateTime));

        eventRepository.save(event);

        return "redirect:/";
    }


}