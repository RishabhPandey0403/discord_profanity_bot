import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfanityBot {
    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create("ODk2MjA2MjYzMzkyODE3MTgz.YWDvHw.DQmkoU2oqI0BBXWpiFsVwV1s0_8");

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
                        return message.getChannel().flatMap(channel -> channel.createMessage("stop!"));
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                return Mono.empty();
            }).then();

            //gateway.on()
            //return todo
        });
    }
}
