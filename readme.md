Access at: http://localhost:8080/swagger-ui.html

Dependencies:
 Add MsmqJava.dll to system path.
 Example: copy to C:\WINDOWS\system32 directory.
 
 Add  MsmqJava.jar to maven with below command
 
 _`mvn install:install-file -Dfile=F:\MSMQ\TruckTrackPublisher\MsmqJava.jar -DgroupId=MsmqJava -DartifactId=MsmqJava -Dversion=1.0 -Dpackaging=jar`_
