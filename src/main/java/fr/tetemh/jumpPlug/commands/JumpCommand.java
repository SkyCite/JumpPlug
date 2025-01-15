package fr.tetemh.jumpPlug.commands;

import fr.tetemh.fastInv.FastInv;
import fr.tetemh.jumpPlug.JumpPlug;
import fr.tetemh.jumpPlug.guis.JumpSelectionGui;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Data
public class JumpCommand implements CommandExecutor {

    private final JumpPlug plugin;

    public JumpCommand(JumpPlug plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, @NotNull String @NotNull [] args) {
        if(sender instanceof Player player) {
            /*
            OPEN GUI
            */

            FastInv selectJumpGui = new JumpSelectionGui(this.getPlugin(), this.getPlugin().getTranslationPlugin().getTranslation("gui-name-select-jump"));
            selectJumpGui.open(player);

            /*
            SELECT JUMP
            RENDRE LE PERSO INVISIBLE POUR LES AUTRES
            TP JUMP
            PERMET DE LE START AU PASSAGE SUR UN BLOCK
            PERMET DE FINIR LE JUMP
            PEUT RESET LE JUMP
                RE TP AU START DU JUMP
             */


        }
        return false;
    }
}
