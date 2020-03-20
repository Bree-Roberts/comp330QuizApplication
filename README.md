# comp330QuizApplication
## Import project to IntelliJ
Download and install sbt (https://www.scala-sbt.org/)
Install the Scala plugin for IntelliJ:
* Go to Settings
* Select Plugins
* Search for Scala (Recommend installing the plugin with the most downloads)
Fork project from github and download the project. 
In IntelliJ select Open
Navigate to the top-level folder of the project and click Ok.
This should open up the Import Project from SBT window:
 * Enable use auto-import
 * enable Sources.
 * disable Javadocs and Sources for SBT and plugins.
 * Click OK.
The project should now be imported correctly.

## Running project from IntelliJ
Create a run configuration:
* From the Run menu, select Run.
* Click Edit Configurations...
* Click +
* Select SBT Task
* Name your configuration
* In the Tasks field, enter run
* Click Run

## Importing project to Eclipse
Download and install sbt (https://www.scala-sbt.org/)
Fork project from github
Copy the clone url to your clipboard
In Eclipse go to File then Import:
* Expand Git
* Select Projects from Git and hit Next.
* Select Clone URL and hit Next.
* The clone url should automatically paste itself into the URL field.
* If you've used github clone before then you should already have your username and password imputted but you might need to add it if you haven't done it before.
* Import all the branches and hit Next.
* Type a directory name and hit Next.
* Select Import as a general project and hit finish.
In the command prompt navigate to the folder containing the build.sbt file (This should be the top most folder where you imported the project to)
Type sbt compile (This should build the sbt project and install all the dependancies)
After thats completed type in sbt eclipse (This will install the plugins for eclipse)

## Running Project from Eclipse 
Click on the Run Menu Go to External Tools and then External Tools Configuration
Create a New Configuration (Little paper icon with a + sign)
For Location field either type in the location of your sbt.bat file on your computer or Browse File System and Navigate to it (This is where you installed sbt to on your pc)
For Working Directory field type in the location of your project or Browse Workspace and navigate to it (This is where you imported the project too from github)
In the arguments field type in run
Hit Apply and then Run.

## Running
The project uses sbt for building so it is needed to comile, run and test the project.
The commands for this are as follows:
* sbt compile
* sbt run
* sbt test
If sbt is installed you should navigate to the folder containing the build.sbt file and run the commands from there.


## Sources
We used information from [this article](https://www.geeksforgeeks.org/parse-json-java/) 
on how to parse and write to a .json file using the simple json library
