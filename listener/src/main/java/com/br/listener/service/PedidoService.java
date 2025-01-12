package com.br.listener.service;

import java.math.BigDecimal;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.br.listener.model.Pedido;
import com.br.listener.model.PedidoStatus;
import com.br.listener.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository orderRepository;
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public PedidoService(PedidoRepository orderRepository, StringRedisTemplate redisTemplate) {
        this.orderRepository = orderRepository;
        this.redisTemplate = redisTemplate;
    }

    public Pedido processarPedido(Pedido pedido) {

        Boolean isDuplicate = redisTemplate.opsForValue().setIfAbsent(
        	pedido.getNumeroPedido(),
            "EXISTE",
            Duration.ofHours(1)
        );

        if (!isDuplicate) {
            throw new IllegalArgumentException("Pedido duplicado: " + pedido.getNumeroPedido());
        }

        if (pedido.getProdutos() == null || pedido.getProdutos().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter ao menos um produto.");
        }

        pedido.setValorTotal(pedido.getProdutos().stream()
            .map(product -> product.getPreco())
            .reduce(BigDecimal.ZERO, BigDecimal::add));

        pedido.setStatus(PedidoStatus.PROCESSADO);

        return orderRepository.save(pedido);
    }
}
