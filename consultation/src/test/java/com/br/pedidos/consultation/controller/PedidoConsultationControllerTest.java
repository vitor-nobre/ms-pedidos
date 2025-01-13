package com.br.pedidos.consultation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOrdersByStatus() throws Exception {
        mockMvc.perform(get("/pedidos/status?status=PROCESSADO&page=0&size=5"))
                .andExpect(status().isOk())  // Espera o status 200
                .andExpect(jsonPath("$.content").isArray());  // Espera que o conteúdo seja um array
    }
}
