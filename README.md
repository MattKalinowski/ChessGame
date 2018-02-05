# Chess game

The following project is a classic chess game. The game is designed for two players matches, AI is not included. 

## Gameplay

The whole action of the game takes place in an IDE console. 
All pieces are represented by their respective initials including the first letter of a team name and first letter of a piece name. For example the White Queen would be WQ.

Right after starting the game, the following will appear: 

![1](https://user-images.githubusercontent.com/30430556/35797915-856c9c8a-0a61-11e8-8de9-450debf789d1.png)

Moves can be performed by using commands "xy/xy". For example if you'd like to move your pawn from e2 to e4, you should use command e2/e4.

![2](https://user-images.githubusercontent.com/30430556/35797948-9c468646-0a61-11e8-82c7-68c81edc878e.png)

During the White Team turn, moves of the black team are blocked and vice versa.

![3](https://user-images.githubusercontent.com/30430556/35798000-cc9d9e1a-0a61-11e8-96d6-affce7a638c4.png)

Each piece has its own unque algorithm calculating possible movement, based on real chess rules.

![4](https://user-images.githubusercontent.com/30430556/35798018-dbd4ea46-0a61-11e8-9b08-0234cefb8708.png)

Some algoithms are shared between multiple types of pieces. For instance: checking whether a move that a player is trying to attempt is legal in a sense of permeability of a path between two coordinates.

![5](https://user-images.githubusercontent.com/30430556/35798030-e2f9a94c-0a61-11e8-8e43-5537250624d2.png)

The game will keep going untill one player wins.

![6](https://user-images.githubusercontent.com/30430556/35798041-e990a828-0a61-11e8-8c31-7a30ed716008.png)

The application is equipped in a special CheckEngine. It's responisble for determining a moment when king is in check. When king is in check, it's impossible to make any move that will break the check.

![7](https://user-images.githubusercontent.com/30430556/35798050-f015c412-0a61-11e8-9028-65eab85ef19e.png)

Another important feature on the game is MateEngine - an algorithm that predicts a move forward to determine whether it's check or checkmate.

![8](https://user-images.githubusercontent.com/30430556/35798071-fe072cbe-0a61-11e8-85a3-ca070db62cf7.png)

Have fun!


## Built With

* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Maven](https://maven.apache.org/)
* [JUnit](http://junit.org/junit5/)

## Author

* **Mateusz Kalinowski** - [Linkedin Account](https://www.linkedin.com/in/mateusz-kalinowski-ba1544ba/)
