package com.br.pedidos.consultation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.pedidos.consultation.model.Pedido;

 
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 
	Page<Pedido> findByStatus(String status, Pageable pageable);
} 