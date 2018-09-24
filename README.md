# PopularTvShows

## Clean Architecture

For this project I used clean architecture in order to differentiate all the layers and it's functions. This architecture is organized by package with one unique module. Although, it can be organized by modules, but without the use of a dependency injector framework (as Dagger)it would complicate things and generate a lot of boiler plate.

Every layer has different responsibilities:

### Data layer: 


Isolated data access of the other different layers ( domain and presentation) . This layer is the one responsable of getting all the necessary data of different data sources, in this project only via network with REST API calls, but in the future, can be used to persist all the data saving it in a database.

Contains all the models received from the network. Being in charge of calling the needed endpoints, parse de responses and propagate it to the domain layer. 

### Domain layer: 

Layer with all of the business logic. This layer is the one responsible to implement all of the business logic of the application, in this case represented as Use case. A use case is defined as an individual action that an user can do. For example, in this application, getting all the popular tv shows, the detail information of a tv show and the similar tv shows. The use case has to do something with the data, so ,in order to obtain that information, the domain layer needs to be connected with the data layer using an interface (Repository) that represents all the actions that the user can to do. 

### Presentation layer: 

Layer with all of the view logic. It's responsible for showing the different information depending on the user actions. T

MVP (Model View Presenter) pattern is used in this layer in order to organize it and structure it as well as possible.

#### Model: 

Has it’s interactors, which are the blocks corresponding to the domain layer and it’s use cases, and also the Rest API calls defined in the data layer.

#### View: 

Corresponds to the view components. In this case, every view has an activity and it’s own fragment.
The view is responsible of showing the needed information when the user implements some action.

#### Presenter: 

Contains all the view logic of every feature(list and detail). That way, all the logic of the view is not in the same view, it’s decoupled from it in the presenter. Doing this approach allows us to have all the logic in one place (Presenter) so the view only has to take care of painting the current state every moment.



This kind of architecture, separated in different layers, helps us testing independently every one of them. In the future steps, if it's decided to modularize every layer, we can re use it for other projects with the same data needs. For example, one's modularized every layer, if we need to implement other applications accessing to the same data (list of tv shows in this case), we would need to implement only the presentation layer and re use the other ones ( Data & Domain).

## Next steps

- Persist all the data in local (database)
- Organize the layers in different modules with the usage of Dagger as the framework to facilitate the dependency injection.

## Libraries:

 - Android support libraries: Design, ConstraintLayout, CardView and RecyclerView for the Layouts.
 - [Retroffit](https://square.github.io/retrofit/) & [OkHttp](http://square.github.io/okhttp/) for all the network requests.
 - [RxJava2](https://github.com/ReactiveX/RxJava): For the asynchronous requests. It's not necessary to use in this kind of project, but the RxJava operators facilitated the use case needed in the detail. To show all the needed information in the detail, I needed to concatenate two requests, so using RxJava operators facilitate the implementation in the cleanest way possible.
 - [Picasso](http://square.github.io/picasso/): To load all the images asynchronously in the easy way possible.
 - [MockitoKotlin](https://github.com/nhaarman/mockito-kotlin): To facilitate the unit testing in Kotlin.

