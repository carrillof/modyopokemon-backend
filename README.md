# Modyo Pokemon Backend

## - [API Features](#api-features)
## - [API Endpoints](#api-endpoints)
## - [Deployment Process](#deployment-process)

---

# API Features

- ### Basic Authentication
- ### Unit and Integration Testing
- ### Error Handling
- ### Cache

# API Endpoints

## GET /pokemon?offset={offset}&limit={limit}

### Description
Get paginated pokemon list based on an specific offset and limit.

### Authentication
Requires a valid user and password passed as a basic authentication haeder.

### Parameters
- **offset** (integer, required): Starting result number of current page.
- **limit** (integer, required): Number of elements per page.

### Response
- **200 OK**: Returns pokemon list.
  ```json
  {
    "count": 1302,
    "next": "http://localhost:8080/pokemon?offset=2&limit=2",
    "previous": null,
    "results": [
        {
            "id": 1,
            "weight": 69,
            "name": "bulbasaur",
            "types": [
                "grass",
                "poison"
            ],
            "abilities": [
                "overgrow",
                "chlorophyll"
            ],
            "sprite": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            "specie": "http://localhost:8080/pokemon-species/1"
        },
        {
            "id": 2,
            "weight": 130,
            "name": "ivysaur",
            "types": [
                "grass",
                "poison"
            ],
            "abilities": [
                "overgrow",
                "chlorophyll"
            ],
            "sprite": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
            "specie": "http://localhost:8080/pokemon-species/2"
        }
    ]
  }

## GET /pokemon-species/{specieId}

### Description
Get pokemon description and evolution chain based on its specie identifier.

### Authentication
Requires a valid user and password passed as a basic authentication haeder.

### Parameters
- **specieId** (integer, required): Specie identifier of the pokemon.

### Response
- **200 OK**: Returns pokemon list.
  ```json
  {
    "description": "A strange seed was\nplanted on its\nback at birth.\fThe plant sprouts\nand grows with\nthis POKéMON.",
    "evolutionChain": {
        "name": "bulbasaur",
        "evolvesTo": [
            {
                "name": "ivysaur",
                "evolvesTo": [
                    {
                        "name": "venusaur",
                        "evolvesTo": []
                    }
                ]
            }
        ]
    }
  }
  
---

# Deployment Process

The API was deployed used heroku.

## Step 1 - Create new heroku app
![Step1](/assets/images/step1.png)
![Step1-2](/assets/images/step1-2.png)
## Step 2 - Connect created app to api github repository
![Step2](/assets/images/step2.png)
![Step2-1](/assets/images/step2-1.png)
## Step 3 - Manual deploy on desired repository branch
![Step3](/assets/images/step3.png)
![Step3-1](/assets/images/step3-1.png)