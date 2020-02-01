package io.github.rjhaytree.simpleclans;

import com.google.inject.Inject;
import io.github.rjhaytree.simpleclans.commands.CommandRegistrar;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "simpleclans",
        name = "SimpleClans",
        description = "A clan plugin developed for Sponge",
        authors = {
                "RyanJH"
        },
        version = "1.0"
)
public class SimpleClans {

    @Inject
    private Logger logger;

    private SimpleClans instance;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        instance = this;
        new CommandRegistrar(this).registerCommands();
        registerEvents();
    }

    private void registerEvents() {
        Sponge.getEventManager().registerListeners(this, new EventHandlers());
    }

    public SimpleClans getInstance() { return instance; }
}
