package controllers;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.InvoiceController;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(InvoiceController.class)

 class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private IInvoiceService invoiceService;


    @BeforeEach
   void setup() {
        Invoice mockInvoice = new Invoice();
        mockInvoice.setIdInvoice(1L); // Ensure this matches your entity and JSON structure
        // Mock further properties of mockInvoice as needed for the test

        when(invoiceService.retrieveInvoice(anyLong())).thenReturn(mockInvoice);

        mockMvc = MockMvcBuilders.standaloneSetup(new InvoiceController(invoiceService)).build();
    }

    @Test
 void retrieveInvoice_ShouldReturnInvoice() throws Exception {
        mockMvc.perform(get("/invoice/{invoiceId}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idInvoice").value(1)); // Ensure this JSON path matches the actual response
    }





    @Configuration
    static class TestConfig {
        @MockBean
        private IInvoiceService invoiceService; // Mock the IInvoiceService

        @Bean
        public InvoiceController invoiceController() {
            return new InvoiceController(invoiceService); // Pass the mock service here
        }
    }

}
