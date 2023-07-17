package ifarded.lol.ifu;

import java.io.File;
import java.util.logging.Level;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ifarded.lol.ifu.cmds.Civilization;
import ifarded.lol.ifu.cmds.IFUCmd;
import ifarded.lol.ifu.listeners.CommandListener;
import ifarded.lol.ifu.listeners.CommandSuggestionListener;
import ifarded.lol.ifu.listeners.JoinListener;
import ifarded.lol.ifu.listeners.OpListener;
import ifarded.lol.ifu.listeners.ServerPingListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class IFUtilities extends JavaPlugin {
    public final String CURRENT_VERSION = this.getDescription().getVersion();
    public final String CONFIG_VERSION = this.getConfig().getString("version");
    public static final String PREFIX = ChatColor.translateAlternateColorCodes('&', "&b[&aiFUtilities&b] &r");
    private Map<UUID, ArmorStand> seats = new HashMap();
    private Permission sitPermission;
    private String sitDownMessage;
    private String sitUpMessage;
    private String sitFailMessage;
    // Component.text("[")
    // .color(IFColors.AQUA)
    // .append(
    // Component.text("iFUtilities")
    // .color(IFColors.GREEN)
    // )
    // .append(
    // Component.text("] ")
    // .color(IFColors.AQUA)
    // )
    private static IFUtilities plugin;

    public void onEnable() {
        if (!CURRENT_VERSION.equals(CONFIG_VERSION)) {
            this.getLogger().severe("IFU is out of sync with it's config file.");
            this.getLogger().severe("IFU v" + CURRENT_VERSION + ", config v" + CONFIG_VERSION);
            this.getLogger().severe("Beware. Here be dragons, the plugin may not even work.");
        }
        plugin = this;
        createConfig();
        reloadConfig();
        checkGroups();
        initListeners();
        getCommand("ifu").setExecutor(new IFUCmd());
        getCommand("ifu").setTabCompleter(new IFUCmd());

        getCommand("civilization").setExecutor(new Civilization());
        getCommand("civilization").setExecutor(new Civilization());
    }

    public void onDisable() {
        Object[] var4;
        int var3 = (var4 = this.seats.keySet().toArray()).length;

        for (int var2 = 0; var2 < var3; ++var2) {
            Object uuid = var4[var2];
            IFPlayer player = new IFPlayer(Bukkit.getPlayer((UUID) uuid));
            player.setSitting(false);
        }
    }

    private void initListeners() {
        PluginManager pw = Bukkit.getPluginManager();
        pw.registerEvents(new JoinListener(), this);
        pw.registerEvents(new CommandSuggestionListener(), this);
        pw.registerEvents(new CommandListener(), this);
        pw.registerEvents(new OpListener(), this);
        pw.registerEvents(new ServerPingListener(), plugin);
    }

    public void createConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists())
            saveDefaultConfig();
    }

    public static String getColoredConfigString(String path) {
        String message = plugin.getConfig().getString(path);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getGroup(Player p) {
        if (p.hasPermission("*"))
            return null;
        int priority = 0;
        String groupName = "";
        for (String group : getPlugin().getConfig().getConfigurationSection("groups").getKeys(false)) {
            if (p.hasPermission("ifu.group." + group)) {
                int currentPriority = getPlugin().getConfig().getInt("groups." + group + ".priority");
                if (currentPriority > priority) {
                    priority = currentPriority;
                    groupName = group;
                }
            }
        }
        if (!groupName.isEmpty())
            return groupName;
        return "default";
    }

    public static void checkGroups() {
        ConfigurationSection groupsSection = getPlugin().getConfig().getConfigurationSection("groups");
        if (!groupsSection.isSet("default"))
            getPlugin().getLogger().severe("default group is missing");
        for (String group : groupsSection.getKeys(false)) {
            if (!groupsSection.isSet(group + ".group-mode"))
                getPlugin().getLogger().severe("The option group-mode is missing for the group " + group);
            if (!groupsSection.isSet(group + ".priority"))
                getPlugin().getLogger().severe("The option priority is missing for the group " + group);
            if (!groupsSection.isSet(group + ".included-groups"))
                getPlugin().getLogger().severe("The option included-groups is missing for the group " + group);
        }
    }

    public static IFUtilities getPlugin() {
        return plugin;
    }

    public Map<UUID, ArmorStand> getSeats() {
        return this.seats;
    }

    public String getSitFailMessage() {
        return this.sitFailMessage;
    }

    public String getSitDownMessage() {
        return this.sitDownMessage;
    }

    public String getSitUpMessage() {
        return this.sitUpMessage;
    }

    public Permission getSitPermission() {
        return this.sitPermission;
    }

}
