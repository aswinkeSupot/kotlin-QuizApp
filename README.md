## 1. Add Retrofit dependency in app level build.gradle.kts
Reference URL - https://square.github.io/retrofit/  (Go to download option)
  - For getting the latest version go to github for retrofit - https://github.com/square/retrofit
```
dependencies {
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
}
```

## 2. Add Converter dependency in app level build.gradle.kts
Reference URL - https://square.github.io/retrofit/
  - also add the save version on retrofitVersion
```
dependencies {
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
}
```

## 3. Add Kotlin coroutines dependency in app level build.gradle.kts
Reference URL - https://developer.android.com/kotlin/coroutines
```
dependencies {
    // Add Coroutines dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}
```

## 4. Add LifeCycle dependency in app level build.gradle.kts (ViewModel Dependency, LiveData Dependency)
Reference URL -  https://developer.android.com/jetpack/androidx/releases/lifecycle
```
dependencies {
    // LifeCycle
    val lifecycle_version = "2.8.5"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
}
```

## 5. Add plugin id kotlin kapt in app level build.gradle.kts
```
plugins {
    id("kotlin-kapt")
}
```

## 6. Add dependency for Annotation processor in app level build.gradle.kts
Reference URL -  https://developer.android.com/jetpack/androidx/releases/lifecycle
```
dependencies {
    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
}
```

## 7. Logging Interceptor (okhttp library) dependency in app level build.gradle.kts
Reference URL - https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
```
dependencies {
    /** Logging Interceptor **/
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}
```

## 8. Add Permissions in AndroidManifest.xml
```
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
```

## 9. Enable DataBinding (in app level build.gradle.kts)
```
android {
     buildFeatures{
        dataBinding = true
    }
}
```

## - **SERVER SIDE**
## 1. Installing XAMP (Server)
URL - https://www.apachefriends.org/
- install after downloading the XAMP
- Note While installing should tick select components - 1. MySQL , 2. Perl, 3. phpMyAdmin, 4. Webalizer, 5. Fake Sendmail

## 2. After installation open XAMP and Start Apache and MySQL.
   - call the url in any browser in the computer for check the XAMP is working fine - http://localhost/dashboard/
```
if there is any error while starting Apache** go to Config(near to Apache) -> Apache(httpd.conf) ->
Look for the line that says Listen 80 and change it to Listen 8080 (or any available port). ->
Also, change the ServerName line ServerName localhost:8080 ->
Save the file and close it. -> Go back to the XAMPP Control Panel, and restart Apache.
Now access Apache using the new port, e.g., http://localhost:8080/dashboard/
or go to terminal -> ipconfig ->
                 Wireless LAN adapter Wi-Fi 3:
                 IPv4 Address. . . . . . . . . . . : 10.0.0.29 ->
                 
                 http://10.0.0.29:8080/dashboard/

In Macbook we can get the ip Address 
 go to Terminal -> ipconfig getifaddr en0 -> we will get the ipaddress
    192.168.75.16
    
    http://192.168.75.16:8080/dashboard
```

## 3. Create MYSQL Database
```
Open  http://localhost:8080/dashboard/   OR  
In windows http://10.0.0.29:8080/dashboard/
In Mac OS  http://192.168.75.16:8080/dashboard/ 
```

```
phpMyAdmin ->
New -> Create database
    DB Name      - 'quiz_db' -> Create
    Table name   - 'math_quiz'  (Number of columns - 7)
    Column Names -  1.  Name:id                 Type:INT      Length/Values:50   A_I(Auto Increment):Tick
                    2.  Name:question           Type:VARCHAR  Length/Values:200
                    3.  Name:option1            Type:VARCHAR  Length/Values:200
                    4.  Name:option2            Type:VARCHAR  Length/Values:200
                    5.  Name:option3            Type:VARCHAR  Length/Values:200
                    6.  Name:option4            Type:VARCHAR  Length/Values:200
                    3.  Name:correct_option     Type:VARCHAR  Length/Values:200   ->
                    SAVE
    For creating a question click on Insert ->  Enter Data -> Go
```

## 4. Create Api in PHP
- Go to the folder 'xampp' -> 'htdocs' -> Inside create a new folder "quiz"
- We can access the quiz folder by calling http://localhost:8080/quiz/
- Inside 'quiz' folder create a new Text Document "questionsapi" -> Save the questionsapi as '.php' ("questionsapi.php") 
## questionsapi.php
```
<?php
    $conn = mysqli_connect("localhost","root","","quiz_db");
    $stmt = $conn->prepare("SELECT `question`, `option1`,`option2`,`option3`,`option4`,`correct_option` FROM `math_quiz`");

	// executing the query
	$stmt->execute();

	// Binding the results to the query
	$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

	// Creating an empty array
	$questions_array = array();

	// Traversing through all the 
	while($stmt->fetch()){
		$temp = array();
		$temp['question'] = $question;
		$temp['option1'] = $option1;
		$temp['option2'] = $option2;
		$temp['option3'] = $option3;
		$temp['option4'] = $option4;
		$temp['correct_option'] = $correct_option;
		array_push($questions_array, $temp);
	}

	// Displaying the results in JSON format
	echo json_encode($questions_array);
?>
```

## - **CREATE with MVVM**
## 1. Create Model Class
   - for creating model go to File -> Setting -> plugins -> JSON to Kotlin Class
   - Create package 'model'
   - Rt-Clk 'model' -> New -> Kotlin data class File form JSON -> Copy the JSON string and give a class Name 'QuestionsList' -> Generate
   - This will create 'QuestionsList.kt' and automatically create 'QuestionsListItem.kt'  (Rename 'QuestionsListItem' to 'Question')

## 2. Retrofit API Interface
   - Create package 'retrofit'
   - Create new interface 'QuestionsAPI'
   - Create a new kotlin/class 'RetrofitInstance'  in retrofit package

## 3. Questions Repository
   - Create package 'repository'
   - Create new kotlin/class 'QuizRepository'

## 4. View Model
   - Create package 'viewmodel'
   - Create new kotlin/class 'QuizViewModel'

## 5. Creating MainActivity
   - Create package 'view'
   - move 'MainActivity' to the view package

## 6. Network Security Rules 
Error - (If we are getting the below error like not permitted by network security policy)
```
FATAL EXCEPTION: DefaultDispatcher-worker-1
Process: com.example.quizapp, PID: 10962
java.net.UnknownServiceException: CLEARTEXT communication to 192.168.75.16 not permitted by network security policy
```
We need to add Network Security Rules while connecting with local host.
```
if there is no 'xml' folder in 'res' directory 
res -> Rt Clk -> New -> Android Resource Directory -> 
        Directory name: xml
        Select Resource type : xml  -> Ok

else 
xml(directory) -> new -> XML Resource File -> File Name: 'network_security' -> OK
```
**network_security.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted = "true">
        <trust-anchors>
            <certificates src ="system"/>
        </trust-anchors>
    </base-config>
</network-security-config>
```

Open AndroidManifest.xml and add the below inside application.
```
<application
        android:networkSecurityConfig="@xml/network_security"
        android:usesCleartextTraffic="true"
</application>
```

## 7. Create ResultActivity


































