package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

//@@author KhorSL
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Name} or {@code Tag} matches any of the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<ReadOnlyPerson> {
    private final HashMap<String, List<String>> keywords;

    public PersonContainsKeywordsPredicate(HashMap<String, List<String>> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if a person contain the name predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain name predicate
     */
    private boolean checkPersonContainsNamePredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_NAME.toString()) && !keywords.get(PREFIX_NAME.toString()).isEmpty()) {
            result = keywords.get(PREFIX_NAME.toString()).stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }
        return result;
    }

    /**
     * Checks if a person contain the tag predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain tag predicate
     */
    private boolean checkPersonContainsTagPredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_TAG.toString()) && !keywords.get(PREFIX_TAG.toString()).isEmpty()) {
            result = person.containTags(keywords.get(PREFIX_TAG.toString()));
        }
        return result;
    }

    /**
     * Checks if a person contain the email predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain email predicate
     */
    private boolean checkPersonContainsEmailPredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_EMAIL.toString()) && !keywords.get(PREFIX_EMAIL.toString()).isEmpty()) {
            result = keywords.get(PREFIX_EMAIL.toString()).stream()
                    .anyMatch(keyword -> person.getEmail().value.contains(keyword));
        }
        return result;
    }

    /**
     * Checks if a person contain the phone predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain phone predicate
     */
    private boolean checkPersonContainsPhonePredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_PHONE.toString()) && !keywords.get(PREFIX_PHONE.toString()).isEmpty()) {
            result = keywords.get(PREFIX_PHONE.toString()).stream()
                    .anyMatch(keyword -> person.getPhone().value.contains(keyword));
        }
        return result;
    }

    /**
     * Checks if a person contain the address predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain address predicate
     */
    private boolean checkPersonContainsAddressPredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_ADDRESS.toString()) && !keywords.get(PREFIX_ADDRESS.toString()).isEmpty()) {
            result = keywords.get(PREFIX_ADDRESS.toString()).stream()
                    .anyMatch(keyword -> person.getAddress().value.toLowerCase().contains(keyword.toLowerCase()));
        }
        return result;
    }

    /**
     * Checks if a person contain the comment predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain comment predicate
     */
    private boolean checkPersonContainsCommentPredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_COMMENT.toString()) && !keywords.get(PREFIX_COMMENT.toString()).isEmpty()) {
            result = keywords.get(PREFIX_COMMENT.toString()).stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseAndCharacters(
                            person.getComment().value, keyword));
        }
        return result;
    }

    /**
     * Checks if a person contain the appoint predicate
     *
     * @param person should not be null
     * @return result that indicates if a person contain appoint predicate
     */
    private boolean checkPersonContainsAppointPredicate(ReadOnlyPerson person) {
        boolean result = false;
        if (keywords.containsKey(PREFIX_APPOINT.toString()) && !keywords.get(PREFIX_APPOINT.toString()).isEmpty()) {
            result = keywords.get(PREFIX_APPOINT.toString()).stream()
                    .anyMatch(keyword -> StringUtil.containsDate(person.getAppoint().value, keyword));

            result = result || keywords.get(PREFIX_APPOINT.toString()).stream()
                    .anyMatch(keyword -> StringUtil.containsTime(person.getAppoint().value, keyword));
        }
        return result;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        return checkPersonContainsNamePredicate(person)
                || checkPersonContainsPhonePredicate(person)
                || checkPersonContainsAddressPredicate(person)
                || checkPersonContainsAppointPredicate(person)
                || checkPersonContainsEmailPredicate(person)
                || checkPersonContainsTagPredicate(person)
                || checkPersonContainsCommentPredicate(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((PersonContainsKeywordsPredicate) other).keywords)); // state check
    }

}
//@@author
