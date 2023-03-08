package com.procesos.inventario.repository;

import com.procesos.inventario.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
