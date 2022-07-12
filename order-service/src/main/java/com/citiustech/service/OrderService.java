package com.citiustech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.common.Payment;
import com.citiustech.common.TransactionRequest;
import com.citiustech.common.TransactionResponse;
import com.citiustech.entity.Order;
import com.citiustech.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Value("${microservice.payment-service.endpoints.doPayment.uri}")
	private String ENDPOINT_URL;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);
		
		String response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed": "there is a failue in payment api, order added to cart";
		
		orderRepository.save(order);
		
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
	}

}
