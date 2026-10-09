
/*
 * Copyright (C) 2025 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
import static bee.api.License.*;

public class Project extends bee.api.Project {
    {
        product("io.github.teletha", "primavera", ref("version.txt"));
        license(MIT);
        versionControlSystem("https://github.com/teletha/primavera");
        describe("""
                Specialized primitive collections.
                """);

        require("io.github.teletha", "sinobu");
        require("io.github.teletha", "antibug").atTest();
        require("io.github.teletha", "psychopath").atTest();
        require("com.google.guava", "guava").atTest();
    }
}