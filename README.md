
Jetpack Part 1:
   - Ensure the project functions as outlined in Assignment 4.
      Retained MVVM, Room, Retrofit and coroutines
   - Replace all fragments and XML layouts in the app with compose functions.
     ListItemScreen.kt   line#39   line#63 
     LoginScreen.kt      line#21   line#91
   - Transition each activity to a ComponentActivity and replace its XML layout with a compose UI.
     MainActivity.kt line#24
   - Additionally, replace the XML NavHost with a compose NavHost, assigning one to each activity.
     MainActivity line#49

WorkManager Part 2 : 
   - refreshes our products as soon as we land on product list screen
     ProductItemsViewModel.kt line#27
   - Configure WorkManager MainActivity  lines 28 - 40
      fetch data every hour - line 33 34
      the first invocation of the worker will be after 1 hour of being scheduled.  - line 37
  
        
       