package top.jonhao.cm.publish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.create
import top.jonhao.cm.publish.constant.PublishConstants
import top.jonhao.cm.publish.extension.findGlobalProperty
import top.jonhao.cm.publish.extension.useLock
import top.jonhao.cm.publish.tool.DependencyUtils
import java.io.File

class PublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.apply {
            plugin(PublishConstants.PUBLICATION_PLUGIN_ID)
        }

        val cmPublishingExtension =
            project.extensions.create(
                CMPublishingExtension.NAME,
                CMPublishingExtension::class.java
            )

        project.afterEvaluate {
            var username = project.gradle.startParameter.projectProperties["username"]
            var password = project.gradle.startParameter.projectProperties["password"]

            val isPublishReleases: Boolean = if (username == null || password == null) {
                username = cmPublishingExtension.username ?: project.findGlobalProperty(
                    PublishConstants.PUBLISH_KEY_USERNAME,
                ) ?: PublishConstants.DEFAULT_USERNAME

                password = cmPublishingExtension.password ?: project.findGlobalProperty(
                    PublishConstants.PUBLISH_KEY_PASSWORD,
                ) ?: PublishConstants.DEFAULT_PASSWORD

                cmPublishingExtension.isPublishReleases ?: project.findGlobalProperty(
                    PublishConstants.PUBLISH_KEY_IS_PUBLISH_RELEASES
                ) ?: PublishConstants.DEFAULT_IS_PUBLISH_RELEASES
            } else {
                val bool = project.gradle.startParameter.projectProperties["isPublishReleases"]
                bool?.run {
                    this != "false"
                } ?: run {
                    true
                }
            }

            project.group =
                project.findGlobalProperty<String>(PublishConstants.PROPERTY_KEY_GROUP).orEmpty()
            project.version =
                project.findGlobalProperty<String>(PublishConstants.PROPERTY_KEY_VERSION).run {
                    if (isPublishReleases) {
                        this + PublishConstants.SUFFIX_RELEASE
                    } else {
                        this + PublishConstants.SUFFIX_SNAPSHOT
                    }
                }

            val publishing = project.extensions.getByType(PublishingExtension::class.java)
            publishing.apply {
                val publicationConfig = Publish.getPublicationConfig(project)
                publicationConfig?.apply {
                    publications.create<MavenPublication>(publicationName) {
                        pom.withXml {
                            val root = asNode()
                            root.children().last()

                            val dependenciesNode = root.appendNode("dependencies")
                            DependencyUtils.getDependencies(project, project.useLock)
                                .onEach { dependency ->
                                    val dependencyNode =
                                        dependenciesNode.appendNode("dependency").apply {
                                            appendNode("groupId", dependency.group)
                                            appendNode("artifactId", dependency.name)
                                            appendNode("version", dependency.version)
                                        }
                                    dependency.excludeRules?.run {
                                        if (isNotEmpty()) {
                                            val exclusions =
                                                dependencyNode.appendNode("exclusions")
                                            forEach { excludeRule ->
                                                val exclusion =
                                                    exclusions.appendNode("exclusion")
                                                excludeRule.group?.run {
                                                    exclusion.appendNode("groupId", this)
                                                }
                                                excludeRule.module?.run {
                                                    exclusion.appendNode("artifactId", this)
                                                }
                                            }
                                        }
                                    }
                                }
                        }
                    }
                }

                publications.onEach label@{
                    if (it !is MavenPublication) {
                        return@label
                    }

                    val publication: MavenPublication = it
                    publication.apply {
                        artifactId = project.findGlobalProperty(
                            PublishConstants.PROPERTY_KEY_NAME,
                            project.name
                        )
                        version = project.version.toString()

                        publicationConfig?.apply {
                            outputFilePath?.apply { artifact(this) } ?: apply {
                                artifact("${project.buildDir}${File.separator}libs${File.separator}${project.name}-${project.version}.jar")
                            }
                            javadocJar?.apply { artifact(this) }
                            sourcesJar?.apply { artifact(this) }
                        }

                        pom {
                            name.setProperty(project, PublishConstants.POM_NAME)
                            description.setProperty(project, PublishConstants.POM_DESCRIPTION)
                            url.setProperty(project, PublishConstants.POM_URL)
                            inceptionYear.setProperty(project, PublishConstants.POM_INCEPTION_YEAR)

                            licenses {
                                license {
                                    name.setProperty(project, PublishConstants.POM_LICENCE_NAME)
                                    url.setProperty(project, PublishConstants.POM_LICENCE_URL)
                                    distribution.setProperty(
                                        project,
                                        PublishConstants.POM_LICENCE_DISTRIBUTION
                                    )
                                    comments.setProperty(
                                        project,
                                        PublishConstants.POM_LICENCE_COMMENTS
                                    )
                                }
                            }

                            developers {
                                developer {
                                    id.setProperty(project, PublishConstants.POM_DEVELOPER_ID)
                                    name.setProperty(project, PublishConstants.POM_DEVELOPER_NAME)
                                    email.setProperty(project, PublishConstants.POM_DEVELOPER_EMAIL)
                                    url.setProperty(project, PublishConstants.POM_DEVELOPER_URL)
                                }
                            }

                            scm {
                                connection.setProperty(project, PublishConstants.POM_SCM_CONNECTION)
                                developerConnection.setProperty(
                                    project,
                                    PublishConstants.POM_SCM_DEVELOPER_CONNECTION
                                )
                                url.setProperty(project, PublishConstants.POM_SCM_URL)
                            }

                            organization {
                                name.setProperty(project, PublishConstants.POM_ORGANIZATION_NAME)
                                url.setProperty(project, PublishConstants.POM_ORGANIZATION_URL)
                            }
                        }
                    }
                }

                repositories {
                    maven {
                        this.isAllowInsecureProtocol = true
                        credentials {
                            this.username = username
                            this.password = password
                        }
                        url = if (isPublishReleases) {
                            val url: String =
                                cmPublishingExtension.releasesUrl ?: project.findGlobalProperty(
                                    PublishConstants.PUBLISH_KEY_URL_RELEASES
                                ) ?: PublishConstants.DEFAULT_URL_RELEASES
                            uri(url)
                        } else {
                            val url: String =
                                cmPublishingExtension.snapshotsUrl ?: project.findGlobalProperty(
                                    PublishConstants.PUBLISH_KEY_URL_SNAPSHOTS
                                ) ?: PublishConstants.DEFAULT_URL_SNAPSHOTS
                            uri(url)
                        }
                    }
                }
            }
        }
    }

    private fun Property<String>.setProperty(project: Project, key: String) {
        project.findProperty(key)?.apply {
            set(this.toString())
        }
    }
}