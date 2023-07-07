/*
 * Copyright (C) 2023 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package primavera.function;

import javax.annotation.processing.Generated;


@Generated("SpecializedCodeGenerator")
public interface FloatTriFunction {

    /**
     * Composes a single value from three arguments.
     * 
     * @param param1 First parameter.
     * @param param2 Second parameter.
     * @param param3 Third parameter.
     * @return A calculated result.
     */
    float applyAsFloat(float param1, float param2, float param3);
}