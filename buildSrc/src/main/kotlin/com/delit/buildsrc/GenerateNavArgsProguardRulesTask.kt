package com.delit.buildsrc

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.*
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

const val NAV_ARGS_PROGUARD_RULES_PATH = "generated/proguard-rules/navargs-proguard-rules.pro"
private const val APP_NAMESPACE = "http://schemas.android.com/apk/res-auto"

@CacheableTask
abstract class GenerateNavArgsProguardRulesTask : DefaultTask() {

    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:InputFiles
    @get:SkipWhenEmpty
    val navigationGraphFiles: FileTree = project.fileTree("src/main/res/navigation") {
        include("*.xml")
    }

    @get:OutputFile
    val rulesFile : File = project.layout.buildDirectory.file(NAV_ARGS_PROGUARD_RULES_PATH).get().asFile

    @Internal
    override fun getGroup() = "generate"

    @Internal
    override fun getDescription() = "Generates proguard rules for keeping names of classes used in navigation graph arguments"

    @TaskAction
    fun generateRules() {
        rulesFile.printWriter().use { writer ->

            val documentBuilder = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = true }.newDocumentBuilder()

            navigationGraphFiles.forEach { inputFile ->
                val xmlDoc = documentBuilder.parse(inputFile)
                val arguments = xmlDoc.getElementsByTagName("argument")

                (0 until arguments.length)
                    .mapNotNull { arguments.item(it).attributes.getNamedItemNS(APP_NAMESPACE, "argType")?.nodeValue }
                    .filter { it.contains('.') }
                    .toSet()
                    .map { "-keepnames class $it" }
                    .forEach(writer::println)
            }
        }
    }
}