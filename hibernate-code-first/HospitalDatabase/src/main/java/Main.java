import entities.Diagnose;
import entities.Patient;
import entities.VisitationHistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        //Collecting data
        System.out.print("Patient info (FirstName LastName email insurance(YES/NO) DateOfBirth): ");
        String[] patientInfo = scanner.nextLine().split("\\s+");

        String firstName = patientInfo[0];
        String lastName = patientInfo[1];
        String email = patientInfo[2];
        boolean insurance = patientInfo[3].equalsIgnoreCase("yes");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date dateOfBirth = formatter.parse(patientInfo[4]);

        System.out.print("Enter diagnose: ");
        String diagnoseName = scanner.nextLine();

        System.out.print("Diagnose comment: ");
        String comment = scanner.nextLine();


        System.out.print("Enter medications (ex. Medicament1 Medicament2 etc): ");
        String[] medicationsArray = scanner.nextLine().split("\\s+");

        //Prepare objects for saving
        Diagnose diagnose = new Diagnose(diagnoseName, comment);
        VisitationHistory visitation = new VisitationHistory(new Date(), "No comment");
        Patient patient = new Patient(firstName, lastName, email, dateOfBirth, null, insurance, diagnose);

        //Save new visitation
        HospitalManager hospitalManager = new HospitalManager("hospital");
        System.out.println(hospitalManager.saveVisitation(patient, diagnose, medicationsArray, visitation));
    }
}
