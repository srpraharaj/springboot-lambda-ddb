package com.ranjan.aws.function;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ranjan.aws.models.PersonRequest;
import com.ranjan.aws.models.PersonResponse;

public class PersonRequestHandler implements RequestHandler<PersonRequest, PersonResponse> {

	private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "Person";

	@Override
	public PersonResponse handleRequest(PersonRequest input, Context context) {

		context.getLogger().log("Input: " + input.toString());

		this.initDynamoDbClient(context);

		persistData(input);
		PersonResponse personResponse = new PersonResponse();
		personResponse.setMessage("Saved Successfully!!!");
		return personResponse;

	}

	private void initDynamoDbClient(Context context) {

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		this.dynamoDb = new DynamoDB(client);
	}

	private PutItemOutcome persistData(PersonRequest personRequest) {

		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
				.putItem(new PutItemSpec().withItem(new Item().withInt("id", personRequest.getId())
						.withString("firstName", personRequest.getFirstName())
						.withString("lastName", personRequest.getLastName())));
	}

}
