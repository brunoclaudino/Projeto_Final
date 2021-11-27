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
	public AmazonSNSClient SnsClient() {	
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIA6BZRT7L44C43DFGE",
						"Py3MdJXul7AhJsxMrNEYhIL72EnaK/YUNPLjyoZH")))
				.build();
	}
}
