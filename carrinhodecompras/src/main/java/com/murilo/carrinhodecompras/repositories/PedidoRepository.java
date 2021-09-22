package com.murilo.carrinhodecompras.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.murilo.carrinhodecompras.models.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query(value = "select c.nome, sum(p.preco_total) from pedido as p inner join cliente as c on p.cliente_id = c.id group by c.id, p.cliente_id order by sum desc", nativeQuery = true)
	List<?> totalPorClientes();
}
