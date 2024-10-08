@echo off
setlocal enabledelayedexpansion

cd /d "%~dp0"

set "versions="

for %%f in (props\*.properties) do (
    set "filename=%%~nf"
    set "versions=!versions! %%~nf"
)

for %%v in (%versions%) do (
    call gradlew -q -Pminecraft=%%v build
)
