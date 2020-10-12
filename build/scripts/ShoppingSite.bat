@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ShoppingSite startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and SHOPPING_SITE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ShoppingSite-0.0.jar;%APP_HOME%\lib\jjwt-0.2.jar;%APP_HOME%\lib\spring-boot-starter-web-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-validation-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.1.3.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.3.0.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-security-2.1.3.RELEASE.jar;%APP_HOME%\lib\postgresql-9.4-1200-jdbc41.jar;%APP_HOME%\lib\springfox-swagger2-2.9.2.jar;%APP_HOME%\lib\springfox-swagger-ui-2.9.2.jar;%APP_HOME%\lib\tomcat-embed-jasper-7.0.42.jar;%APP_HOME%\lib\hibernate-core-5.4.9.Final.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.11.0.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.11.0.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.11.0.jar;%APP_HOME%\lib\jackson-databind-2.11.0.jar;%APP_HOME%\lib\jackson-core-2.11.0.jar;%APP_HOME%\lib\h2-1.4.200.jar;%APP_HOME%\lib\spring-data-jpa-2.3.0.RELEASE.jar;%APP_HOME%\lib\waffle-jna-1.7.jar;%APP_HOME%\lib\slf4j-simple-1.7.30.jar;%APP_HOME%\lib\springfox-swagger-common-2.9.2.jar;%APP_HOME%\lib\swagger-models-1.5.20.jar;%APP_HOME%\lib\springfox-spring-web-2.9.2.jar;%APP_HOME%\lib\springfox-schema-2.9.2.jar;%APP_HOME%\lib\springfox-spi-2.9.2.jar;%APP_HOME%\lib\springfox-core-2.9.2.jar;%APP_HOME%\lib\spring-plugin-metadata-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-core-1.2.0.RELEASE.jar;%APP_HOME%\lib\HikariCP-3.4.5.jar;%APP_HOME%\lib\spring-data-commons-2.3.0.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\spring-security-config-5.3.2.RELEASE.jar;%APP_HOME%\lib\spring-security-web-5.3.2.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-security-core-5.3.2.RELEASE.jar;%APP_HOME%\lib\spring-context-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.2.6.RELEASE.jar;%APP_HOME%\lib\javax.transaction-api-1.3.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\spring-aspects-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-web-5.2.6.RELEASE.jar;%APP_HOME%\lib\jakarta.el-3.0.3.jar;%APP_HOME%\lib\hibernate-validator-6.1.5.Final.jar;%APP_HOME%\lib\swagger-annotations-1.5.20.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\classmate-1.5.1.jar;%APP_HOME%\lib\mapstruct-1.2.0.Final.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.35.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.35.jar;%APP_HOME%\lib\ecj-4.2.2.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.4.1.Final.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\javassist-3.24.0-GA.jar;%APP_HOME%\lib\byte-buddy-1.10.10.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;%APP_HOME%\lib\jandex-2.1.1.Final.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\jaxb-runtime-2.3.3.jar;%APP_HOME%\lib\jackson-annotations-2.11.0.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\spring-orm-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-core-5.2.6.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.26.jar;%APP_HOME%\lib\aspectjweaver-1.9.5.jar;%APP_HOME%\lib\spring-jcl-5.2.6.RELEASE.jar;%APP_HOME%\lib\txw2-2.3.3.jar;%APP_HOME%\lib\jakarta.activation-1.2.2.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.3.jar;%APP_HOME%\lib\tomcat-annotations-api-9.0.35.jar;%APP_HOME%\lib\jna-platform-4.1.0.jar;%APP_HOME%\lib\jna-4.1.0.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.11.jar


@rem Execute ShoppingSite
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SHOPPING_SITE_OPTS%  -classpath "%CLASSPATH%" com.anish.ShoppingSite.ShoppingSiteApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SHOPPING_SITE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SHOPPING_SITE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
