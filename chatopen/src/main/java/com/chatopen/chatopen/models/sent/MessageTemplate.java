package com.chatopen.chatopen.models.sent;

import com.chatopen.chatopen.models.MessageType;
import com.chatopen.chatopen.models.Text;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MessageTemplate {
    private String messaging_product;
    private String from;
    private String to;
    private String id;
    private String timestamp;
    private Text text;
    private MessageType type;

}
