# GM Screen Backend

#### Created By Ben Ghalami

This is the back end API with the ability to keep track of tapletop game GM's, Campaigns, Characters, Encounters, Monsters, and Treasures

You can use the app in production on [Heroku](https://gm-screen-backend.herokuapp.com/).

## Schema

![](https://imgur.com/4jdWUpN.jpg)

## Endpoints

```
{
GMs:
"GET": "/api/v1/game_masters"
	- Show all GM’s
"POST": "/api/v1/game_masters"
	- Create a GM, takes “name”
"GET": "/api/v1/game_masters/:id"
	- Show GM id, name
"PUT": "/api/v1/game_masters/:id"
	- Changes GM info, takes “name”
"DELETE": "/api/v1/game_masters/:id"
	- Delete GM based on id

CAMPAIGNS:
"GET": "/api/v1/game_masters/:id/campaigns"
	- Shows all campaigns with name and ID
"POST": "/api/v1/game_masters/:id/campaigns"
	- Creates campaign, takes “title”
"GET": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id"
	- Shows campaign with all encounters with title and ID 
"PUT": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id"
	- Edits campaign, takes “title”
"DELETE": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id"
	- Deletes campaign with id

ENCOUNTERS:
"GET": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters"
	- Shows campaign and campaign encounters with id and title
"POST": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters"
	- Creates encounter for specific campaign, takes “title”
"GET": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id"
	- Shows encounter with id, campaign_id, and title, shows characters in encounter with name and id and treasures, shows monsters in encounter with name and id and locked treasures
"PUT": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id"
	- Edits an encounter, takes “title”
"DELETE": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id"
	- Deletes an encounter

"POST": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id/characters/:id"
	- Links a character to an encounter

"GET":	"/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id/monsters"
	- Shows encounter with id and name, shows monsters in encounter with name and id, on each monster you see their treasure(s)
"POST": "/api/v1/game_masters/:gm_id/campaigns/:campaign_id/encounters/:encounter_id/monsters/:monster_id"
	- Creates a monster on an encounter

CHARACTERS:
"GET": "/api/v1/characters/play_code/:code"
	- Looks for player based on code, returns player

"GET": "/api/v1/characters"
	- Shows all characters with name, id, and treasures
"POST": "/api/v1/characters"
	- Creates new, empty character
	
"PUT": "/api/v1/characters/:id"
	- Edits character (character creation page), takes name and role
"GET": "/api/v1/characters/:id"
	- Shows id, name, and treasures for one character
"POST": "/api/v1/characters/:id/treasures"
	- Creates a treasure to be associated with that character
"PUT": "/api/v1/characters/:id/treasures/:treasure_id"
	- Edits a treasure associated with that character
"DELETE": "/api/v1/characters/:id/treasures/:treasure_id"
	- Deletes a treasure associated with that character

MONSTERS:
"GET": "/api/v1/monsters"
	- Shows all monsters with title, id, and encounters_id
"POST": "/api/v1/monsters"
	- Creates new monster, takes title
"PUT": "/api/v1/monsters/:id"
	- Edits monster, takes title

"GET": "/api/v1/monsters/:id"
	- Shows id, name, and locked_treasures for one monster
"POST": "/api/v1/monsters/:id/locked_treasures"
	- Creates a locked treasure to be associated with that monster, takes title
"PUT": "/api/v1/monsters/:id/locked_treasures"
	- Edits a locked treasure associated with that monster, takes title
"DELETE": "/api/v1/game_masters/:gm_id/campaigns/:c_id/encounters/:e_id/monsters/:m_id/treasures/:lt_id"
}
```


## Contributing

If you would like to contribute, you can follow the steps in the next two sections to get running on your machine.

## Initial Setup

1. Clone the repository and rename the repository to anything you'd like in one command:
  ```shell
  git clone git@github.com:bghalami/gm_screen_backend.git
  ```
2. Change into the new director directory.

3. Install the dependencies:

  ```shell
  mvn clean install
  ```
  

## Running the Server Locally

To see your code in action locally, you need to fire up a development server. Use the command:

```shell
mvn spring-boot:run
```

Once the server is running, visit in your browser:

* `http://localhost:8080/` to run your application.


## Built With

* [Java Developer Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/)
* [Spring Boot](http://spring.io/projects/spring-boot)
* [Apache Libraries](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/RandomStringUtils.html)
* [PostgreSQL](https://www.postgresql.org/)
