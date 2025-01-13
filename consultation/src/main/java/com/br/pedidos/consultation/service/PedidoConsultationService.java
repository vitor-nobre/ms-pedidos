package com.br.pedidos.consultation.service;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.pedidos.consultation.dto.PedidoDTO;
import com.br.pedidos.consultation.model.Pedido;
import com.br.pedidos.consultation.model.PedidoStatus;
import com.br.pedidos.consultation.repository.PedidoRepository;

@Service
public class PedidoConsultationService {

	private final PedidoRepository pedidoRepository;
	private final ModelMapper modelMapper;

	public PedidoConsultationService(PedidoRepository pedidoRepository, ModelMapper modelMapper) {
		this.pedidoRepository = pedidoRepository;
		this.modelMapper = modelMapper;
	}

	public Page<PedidoDTO> findAll(Pageable pageable) {
		Page<Pedido> pedidos = pedidoRepository.findAll(pageable);
		return pedidos.map(pedido -> modelMapper.map(pedido, PedidoDTO.class));
	}

	@Cacheable(value = "pedidosStatus", key = "#status + #pageable")
	public Page<PedidoDTO> findByStatus(String status, Pageable pageable) {

		PedidoStatus statusEnum;
		try {
			statusEnum = PedidoStatus.valueOf(status.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Status inválido: " + status);
		}

		Page<Pedido> pedidos = pedidoRepository.findByStatus(statusEnum, pageable);
		return pedidos.map(pedido -> modelMapper.map(pedido, PedidoDTO.class));
	}

}
