@echo off
echo Building all services...

echo Building API Gateway...
call ./gradlew :api-gateway:bootJar
if %ERRORLEVEL% neq 0 goto error

echo Building Eureka Service...
call ./gradlew :eureka-service:bootJar
if %ERRORLEVEL% neq 0 goto error

echo Building Post Service...
call ./gradlew :post-service:bootJar
if %ERRORLEVEL% neq 0 goto error

echo Building Reply Service...
call ./gradlew :reply-service:bootJar
if %ERRORLEVEL% neq 0 goto error

echo All services built successfully.
goto end

:error
echo An error occurred while building services.
exit /b %ERRORLEVEL%

:end
echo Build process completed.