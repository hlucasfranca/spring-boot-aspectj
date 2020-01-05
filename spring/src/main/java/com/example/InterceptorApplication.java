package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InterceptorApplication {

	@Resource
	private InterceptorApplication app;

	@RequestMapping("/")
	public String home() {
		return "Hello World";
	}

	@GetMapping("/hi")
	public void hi() {
		app.teste("Fulano de ", "Tal");
	}

	public void teste(String nome1, String nome2){
		System.out.println(nome1 + nome2);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(InterceptorApplication.class).run(args);
	}
}
