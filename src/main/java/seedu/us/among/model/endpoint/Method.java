package seedu.us.among.model.endpoint;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.AppUtil.checkArgument;

/**
 * Represents a Endpoint's Method in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidMethod(String)}
 */
public class Method {

    public static final String MESSAGE_CONSTRAINTS = "Methods should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a
     * blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String methodName;
    // public final MethodType methodType;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid method name.
     */
    public Method(String name) {
        requireNonNull(name);
        checkArgument(isValidMethod(name), MESSAGE_CONSTRAINTS);
        methodName = name;
    }

    /**
     * Returns true if a given string is a valid method.
     */
    public static boolean isValidMethod(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return methodName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Method // instanceof handles nulls
                        && methodName.equals(((Method) other).methodName)); // state check
    }

    @Override
    public int hashCode() {
        return methodName.hashCode();
    }

}
