package com.vic.ecommerce.ai.services;


import com.vic.ecommerce.exception.ProductException;
import com.vic.ecommerce.response.ApiResponse;
import org.json.JSONException;

public interface AiChatBotService {

    ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException, JSONException;
}
