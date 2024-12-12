package com.clean_slate.bills_rest_server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
  // Default methods for CRUD are inherited
}
