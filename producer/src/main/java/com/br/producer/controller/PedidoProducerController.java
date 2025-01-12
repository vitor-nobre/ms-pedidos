package com.br.producer.controller;

import com.br.producer.model.Pedido;
import com.br.producer.service.PedidoProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoProducerController {

	private final PedidoProducerService producerService;

	public PedidoProducerController(PedidoProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping
	public ResponseEntity<String> criarPedido(@RequestBody Pedido pedido) {
		producerService.enviarPedido(pedido);
		return ResponseEntity.ok("Pedido enviado com sucesso para o RabbitMQ!");
	}
}