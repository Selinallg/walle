

### Gradle插件使用方式
#### 配置build.gradle

在位于项目的根目录 `build.gradle` 文件中添加Walle Gradle插件的依赖， 如下：

```groovy
buildscript {
    dependencies {
        classpath 'com.meituan.android.walle:plugin:1.1.7'
    }
}
```

并在当前App的 `build.gradle` 文件中apply这个插件，并添加上用于读取渠道号的AAR

```groovy
apply plugin: 'walle'

dependencies {
    compile 'com.meituan.android.walle:library:1.1.7'
}
```

#### 配置插件

```groovy
walle {
    // 指定渠道包的输出路径
    apkOutputFolder = new File("${project.buildDir}/outputs/channels");
    // 定制渠道包的APK的文件名称
    apkFileNameFormat = '${appName}-${packageName}-${channel}-${buildType}-v${versionName}-${versionCode}-${buildTime}.apk';
    // 渠道配置文件
    channelFile = new File("${project.getProjectDir()}/channel")
}
```

#### 如何生成渠道包


用法示例：

* 生成渠道包 `gradlew clean assembleReleaseChannels`



walle-cli-all.jar 在工程根目录


命令方式查看 显示当前apk中的渠道和额外信息：
java -jar walle-cli-all.jar show  你要查看的apk路径
例如：java -jar walle-cli-all.jar show  app\build\outputs\channels\app-com.nolo.testwalle.myapplication-nolo-release-v1.0-1-20210805-114003.apk

写入渠道:
java -jar walle-cli-all.jar put -c nolo  app\build\outputs\channels\app-com.nolo.testwalle.myapplication-nolo-release-v1.0-1-20210805-114003.apk

写入额外信息，不提供渠道时不写入渠道

java -jar walle-cli-all.jar put -c nolo -e msg=xxxxxxx  app\build\outputs\channels\app-com.nolo.testwalle.myapplication-nolo-release-v1.0-1-20210805-114003.apk
