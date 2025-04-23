package ai.chatbot.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlamaAIService {
    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Autowired
    private ChatModel chatModel;

    public String generateResult(String prompt) {
        OllamaOptions ollamaOptions = new OllamaOptions();
        ollamaOptions.setModel("llama3");
        ChatResponse response = chatModel.call(new Prompt(prompt, ollamaOptions));
        if (response!=null){
            return response.getResult().getOutput().getText();
        } else {
            return "No response from the model.";
        }
    }
}
