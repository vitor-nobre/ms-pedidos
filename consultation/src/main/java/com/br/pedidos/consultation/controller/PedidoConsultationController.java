package com.br.pedidos.consultation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.pedidos.consultation.dto.PedidoDTO;
import com.br.pedidos.consultation.service.PedidoConsultationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoConsultationController {

	private final PedidoConsultationService pedidoService;

	@GetMapping("/status")
	public ResponseEntity<Page<PedidoDTO>> getPedidosByStatus(@RequestParam String status, Pageable pageable) {

		Page<PedidoDTO> pedidos = pedidoService.findByStatus(status, pageable);

		return ResponseEntity.ok(pedidos);
	}

	@GetMapping
	public ResponseEntity<Page<PedidoDTO>> getAllPedidos(Pageable pageable) {

		Page<PedidoDTO> pedidos = pedidoService.findAll(pageable);

		return ResponseEntity.ok(pedidos);
	}
}
