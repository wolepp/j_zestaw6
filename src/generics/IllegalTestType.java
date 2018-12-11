package generics;

public class IllegalTestType extends Exception {

    private String testType;

    public IllegalTestType(String testType) {
        this.testType = testType;
    }

    public String getTestType() {
        return this.testType;
    }
}
