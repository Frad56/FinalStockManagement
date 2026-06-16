package com.example.store.controller.quotationControllerManagement;

import com.example.store.controller.quotationController.QuotationLineController;
import com.example.store.dto.quotationManagement.QuotationLineDTO;
import com.example.store.security.jwt.JwtUtil;
import com.example.store.service.quotationService.interfaces.QuotationLineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(QuotationLineController.class)
@AutoConfigureMockMvc(addFilters = false)
public class QuotationLineControllerTest {

    @MockBean
    private QuotationLineService quotationLineService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.example.store.security.details.CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @WithMockUser
    @Test
    void shouldReturnQuotationLines() throws Exception {

        QuotationLineDTO dto = new QuotationLineDTO();
        dto.setQuotationLineId(1L);

        when(quotationLineService.fetchQuotationLineByQuotationId(1L))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/quotationLine/find/1"))
                .andExpect(status().isOk());

        verify(quotationLineService)
                .fetchQuotationLineByQuotationId(1L);
    }


    @WithMockUser
    @Test
    void shouldDeleteQuotationLine() throws Exception {

        doNothing().when(quotationLineService)
                .deleteQuotationLine(1L);

        mockMvc.perform(
                        delete("/api/quotationLine/deleteByQuotationLine/1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Deleted Successfully"));

        verify(quotationLineService)
                .deleteQuotationLine(1L);
    }

    @WithMockUser
    @Test
    void shouldUpdateQuotationLine() throws Exception {

        QuotationLineDTO dto = new QuotationLineDTO();
        dto.setQuotationLineId(1L);

        when(quotationLineService.updateQuotationLine(any(), eq(1L)))
                .thenReturn(dto);

        String json = """
        {
            "quotationLineId":1
        }
        """;

        mockMvc.perform(
                        put("/api/quotationLine/update/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());

        verify(quotationLineService)
                .updateQuotationLine(any(), eq(1L));
    }
}
