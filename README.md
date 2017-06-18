# Selenium Technical Test #

## Assumptions ##
- Before listing the games the user has to be logged.
- For simplicity no operating system virtualization is required.
- In order to make easier to review this test an extra vm parameter with the browser is included. This implementation supports the browsers Chrome, Firefox, Safari, InternetExplorer.

## Verified environments ##
This test have been run in the following configurations:

 ### OS X El Capitan Version 10.11.6 ### 
 - Chrome Version 58.0.3029.110 (64-bit).    
 - Firefox version 47.    
 - Safari version 10.0.1. 
    - Developer tools needs to be turned on.
    - No other safari browser can be opened at the same time.
 
 ### Windows 10 ###
 - Firefox version 47.
 - Internet Explorer version 11.545.10586.0.
 - Chrome 58.0.3029.110 (64-bit).
 
 ### Ubuntu 16.04 ###
 - Chrome 573.0.2987.98


## Known Issues ##
- Firefox  latest selenium webdriver will not work properly with Firefox browser version after 47.
- OS X El Capitan. Some users reported that they could not get safaridriver to run correctly after installing Safari 10 on El Capitan. The main symptom of this issue is that safaridriver will quit immediately when launched manually via the command line. 
- Windows 10 and Internet Explorer version 11 webdriver 64bit fails to retrieve the cookie, this test uses 32 bit version instead.


## How to get setup ##
- Clone the project from github: `git clone https://github.com/anaisabelfg/selenium-test.git` or alternatively extract the provided zip file.
- Make sure you have a Jdk 1.8 installed
- Make sure you have Maven already installed
- Run `mvn compile`
- **Make sure the files under `target/classes/webdrivers/` have execution permissions.**


## How to run the tests ##
From the project folder run the test using maven:

`mvn test -Dusername=username -Dpassword=password -Dbrowser=[Chrome,Firefox,Safary,InternetExplorer]`
