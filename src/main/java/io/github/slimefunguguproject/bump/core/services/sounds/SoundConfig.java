package io.github.slimefunguguproject.bump.core.services.sounds;

import java.util.Objects;

import javax.annotation.Nonnull;

import org.bukkit.Sound;

import com.google.common.base.Preconditions;

/**
 * A wrapper of sound configuration.
 *
 * @author ybw0014
 */
final class SoundConfig {

    @Nonnull Sound sound;
    float volume;
    float pitch;
    @Nonnull Sound sound() { return sound; }
    float volume() { return volume; }
    float pitch() { return pitch; }


    public SoundConfig(@Nonnull Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        Preconditions.checkArgument(sound != null, "Sound cannot be null");
    }

    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) return true;
        if (rhs == null || getClass() != rhs.getClass()) return false;
        SoundConfig sc = (SoundConfig)rhs;
        return this.sound == sc.sound
            && this.volume == sc.volume
            && this.pitch == sc.pitch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sound, volume, pitch);
    }

    @Override
    public String toString() {
        return "SoundConfig{"
            + "sound=" + sound + ", "
            + "volume=" + volume + ", "
            + "pitch=" + pitch +
        '}';
    }
}
