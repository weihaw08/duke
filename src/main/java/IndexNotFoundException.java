public class IndexNotFoundException extends Exception {
    private int missingIndex;

    IndexNotFoundException(int missingIndex) {
        this.missingIndex = missingIndex;
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The index " + this.missingIndex + " cannot be found in the list.";
    }
}
