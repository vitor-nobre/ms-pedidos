package com.br.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.br.producer.config.RabbitMQConfig;
import com.br.producer.model.Pedido;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoProducerService {

	private final RabbitTemplate rabbitTemplate;

	public void enviarPedido(Pedido pedido) {
		System.out.println("Producer pedido");
		rabbitTemplate.convertAndSend(
				RabbitMQConfig.EXCHANGE_NAME, 
				RabbitMQConfig.ROUTING_KEY, 
				pedido);
	}
}