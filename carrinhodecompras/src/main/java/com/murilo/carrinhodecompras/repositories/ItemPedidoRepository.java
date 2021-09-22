package com.murilo.carrinhodecompras.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murilo.carrinhodecompras.models.ItemPedido;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
