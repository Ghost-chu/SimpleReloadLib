# SimpleReloadLib
Java 简易通用重载库。

## 前言
SimpleReloadLib 曾是 [QuickShop-Reremake](https://github.com/PotatoCraft-Studio/QuickShop-Reremake) 的一部分。但我发现实在是太TM好用和灵活了，于是单独拿出来作为一个库使用。  
除了 Bukkit 和 BungeeCord 插件，你甚至可以嵌入到任何需要重载配置文件的任意 Java 应用程序中。  
在中型复杂架构的项目中，这会是一个非常有用的工具。  
该库在 [QuickShop-Reremake](https://github.com/PotatoCraft-Studio/QuickShop-Reremake) 使用已近一年，我们没有发现任何严重错误，大概可以放心使用（。  

## 特点
* 基于注册顺序的重载机制，无需担心重载顺序的问题。
* 简单易用，只需无脑注册即可。
* 轻量，只有 5 个 Java 文件，可以打包到项目中。
* 弱引用存储，不会导致内存泄漏。
* 支持对象和静态方法

## 如何使用

1. 首先需要创建一个重载管理器
```java
// 注册重载管理器
ReloadManager reloadManager = new ReloadManager();
```

2. 将需要重载配置文件的模块的 class 文件实现 Reloadable 接口，注册到重载管理器，并返回重载结果。

```java
public class Example implements Reloadable {
    public Example(){
        Instance.getReloadManager().register(this);
    }
    
    @Override
    public ReloadResult reloadModule() throws Exception {
        // Reload code here
        return ReloadResult.builder().status(ReloadStatus.SUCCESS).build();
    }
}
```

3. 开始重载

```java
// 按照注册顺序重载所有模块
Map<ReloadableContainer, ReloadResult> results = reloadManager.reload();
// 按照注册顺序重载特定 class 的所有实例
Map<ReloadableContainer, ReloadResult> results = reloadManager.reload(Example.class);
```

## 哪些内容可被注册到重载管理器

* 普通的实现了 Reloadable 接口的类
* 任何无参返回 ReloadResult 的 Method （也就是支持静态调用）

## 针对性重载

除了 `reload()` 集体重载以外，你也可以通过传递一个 class 参数指定特定的 class 实例重载

## 获取重载结果

SimpleReloadLib 提供了以下几种结果可以返回：

* SUCCESS - 成功
* OUTDATED - 引用的实例已经过期（被 Java 虚拟机回收），重载被跳过（且自动从重载注册表中移除）
* REQUIRE_RESTART - 无法重载，需要重启应用程序
* SCHEDULED - 无法立即重载，但已经计划重载
* EXCEPTION - 发生了异常 （Exception对象可在ReloadResult中拿到）

## 协议

此存储库中的代码使用 MIT 协议授权，原 QuickShop-Reremake 的 GPL 协议不在本存储库中适用。
```
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
