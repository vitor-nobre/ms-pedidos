package com.br.listener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.listener.model.Pedido;
 
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 

} 