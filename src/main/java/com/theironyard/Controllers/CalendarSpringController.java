package com.theironyard.Controllers;

import com.theironyard.Entities.Event;
import com.theironyard.Entities.Favorite;
import com.theironyard.Entities.User;
import com.theironyard.Services.EventRepository;
import com.theironyard.Services.FavRepository;
import com.theironyard.Services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by branden on 3/14/16 at 10:18.
 */
@Controller
public class CalendarSpringController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FavRepository favRepository;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        List<Event> eventEntities = eventRepository.findByOrderByDateTimeDesc();
        if (userName != null) {
            User user = userRepository.findFirstByName(userName);

            model.addAttribute("user", user);
            model.addAttribute("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            for (Event event : eventEntities) {
                event.setShowFavButton(favRepository.findByUserAndEvent(user, event) == null);
            }
        }

        model.addAttribute("events", eventEntities);
        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String dateTime) {
        User user = userRepository.findFirstByName((String) session.getAttribute("userName"));

        if (user != null) {
            Event event = new Event(description, LocalDateTime.parse(dateTime), user);
            eventRepository.save(event);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        User user = userRepository.findFirstByName(userName);
        if (user == null) {
            user = new User(userName);
            userRepository.save(user);
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/fav", method = RequestMethod.POST)
    public String fav(HttpSession session, int id) {
        User user = userRepository.findFirstByName((String) session.getAttribute("userName"));
        if (user != null) {
            Event event = eventRepository.findOne(id);
            favRepository.save(new Favorite(user, event));
        }

        return "redirect:/";

    }


}