TABLE OF CONTENTS
-----------------
<ol>
<li> Introduction  </li>
<li> Statement of Work </li>
<li> Requirements </li>
<li> What was automated </li>
<li> Project (Location, Structure) </li>
<li> API Documentation (Javadoc) </li>
<li> Command-line Invocation </li>
<li> Output (Expected) </li>
<li> Explanation of Issues seen with Automation Exercise </li>
</ol>


1. Introduction
---------------------------------------------------------------------------
This is the README.md file for the Comcast Silicon Valley Test Engineering 
    interview exercise presented to Eric Wiegman as part of his consideration
     for the _""_ position at Comcast. The choice was made to program the "2.
      Meme Sorter and Scorer" exercise.

2. Statement of Work
------------------------------
 I have created a Java project that takes a backup file and copies it over 
 the input JSON file and then parses that JSON code to be able to retrieve 
 all the memes and sort them in an ascending fashion. Next, the code assigns 
 a random value from 1 to 10 as the lulz factor for each meme ... and that 
 input JSON file is overwritten so it is now a list of JSON pairs, one being 
 the meme name and the other the lulz factor.

 Maven is used as the tool to perform the build and deploy the test (running 
 the TestNG suite with the surefire plugin. There seems to be a problem with 
 my POM file, maven, or something related, which I am not able to solve. The 
 surefile plugin is correctly defined in the POM file, but it is not being 
 recognized by maven. Also, the 'mvn test' command line is not only not 
 running the test, but it is also running some python script (presumably to 
 do diagnostics) but that script refuses to run due to an invalid python syntax.
 
 

3. Specified Project Requirements
-------------------------------------------------------------
The requirement is, verbatim: Read in a list of internet memes from a json file 
on the classpath (you choose the memes!). Create one method which takes the list
of memes and sorts them by name. Create a second method which associates a
"lulz" score (from 1-10) with each meme and writes the updated values to the 
same json file.

3.1. Notes on Requirements
--------------------------
1. .
6. This file contains instructions on how to run the test from command line.


4. The following is a general outline of what was automated:
---------------------------------------------------------------------------
1. 


5. Project is stored on GitHub at public repository 
Eric-Wiegman/2_Meme-sorter-and-scorer.
---------------------------------------------------------------------------
6.1 The directory structure is shown below:

    │   pom.xml
    │
    ├───docs
    │   │   allclasses-frame.html
    │   │   allclasses-noframe.html
    │   │   constant-values.html
    │   │   Consts.html
    │   │   DataProviderMemeExercise.html
    │   │   deprecated-list.html
    │   │   help-doc.html
    │   │   index.html
    │   │   InternetMeme.html
    │   │   overview-tree.html
    │   │   package-frame.html
    │   │   package-list
    │   │   package-summary.html
    │   │   package-tree.html
    │   │   script.js
    │   │   stylesheet.css
    │   │   TestMemeExercise.html
    │   │
    │   └───index-files
    │           index-1.html
    │           index-10.html
    │           index-2.html
    │           index-3.html
    │           index-4.html
    │           index-5.html
    │           index-6.html
    │           index-7.html
    │           index-8.html
    │           index-9.html
    │
    └───src
        ├───main
        │   ├───java
        │   │       Consts.java
        │   │       InternetMeme.java
        │   │
        │   └───resources
        │           internetmemes.json
        │           internetmemes_backup.json
        │           internetmemes_output.json
        │           testng.xml
        │
        └───test
            └───java
                    DataProviderMemeExercise.java
                    TestMemeExercise.java


6.2 Please note that the directory structure is important, and file/directory
    changes should **not** be made. Failure to leave the structure as is will
    cause Maven, the surefire plugin, or other tools to not recognize the
    classpath items correctly, leading to failures.

7. API Documentation (Javadoc)
------------------------
For more information on the defined java elements in this project, invoke
    index.html at 2_Meme-sorter-and-scorer/docs and use the API viewer to read 
    the Javadoc supplied text.

8. Command-line invocation
---------------------------
1. To call the test from the command line (from within your Windows (DOS)
    Command Prompt or Macintosh/Linux Terminal), you need to ensure some
    prerequisites have first been met.
2. You should have your path set so that it will recognize the Maven binary
    'mvn' no matter which directory you are currently browsing.
3. As it is required by Maven, if you have not already done so set your
    JAVA\_HOME environment variable to be where you installed your Java
    (for instance, 'C:\Program Files\Java\jdk1.8.0_40' if using defaults for
    Windows installation).
4. To simplify the command line call, you should use your Terminal to
    navigate to the directory where the desired pom.xml file is located.
    For this case, this is the directory '2_Meme-sorter-and-scorer'.
    In that way, the command line call will assume there is only one POM file
     to be run and will assume it is in the current directory.

8.2. The following is the command line text to be entered in the Command Prompt
    or Terminal:

        mvn compile test

9. Output
---------
9.1. The command line output that I am seeing is shown below
<pre>
[[INFO] Scanning for projects...
 [INFO]
 [INFO] ------------------------------------------------------------------------
 [INFO] Building Internet Meme JSON tests project 1.0-SNAPSHOT
 [INFO] ------------------------------------------------------------------------
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESS
 [INFO] ------------------------------------------------------------------------
 [INFO] Total time: 0.648s
 [INFO] Finished at: Sun Oct 18 14:54:23 PDT 2015
 [INFO] Final Memory: 7M/123M
 [INFO] ------------------------------------------------------------------------
 
 Traceback (most recent call last):
   File "C:\Python34\Lib\cmd.py", line 45, in <module>
     import string, sys
   File "C:\Python34\Lib\string.py", line 73
     class Template(metaclass=_TemplateMetaclass):
                             ^
 SyntaxError: invalid syntax
</pre>

9.2. At the point where the 'Running TestSuite' is announced, Maven should call
    surefire, which in turn calls TestNG harness that runs the XML suite in
    Selenium Java code. This performs the steps outlined in README section 4.

9.3. The directory 'target' should be created in the 'HomeWork' root, where 
binaries and pass/fail report files are found.

9.4. To see a graphical representation of the pass/fail/skip data associated
    with the testNG Suite run, open the file /target/surefire-reports/index.html
    in your favorite browser.
    
9.5 Of course, my setup is not working currently for some reason - so maven 
builds the project and retrieves plugins needed for that build. However,
 as the test is not run, the java dependencies are not retrieved. I 
 apologize, but I don't have enough knowledge of the underlying problem to 
 solve it in a timely manner. Thus, you may need to set up an Eclipse or 
 IntelliJ project and download and specify the dependent jars yourself.
 
 Hopefully, your setup is less fragile than mine, so you will be able to just
  run the maven command line and have it all work flawlessly. But, in case there
  is something wrong with my POM file, here are the required JARs:
  a. com.googlecode.json-simple:json-simple:1.1.1
  b. org.testng:testng:6.8.8
  
  And then you could run the TestNG XML Suite file found at /src/main/resources
