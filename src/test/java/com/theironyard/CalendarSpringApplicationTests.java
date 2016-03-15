package com.theironyard;

import com.theironyard.Services.EventRepository;
import com.theironyard.Services.FavRepository;
import com.theironyard.Services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
    FavRepository favRepository;


    @Autowired
    WebApplicationContext wap;

    //sends a fake request
    MockMvc mockMvc;

    boolean firstRun = true;


    @Before
    public void before() {
        if (firstRun) {
            favRepository.deleteAll();
            eventRepository.deleteAll();
            userRepository.deleteAll();
            mockMvc = MockMvcBuilders.webAppContextSetup(wap).build(); //this thing sends fake get and post requests.
            firstRun = false;
        }
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login") //the route to hit
                .param("userName", "testUser")  //created a fake user as key, value
        );

        Assert.assertTrue(userRepository.count() == 1);
    }

    @Test
    public void testAddEvent() throws Exception {
        testLogin();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-event")
                .param("description", "Test event")
                .param("dateTime", LocalDateTime.now().toString())
                .sessionAttr("userName", "testUser")
        );
        Assert.assertTrue(eventRepository.count() == 1);

    }



}
