package com.chatopen.chatopen.service;
import com.chatopen.chatopen.models.Text;
import com.chatopen.chatopen.models.WhatsappCallbackResponse;
import com.chatopen.chatopen.models.sent.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public class CallbackService {

    @Value("${openai.url}")
    private String callingUrl;
    @Value("${whatsapp.token}")
    private String token;

    @Value("${whatsapp.callbackurl}")
    private String callbackUrl;

    @Async
    public String callToAi(WhatsappCallbackResponse callbackResponse) throws IOException, InterruptedException {

        String msg =  callbackResponse.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(callingUrl)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        int statusCode = response.code();
        Headers headers = response.headers();
        ResponseBody body = response.body();
           log.info(body.string());
            RestTemplate restTemplate = new RestTemplate();
            MessageTemplate wMessage = new MessageTemplate();
            wMessage.setMessaging_product("whatsapp");
            wMessage.setText(Text.builder().body(body.string()).build());
            log.info("hi 3");
            // Create the request body as an instance of a class
            // Set the headers
            HttpHeaders headersWp = new HttpHeaders();
            headersWp.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            headersWp.setBearerAuth(token);

            // Create the request
            HttpEntity<MessageTemplate> requestWp = new HttpEntity<>(wMessage, headersWp);
            // Send the request and parse the response
            String responseWp = restTemplate.postForObject(callbackUrl, request, String.class);
        return msg;
    }
}
