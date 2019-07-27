# RecipesBook
This is a project using the latest architecture components and the Paging Library to retrieve some data from this [public api](http://www.recipepuppy.com/).

## View
It's based in a [Activity - Fragment] architecture, also on a RecyclerView and Adapter view based on the data that has to be shown.
The Recipes Adapter implements PagedListAdapter to be able to handle the pagination.

## ViewModel
Recipe ViewModel handling the Repo and the DataSource to be able to obtain the data paginated.
Favourites ViewModel handles only the repo, because the data is not being obtained in pagination, only from Persistence(Room)

## Model
The model is the one who is managing the repo and where the data is coming from. In the case of the Recipe search flow, the data is coming
from the Network in a pagination mode. 
The RecipeDataSource is the heart of the Pagination. The one listening for query changes and making query request to the repo api.
Also listening for the pagination when scrolling the RecyclerView to get more data and managing the NetwrokState for UI purposes (RUNNING,SUCCESS,FAILED).

## Repository
The repo has the ApiService and the Room Service. 
- We get the search data from the ApiService.
- We save data to the Room Service.
- We delete the data to the Room Service.
- We get the saved data from the Room Service.

## Dependency Injection
We use Koin for the dependency injection, it's a very simple library for DI for Kotlin. Very easy to understand and to use.
We have 4 major modules:
- networkModule > Retrofit, OkHttpClient, Gson...
- repoModule > Provides the ApiService and the Room Service to the RepoModule.
- roomModule > Provides the instance of the DataBase and the Dao Interface.
- viewModelModule > Provides the Repository instance to both viewmodels.

## Testing
- For Repo Unit testing I have been using [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) to mock the response from the API.
- The DAO is tested as well.

## Main libraries used
- [Kotlin](https://kotlinlang.org/docs/reference/) :heart:
- [Koin](https://github.com/InsertKoinIO/koin) (DI)
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Architecture)
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging) (Paging)
- [Room](https://developer.android.com/topic/libraries/architecture/room) (Persistence)
- [Retrofit](https://square.github.io/retrofit/) (Network)
- [Coroutines](https://developer.android.com/kotlin/coroutines)

## TODO
- Instrumented Test

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
