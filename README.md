# SimpleReloadLib
Simple Java generic reload library.

## Introduce
SimpleReloadLib used to be a part of QuickShop-Reremake. But it really easy to use and powerful, so I made it to a standalone lib.   
You can insert to any Java project including Bukkit, Paper, BungeeCord, Forge, Fabric etc.  
This lib already used in QuickShop-Reremake over 1 year and no errors found.

## Features
* Reloading mechanism based on registration order, no need to worry about reload order。
* Simple and easy to use, just need registration with eyes closed.
* Lightweight, powered by 5 java classes. You can package it into any project.
* WeakReference to save your memory and prevent memory leaking.
* Support both object and static method.

## How to use

1. Create a Reload Manager
```java
ReloadManager reloadManager = new ReloadManager();
```

2. Implement Reloadable and register it into ReloadManager (or static method if register for static util)

```java
public class Example implements Reloadable {
    public Example(){
        Instance.getReloadManager().register(this);
    }
    
    @Override
    public ReloadResult reloadModule() throws Exception {
        // Reload code here
        try{
            // Reload code here
             return ReloadResult.builder().status(ReloadStatus.SUCCESS).build();
        } catch (IllegalStateException scheduleException) {
              return ReloadResult.builder().status(ReloadStatus.SCHEDULED).reason("资源正被使用").build();
        } catch (RuntimeException requireRestartException) {
              return ReloadResult.builder().status(ReloadStatus.REQUIRE_RESTART).reason("开发者长得太丑，因此需要重新启动应用程序").build();
        } catch (Exception otherException){
              return ReloadResult.builder().status(ReloadStatus.EXCEPTION).exception(otherException).reason("什么玩意儿爆炸了草").build();
        }
    }
}
```

3. Reload it!

```java
Map<ReloadableContainer, ReloadResult> results = reloadManager.reload();
Map<ReloadableContainer, ReloadResult> results = reloadManager.reload(Example.class);
```

## Registerable reloadables

* Any classes that implement Reloadable
* Any no args and returns ReloadResult static Method

## Get summary

SimpleReloadLib offers a Map contains ReloadContainer and ReloadResults：

* SUCCESS - Successfully to reloading.
* OUTDATED - WeakReferenced object already invalid and will be removed in next reload.
* REQUIRE_RESTART - Reload is impossible, restart required.
* SCHEDULED - Cannot reload in this time but already scheduled reloading if possible.
* EXCEPTION - Something just exploded

## Maven

We're in central.

```xml
    <dependencies>
        <dependency>
            <groupId>com.ghostchu</groupId>
            <artifactId>simplereloadlib</artifactId>
            <version>1.1.2</version>
        </dependency>
    </dependencies>
```

## License

MIT + 996.ICU mixed.
