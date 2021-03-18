# WordList
- It is a sample word list app to practice App Architechture using Repository Pattern.

## It makes use of following:-
- **LiveData** for updating and listening to realtime datachanges in app. Also livedata can survive configuration changes.
- **ViewModel** with **ViewModelFactory** to acess livedata
- **Repository** as **single source of truth** to make Database operations
- **Coroutines** to perform databse operations in background thread without blocking main thread.
- Various Coroutine Context based on type of operation: e.g Dispatchers.Main for updaing UI in main htread and Dispatchers.IO to work in background thread.
- Also, database dao methods are defined suspend fun methods so that they run in background thread. This word is done internally by Room library automatically.
- Also, LiveData in ViewModel are update in viewModelScope, a Coroutine scope provided for livedata.

<div>
<img src="https://developer.android.com/codelabs/android-training-livedata-viewmodel/img/fd28069527c8d615.png">
</div>
