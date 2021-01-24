@echo off

set OUT=bin

set PROJECT=StaTerContAcRestClient
set SOURCE=Program
set EXECUTABLE=%OUT%\%PROJECT%.exe
set ICON=ico\states.ico
set DOTNET_HOME=C:\WINDOWS\Microsoft.NET\Framework64\v4.0.30319

mkdir %OUT%
"%DOTNET_HOME%\csc.exe" /win32icon:%ICON% /out:%EXECUTABLE% %SOURCE%.cs
