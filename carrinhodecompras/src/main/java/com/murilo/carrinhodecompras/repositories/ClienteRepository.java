package com.murilo.carrinhodecompras.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.murilo.carrinhodecompras.models.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("select c from Cliente c where c.id=:id")
	Cliente porId(@Param("id") Long id);
}
