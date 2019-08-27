package duke.tasks;

import duke.exception.InvalidTimeAndDateException;

import java.text.ParseException;

class FormattedDateStub extends FormattedDate {

    FormattedDateStub(String s) throws ParseException, InvalidTimeAndDateException {
        super("20/10/3999 2345");
    }

    @Override
    public String toString() {
        return "20/10/3999 2345";
    }
}
