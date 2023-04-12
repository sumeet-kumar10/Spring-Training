package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.model.AccountsDAO;
import com.model.AccountsDTO;
import com.model.InsufficientBalanceException;
import com.model.MyService;
import com.model.TransactionService;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class Project1Application {

	public static void main(String[] args) throws InsufficientBalanceException {
		ConfigurableApplicationContext context = SpringApplication.run(Project1Application.class, args);
		
		MyService myservice = context.getBean("myService",MyService.class);
		myservice.doServe(100,50000);
		
		MyService myservice1 = context.getBean("myService",MyService.class);
		myservice1.doServe(200,25000);
	
		TransactionService transaction = context.getBean("transactionService",TransactionService.class);
		transaction.moneyTransfer(100,200,1000);
		
	}

}
