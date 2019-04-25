#  Nacos demo

## 项目说明
本项目用于练习演示，以及学习积累

## 示例

### 部署Nacos
1. 下载Nacos
下载链接: https://github.com/alibaba/nacos/releases  
本文版本: 1.0.0


2. 运行Nacos
Linux/Unix/Mac: `sh startup.sh -m standalone`  
Windows: `cmd startup.cmd -m standalone`

```log
nacos is starting

         ,--.
       ,--.'|
   ,--,:  : |                                           Nacos 1.0.0
,`--.'`|  ' :                       ,---.               Running in stand alone mode, All function modules
|   :  :  | |                      '   ,'\   .--.--.    Port: 8848
:   |   \ | :  ,--.--.     ,---.  /   /   | /  /    '   Pid: 70198
|   : '  '; | /       \   /     \.   ; ,. :|  :  /`./   Console: http://192.168.1.149:8848/nacos/index.html
'   ' ;.    ;.--.  .-. | /    / ''   | |: :|  :  ;_
|   | | \   | \__\/: . ..    ' / '   | .; : \  \    `.      https://nacos.io
'   : |  ; .' ," .--.; |'   ; :__|   :    |  `----.   \
|   | '`--'  /  /  ,.  |'   | '.'|\   \  /  /  /`--'  /
'   : |     ;  :   .'   \   :    : `----'  '--'.     /
;   |.'     |  ,     .-./\   \  /            `--'---'
'---'        `--`---'     `----'
```

***默认启动Nacos只是作用于单机版***

***startup.sh脚本位于Nacos解压后的bin目录下***

3. 进入Nacos管理页面
启动完成之后，访问：http://127.0.0.1:8848/nacos/，可以进入Nacos的服务管理页面，具体如下：  

**注意 本章节只是为了便于您理解接入方式，本示例代码中已经完成接入工作，您无需再进行修改。**