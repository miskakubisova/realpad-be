package cz.interview.realpad.realpadinterview;

import cz.interview.realpad.realpadinterview.service.RealpadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RealpadController.class)
@AutoConfigureMockMvc
public class RealpadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RealpadService realpadService;

    @Test
    public void getWeatherForecast_success() throws Exception {
        mockMvc.perform(get("/forecast/Prague"))
                .andExpect(status().isOk());
    }

    @Test
    public void getWeatherForecast_invalidInput() throws Exception {
        mockMvc.perform(get("/forecast/blabla"))
                .andExpect(status().isBadRequest());
    }
}
