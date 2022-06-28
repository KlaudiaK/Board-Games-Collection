
<h1 align="center">Board Games Collector</h1>
<p align="center">
  <img width="20%" height="20%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/board-game.png">
</p>

<p align="center">
Android App with Retrofit, Room, Hilt, MVVM and Clean Architecture concepts integrated with BGG Api.  <br> <br>
The app allows to keep track of owned board games and other goodies from <a href="https://boardgamegeek.com/" target="_blank">BoardGameGeek</a>.

</p>


## Tech stack & Open-source libraries
- Mimimum SDK level 21
- <a href="https://kotlinlang.org/" target="_blank">Kotlin</a> based, <a href="https://github.com/Kotlin/kotlinx.coroutines" target="_blank">Coroutines</a> + <a href="https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/" target="_blank">Flow</a> for asynchronous.
- <a href="https://developer.android.com/jetpack/compose" target="_blank">Jetpack Compose</a>
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
- **MVVM Architecture**   (View - DataBinding - ViewModel - Model)
- <a href="https://dagger.dev/hilt/" target="_blank">Hilt</a> - dependency injection
- <a href="https://github.com/square/retrofit" target="_blank">Retrofit2 & OkHttp3</a> - construct the REST APIs
- <a href="https://coil-kt.github.io/coil/compose/" target="_blank">Coil</a> - image loading



<p align="center">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/1.png">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/2.png">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/3.png">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/4.png">
</p>
<p align="center">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/5.png">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/6.png">
  <img width="15%" height="15%" src="https://github.com/KlaudiaK/Board-Games-Collection/blob/master/images/7.png">
</p>
