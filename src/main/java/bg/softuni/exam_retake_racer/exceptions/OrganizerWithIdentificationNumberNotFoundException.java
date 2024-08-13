package bg.softuni.exam_retake_racer.exceptions;

public class OrganizerWithIdentificationNumberNotFoundException extends RuntimeException {
    public OrganizerWithIdentificationNumberNotFoundException(String identificationNumber) {
        super("Organizer with identification number " + identificationNumber + " was not found");
    }
}
