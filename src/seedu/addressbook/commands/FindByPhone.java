package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class FindByPhone extends Command
{
    public static final String COMMAND_WORD = "find by phone number";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Finds all persons whose phone number is" + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"+ "Parameters: KEYWORD [MORE_KEYWORDS]...\n"+ "Example" + COMMAND_WORD + "alice";

    private final Set<String> keywords;

    public FindByPhone(Set<String> keywords)
    {
        this.keywords = keywords;
    }

    public Set<String> getKeywords()
    {
        return new HashSet<>(keywords);
    }

    public CommandResult execute()
    {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords)
    {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> PhoneNumber = new HashSet<>(person.getPhone().getPhone());
            if (!Collections.disjoint(PhoneNumber, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
