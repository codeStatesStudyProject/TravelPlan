package travelplanrepo.global;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        // when
        ResultActions actions = mockMvc.perform(
                        post("/board")
                                .param("title", "스위스 일주")
                                .param("preface", "온천여행")
                );

        // then
        actions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.state").value("400"))
                .andExpect(jsonPath("$.exception").value(NoSuchElementException.class.getSimpleName()))
                .andExpect(jsonPath("$.message").value("회원을 찾을 수 없습니다.")
                );
    }
}
