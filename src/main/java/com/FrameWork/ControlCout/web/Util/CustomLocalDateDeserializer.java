/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.web.Util;

/**
 *
 * @author Administrator
 */
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        if (node.isNull()) {
            return null; // Handle null values appropriately
        }
        long timestamp = node.asLong(); // Assuming the JSON sends a timestamp in milliseconds
        Date date = new Date(timestamp); // Convert the timestamp to a java.util.Date object
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Convert to LocalDate
        
       //Alternative if the JSON sends a Date string:
       //String dateString = node.asText();
       //return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
}