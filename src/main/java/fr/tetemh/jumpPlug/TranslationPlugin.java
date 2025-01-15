package fr.tetemh.jumpPlug;

import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

@Data
public class TranslationPlugin {
    private HashMap<String, FileConfiguration> translations;
    private JumpPlug plugin;
    
    private FileConfiguration currentLanguage;

    TranslationPlugin(JumpPlug plugin) {
        this.setPlugin(plugin);
        this.setTranslations(new HashMap<>());

        this.init();
    }

    private void init() {
        this.loadLanguageConfigFile();
        this.setCurrentLanguage(this.getTranslations().get(this.getBaseLanguageConfig()));
    }

    private String getBaseLanguageConfig() {
        return this.getPlugin().getConfig().getString("lang");
    }

    private void loadLanguageConfigFile() {
        File languageFolder = new File(this.getPlugin().getDataFolder(), "languages");
        if (!languageFolder.exists()) {
            languageFolder.mkdirs();
            this.getPlugin().saveResource("languages/en_US.yml", false);
            this.getPlugin().saveResource("languages/fr_FR.yml", false);
        }
        File[] languageFiles = languageFolder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (languageFiles == null || languageFiles.length == 0) {
            this.getPlugin().getLogger().warning("No language files found in " + languageFolder.getPath());
            return;
        }

        Arrays.stream(languageFiles).forEach(file -> {
            try {
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                String langKey = file.getName().replace(".yml", ""); // Clé correspondant au nom du fichier sans extension
                this.getTranslations().put(langKey, config);
                this.getPlugin().getLogger().info("Loaded language file: " + file.getName());
            } catch (Exception e) {
                this.getPlugin().getLogger().severe("Failed to load language file: " + file.getName());
                e.printStackTrace();
            }
        });
    }

    public String getTranslation(String key) {
        return this.getCurrentLanguage().getString(key);
    }
}
