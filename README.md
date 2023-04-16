# ShippingProblem

This is a solution to a shipment discount calculation problem.
The task is to calculate discounts for packages shipped via two providers - Mondial Relay (MR) and La Poste (LP),
which have different prices for each package size. The package sizes are S - Small, M - Medium, L - Large.
The package prices for each size and provider are provided in the problem statement. The discount rules are the following:

    1. All S shipments should always match the lowest S package price among the providers.
    2. The third L shipment via LP should be free, but only once a calendar month.
    3. Accumulated discounts cannot exceed 10 € in a calendar month. If there are not enough funds to fully cover a discount this calendar month, it should be covered partially.

The solution is implemented in Java,
it was built to be maintainable and easily modifiable using OOP, SOLID, Unit Testing principles.

# Getting started
## Prerequisites

- Java 17 or higher

Optional:

- Maven - to run tests

## Running the project
1. Download ShippingProblem-1.0-BUILD.jar from https://github.com/DovydasLatkauskas/ShippingProblem
2. In the same folder create an "input/" directory with as many .txt files as you want
3. open console in the directory containing the .jar file and run: "$java -jar ShippingProblem-1.0-BUILD.jar"

![image](https://user-images.githubusercontent.com/77624813/232336727-281712db-125b-4111-a56e-45a0f604c27b.png)


![image](https://user-images.githubusercontent.com/77624813/232336912-dfdc0436-bb03-45d4-b515-2e4c74ca2d4b.png)

Running the project will show the applied discounts in the console and create a file with the outputs in "outputs/"


## Running Unit Tests
Requires maven

1. Clone the repository from GitHub https://github.com/DovydasLatkauskas/ShippingProblem
2. Open console in the root folder of the project
3. Run "$mvn clean package" to build the project
4. Run "$mvn test" to run the unit tests

![image](https://user-images.githubusercontent.com/77624813/232336744-27b534d7-e9ee-4bab-bb23-38a7f1bda8ba.png)
![image](https://user-images.githubusercontent.com/77624813/232336760-4482fb9f-d18e-462b-9de8-30a9aebf3d09.png)


# Modifying the project
It is easy to modify existing rules and add new ones.

To add a new discount rule:
Add it in DiscountRule.java, append it to discountList in getDiscountRuleList()
If there are any additional non-monetary monthly restrictions for these discounts add them to MonthlyRestrictions

To change the maximum discount amount per month change it in Constants.java
Same for input and output folder paths

For new postal services just add them to the enum in DeliveryMethod.java
To add new PackageSizes go to PackageSize.java and define their delivery prices in DeliveryMethod.java

# Design choices
- Postal services and package sizes are represented as enums, thus it is trivial to add new ones

- For easy addition of new rules, discount rules are represented as instances of the abstract DiscountRule class and are all run as long as they are added to getDiscountRuleList()

- Maximum monthly discounts, input and output paths are defined in Constants.java for easy modification

- main in Main.java only calls runApplication in Runner.java, so that we can use runApplication with different input and output paths for Unit Testing

# Project structure
![image](https://user-images.githubusercontent.com/77624813/232336638-7707e7cd-e3c7-43f9-8361-284bb873f80d.png)
