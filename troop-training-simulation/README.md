# Problem Statement: (Troop training simulation is inspired from Clash of Clans games.)

As a gaming programmer you have to create a simulation software using which gamers can train army troops.

Definition and details of the simulation is as follows

## Army Troops:

There are two kinds of troops Archers and Barbarians. Each trooper(archer/barbarian)

-----------------------------------------------------------------------
| Trooper  | Training Time (seconds) |  Training Cost (magic potions) |
-----------------------------------------------------------------------
| Barbarian(weapon: sword) | 3 | 10 |
-------------------------------------
| Archer(weapon: Bow and Arrow) |  6  | 20 |
--------------------------------------------

Barracks: where each trooper gets trained.

Only one trooper can be trained at a given point of time.

The maximum seat capacity of a barracks is 10.

Others have to wait to outside the barracks

Barracks can have mix of troopers: e.g.: 5 Archers and 5 Barbarians or 4 Archers and 6 Barbarians or 10 Archers or 10 Barbarians

Army Camp: where troops assemble after training.

Scenario 1: Simulate the training and train barbarians and/or archers.

As a gamer

1. i should be able to input no.of barbarians and/or archers i would like to train.

2. i should be able to see how many troops are trained and available in troop camp after training completes.

Example input/output:

troop-training > start-training

1. train troops.

2. view troop camp

What do you want to do ? 1

1. Archer

2. Barbarian

which troop u want to train ? 1

how many archers you want to train ? 10

training...

training complete. troops are available in the troop camp.

1. train troops.

2. view troop camp

What do you want to do ? 2

Archers: 10

Barbarian: 0

1. train troops.

2. view troop camp

What do you want to do ? 1

Expectations:

1. You are required to help create the software that works according to the sample inputs applied and generates output as shown in

the Input-Output section shown for the problem.

2. You should demonstrate the working software by building a console application or writing test program that exercises the sample

inputs, for this purpose you may use jUnit or any other testing framework.

3. Maturity of your solution will be judged on your object oriented analysis and design skills. Your coding skills will also be judged.

4. Please note that the problems below do not require any relational database or knowledge of underlying OS or Windowing platform.

5. There can be future stories which can be asked to play later.
