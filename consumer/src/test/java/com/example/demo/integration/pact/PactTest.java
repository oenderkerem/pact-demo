package com.example.demo.integration.pact;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(PactConsumerTestExt.class)
public class PactTest{


   private final String pactProviderName="asdf";

   private final String pactConsumerName="asdf";


    @Pact(provider=pactProviderName, consumer=pactConsumerName)
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        return builder
                .uponReceiving("Get Request for notes")
                .path("/notes")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(
                        getJsonArrayOfNotes(2).toString())
                .toPact();
    }

    @ExtendWith(PactConsumerTestExt.class)
    @PactTestFor(providerName = pactProviderName)
    public class ConsumerPactTest{

    }

    @Test
    void test(MockServer mockServer) throws IOException {
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/articles.json").execute().returnResponse();
        assertEquals(200,httpResponse.getStatusLine().getStatusCode());
    }

    private JSONArray getJsonArrayOfNotes(int size){
        var responseJsonObject = new JSONArray();
        for(int i=0;i<size;i++){
            var note = new JSONObject();
            try{
                note.put("title",String.format("Title %s",i+1));
                note.put("content",String.format("Some Note Content of Note %s", i+1 ));
            }catch (Exception exception){

            }
            responseJsonObject.put(note);
        }
        return responseJsonObject;
    }

}
