@echo off
cd c:\development\eclipse\workspace\${artifactId}\ant
set ANT_OPTS=-Xms384m -Xmx512m
REM set JAVA_HOME=C:\Program Files (x86)\Java\jdk1.7.0_60
C:\development\apache-ant-1.7.1\bin\ant -f menu.xml -lib C:\development\eclipse\workspace\LNWFramework_v2\lib\jsch-0.1.37.jar
