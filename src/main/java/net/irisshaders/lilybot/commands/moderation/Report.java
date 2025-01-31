package net.irisshaders.lilybot.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@SuppressWarnings("ConstantConditions")
public class Report extends Command implements EventListener {

    public Report() {
        this.name = "report";
        this.help = "Triggers the bot to send the report embed and button.";
        this.hidden = true;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        MessageChannel channel = event.getChannel();
        MessageEmbed reportEmbed = new EmbedBuilder()
                .setTitle("Report a problem to a mod.")
                .setDescription(
                        "This should only be used when a case of severe-rule breaking has occurred and " +
                        "there are no mods available to take control of the situation."
                ).setColor(Color.RED)
                .build();
        channel.sendMessageEmbeds(reportEmbed).setActionRow(
                Button.of(ButtonStyle.PRIMARY, "report:report", "Report", Emoji.fromUnicode("\u26A0"))
        ).queue();

    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {

        if (genericEvent instanceof ButtonClickEvent event) {

            String id = event.getButton().getId().split(":")[1];

            if (id.equals("report")) {
                event.reply("We are currently waiting for threads to be implemented in JDA." +
                        " After that, we will finish this command.").mentionRepliedUser(false).setEphemeral(true).queue();
                // TODO THREADS
            }

        }

    }

}
