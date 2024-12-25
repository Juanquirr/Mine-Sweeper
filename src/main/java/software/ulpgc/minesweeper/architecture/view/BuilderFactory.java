package software.ulpgc.minesweeper.architecture.view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BuilderFactory {
    private static final Map<Class<?>, Supplier<? extends Builder<?>>> builders = new HashMap<>();

    public static <T, B extends Builder<T>> void registerBuilder(Class<T> productClass, Supplier<B> builderSupplier) {
        builders.put(productClass, builderSupplier);
    }

    public static <T, B extends Builder<T>> B getBuilder(Class<T> productClass) {
        @SuppressWarnings("unchecked")
        Supplier<B> builderSupplier = (Supplier<B>) builders.get(productClass);

        if (builderSupplier == null) {
            throw new IllegalArgumentException("No builder registered for class: " + productClass.getName());
        }

        return builderSupplier.get();
    }
}
