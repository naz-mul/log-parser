# custom-log-parser

A simple Java based tool to analyse server logs.


# Pre-requisites

|Tool| Version  |
|--|--|
| Java | 8 |
| Maven | >= 3.5.4 |


# Running

- Run `./mvnw compile` on Linux/macOS based platforms
  - **OR** Run `mvnw.bat compile` on Windows
- Enter the absolute/relative path of the **logfile.txt**
- The program will continue to run indefinitely unless a wrong file path/extension is fed in the input
  - If you feed the same logfile again, it will throw `ConstraintViolationException` 
- Otherwise, press `Ctrl + C`  or `Cmd + C` to exit

# TL;DR

- Solution could utilse Java Persistance API
- Multi-threaded
- Supporting large files
