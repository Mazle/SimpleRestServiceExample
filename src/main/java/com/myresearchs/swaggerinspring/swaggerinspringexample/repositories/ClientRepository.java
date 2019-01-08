package com.myresearchs.swaggerinspring.swaggerinspringexample.repositories;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository extends JpaRepository<Client,Long> {
}
