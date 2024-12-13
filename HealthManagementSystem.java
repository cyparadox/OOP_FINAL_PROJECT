import java.util.*;


class Patient {
    private String name;
    private int age;
    private String ailment;
    private List<Appointment> appointmentHistory = new ArrayList<>();
    private Map<Appointment, String> appointmentHistoryWithStatus = new HashMap<>();

    public Patient(String name, int age, String ailment) {
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAilment() {
        return ailment;
    }

    public List<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public Map<Appointment, String> getAppointmentHistoryWithStatus() {
        return appointmentHistoryWithStatus;
    }

    public void addAppointmentToHistory(Appointment appointment) {
        appointmentHistory.add(appointment);
    }

    public void addAppointmentToHistoryWithStatus(Appointment appointment, String status) {
        appointmentHistoryWithStatus.put(appointment, status);
    }

    @Override
    public String toString() {
        return "Patient Name: " + name + ", Age: " + age + ", Ailment: " + ailment;
    }
}

class Doctor {
    private String name;
    private String specialization;
    private List<String> availableDates = new ArrayList<>();
    private Map<String, Boolean> availability = new HashMap<>();

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<String> getAvailableDates() {
        return availableDates;
    }

    public Map<String, Boolean> getAvailability() {
        return availability;
    }

    public void setAvailableDates(List<String> availableDates) {
        this.availableDates = availableDates;
    }

    public void setAvailability(String date, boolean isAvailable) {
        availability.put(date, isAvailable);
    }

    public boolean isAvailable(String date) {
        return availability.getOrDefault(date, true);
    }

    @Override
    public String toString() {
        return "Doctor Name: " + name + ", Specialization: " + specialization;
    }
}

// Class representing an Appointment
class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;
    private boolean isRecurring;

    public Appointment(Patient patient, Doctor doctor, String date, boolean isRecurring) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.isRecurring = isRecurring;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    @Override
    public String toString() {
        return "Appointment Details:\n" + patient + "\n" + doctor + "\nDate: " + date;
    }
}

public class HealthManagementSystem {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        initializeDoctors();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Healthcare Management System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View Doctors");
            System.out.println("2. Book an Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Cancel an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewDoctors();
                    break;
                case 2:
                    bookAppointment(scanner);
                    break;
                case 3:
                    viewAppointments();
                    break;
                case 4:
                    cancelAppointment(scanner);
                    break;
                case 5:
                    rescheduleAppointment(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the program. Stay healthy!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize a list of doctors
    private static void initializeDoctors() {
        doctors.add(new Doctor("Dr. Alice Smith", "Pediatrics"));
        doctors.add(new Doctor("Dr. Bob Johnson", "Cardiology"));
        doctors.add(new Doctor("Dr. Carol Davis", "Neurology"));
        doctors.add(new Doctor("Dr. Lucas Bennett", "Orthopedic Surgery"));
        doctors.add(new Doctor("Dr. Elijah Khan", "Psychiatry"));
        doctors.add(new Doctor("Dr. Mason Carter", "General Medicine"));
        doctors.add(new Doctor("Dr. William Adams", "Pulmonology"));
        doctors.add(new Doctor("Dr. Lily Morgan", "Infectious Disease"));
        doctors.add(new Doctor("Dr. Alexander Lee", "Urology"));
        doctors.add(new Doctor("Dr. Charlotte Brooks", "Obstetrics and Gynecology"));
    }

    
    private static void viewDoctors() {
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
    }

    private static void bookAppointment(Scanner scanner) {
        System.out.println("\nEnter patient details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Ailment: ");
        String ailment = scanner.nextLine();

        Patient patient = new Patient(name, age, ailment);

  
        System.out.print("Enter the appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        if (isAppointmentConflict(patient, date)) {
            System.out.println("\nError: This patient already has an appointment on the selected date.");
            return;
        }

        System.out.print("Is this a recurring appointment? (yes/no): ");
        boolean isRecurring = scanner.nextLine().trim().equalsIgnoreCase("yes");

        Doctor autoAssignedDoctor = assignDoctorBasedOnAilment(ailment);
        if (autoAssignedDoctor != null) {
            System.out.println("\nAutomatically assigned doctor: " + autoAssignedDoctor);
            System.out.print("Do you want to choose a different doctor? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("no")) {
                if (autoAssignedDoctor.isAvailable(date)) {
                    Appointment appointment = new Appointment(patient, autoAssignedDoctor, date, isRecurring);
                    appointments.add(appointment);
                    autoAssignedDoctor.setAvailability(date, false);
                    patient.addAppointmentToHistoryWithStatus(appointment, "Scheduled");

                    System.out.println("\nAppointment booked successfully!");
                    System.out.println(appointment);
                } else {
                    System.out.println("The selected doctor is not available on the chosen date.");
                }
                return;
            }
        }

        System.out.println("\nSelect a doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }

        System.out.print("Enter the number of the doctor: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine(); 

        if (doctorChoice > 0 && doctorChoice <= doctors.size()) {
            Doctor doctor = doctors.get(doctorChoice - 1);
            if (doctor.isAvailable(date)) {
                Appointment appointment = new Appointment(patient, doctor, date, isRecurring);
                appointments.add(appointment);
                doctor.setAvailability(date, false);
                patient.addAppointmentToHistoryWithStatus(appointment, "Scheduled");

                System.out.println("\nAppointment booked successfully!");
                System.out.println(appointment);
            } else {
                System.out.println("The selected doctor is not available on the chosen date.");
            }
        } else {
            System.out.println("Invalid doctor selection. Appointment not booked.");
        }
    }

    private static boolean isAppointmentConflict(Patient patient, String date) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equals(patient.getName()) &&
                appointment.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    private static Doctor assignDoctorBasedOnAilment(String ailment) {
        for (Doctor doctor : doctors) {
            if (ailment.toLowerCase().contains(doctor.getSpecialization().toLowerCase())) {
                return doctor;
            }
        }
        return null;
    }

    private static void viewAppointments() {
        System.out.println("\nScheduled Appointments:");
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
                System.out.println();
            }
        }
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter the patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Appointment appointmentToCancel = null;
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equals(name) && appointment.getDate().equals(date)) {
                appointmentToCancel = appointment;
                break;
            }
        }

        if (appointmentToCancel != null) {
            appointments.remove(appointmentToCancel);
            appointmentToCancel.getPatient().addAppointmentToHistoryWithStatus(appointmentToCancel, "Cancelled");
            System.out.println("Appointment cancelled successfully.");
        } else {
            System.out.println("No appointment found for the given details.");
        }
    }

    
    private static void rescheduleAppointment(Scanner scanner) {
        System.out.print("Enter the patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the current appointment date (YYYY-MM-DD): ");
        String currentDate = scanner.nextLine();
        System.out.print("Enter the new appointment date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();

        Appointment appointmentToReschedule = null;
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equals(name) && appointment.getDate().equals(currentDate)) {
                appointmentToReschedule = appointment;
                break;
            }
        }

        if (appointmentToReschedule != null) {
            appointmentToReschedule.setDate(newDate);
            appointmentToReschedule.getPatient().addAppointmentToHistoryWithStatus(appointmentToReschedule, "Rescheduled");
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("No appointment found for the given details.");
        }
    }
}
