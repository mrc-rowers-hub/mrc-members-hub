package com.codeaddi.row_your_boat.view;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.view.display.ViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ViewService viewService;

    @MockBean
    private AvailabilityClient availabilityClient;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(post("/home")
                        .param("username", "testUser")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("username", "testUser"));
    }

    @Test
    public void testMyAvailability() throws Exception {
        mockMvc.perform(get("/my-availability"))
                .andExpect(status().isOk())
                .andExpect(view().name("availability/my-availability"));
    }

    @Test
    public void testShowSessionAvailability() throws Exception {
        mockMvc.perform(post("/session-availability")
                        .param("date", "2024-08-24")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("adminOnly/session-availability"))
                .andExpect(model().attribute("availabilities", List.of()));
    }

    @Test
    public void testStandardSessions() throws Exception {
        mockMvc.perform(get("/standard-sessions"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminOnly/standard-sessions"))
                .andExpect(model().attribute("sessions", Map.of()));
    }

    @Test
    public void testViewSessions() throws Exception {
        mockMvc.perform(get("/view-sessions-to-edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminOnly/view-sessions-to-edit"))
                .andExpect(model().attribute("sessions", List.of())); 
    }

    @Test
    public void testMakeNewSessions() throws Exception {
        mockMvc.perform(get("/make-weekly-plan"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminOnly/make-new-sessions"))
                .andExpect(model().attribute("list", List.of())); 
    }

    @Test
    public void testBoats() throws Exception {
        mockMvc.perform(get("/boats"))
                .andExpect(status().isOk())
                .andExpect(view().name("resources/boats"));
    }

    @Test
    public void testBlades() throws Exception {
        mockMvc.perform(get("/blades"))
                .andExpect(status().isOk())
                .andExpect(view().name("resources/blades"));
    }
}
