package fr.tetemh.jumpPlug;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import fr.tetemh.fastInv.FastInvManager;
import fr.tetemh.jumpPlug.commands.JumpCommand;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class JumpPlug extends JavaPlugin {

    @Getter @Setter
    private HologramManager holoManager;
    @Getter @Setter
    private JumpPlug plugin;
    @Getter @Setter
    private TranslationPlugin translationPlugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.setPlugin(this);
        this.setHoloManager(FancyHologramsPlugin.get().getHologramManager());
        FastInvManager.register(this);

        this.setTranslationPlugin(new TranslationPlugin(this));

        this.getCommand("jump").setExecutor(new JumpCommand());

        this.getTranslationPlugin().getTranslation("start-message");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
