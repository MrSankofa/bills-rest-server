package com.clean_slate.bills_rest_server.service;


import com.clean_slate.bills_rest_server.model.Bill;
import com.clean_slate.bills_rest_server.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
  private final BillRepository billRepository;

  public BillService(BillRepository billRepository) {
    this.billRepository = billRepository;
  }

  public List<Bill> getAllBills() {
    return billRepository.findAll();
  }

  public Bill getBillById(Long id) {
    return billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found"));
  }

  public Bill saveBill(Bill bill) {
    return billRepository.save(bill);
  }

  public void deleteBill(Long id) {
    billRepository.deleteById(id);
  }

  public List<Bill> saveAllBills(List<Bill> bills) {
    return billRepository.saveAll(bills);
  }
}

