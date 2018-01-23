
osascript -e 'tell app "Terminal"
    do script "cd /Users/lizhughes/testEnvironment/Selenium_Tests && java -jar TestNGEntry.jar validateMiniConsole.xml"
end tell'
