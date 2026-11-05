package com.qeiwt.pets;

import com.qeiwt.pets.manager.PetManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class QeiwtPets extends JavaPlugin implements CommandExecutor {

    private PetManager petManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.petManager = new PetManager(this);
        
        getCommand("qeiwtpets").setExecutor(this);
        
        getLogger().info("========================================");
        getLogger().info("  QeiwtPets បានបើកដំណើរការដោយជោគជ័យ! ");
        getLogger().info("========================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("QeiwtPets ត្រូវបានបិទដំណើរការ។");
    }

    public PetManager getPetManager() {
        return petManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("คำสั่งนี้ใช้ได้เฉพาะผู้เล่นเท่านั้น។");
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[QeiwtPets] &eកំពុងបើក Menu សត្វចិញ្ចឹមរបស់អ្នក..."));
        
        if (args.length > 0 && args[0].equalsIgnoreCase("spawn")) {
            String sampleTexture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83ZTM5ZDNmOWVmMWQ5ZTk0OWM0Y2Y0Zjg3YjgzZTU0ODgzZjY2OWUxZDk2MGQ1ODc3NTg3ZGM3YTdlYjdiYyJ9fX0=";
            petManager.spawnPet(player, sampleTexture, "&6&lGolden Dragon Pet");
            player.sendMessage(ChatColor.GREEN + "បានកោះហៅសត្វចិញ្ចឹមជោគជ័យ!");
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("remove")) {
            petManager.removePet(player);
            player.sendMessage(ChatColor.RED + "បានលុបសត្វចិញ្ចឹមចេញហើយ!");
            return true;
        }

        return true;
    }
}
