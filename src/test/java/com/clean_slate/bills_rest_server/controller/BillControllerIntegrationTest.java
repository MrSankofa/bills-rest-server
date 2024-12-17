package com.clean_slate.bills_rest_server.controller;


import com.clean_slate.bills_rest_server.model.Bill;
import com.clean_slate.bills_rest_server.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BillControllerIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BillRepository billRepository;

  @BeforeEach
  void setUp() {
    billRepository.deleteAll(); // Clear data before each test
  }

  @Test
  void getAllBills_ShouldReturnAllBills() throws Exception {
    // Arrange: Save some sample bills in the database
    Bill bill1 = new Bill();
    bill1.setName("Electricity Bill");
    bill1.setAmount(120.5);
    billRepository.save(bill1);

    Bill bill2 = new Bill();
    bill2.setName("Internet Bill");
    bill2.setAmount(60.75);
    billRepository.save(bill2);

    // Act & Assert: Perform GET request and validate response
    mockMvc.perform(get("/api/bills"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Electricity Bill"))
        .andExpect(jsonPath("$[1].name").value("Internet Bill"));
  }
}
