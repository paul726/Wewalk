pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }

        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven { setUrl("https://maven.aliyun.com/repository/google/") }
//        maven { setUrl("https://maven.aliyun.com/repository/jcenter/") }
//        maven { setUrl("https://maven.aliyun.com/repository/central/") }
//        maven { setUrl("https://maven.aliyun.com/nexus/content/groups/public/") }
//        maven { setUrl("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Wewalk"
include(":app")
 