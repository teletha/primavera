/*
 * Copyright (C) 2021 cointoss Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package primavera.function;

import javax.annotation.processing.Generated;

import primavera.SpecializedCodeGenerator.Primitive;

@Generated("SpecializedCodeGenerator")
public interface WrapperTetraFunction {

    /**
     * Composes a single value from four arguments.
     * 
     * @param param1 First parameter.
     * @param param2 Second parameter.
     * @param param3 Third parameter.
     * @param param4 Fourth parameter.
     * @return A calculated result.
     */
    Primitive applyAsWrapper(Primitive param1, Primitive param2, Primitive param3, Primitive param4);
}