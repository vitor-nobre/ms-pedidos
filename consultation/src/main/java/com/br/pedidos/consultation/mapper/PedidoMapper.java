package com.br.pedidos.consultation.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.br.pedidos.consultation.dto.PedidoDTO;
import com.br.pedidos.consultation.model.Pedido;

@Component
public class PedidoMapper {
	private final ModelMapper modelMapper;

	@Autowired
	public PedidoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public PedidoDTO toDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoDTO.class);
	}
}
