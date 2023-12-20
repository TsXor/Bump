package io.github.slimefunguguproject.bump.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import lombok.experimental.UtilityClass;

/**
 * Utility methods for json parsing.
 *
 * @author ybw0014
 */
@UtilityClass
public final class JsonUtils {
    /**
     * This method parses JSON from the {@link JsonReader}.
     *
     * @param reader The {@link JsonReader} to be read JSON from.
     *
     * @return The root {@link JsonElement}
     */
    public static JsonElement parseReader(@Nonnull JsonReader reader) {
        return new JsonParser().parse(reader);
    }
}
