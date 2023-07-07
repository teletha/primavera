/*
 * Copyright (C) 2023 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package primavera;

import java.util.List;
import java.util.Objects;

import primavera.array.WrapperList;
import primavera.function.ToWrapperTriFunction;
import primavera.function.WrapperPentaFunction;
import primavera.function.WrapperTetraFunction;
import primavera.function.WrapperTriFunction;
import primavera.map.ConcurrentNavigableWrapperMap;
import primavera.map.ConcurrentWrapperMap;
import primavera.map.NavigableWrapperMap;
import primavera.map.SkipListWrapperMap;
import primavera.map.WrapperMap;
import primavera.ring.WrapperRingBuffer;
import primavera.set.NavigableWrapperSet;
import primavera.set.SortedWrapperSet;
import primavera.set.WrapperSet;
import psychopath.File;
import psychopath.Locator;

public class SpecializedCodeGenerator {

    /**
     * Generates the specialized code for each type from the specified source code.
     * 
     * @param sourceCode
     */
    public static void write(Class sourceCode) {
        write(sourceCode, Type.values());
    }

    /**
     * Generates the specialized code for each type from the specified source code.
     * 
     * @param sourceCode
     * @param types
     */
    public static void write(Class sourceCode, Type... types) {
        write(sourceCode, false, types);
    }

    /**
     * Generates the specialized code for each type from the specified source code.
     * 
     * @param sourceCode
     */
    public static void writeAsPackagePrivate(Class sourceCode) {
        write(sourceCode, true, Type.values());
    }

    /**
     * Generates the specialized code for each type from the specified source code.
     * 
     * @param sourceCode
     * @param types
     */
    public static void writeAsPackagePrivate(Class sourceCode, Type... types) {
        write(sourceCode, true, types);
    }

    /**
     * Generates the specialized code for each type from the specified source code.
     * 
     * @param sourceCode
     * @param types
     */
    private static void write(Class sourceCode, boolean packagePrivate, Type... types) {
        Objects.requireNonNull(sourceCode);

        // find source code
        File sourceFile = Locator.directory("src/test/java").file(sourceCode.getName().replace('.', '/') + ".java");

        for (Type type : types) {
            File generateFile = Locator.directory("src/main/auto").file(type.replace(sourceCode.getName().replace('.', '/') + ".java"));
            List<String> lines = sourceFile.lines().map(line -> type.replace(line)).skip(line -> line.equals("SKIPLINE")).toList();
            if (packagePrivate) makePackagePrivate(lines);
            generateFile.text(lines);
            System.out.println("Generate " + generateFile);
        }
    }

    /**
     * Make the modifier of top-level type package-private.
     * 
     * @param lines
     */
    private static void makePackagePrivate(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("public ")) {
                lines.set(i, line.replace("public ", ""));
                return;
            }
        }
    }

    /**
     * 
     */
    public enum Type {

        Object(false, "E", "", "E", "null"),

        Int(true, "int", "Int", "Integer", "0"),

        Long(true, "long", "Long", "Long", "0L"),

        Float(true, "float", "Float", "Float", "0f"),

        Double(true, "double", "Double", "Double", "0d");

        private final boolean numeric;

        private final String primitiveName;

        private final String wrapperName;

        private final String wrapperType;

        private final String initialValue;

        /**
         * @param specializedType
         */
        private Type(boolean numeric, String primitiveName, String wrapperName, String wrapperType, String initialValue) {
            this.numeric = numeric;
            this.primitiveName = primitiveName;
            this.wrapperName = wrapperName;
            this.wrapperType = wrapperType;
            this.initialValue = initialValue;
        }

        String replace(String text) {
            if (text.startsWith("import " + SpecializedCodeGenerator.class.getCanonicalName())) {
                if (text.startsWith("import " + WrapperFunction.class.getCanonicalName())) {
                    return "import java.util.function." + wrapperName + "Function;";
                } else if (text.startsWith("import " + WrapperConsumer.class.getCanonicalName())) {
                    return "import java.util.function." + wrapperName + "Consumer;";
                } else if (text.startsWith("import " + WrapperBinaryOperator.class.getCanonicalName())) {
                    return "import java.util.function." + wrapperName + "BinaryOperator;";
                } else {
                    return "SKIPLINE";
                }
            }

            String primitiveFunction = WrapperFunction.class.getSimpleName();

            // initial value
            text = text.replaceAll("Wrapper\\.initital\\(\\)", initialValue);

            // new int[size] or (E[]) Array.newInstance(Object.class, size)
            text = text.replaceAll("Wrapper\\.newArray\\((.+)\\)", //
                    numeric ? "new " + primitiveName + "[$1]" : "(E[]) java.lang.reflect.Array.newInstance(Object.class, $1)");

            // comparing, increment and decrement
            text = text.replaceAll("Primitive\\.compare\\((.+), (.+)\\)", wrapperType + ".compare($1, $2)");
            text = text.replaceAll("Primitive\\.increment\\((.+), (.+)\\)", "$1 += $2");
            text = text.replaceAll("Primitive\\.decrement\\((.+), (.+)\\)", "$1 -= $2");

            // Primitive and Wrapper
            text = text.replace(primitiveFunction, wrapperName + "Function");
            text = text.replace("Primitive", primitiveName);
            text = text.replaceAll("(\\w*)Wrapper\\d?(\\w*)<Wrapper\\d?>", //
                    "$1" + wrapperName + "$2" + (numeric ? "" : "<" + wrapperType + ">"));
            if (!numeric) text = text.replaceAll("AsWrapper\\d?", "");
            text = text.replaceAll("(\\W)Wrapper\\d?(\\W)", "$1" + wrapperType + "$2");
            text = text.replaceAll("Wrapper\\d?", wrapperName);
            text = text.replaceAll("new List\\(\\)", "new ArrayList()");

            return text;
        }
    }

    /**
     * Generate code.
     */
    public static void main(String[] args) {
        // Array
        SpecializedCodeGenerator.write(WrapperRingBuffer.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(WrapperList.class, Type.Int, Type.Long, Type.Float, Type.Double);

        // Set
        SpecializedCodeGenerator.write(WrapperSet.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(SortedWrapperSet.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(SortedWrapperSet.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(NavigableWrapperSet.class, Type.Int, Type.Long, Type.Double);

        // Map
        SpecializedCodeGenerator.write(WrapperMap.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(NavigableWrapperMap.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(ConcurrentWrapperMap.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(ConcurrentNavigableWrapperMap.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.writeAsPackagePrivate(SkipListWrapperMap.class, Type.Int, Type.Long, Type.Double);

        // Function
        SpecializedCodeGenerator.write(WrapperPentaFunction.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(WrapperTetraFunction.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(WrapperTriFunction.class, Type.Int, Type.Long, Type.Double);
        SpecializedCodeGenerator.write(ToWrapperTriFunction.class, Type.Int, Type.Long, Type.Double);
    }

    /**
     * Replaceable type for wrapper types.
     */
    public static interface Wrapper {

        /** The minimum value of primitive type. */
        Primitive MIN_VALUE = null;

        /** The maximum value of primitive type. */
        Primitive MAX_VALUE = null;

        static int compare(Primitive one, Primitive other) {
            throw new Error("Dummy code");
        }

        /**
         * Create inital value.
         * 
         * @return
         */
        public static <AnyType> AnyType initital() {
            throw new Error("Dummy code");
        }

        /**
         * Create array.
         * 
         * @param size
         * @return
         */
        public static <AnyType> AnyType[] newArray(int size) {
            throw new Error("Dummy code");
        }
    }

    /**
     * Replaceable type for primitive function types.
     */
    public static interface WrapperFunction<V> {
        V apply(Primitive value);
    }

    /**
     * Replaceable type for primitive function types.
     */
    public static interface WrapperConsumer<V> {
        void accept(Primitive value);
    }

    /**
     * Replaceable type for primitive function types.
     */
    public static interface WrapperBinaryOperator<V> {
        Primitive applyAsWrapper(Primitive left, Primitive right);
    }

    /**
     * Replaceable type for primitive types.
     */
    public static interface Primitive extends Wrapper {

        /**
         * This code will be replaced by increment code of primitive type (i.e. base += 3).
         * 
         * @param base A base value.
         * @param increment A increment size.
         * @return Increment code.
         */
        public static Primitive increment(Primitive base, Primitive increment) {
            throw new Error("Dummy code");
        }

        /**
         * This code will be replaced by decrement code of primitive type (i.e. base -= 3).
         * 
         * @param base A base value.
         * @param decrement A decrement size.
         * @return Decrement code.
         */
        public static Primitive decrement(Primitive base, Primitive decrement) {
            throw new Error("Dummy code");
        }

        /**
         * This code will be replaced by comparing code of primitive type (i.e. Long.compare(a, b)).
         * 
         * @param a A target value.
         * @param b A other value.
         * @return Comparing code.
         */
        public static int compare(Primitive a, Primitive b) {
            throw new Error("Dummy code");
        }
    }
}