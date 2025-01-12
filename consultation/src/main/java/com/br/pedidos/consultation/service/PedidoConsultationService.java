package com.br.pedidos.consultation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.pedidos.consultation.model.Pedido;
import com.br.pedidos.consultation.repository.PedidoRepository;

@Service
public class PedidoConsultationService {

	private final PedidoRepository pedidoRepository;

	public PedidoConsultationService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public Page<Pedido> findAll(Pageable pageable) {
		return pedidoRepository.findAll(pageable);
	}

	public Page<Pedido> findByStatus(String status, Pageable pageable) {
		return pedidoRepository.findByStatus(status, pageable);
	}
}
