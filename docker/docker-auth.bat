@echo off
setlocal enabledelayedexpansion

REM 변수 설정
set VERSION=v1.0.16
set DOCKER_HUB_USERNAME=yangyag2
set SERVICE_NAME=auth-service

REM Gradle을 사용하여 JAR 파일 빌드
echo Building JAR file for %SERVICE_NAME%...
cd ..
call gradlew :%SERVICE_NAME%:bootJar
if %errorlevel% neq 0 (
    echo Failed to build JAR file for %SERVICE_NAME%
    exit /b 1
)
cd docker

REM 이미지 빌드
echo Building %SERVICE_NAME% image...
docker build -t msa-%SERVICE_NAME%-image ..\%SERVICE_NAME%\
if %errorlevel% neq 0 (
    echo Failed to build %SERVICE_NAME% image
    exit /b 1
)

REM 이미지 태그
echo Tagging %SERVICE_NAME% image...
docker tag msa-%SERVICE_NAME%-image %DOCKER_HUB_USERNAME%/msa-%SERVICE_NAME%-image:%VERSION%
if %errorlevel% neq 0 (
    echo Failed to tag %SERVICE_NAME% image
    exit /b 1
)

REM 이미지 푸시
echo Pushing %SERVICE_NAME% image to Docker Hub...
docker push %DOCKER_HUB_USERNAME%/msa-%SERVICE_NAME%-image:%VERSION%
if %errorlevel% neq 0 (
    echo Failed to push %SERVICE_NAME% image
    exit /b 1
)

echo %SERVICE_NAME% image has been successfully built, tagged, and pushed.