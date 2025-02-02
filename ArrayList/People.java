package ArrayList;

//Copy your class from the task2 below. It should create an array of 3 objects of your class and initialize them to new objects.
//Instead of calling their print() methods individually, write a loop that traverses your array to print out each object using the index i.

public class People
{

    private int ageGroup;
    private String gender;
    private String ethnicity;


    public People() {
        ageGroup = 0;
        gender = "";
        ethnicity = "";
    }

    public int getAgeGroup() {
        return this.ageGroup;
    }

    public String getGender() {
        return this.gender;
    }
    public String getEthnicity() {
        return this.ethnicity;
    }

    public People(int initAgeGroup, String initGender, String initEthnicity) {
        ageGroup = initAgeGroup;
        gender = initGender;
        ethnicity = initEthnicity;
    }

    public void print() {
        System.out.println("AgeGroup:  " + this.getAgeGroup());
        System.out.println("Gender: "+ this.getGender());
        System.out.println("Ethnicity: " + this.getEthnicity());
    }

    public void print(String format) {
        if(format == "plain")
            print();
        else if(format == "table") {
            //prints the list objects in table format
            System.out.println("---------------------------------------------");
            System.out.printf("%5s %10s %10s", "AgeGroup", "Gender", "Ethnicity");
            System.out.println();
            System.out.println("---------------------------------------------");
            System.out.format("%7s %10s %10s", this.getAgeGroup(), this.getGender(), this.getEthnicity());
            System.out.println();
            System.out.println("---------------------------------------------");
        }
    }


    public static void main(String[] args) {
        //2. Create an array of 3 objects of your class.
        People[] peoples = new People[3];
        //3.initialize array elements 0-2 to new objects of your class.


        peoples[0] = new People(30, "Male", "Hispanic");
        peoples[1] = new People(40, "Female", "Asian");
        peoples[2] = new People(20, "Female", "Latin");
        //write a for loop that traverses the array and calls
        // the print method of each object in the array using the array index i.
        for(int index=0;index<peoples.length;index++) {
            People people = peoples[index];
            people.print("table");
        }
    }
}
