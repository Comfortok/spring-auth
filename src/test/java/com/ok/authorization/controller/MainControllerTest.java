package com.ok.authorization.controller;

import com.ok.authorization.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class MainControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MainController mainController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldReturnDefaultHelloPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/", "welcome**"));
        result.andDo(print())
                .andExpect(view().name("hello"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLoginPageWithErrorAttribute() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .param("error", "true"));
        result.andDo(print())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLoginPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/login"));
        result.andDo(print())
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn403ErrorPage() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/403"));
        result.andDo(print())
                .andExpect(view().name("403"))
                .andExpect(status().isOk());
    }
}