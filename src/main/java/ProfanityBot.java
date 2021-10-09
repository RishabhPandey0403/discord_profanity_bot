import commands.Token;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfanityBot {
    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create(Token.token).build().login().block()

        Mono<Void> stopWarning = client.withGateway((GatewayDiscordClient gateway) -> {

            gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                try {
                    File textFile = new File("C:\\Users\\email\\Desktop\\badwords.txt");
                    Scanner myReader = new Scanner(textFile);

                    ArrayList<String> list = new ArrayList<>();
                    while (myReader.hasNextLine())
                        list.add(myReader.nextLine());

                    if (list.contains(message.getContent().toLowerCase())) {
                        return message.getChannel().
                                flatMap(channel -> channel.createMessage("stop!"));
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                return Mono.empty();
            })
        };

        Color rgbColor = Color.of(255, 0, 127);

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(rgbColor)
                .title("Language Warning!")
                .description("This is a warning to avoid using profane language")
                .image("https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.clipartbest.com%2Fcliparts%2FKcj%2Fo9R%2FKcjo9RjBi.png&f=1&nofb=1")
                .timestamp(Instant.now())
                .author("Server owner", null, null)
                .build();
        //gateway.on()
        //return todo
        );
    }
}
