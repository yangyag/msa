@echo off
setlocal enabledelayedexpansion

REM 변수 설정
set VERSION=v1.1.0
set DOCKER_HUB_USERNAME=yangyag2
set SERVICE_NAME=post-service

REM 이미지 빌드
echo Building %SERVICE_NAME% image...
docker build -t msa-%SERVICE_NAME%-image:%VERSION% .\%SERVICE_NAME%\
if %errorlevel% neq 0 (
    echo Failed to build %SERVICE_NAME% image
    exit /b 1
)

REM 이미지 태그
echo Tagging %SERVICE_NAME% image...
docker tag msa-%SERVICE_NAME%-image:%VERSION% %DOCKER_HUB_USERNAME%/msa-%SERVICE_NAME%-image:%VERSION%
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