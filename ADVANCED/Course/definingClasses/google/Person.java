package definingClasses.google;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private Company company;
    private List<Pokemon> pokemonList;
    private List<Parent> parents;
    private List<Children> kids;
    private Car car;

    public Person(String name) {
        this.name = name;
        this.pokemonList = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.kids = new ArrayList<>();
    }

    protected void addObject(String[] data) {
        String type = data[1];

        switch (type) {
            case "company":
                Company company = new Person.Company(data[2], data[3], Double.parseDouble(data[4]));
                this.setCompany(company);
                break;
            case "pokemon":
                Pokemon pokemon = new Person.Pokemon(data[2], data[3]);
                this.pokemonList.add(pokemon);
                break;
            case "parents":
                Parent parent = new Person.Parent(data[2], data[3]);
                this.parents.add(parent);
                break;
            case "children":
                Children children = new Person.Children(data[2], data[3]);
                this.kids.add(children);
                break;
            case "car":
                Car car = new Person.Car(data[2], Integer.parseInt(data[3]));
                this.setCar(car);
                break;
        }
    }

    private void setCompany(Company company) {
        this.company = company;
    }

    private void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append(System.lineSeparator());
        builder.append("Company:").append(System.lineSeparator());
        if (company != null) {
            builder.append(String.format("%s %s %.2f",
                            this.company.name, this.company.department, this.company.salary))
                    .append(System.lineSeparator());
        }

        builder.append("Car:").append(System.lineSeparator());
        if (car != null) {
            builder.append(String.format("%s %d", this.car.model, this.car.speed)).append(System.lineSeparator());
        }

        builder.append("Pokemon:").append(System.lineSeparator());
        if (this.pokemonList.size() > 0) {
            for (Pokemon pokemon : this.pokemonList) {
                builder.append(pokemon.name + " " + pokemon.type).append(System.lineSeparator());
            }
        }

        builder.append("Parents:").append(System.lineSeparator());
        if (this.parents.size() > 0) {
            for (Parent parent : this.parents) {
                builder.append(parent.name + " " + parent.birthday).append(System.lineSeparator());
            }
        }

        builder.append("Children:").append(System.lineSeparator());
        if (this.kids.size() > 0) {
            for (Children kid : this.kids) {
                builder.append(kid.name + " " + kid.birthday).append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    private void addPokemon(Pokemon pokemon) {
        this.pokemonList.add(pokemon);
    }

    private void addParent(Parent parent) {
        this.parents.add(parent);
    }

    private void addKid(Children children) {
        this.kids.add(children);
    }

    protected static class Company {
        private String name;
        private String department;
        private double salary;

        public Company(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }

    protected static class Pokemon {
        private String name;
        private String type;

        public Pokemon(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    protected static class Parent {
        private String name;
        private String birthday;

        public Parent(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }
    }

    protected static class Children {
        private String name;
        private String birthday;

        public Children(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }
    }

    protected static class Car {
        private String model;
        private int speed;

        public Car(String model, int speed) {
            this.model = model;
            this.speed = speed;
        }
    }

}
