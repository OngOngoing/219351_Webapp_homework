
1. Complie hadoop code with

$ hadoop com.sun.tools.javac.Main TwoLinkHa.java


2. Tranform it to .jar format

$ jar cf TwoLink.jar TwoLinkHa*.class


3. Run hadoop with your input

$ hadoop jar TwoLink.jar TwoLinkHa ../input/web-Google.txt output101

