package com.br.pedidos.consultation.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.pedidos.consultation.dto.PedidoDTO;
import com.br.pedidos.consultation.model.Pedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Configuração de mapeamento customizado para Pedido -> PedidoDTO
		TypeMap<Pedido, PedidoDTO> typeMap = modelMapper.createTypeMap(Pedido.class, PedidoDTO.class);
		typeMap.addMappings(mapper -> mapper.map(Pedido::getProdutos, PedidoDTO::setProdutos));

		return modelMapper;
	}
}
