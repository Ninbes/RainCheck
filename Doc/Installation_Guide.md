# WSL 2 (windows subsystem for linux)
### Prerequisites

- You must be running Windows 10 version 2004 and higher (Build 19041 and higher)
- or Windows 11 to use the commands below.
- If you are on earlier versions please see the [manual install page.](https://learn.microsoft.com/en-us/windows/wsl/install-manual)
#### To install wsl in terminal    
    wsl --install
- You can list your installed Linux distributions and check the version of WSL
- each is set to by entering the command: wsl -l -v in PowerShell or Windows Command Prompt.

- To set the default version to WSL 1 or WSL 2 when a new Linux distribution is installed,
- use the command: wsl --set-default-version <Version#>, replacing <Version#> with either 1 or 2.
# Redis server
 Once you're running Ubuntu on Windows, you can follow the steps detailed  
 at [Install on Ubuntu/Debian](https://redis.io/docs/latest/operate/oss_and_stack/install/install-redis/install-redis-on-linux/#install-on-ubuntu-debian) to install recent stable versions of Redis from the official  
 packages.redis.io APT repository. 
#### Add the repository to the apt index, update it, and then install:
    curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg

    echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
    
    sudo apt-get update
    sudo apt-get install redis
#### Lastly start the Redis server like so: 
    sudo service redis-server start
# Node.js & npm
    winget install Schniz.fnm # installs fnm (Fast Node Manager)

    fnm env --use-on-cd | Out-String | Invoke-Expression # configure fnm environment

    fnm use --install-if-missing 22 # download and install Node.js

    node -v # should print `v22.11.0` 
    npm -v # should print `10.9.0`
- or just follow the official [docs](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

## Angular
###### Install the Angular-cli to access the ng command, which is required to run the application
    npm install -g @angular/cli
### Start the application with npm
###### Navigate to the Frontend/Frontend folder and install the necessary dependencies before the first run:
    npm install
###### Go to Frontend/Frontend folder of the project and open a command line tool in that folder then execute:
    npm start
## Cypress
###### Go to the Frontend/Frontend folder of the project and open a command line tool in that folder then execute:
    npm install cypress --save-dev
### Testing with cypress
    npx cypress open
# Java (JDK)

### First step is to install [winget](https://learn.microsoft.com/en-us/windows/package-manager/winget/#install-winget)

### After you installed winget in terminal: 
    winget search Microsoft.OpenJDK 
### You'll see output similar to the following: 

    Name                           Id                    Version
    --------------------------------------------------------------
    Microsoft Build of OpenJDK 21  Microsoft.OpenJDK.21  21.0.0

### You can now install the package by referencing the Id shown above, using the following command:

    winget install Microsoft.OpenJDK.21

### Output should look like this:

    Found Microsoft Build of OpenJDK 21 [Microsoft.OpenJDK.21]
    This application is licensed to you by its owner.
    Microsoft is not responsible for, nor does it grant any licences to, third-party packages.
    Downloading https://aka.ms/download-jdk/microsoft-jdk-21.0.0-windows-x64.msi
    ██████████████████████████████   160 MB /  160 MB
    Successfully verified installer hash
    Starting package install...    
    Successfully installed


# Maven

- Have a JDK installation on your system. 
- Either set the JAVA_HOME environment variable pointing to your JDK installation 
- or have the java executable on your PATH.
#### Extract distribution archive in any directory:

    unzip apache-maven-3.9.9-bin.zip

### Alternatively use your preferred archive extraction tool.

##### Add the bin directory of the created directory apache-maven-3.9.9 to the PATH environment variable
##### Confirm with mvn -v in a new shell. The result should look similar to
    Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
    Maven home: /opt/apache-maven-3.9.9
    Java version: 1.8.0_45, vendor: Oracle Corporation
    Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
    Default locale: en_US, platform encoding: UTF-8
    OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"   