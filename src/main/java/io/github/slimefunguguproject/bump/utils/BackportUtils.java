package io.github.slimefunguguproject.bump.utils;

import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

import com.google.common.base.Preconditions;

/**
 * Backported function from newer versions.
 * @author TsXor
 */
public class BackportUtils {
    /**
     * Backported {@code Namespacedkey.fromString}
     * @param string
     * @param defaultNamespace
     * @return constructed NamespacedKey
     */
    @Nullable
    public static NamespacedKey nkFromString(@Nonnull String string, @Nullable Plugin defaultNamespace) {
        // copied from newer bukkit API
        final Pattern VALID_KEY = Pattern.compile("[a-z0-9/._-]+");
        Preconditions.checkArgument(string != null && !string.isEmpty(), "Input string must not be empty or null");
        String[] components = string.split(":", 3);
        if (components.length > 2) { return null; }
        String key = (components.length == 2) ? components[1] : "";
        if (components.length == 1) {
            String value = components[0];
            if (value.isEmpty() || !VALID_KEY.matcher(value).matches()) { return null; }
            return (defaultNamespace != null) ? new NamespacedKey(defaultNamespace, value) : NamespacedKey.minecraft(value);
        } else if (components.length == 2 && !VALID_KEY.matcher(key).matches()) { return null; }
        String namespace = components[0];
        if (namespace.isEmpty()) {
            return (defaultNamespace != null) ? new NamespacedKey(defaultNamespace, key) : NamespacedKey.minecraft(key);
        }
        if (!VALID_KEY.matcher(namespace).matches()) { return null; }
        return new NamespacedKey(namespace, key);
    }

    /**
     * Backported {@code PlayerInventory.setItems} overload
     * @param self the PlayerInventory object
     * @param slot
     * @param item
     */
    public static void setEquipmentSlotItems(PlayerInventory self, EquipmentSlot slot, @Nullable ItemStack item) {
        /****/ if (slot == EquipmentSlot.HEAD){
            self.setHelmet(item);
        } else if (slot == EquipmentSlot.CHEST) {
            self.setChestplate(item);
        } else if (slot == EquipmentSlot.LEGS) {
            self.setLeggings(item);
        } else if (slot == EquipmentSlot.FEET) {
            self.setBoots(item);
        } else if (slot == EquipmentSlot.HAND) {
            self.setItemInMainHand(item);
        } else if (slot == EquipmentSlot.OFF_HAND) {
            self.setItemInOffHand(item);
        }
    }
}
