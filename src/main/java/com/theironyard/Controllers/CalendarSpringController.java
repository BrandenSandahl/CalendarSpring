package com.theironyard.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

/**
 * Created by branden on 3/14/16 at 10:18.
 */
@Controller
public class CalendarSpringController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {

        model.addAttribute("now", LocalDateTime.now());


        return "home";
    }


}