# quotesapplication
quotes showing application with firebase storage
   
   # Proper application


# Application Scenario 

     android application thats fetching images from firebase and show in recycler view and this is proper application with 
     beautifull bottom navigation bar in which we have three items All,Menu and setting 
     and in Menu application have 8 more fragments and each fragment have more than 7 pictures with different catrgory 
     and these all images off app is fetching from firebase
     
# Dependencies

    api 'com.github.ittianyu:BottomNavigationViewEx:2.0.2'
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.google.firebase:firebase-core:16.0.1'
    implementation 'com.google.firebase:firebase-database:16.0.1'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.google.firebase:firebase-storage:16.0.1'
    implementation 'com.google.firebase:firebase-auth:16.0.1'
    
 # Security Permissions
 
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    
   # FirebaseContent
    
    Realtime Database Rules
    
        "rules": {
    ".read": true,
    ".write": true
  }
  
    Storage Rule
    
     match /b/{bucket}/o {
     match /{allPaths=**} {
      allow read, write: if true;
       }
  
