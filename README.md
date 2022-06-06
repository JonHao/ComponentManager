# **ComponentManager**
Collection of gradle plugins for managing Android components.

**ComponentManager**（简称**CM**）是一个用于管理 **Android** 组件的插件集。

组件化架构当前非常流行，但是随着项目的发展，组件会越来越多，组件的增长会让开发和技术管理人员愈加难以维护，**CM** 的产生是为了更好的管理组件。**CM** 目前还不是一个框架，您可以自己发布插件以及部署远程 **gradle** 脚本来使用，未来将会提供更友好的继承方式。目前CM包括以下功能：

* **publish**：发布插件，基于 **gradle** 的 `maven-publish` 插件开发，可以方便的发布制品，从 **AGP** v7.1.0 开始，**AGP** 的 `library` 闭包中提供了发布能力，支持环境感知的发布能力，目前 **publish** 插件还不支持，后续将会适配；

* **verison**：版本声明插件，用于声明公有库和私有库，公有库包括开源和闭源的三方库，私有库是个人/团队/公司研发的组件，由于私有库的私密性，所以根据目前的设计，如果您不是开源的项目或产品，建议 **version 插件**代码放到您的私人代码仓库中进行维护，并且发布到私有的制品仓库中（您可以搭建 nexus / jfrog），通过与 **catlog** 对比，个人认为通过 **version 插件**的形式更加便于版本号的管理，未来将结合 **catlog** 的优点，将 **version 插件**做成可配置性的框架；
  
* **common**：通用组件管理插件，是对 **AGP** 常用插件的包装，把习惯性的配置放到了默认配置当中，可以消除开发过程中的大量的样板脚本和代码，此外还提供了一些常用的 **task**，为了便于私有库版本号的管理，私有库版本可以全部声明为 **latest.release** 动态版本号，当您在进入集成测试阶段的时候，将**cm.useLock** 改为 **true** ，然后执行 **generateAllLocks** 生成gradle锁文件。

## Usage
---

### **原则**

* **CM** 使用 **Kotlin** 开发，采用习惯优于配置原则，减少开发过程中的样板脚本/代码，便于各种版本号的统一维护；
  
* 建议使用 Gradle Kotlin DSL，在 **groovy** 中也可以使用 **CM**，但是得不到 **IDE** 的代码提示，使用 **buildSrc** 可以做到代码提示，但是 **buildSrc** 在大型项目中的统一维护是一件很麻烦的事情；

* 建议每个工程采用同一个 `groupId`、`version`，以空间换时间，降低版本号维护成本，如果在一个代码仓库中，个别组件的维护频率和其它组件差异很大的情况下，您也可以单独对组件设置 **version**。

* **CM** 中提供了很多参数，主要分为4类，被采用的优先级从低到高为：**rootProjec中的extra**、**工程根目录下的gradle.properties**、**子工程中的gradle.properties**（gradle 自身支持了子工程属性可以覆盖根工程属性）、**子工程中的 build.gradle / build.gradle.kts 脚本中的扩展**。

### **主要参数**

| 参数 | 类型 | 必要 | 作用域 | 说明 | extra | 属性 | 扩展 |
|---|---|---|---|---|---|---|---|
|cm.gradleVersion|String|O|common|gradle 版本号|Y|Y|N|
|cm.compileSdk|Int|O|common|compile sdk version|Y|Y|N|
|cm.minSdk|Int|O|common|min sdk version|Y|Y|N|
|cm.targetSdk|Int|O|common|target sdk version|Y|Y|N|
|cm.group|String|M|common,publish|默认使用工程名|Y|Y|N|
|cm.name|String|O|common,publish|默认使用模块名|N|Y|N|
|cm.version|String|M|common,publish|版本号名称，默认为unspecified|Y|Y|N|
|cm.versionCode|Int|O|common,publish|版本号，默认为1|N|Y|N|
|cm.withSource|Boolean|O|common,publish|是否上传源码，默认true|Y|Y|N|
|cm.withDocument|Boolean|O|common,publish|是否上传文档，默认false|Y|Y|N|
|cm.useLock|Boolean|O|common|是否锁定依赖，发布带有动态版本号的组件尽可能的使用锁|Y|Y|N|
|cm.publish.username|String|O|common,publish|制品仓库用户名|Y|Y|Y|
|cm.publish.password|String|O|common,publish|制品仓库用户密码|Y|Y|Y|
|cm.publish.snapshotsUrl|String|O|common,publish|制品仓库snapshots url|Y|Y|Y|
|cm.publish.releasesUrl|String|O|common,publish|制品仓库releases url|Y|Y|Y|
|cm.publish.isRelease|Boolean|O|common,publish|是否发布release版本|Y|Y|Y|
|cm.useKotlin|Boolean|O|common|是否启用 kotlin|N|Y|N|
|cm.useJvmDefault|Boolean|O|common|是否启用 kotlin default method兼容|N|Y|N|
|cm.applicationId|String|O|common|application id|N|Y|N|
|cm.keyAlias|String|O|common|签名key alias，内置了debug签名|N|Y|N|
|cm.keyPassword|String|O|common|签名key密码，内置了debug签名|N|Y|N|
|cm.storeFile|String|O|common|签名存储文件，内置了debug签名|N|Y|N|
|cm.storePassword|String|O|common|签名存储密码，内置了debug签名|N|Y|N|

### **pom参数**
**pom** 参数均为非必要参数，均为 `String` 类型，仅作用于 **publish 插件**，仅用于 **gradle.properties** 中。

**基础信息**
* cm.publish.pom.name
* cm.publish.pom.description
* cm.publish.pom.inceptionYear
* cm.publish.pom.url

**license**
* cm.publish.pom.license.name
* cm.publish.pom.license.url
* cm.publish.pom.license.distribution
* cm.publish.pom.license.comments

**SCM**
* cm.publish.pom.scm.publish.url
* cm.publish.pom.scm.publish.connection
* cm.publish.pom.scm.publish.developerConnection

**开发者信息**
* cm.publish.pom.developer.id
* cm.publish.pom.developer.name
* cm.publish.pom.developer.email
* cm.publish.pom.developer.url

*未支持多个开发者，可以通过gradle脚本中补充实现*

**组织/机构**
* cm.publish.pom.organization.name
* cm.publish.pom.organization.url

### **publish使用说明**
如果您已经集成了 **common 插件**，不需要单独集成 **publish 插件**，单独集成需要在gradle脚本中添加如下配置：
```
// 主工程的 build.gradle.kts
buildscript {
    repositories {
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://192.168.28.127:8081/repository/maven-public/")
        }
    }
    dependencies {
        classpath("top.jonhao.cm:publish:latest.release")
    }
}
```
```
// 组件的 build.gradle.kts
plugins {
    id("cm.publish")
    // 或者
    // id(PrivatePlugins.publish)
}
```

如果您需要添加组件发布信息，请参考**pom参数**，添加到root工程下的 **gradle.properties** 中，或者组件的**gradle.properties** 中。

**publish**插件提供了两种发布方式：

1. 通过在配置 **extra** 或 **gradle.properties** 或 **gradle 脚本中的 cmPublishing 扩展**，添加发布**账号**、**密码**、**snapshotsUrl**、**releasesUrl** 及 **isRelease** 参数，然后执行发布 **task**（可以通过点击 **IDE** 中的任务 **item**，也可以直接执行`gradlew publishCmPublicationsToMavenRepository`）；
2. 通过执行 **task** 命令添加参数的方式：
```
// 使用命令添加参数的方式，默认 isRelease 为 true（因为该方式主要作用是为了保护发布账户和密码，如果填入账户和密码，默认是具有发布release版本权限的账户）
gradlew publishCmPublicationToMavenRepository -Pusername=developer -Ppassw
ord=123456

// 使用命令添加参数的方式，如果您依然想发布 snapshot 版本，需要显式的填写 isRelease=false
gradlew publishCmPublicationToMavenRepository -Pusername=developer -Ppassw
ord=123456 -PisRelease=false
```
### **version使用说明**

**version 插件**用于声明团队/公司允许使用的公有库以及自研的私有库，统一管理版本号，目前 **version 插件**不是一个框架，您可以自己维护并发布 **version 插件**，如果 **version 插件**单独使用，需要单独初始化 **version 插件**：
```
Version.init(rootProject)
```
如果使用了 **common 插件**，不需要单独初始化 **version** 插件，为了便于管理版本号，而不是 **common 插件**的版本 和 version插件的版本绑定，所以 **common** 插件对于 **version** 的依赖是 `compileOnly` 方式，在使用 **common 插件**的时候需要显式依赖 **version 插件**，否则会导致 **common 插件**无法工作。

**version** 插件中提供了一个公有库版本号统一的工具类，默认是打开状态，如果您想要关闭该功能，可以通过如下方式：
```
Version.isUnifiedDependenciesVersion = false
```
### **common使用说明**
**common** 插件提供了如下几个插件：
* **cm.android.application** ：对 `com.android.application` 的包装；
* **cm.android.library** ：对 `com.android.library` 的包装；
* **cm.android.library.view.binding** ：在 `cm.android.library` 的基础上默认打开 `viewBinding`；
* **cm.java.library** ：对 `org.gradle.java-library` 的包装。

可以使用这些插件替代 **AGP** 和 **gradle** 中默认的插件来消除样板脚本/代码。
**common** 依赖了 **publish 插件**和 **version 插件**（`compileOnly`），不需要显式依赖 **publish 插件**，但是需要显式依赖 **version 插件**。
为了便于 **CM** 的集成和使用，在 **Sample** 中提供了 **project.gradle** 和 **project.gradle.kts** 两个脚本，可以通过 `apply` 的方式引入工程根目录下的 **build.gradle** 中。
在实际使用过程当中，使用 `buildSrc` 的方式更好，但是不利于多工程共享配置（可以通过 `git submodule` 等方式），所以在团队使用过程中，我将 **project.gradle** 发布到了远端，通过 `apply(from = resources.text.fromInsecureUri("url")` 的方式引入脚本，注意，这里不能发布 **project.gradle.kts**，因为目前 gradle 还不支持识别远程 gradle kotlin dsl。具体使用方法参考 Sample。

**备注** ：由于在国内使用 **gradlePluginPortal** 不便，并且 **CM** **version 插件** 不适合发布到公有插件库，所以 **Sample** 中并未使用 `pluginManagement` 

此外 **common** 中还提供了一些实用的 **task**：
* **cleanGradleCache** ：以 `groupId` 为单位清除 **gradle 缓存**，默认清除当前工程的 `groupId` 缓存，也可以通过执行命令指定 `groupId` ：
```
gradlew cleanGradleCache --groupId=xxx
```
* **generateAllLocks** ：为当前工程所有的工程及插件生成/更新锁文件；
* **deleteAllLocks** ：删除当前工程下所有的锁文件；

### **TODO List**
* 私有库支持源码和组件自由切换；
* 发布插件支持API兼容性检查；
* version 插件可配置；
* ......

## License
---
MIT License

Copyright (c) 2022 Jon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.