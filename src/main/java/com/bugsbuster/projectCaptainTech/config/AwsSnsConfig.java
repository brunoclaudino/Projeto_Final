package com.bugsbuster.projectCaptainTech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSnsConfig {
	@Primary
	@Bean
	public AmazonSNSClient SnsClient() {	                                  // Configura o client do SNS da AWS             
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().           // Retornar um Amazon SNS Client Builder
				withRegion(Regions.US_EAST_2)                                 // Define a região do serviço (usamos Ohio leste US)
				.withCredentials(new AWSStaticCredentialsProvider(            // Define as credenciais
						new BasicAWSCredentials("AKIA6BZRT7L43R62EAHD",       // Passa o Id da chave de acesso e a chave
						"cMqBx+8WDolTEmt7CBYND/SeY7+YYQ923gAPvDNC")))
				.build();
	}
}
