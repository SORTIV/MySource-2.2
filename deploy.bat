echo off

rem this will read properties from deploy.properties file.
For /F "tokens=1* delims==" %%A IN (deploy.properties) DO (
	IF "%%A"=="cloudseer.server.path" set cloud_seer_path=%%B
	IF "%%A"=="cloudseer.module.name" set csmodule_name=%%B
	)

IF exist %cloud_seer_path% (
rem echo Deploying cloudseer module %csmodule_name%

rem copy files from unzipped solution archive to cloudseer/web-inf/lib.
robocopy ./libs %cloud_seer_path%/web-inf/lib /E /XO *
echo Copied files from libs folder to %cloud_seer_path%/web-inf/lib /E /XO *

echo csmodule_name deployed successfully

)else (
   echo %cloud_seer_path% Path is invalid
)