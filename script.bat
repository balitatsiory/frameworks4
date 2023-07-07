setlocal
cd framework
javac -d classes *.java
cd classes
jar -cf framework.jar ./
move framework.jar ../../testeFramework/WEB-INF/lib/
cd ../../testeFramework/java/
javac -cp ../WEB-INF/lib/framework.jar -d . Emp.java
move personne ../WEB-INF/classes/
cd ../
jar -cvf ../../../testeFramework.war ./
pause