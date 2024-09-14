@echo off
setlocal enabledelayedexpansion

cd /d "%~dp0"

set "versions="

echo Building the following versions:

for %%f in (props\*.properties) do (
    set "filename=%%~nf"
    set "versions=!versions! %%~nf"
    echo %%~nf
)

echo.
echo Beginning to build...

for %%v in (%versions%) do (
    call gradlew -q -Pminecraft=%%v build
)
