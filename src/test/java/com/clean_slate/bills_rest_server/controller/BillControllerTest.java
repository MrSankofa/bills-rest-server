package com.clean_slate.bills_rest_server.controller;

import com.clean_slate.bills_rest_server.model.Bill;
import com.clean_slate.bills_rest_server.service.BillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Some redundant tests just for practice and learning
@WebMvcTest(BillController.class)
@ActiveProfiles("test")
@WithMockUser(username = "testuser", roles = {"USER"})
public class BillControllerTest {


  @Autowired
  private MockMvc mockMvc;

  // this allows us to have access to the bill service for all of tests and maintains it state
  @MockitoBean
  private BillService billService;


  @Test
  void getAllBills_ShouldReturnListOfBills() throws Exception {
    Bill bill1 = new Bill();
    bill1.setId(1L);
    bill1.setName("Electricity Bill");
    bill1.setAmount(120.5);

    Bill bill2 = new Bill();
    bill2.setId(2L);
    bill2.setName("Internet Bill");
    bill2.setAmount(60.75);

    List<Bill> bills = Arrays.asList(bill1, bill2);
    when(billService.getAllBills()).thenReturn(bills);


  /*
  * Intercepting the Request
      The mockMvc.perform(get("/api/bills")) does the following:
         Sends a simulated HTTP GET request to /api/bills.

      Spring’s MockMvc:
         Routes the request internally to the BillController.

      Finds the matching controller method based on the path (@GetMapping("/api/bills")).
  *
  * */

    mockMvc.perform(get("/api/bills"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Electricity Bill"))
        .andExpect(jsonPath("$[1].name").value("Internet Bill"));

    verify(billService, times(1)).getAllBills();
  }

  @Test
  void getBillById_ShouldReturnBill() throws Exception {
    Bill bill1 = new Bill();
    bill1.setId(1L);
    bill1.setName("Electricity Bill");
    bill1.setAmount(120.5);

    Bill bill2 = new Bill();
    bill2.setId(2L);
    bill2.setName("Internet Bill");
    bill2.setAmount(60.75);

    List<Bill> bills = Arrays.asList(bill1, bill2);

    when(billService.getBillById(1L)).thenReturn(bill1);
    when(billService.getBillById(2L)).thenReturn(bill2);

    mockMvc.perform(get("/api/bills/{id}", 2L))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Internet Bill"))
        .andExpect(jsonPath("$.amount").value(60.75));


  }

}
