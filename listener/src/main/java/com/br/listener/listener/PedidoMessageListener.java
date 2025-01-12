package com.br.listener.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.br.listener.config.RabbitMQConfig;
import com.br.listener.model.Pedido;
import com.br.listener.service.PedidoService;

@Component
public class PedidoMessageListener {

	private final PedidoService pedidoService;

	public PedidoMessageListener(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	/**
	 * Método que recebe mensagens da fila RabbitMQ e processa o pedido.
	 *
	 * @param pedido Mensagem do pedido recebido da fila.
	 */
	
	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void receberPedido(Pedido pedido) {
		try {
			// Processa o pedido
			System.out.println("pedido recebido");
			Pedido pedidoProcessado = pedidoService.processarPedido(pedido);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}