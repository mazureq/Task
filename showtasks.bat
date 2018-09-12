call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo There were errors
goto fail

:runchrome
start "" "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open chrome
goto fail

:fail
echo.
echo Cannot run application
goto end

:end
echo Finishing process