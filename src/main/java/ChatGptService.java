import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import io.github.cdimascio.dotenv.Dotenv;

public class ChatGptService {

    final private OpenAIClient client;
    final private String model = "gpt-4o-mini";
    final private int maxTokensPerMessage = 1000;
    final private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    ChatGptService() {

        client = OpenAIOkHttpClient.builder()
            .apiKey(dotenv.get("OPENAI_API_KEY"))
            .build();
    
    }

    public String sendPrompt(String message) throws Exception {

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .model(model) 
            .maxCompletionTokens(maxTokensPerMessage)
            .addUserMessage(message)
            .build();

        try {
            ChatCompletion chatCompletion = client.chat().completions().create(params);
            String reply = chatCompletion.choices().get(0).message().content().orElse("No response generated.");
            return reply;

        } catch (Exception e) {
            System.out.println("Execution failed:");
            System.out.println(e.getMessage());

            int errorCode = Integer.valueOf(e.getMessage().split(":")[0]);

            switch ( errorCode ) {

                case 400:
                    throw new Exception("400: Bad request");

                case 401:
                    throw new Exception("401: API key is missing, invalid, or incorrect!");

                case 429:
                    throw new Exception("429: GPT Rate limit exceeded!");

                default:
                    throw new Exception( errorCode + ": unspecified error, check ChatGPT API documentation");

            }

        }
    }
}
