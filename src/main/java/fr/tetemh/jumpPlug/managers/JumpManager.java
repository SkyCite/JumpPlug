package fr.tetemh.jumpPlug.managers;

import fr.tetemh.jumpPlug.JumpPlug;
import fr.tetemh.jumpPlug.objects.Jump;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JumpManager {
    private Map<String, Jump> jumps;
    private final JumpPlug plugin;

    public JumpManager(JumpPlug plugin) {
        this.plugin = plugin;
        this.setJumps(new HashMap<String, Jump>());
    }
}
