REM compiler framework framework/src/*.java mettre .class dans framework/classes
javac -d framework/classes framework/src/*.java

cd framework\classes

Rem mamadika .class ho jar
jar -cf fw.jar .
 
copy fw.jar "..\..\test-framework\WEB-INF\lib"

cd ..\..\test-framework

javac -d WEB-INF\classes WEB-INF\classes\*.java

jar -cf test-framework-1903.war .

copy test-framework-1903.war "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps"
pause