package com.br.pedidos.consultation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PedidosConsultationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosConsultationApplication.class, args);
	}

}
