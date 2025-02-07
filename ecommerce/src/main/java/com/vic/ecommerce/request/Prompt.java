package com.vic.ecommerce.request;

import lombok.Data;

@Data
public class Prompt {
    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
