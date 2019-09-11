package duke.tasks;

class FormattedDateStub extends FormattedDateTime {

    FormattedDateStub(String s) {
        super("20/10/3999 2345");
    }

    @Override
    public String toString() {
        return "20/10/3999 2345";
    }
}
