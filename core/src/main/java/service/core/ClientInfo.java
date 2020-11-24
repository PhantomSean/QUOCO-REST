package service.core;

public class ClientInfo {
    public static final char MALE               = 'M';
    public static final char FEMALE             = 'F';

    public ClientInfo(String name, char sex, int age, int points, int noClaims, String licenseNumber) {
		this.name = name;
		this.gender = sex;
		this.age = age;
		this.points = points;
		this.noClaims = noClaims;
		this.licenseNumber = licenseNumber;
	}

    public ClientInfo() {
        // Default Contructor
    }

    private String name;
    private char gender;
    private int age;
    private int points;
    private int noClaims;
    private String licenseNumber;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public char getGender() {
        return gender;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setPoint(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
    public void setNoClaims(int noClaims) {
        this.noClaims = noClaims;
    }
    public int getNoClaims() {
        return noClaims;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
}
