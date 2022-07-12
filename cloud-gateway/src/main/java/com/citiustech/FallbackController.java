package com.citiustech;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/orderFallBack")
	public Mono<String> orderServiceFallBack() {
		return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
	}

	@RequestMapping("/paymentFallback")
	public Mono<String> paymentServiceFallBack() {
		return Mono.just("Payment Service is taking too long to respond or is down. Please try again later");
	}
	
	@RequestMapping("/productCatalogFallback")
	public Mono<String> productCatalogFallback() {
		return Mono.just("Product catalog service is down, please try again later");
	}
	
	@RequestMapping("/inventoryFallback")
	public Mono<String> inventoryFallback() {
		return Mono.just("Inventory catalog service is down, please try again later");
	}
	
}
