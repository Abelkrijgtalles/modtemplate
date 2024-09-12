@echo off
setlocal enabledelayedexpansion

cd /d "%~dp0"

set "versions="

echo De volgende versies worden verwerkt:

for %%f in (props\*.properties) do (
    set "filename=%%~nf"
    set "versions=!versions! %%~nf"
    echo %%~nf
)

echo.
echo Beginnen met builden...

for %%v in (%versions%) do (
    call gradlew -q -Pminecraft=%%v build
)
