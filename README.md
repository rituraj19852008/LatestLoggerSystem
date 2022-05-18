# LatestLoggerSystem

Running The Program
In order to run the code you will need to either clone the repository locally or you can download the zip file into whichever directory you prefer and UnZip it.
There are two ways to run this application :-

1. Fire this maven command & it will boot up the Main class's main method , passing in the default log file in the 
resources folder -> mvn compile exec:java .(look in the pom.xml exec-maven-plugin & change the argument if you want to 
se your own log file. )
2. After this you simply need to cd into LatestLoggerSystem and run the com.cs.main.LoggerSystemMainEntryPoint main method passing in the 
filename as the argument to the main method , like this-> --args='src/main/resources/logfile.txt'(May be add it in the VM arguments in the Edit COnfigurations,
if you are using Intellij) which will build and run the program with the log 
file to be parsed being logfile.txt which is a sample file I created based on the example given in the assignment.

In order to run with a different logfile you just need to move the log file into the LatestLoggerSystem directory and replace logfile.txt
with the new file name in the mentioned command or give a relative path to the logfile that you would like to be parsed.

Logging output from the program can be found in LatestLoggerSystemOutputLogs.txt


Side Notes on the Output:-

Regarding displaying the database values back to us for evaluation as in, 
 what the program should do it just says to 
,Flag any long events that take longer than 4ms and Write the found 
event details to file-based HSQLDB in the working folder.
So I implemented a SELECT * FROM Events statement which reads all 
table entries and logs the alerts in debug mode.
As well as this I also added a method to purge all records to clear the table
before closing the connection as to avoid overlap of different files.
In the LatestLoggerSystemOutputLogs.txt one can see the output after running the program
& it prints the LogEvents that took more than 4ms , also we can inspect the DB while running
the application to see all the values.

