# ImoocDict
Android 语音词典

这是学习慕课网的教程《Android 语音词典》的过程。

讯飞的语音接口注册感觉太麻烦，要填姓名，公司啥的。我就想换成百度的语音接口了。

百度的接口文档开始没看懂，后来理解了，两种语音识别的调用方式：API 和调出窗口。

如果使用 api，就需要根据文档来实现接口，重写方法，做很多的事情。

而调出窗口来进行语音识别就很简单，只需要配置一下 AndroidManifest.xml，然后在 onActivityResult() 方法里处理识别的结果就行了。

查词典使用的是有道的接口，感觉有道的接口内容很少。主要就是练习了一下OKHttp的异步用法，以及Gson的解析。

这里需要注意的几点有：

1. 百度的语音识别，是 lib + jar + so，需要把 so 包，导入进来，不然会一直报错说：语音识别失败。

简单来说就是在 build.gradle 里加入下面的代码：

```
task nativeLibsToJar(type: Zip, description: "create a jar archive of the native libs") {
  destinationDir file("$projectDir/libs")
  baseName "Native_Libs2"
  extension "jar"
  from fileTree(dir: "libs", include: "armeabi/*.so")
  into "lib"
}

tasks.withType(JavaCompile) {
  compileTask -> compileTask.dependsOn(nativeLibsToJar)
}
```

2. 即使使用调用窗口的方式来识别语音，资源文件也是不能少的。

大概就是这些。