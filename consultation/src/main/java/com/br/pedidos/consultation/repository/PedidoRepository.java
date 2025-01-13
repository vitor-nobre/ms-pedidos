package com.br.pedidos.consultation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.pedidos.consultation.model.Pedido;
import com.br.pedidos.consultation.model.PedidoStatus;

 
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 
	
	@EntityGraph(attributePaths = {"produtos"})
	Page<Pedido> findByStatus(PedidoStatus status, Pageable pageable);
} 