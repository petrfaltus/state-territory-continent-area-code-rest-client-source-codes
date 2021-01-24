@echo off

set OUT=bin

set PROJECT=StaTerContAcRestClient
set SOURCE=Program
set EXECUTABLE=%OUT%\%PROJECT%.exe
set ICON=ico\states.ico
set DOTNET_HOME=C:\WINDOWS\Microsoft.NET\Framework64\v4.0.30319

set DLL=%OUT%\Newtonsoft.Json.dll

mkdir %OUT%
"%DOTNET_HOME%\csc.exe" /win32icon:%ICON% /out:%EXECUTABLE% /reference:%DLL% %SOURCE%.cs OneItem.cs RestRequest3.cs RestReply3.cs
