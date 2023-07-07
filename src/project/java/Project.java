
/*
 * Copyright (C) 2023 The PRIMAVERA Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
import static bee.api.License.MIT;

import javax.lang.model.SourceVersion;

public class Project extends bee.api.Project {
    {
        product("com.github.teletha", "primavera", ref("version.txt"));
        license(MIT);
        versionControlSystem("https://github.com/teletha/primavera");
        require(SourceVersion.RELEASE_19, SourceVersion.RELEASE_17);
        describe("""
                Specialized primitive collections.
                """);

        require("com.github.teletha", "sinobu");
        require("com.github.teletha", "antibug").atTest();
        require("com.github.teletha", "psychopath").atTest();
        require("com.google.guava", "guava").atTest();
    }
}