# Cards Against a Manatee
Manatees (family Trichechidae, genus Trichechus) are large, fully aquatic, mostly herbivorous marine mammals sometimes known as sea cows ([Wikipedia](https://en.wikipedia.org/wiki/Manatee)). _Cards Against a Manatee_ is a digital card game, which has absolutely nothing to do with said mammal. It's heavily inspired by (but not associated with) the similarly named game [Cards Against Humanity](https://www.cardsagainsthumanity.com/). Credit for the core game idea and all the playing cards used in this project goes to Cards Against Humanity.

## Usage
### Initial Database Setup
- `docker-compose up -d database`
- `docker container exec -it cards-against-a-manatee_database_1 bash`
- `mysql -h localhost -u root -p < /mnt/host/cards_against_a_manatee.sql`
- Enter the MySQL root password (currently hardcoded to `RosewoodSilverSpoon`)
- `exit`
- `exit`
- `docker-compose down`

### Running the Database for Development
After the initial database setup, run `docker-compose up -d database`.

### Building the Application
- Make sure you have Maven, npm, Docker and docker-compose installed on your system
- Make sure you have completed the "Initial Database Setup" step
- Run `./build.sh`

### Running the Application
After building the application, run `docker-compose up -d`.

## Current Project State
This project is currently under construction. Release v0.1.0 marks the first playable prototype. However, most components will undergo signigicant changes before a version v1.0.0 will get released.
