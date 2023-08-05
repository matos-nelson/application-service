package org.rent.circle.application.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.AdditionalIncomeSourceDto;
import org.rent.circle.application.api.dto.ApplicantDto;
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.CoApplicantDto;
import org.rent.circle.application.api.dto.EmergencyContactDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.OccupantDto;
import org.rent.circle.application.api.dto.PersonalReferenceDto;
import org.rent.circle.application.api.dto.PetDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.VehicleDto;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.enums.Suffix;
import org.rent.circle.application.api.persistence.model.AdditionalIncomeSource;
import org.rent.circle.application.api.persistence.model.Applicant;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.model.CoApplicant;
import org.rent.circle.application.api.persistence.model.EmergencyContact;
import org.rent.circle.application.api.persistence.model.Employer;
import org.rent.circle.application.api.persistence.model.Identification;
import org.rent.circle.application.api.persistence.model.Occupant;
import org.rent.circle.application.api.persistence.model.PersonalReference;
import org.rent.circle.application.api.persistence.model.Pet;
import org.rent.circle.application.api.persistence.model.ResidentialHistory;
import org.rent.circle.application.api.persistence.model.Vehicle;

@QuarkusTest
public class ApplicationMapperTest {

    @Inject
    ApplicationMapper applicationMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Application result = applicationMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveApplicationDto_ShouldMap() {
        // Arrange
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .propertyId(1L)
            .managerId(2L)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertEquals(saveApplicationDto.getManagerId(), result.getManagerId());
        assertEquals(saveApplicationDto.getPropertyId(), result.getPropertyId());
    }

    @Test
    public void toModel_WhenGivenASaveApplicationWithApplicant_ShouldMap() {
        // Arrange
        ApplicantDto applicantDto = ApplicantDto.builder()
            .firstName("first")
            .lastName("last")
            .middleName("middle")
            .suffix(Suffix.IV)
            .email("applicant@email.com")
            .phone("1234567890")
            .recentlyEvicted(true)
            .evictionExplanation("Moved out of state")
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertEquals(applicantDto.getFirstName(), result.getApplicant().getFirstName());
        assertEquals(applicantDto.getLastName(), result.getApplicant().getLastName());
        assertEquals(applicantDto.getMiddleName(), result.getApplicant().getMiddleName());
        assertEquals(applicantDto.getSuffix().value, result.getApplicant().getSuffix());
        assertEquals(applicantDto.getEmail(), result.getApplicant().getEmail());
        assertEquals(applicantDto.getPhone(), result.getApplicant().getPhone());
        assertEquals(applicantDto.isRecentlyEvicted(), result.getApplicant().isRecentlyEvicted());
        assertEquals(applicantDto.getEvictionExplanation(), result.getApplicant().getEvictionExplanation());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithResidentialHistory_ShouldMap() {
        // Arrange
        ResidentialHistoryDto residentialHistory = ResidentialHistoryDto.builder()
            .addressId(1L)
            .currentResidence(true)
            .residedFromMonth(Month.JANUARY)
            .residedFromYear(2000)
            .residedToMonth(Month.FEBRUARY)
            .residedToYear(2010)
            .monthlyRent(BigDecimal.valueOf(12.34))
            .landlordName("Landlord Name")
            .landlordPhone("1234567890")
            .landlordEmail("landord@email.com")
            .leaveReason("It was time.")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .residentialHistories(Collections.singletonList(residentialHistory))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getResidentialHistories());
        assertEquals(residentialHistory.getAddressId(), result.getApplicant().getResidentialHistories().get(0).getAddressId());
        assertEquals(residentialHistory.isCurrentResidence(), result.getApplicant().getResidentialHistories().get(0).isCurrentResidence());
        assertEquals(residentialHistory.getResidedFromMonth().name(), result.getApplicant().getResidentialHistories().get(0).getResidedFromMonth());
        assertEquals(residentialHistory.getResidedFromYear(), result.getApplicant().getResidentialHistories().get(0).getResidedFromYear());
        assertEquals(residentialHistory.getResidedToMonth().name(), result.getApplicant().getResidentialHistories().get(0).getResidedToMonth());
        assertEquals(residentialHistory.getResidedToYear(), result.getApplicant().getResidentialHistories().get(0).getResidedToYear());
        assertEquals(residentialHistory.getMonthlyRent(), result.getApplicant().getResidentialHistories().get(0).getMonthlyRent());
        assertEquals(residentialHistory.getLandlordName(), result.getApplicant().getResidentialHistories().get(0).getLandlordName());
        assertEquals(residentialHistory.getLandlordPhone(), result.getApplicant().getResidentialHistories().get(0).getLandlordPhone());
        assertEquals(residentialHistory.getLandlordEmail(), result.getApplicant().getResidentialHistories().get(0).getLandlordEmail());
        assertEquals(residentialHistory.getLeaveReason(), result.getApplicant().getResidentialHistories().get(0).getLeaveReason());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithPersonalReference_ShouldMap() {
        // Arrange
        PersonalReferenceDto personalReference = PersonalReferenceDto.builder()
            .name("Personal Reference")
            .relationship("System")
            .email("test@email.com")
            .phone("1234567890")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .personalReferences(Collections.singletonList(personalReference))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getPersonalReferences());
        assertEquals(personalReference.getName(), result.getApplicant().getPersonalReferences().get(0).getName());
        assertEquals(personalReference.getRelationship(), result.getApplicant().getPersonalReferences().get(0).getRelationship());
        assertEquals(personalReference.getEmail(), result.getApplicant().getPersonalReferences().get(0).getEmail());
        assertEquals(personalReference.getPhone(), result.getApplicant().getPersonalReferences().get(0).getPhone());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithCoApplicant_ShouldMap() {
        // Arrange
        CoApplicantDto coApplicant = CoApplicantDto.builder()
            .firstName("Co")
            .lastName("Applicant")
            .email("test@email.com")
            .phone("1234567890")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .coApplicants(Collections.singletonList(coApplicant))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getCoApplicants());
        assertEquals(coApplicant.getFirstName(), result.getApplicant().getCoApplicants().get(0).getFirstName());
        assertEquals(coApplicant.getLastName(), result.getApplicant().getCoApplicants().get(0).getLastName());
        assertEquals(coApplicant.getEmail(), result.getApplicant().getCoApplicants().get(0).getEmail());
        assertEquals(coApplicant.getPhone(), result.getApplicant().getCoApplicants().get(0).getPhone());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithOccupant_ShouldMap() {
        // Arrange
        OccupantDto occupant = OccupantDto.builder()
            .firstName("Co")
            .lastName("Applicant")
            .dateOfBirth(LocalDate.of(2000, 8, 20))
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .occupants(Collections.singletonList(occupant))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getOccupants());
        assertEquals(occupant.getFirstName(), result.getApplicant().getOccupants().get(0).getFirstName());
        assertEquals(occupant.getLastName(), result.getApplicant().getOccupants().get(0).getLastName());
        assertEquals(occupant.getDateOfBirth(), result.getApplicant().getOccupants().get(0).getDateOfBirth());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithPet_ShouldMap() {
        // Arrange
        PetDto pet = PetDto.builder()
            .name("Pet")
            .breed("Dog")
            .weight(10)
            .age((byte) 3)
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .pets(Collections.singletonList(pet))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getPets());
        assertEquals(pet.getName(), result.getApplicant().getPets().get(0).getName());
        assertEquals(pet.getBreed(), result.getApplicant().getPets().get(0).getBreed());
        assertEquals(pet.getWeight(), result.getApplicant().getPets().get(0).getWeight());
        assertEquals(pet.getAge(), result.getApplicant().getPets().get(0).getAge());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithIdentification_ShouldMap() {
        // Arrange
        IdentificationDto identification = IdentificationDto.builder()
            .dateOfBirth(LocalDate.of(2020, 3, 12))
            .ssn("SSN")
            .governmentIssuedId("Driver Licence")
            .issuedLocation("State")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .identification(identification)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getApplicant().getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getApplicant().getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getApplicant().getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getApplicant().getIdentification().getIssuedLocation());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithEmergencyContact_ShouldMap() {
        // Arrange
        EmergencyContactDto emergencyContact = EmergencyContactDto.builder()
            .name("Emergency Contact")
            .relationship("Friend")
            .phone("1234567890")
            .email("contact@email.com")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .emergencyContact(emergencyContact)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getEmergencyContact());
        assertEquals(emergencyContact.getName(), result.getApplicant().getEmergencyContact().getName());
        assertEquals(emergencyContact.getRelationship(), result.getApplicant().getEmergencyContact().getRelationship());
        assertEquals(emergencyContact.getPhone(), result.getApplicant().getEmergencyContact().getPhone());
        assertEquals(emergencyContact.getEmail(), result.getApplicant().getEmergencyContact().getEmail());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithVehicle_ShouldMap() {
        // Arrange
        VehicleDto vehicle = VehicleDto.builder()
            .make("Make")
            .model("Model")
            .year(1000)
            .color("Color")
            .licenceNumber("123-ABC")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getVehicles());
        assertEquals(vehicle.getMake(), result.getApplicant().getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getApplicant().getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getApplicant().getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getApplicant().getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getApplicant().getVehicles().get(0).getLicenceNumber());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithEmployer_ShouldMap() {
        // Arrange
        EmployerDto employer = EmployerDto.builder()
            .addressId(1L)
            .name("Employer Name")
            .phone("1234567890")
            .monthlySalary(BigDecimal.valueOf(123.3))
            .positionHeld("Position")
            .yearsWorked((byte) 10)
            .supervisorName("Supervisor Name")
            .supervisorEmail("supervisor@email.com")
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .employer(employer)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getEmployer());
        assertEquals(employer.getAddressId(), result.getApplicant().getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getApplicant().getEmployer().getName());
        assertEquals(employer.getPhone(), result.getApplicant().getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getApplicant().getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getApplicant().getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getApplicant().getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getApplicant().getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getApplicant().getEmployer().getSupervisorEmail());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSourceDto additionalIncome = AdditionalIncomeSourceDto.builder()
            .name("Additional Income")
            .monthlyIncome(BigDecimal.valueOf(567.32))
            .build();
        ApplicantDto applicantDto = ApplicantDto.builder()
            .additionalIncomeSources(Collections.singletonList(additionalIncome))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .applicant(applicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getAdditionalIncomeSources());
        assertEquals(additionalIncome.getName(), result.getApplicant().getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncome.getMonthlyIncome(), result.getApplicant().getAdditionalIncomeSources().get(0).getMonthlyIncome());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        ApplicationDto result = applicationMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAnApplication_ShouldMap() {
        // Arrange
        Application application = new Application();
        application.setId(1L);
        application.setPropertyId(2L);
        application.setManagerId(3L);
        application.setStatus(Status.DENIED.name());
        application.setNote("My Note");

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertEquals(application.getId(), result.getId());
        assertEquals(application.getPropertyId(), result.getPropertyId());
        assertEquals(application.getManagerId(), result.getManagerId());
        assertEquals(application.getStatus(), result.getStatus().name());
        assertEquals(application.getNote(), result.getNote());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithApplicant_ShouldMap() {
        // Arrange
        Applicant applicant = new Applicant();
        applicant.setFirstName("first");
        applicant.setLastName("last");
        applicant.setMiddleName("middle");
        applicant.setSuffix("JR");
        applicant.setEmail("applicant@email.com");
        applicant.setPhone("1234567890");
        applicant.setRecentlyEvicted(true);
        applicant.setEvictionExplanation("Moved out of state");

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertEquals(applicant.getFirstName(), result.getApplicant().getFirstName());
        assertEquals(applicant.getLastName(), result.getApplicant().getLastName());
        assertEquals(applicant.getMiddleName(), result.getApplicant().getMiddleName());
        assertEquals(applicant.getSuffix(), result.getApplicant().getSuffix().toString());
        assertEquals(applicant.getEmail(), result.getApplicant().getEmail());
        assertEquals(applicant.getPhone(), result.getApplicant().getPhone());
        assertEquals(applicant.isRecentlyEvicted(), result.getApplicant().isRecentlyEvicted());
        assertEquals(applicant.getEvictionExplanation(), result.getApplicant().getEvictionExplanation());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithResidentialHistory_ShouldMap() {
        // Arrange
        ResidentialHistory residentialHistory = new ResidentialHistory();
        residentialHistory.setAddressId(1L);
        residentialHistory.setCurrentResidence(true);
        residentialHistory.setResidedFromMonth(Month.JANUARY.name());
        residentialHistory.setResidedFromYear(2010);
        residentialHistory.setResidedToMonth(Month.FEBRUARY.name());
        residentialHistory.setResidedToYear(2015);
        residentialHistory.setMonthlyRent(BigDecimal.valueOf(12.34));
        residentialHistory.setLandlordName("Landlord Name");
        residentialHistory.setLandlordEmail("landlord@email.com");
        residentialHistory.setLeaveReason("It was time.");

        Applicant applicant = new Applicant();
        applicant.setResidentialHistories(Collections.singletonList(residentialHistory));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getResidentialHistories());
        assertEquals(residentialHistory.getAddressId(), result.getApplicant().getResidentialHistories().get(0).getAddressId());
        assertEquals(residentialHistory.isCurrentResidence(), result.getApplicant().getResidentialHistories().get(0).isCurrentResidence());
        assertEquals(residentialHistory.getResidedFromMonth(), result.getApplicant().getResidentialHistories().get(0).getResidedFromMonth().name());
        assertEquals(residentialHistory.getResidedFromYear(), result.getApplicant().getResidentialHistories().get(0).getResidedFromYear());
        assertEquals(residentialHistory.getResidedToMonth(), result.getApplicant().getResidentialHistories().get(0).getResidedToMonth().name());
        assertEquals(residentialHistory.getResidedToYear(), result.getApplicant().getResidentialHistories().get(0).getResidedToYear());
        assertEquals(residentialHistory.getMonthlyRent(), result.getApplicant().getResidentialHistories().get(0).getMonthlyRent());
        assertEquals(residentialHistory.getLandlordName(), result.getApplicant().getResidentialHistories().get(0).getLandlordName());
        assertEquals(residentialHistory.getLandlordPhone(), result.getApplicant().getResidentialHistories().get(0).getLandlordPhone());
        assertEquals(residentialHistory.getLandlordEmail(), result.getApplicant().getResidentialHistories().get(0).getLandlordEmail());
        assertEquals(residentialHistory.getLeaveReason(), result.getApplicant().getResidentialHistories().get(0).getLeaveReason());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithPersonalReference_ShouldMap() {
        // Arrange
        PersonalReference personalReference = new PersonalReference();
        personalReference.setName("Personal Reference");
        personalReference.setRelationship("System");
        personalReference.setEmail("test@email.com");
        personalReference.setPhone("1234567890");

        Applicant applicant = new Applicant();
        applicant.setPersonalReferences(Collections.singletonList(personalReference));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getPersonalReferences());
        assertEquals(personalReference.getName(), result.getApplicant().getPersonalReferences().get(0).getName());
        assertEquals(personalReference.getRelationship(), result.getApplicant().getPersonalReferences().get(0).getRelationship());
        assertEquals(personalReference.getEmail(), result.getApplicant().getPersonalReferences().get(0).getEmail());
        assertEquals(personalReference.getPhone(), result.getApplicant().getPersonalReferences().get(0).getPhone());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCoApplicant_ShouldMap() {
        // Arrange
        CoApplicant coApplicant = new CoApplicant();
        coApplicant.setFirstName("Co");
        coApplicant.setLastName("Applicant");
        coApplicant.setEmail("test@email.com");
        coApplicant.setPhone("1234567890");

        Applicant applicant = new Applicant();
        applicant.setCoApplicants(Collections.singletonList(coApplicant));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getCoApplicants());
        assertEquals(coApplicant.getFirstName(), result.getApplicant().getCoApplicants().get(0).getFirstName());
        assertEquals(coApplicant.getLastName(), result.getApplicant().getCoApplicants().get(0).getLastName());
        assertEquals(coApplicant.getEmail(), result.getApplicant().getCoApplicants().get(0).getEmail());
        assertEquals(coApplicant.getPhone(), result.getApplicant().getCoApplicants().get(0).getPhone());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithOccupant_ShouldMap() {
        // Arrange
        Occupant occupant = new Occupant();
        occupant.setFirstName("First");
        occupant.setLastName("Last");
        occupant.setDateOfBirth(LocalDate.of(2000, 8, 20));

        Applicant applicant = new Applicant();
        applicant.setOccupants(Collections.singletonList(occupant));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getOccupants());
        assertEquals(occupant.getFirstName(), result.getApplicant().getOccupants().get(0).getFirstName());
        assertEquals(occupant.getLastName(), result.getApplicant().getOccupants().get(0).getLastName());
        assertEquals(occupant.getDateOfBirth(), result.getApplicant().getOccupants().get(0).getDateOfBirth());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithPet_ShouldMap() {
        // Arrange
        Pet pet = new Pet();
        pet.setName("Pet");
        pet.setBreed("Dog");
        pet.setWeight(10);
        pet.setAge((byte) 3);

        Applicant applicant = new Applicant();
        applicant.setPets(Collections.singletonList(pet));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getPets());
        assertEquals(pet.getName(), result.getApplicant().getPets().get(0).getName());
        assertEquals(pet.getBreed(), result.getApplicant().getPets().get(0).getBreed());
        assertEquals(pet.getWeight(), result.getApplicant().getPets().get(0).getWeight());
        assertEquals(pet.getAge(), result.getApplicant().getPets().get(0).getAge());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithIdentification_ShouldMap() {
        // Arrange
        Identification identification = new Identification();
        identification.setDateOfBirth(LocalDate.of(2020, 3, 12));
        identification.setSsn("SSN");
        identification.setGovernmentIssuedId("Driver Licence");
        identification.setIssuedLocation("State");

        Applicant applicant = new Applicant();
        applicant.setIdentification(identification);

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getApplicant().getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getApplicant().getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getApplicant().getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getApplicant().getIdentification().getIssuedLocation());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithEmergencyContact_ShouldMap() {
        // Arrange
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setName("Emergency Contact");
        emergencyContact.setRelationship("Friend");
        emergencyContact.setPhone("1234567890");
        emergencyContact.setEmail("contact@email.com");

        Applicant applicant = new Applicant();
        applicant.setEmergencyContact(emergencyContact);

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getEmergencyContact());
        assertEquals(emergencyContact.getName(), result.getApplicant().getEmergencyContact().getName());
        assertEquals(emergencyContact.getRelationship(), result.getApplicant().getEmergencyContact().getRelationship());
        assertEquals(emergencyContact.getPhone(), result.getApplicant().getEmergencyContact().getPhone());
        assertEquals(emergencyContact.getEmail(), result.getApplicant().getEmergencyContact().getEmail());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithVehicle_ShouldMap() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Make");
        vehicle.setModel("Model");
        vehicle.setYear(1000);
        vehicle.setColor("Color");
        vehicle.setLicenceNumber("123-ABC");

        Applicant applicant = new Applicant();
        applicant.setVehicles(Collections.singletonList(vehicle));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getVehicles());
        assertEquals(vehicle.getMake(), result.getApplicant().getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getApplicant().getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getApplicant().getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getApplicant().getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getApplicant().getVehicles().get(0).getLicenceNumber());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithEmployer_ShouldMap() {
        // Arrange
        Employer employer = new Employer();
        employer.setAddressId(1L);
        employer.setName("Employer Name");
        employer.setPhone("1234567890");
        employer.setMonthlySalary(BigDecimal.valueOf(123.3));
        employer.setPositionHeld("Position");
        employer.setYearsWorked((byte) 10);
        employer.setSupervisorName("Supervisor Name");
        employer.setSupervisorEmail("supervisor@email.com");

        Applicant applicant = new Applicant();
        applicant.setEmployer(employer);

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getEmployer());
        assertEquals(employer.getAddressId(), result.getApplicant().getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getApplicant().getEmployer().getName());
        assertEquals(employer.getPhone(), result.getApplicant().getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getApplicant().getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getApplicant().getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getApplicant().getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getApplicant().getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getApplicant().getEmployer().getSupervisorEmail());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSource additionalIncomeSource = new AdditionalIncomeSource();
        additionalIncomeSource.setName("Additional Income");
        additionalIncomeSource.setMonthlyIncome(BigDecimal.valueOf(567.32));

        Applicant applicant = new Applicant();
        applicant.setAdditionalIncomeSources(Collections.singletonList(additionalIncomeSource));

        Application application = new Application();
        application.setApplicant(applicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getApplicant());
        assertNotNull(result.getApplicant().getAdditionalIncomeSources());
        assertEquals(additionalIncomeSource.getName(), result.getApplicant().getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncomeSource.getMonthlyIncome(), result.getApplicant().getAdditionalIncomeSources().get(0).getMonthlyIncome());
    }

    @Test
    public void toDtoList_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        List<ApplicationDto> result = applicationMapper.toDtoList(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDtoList_WhenGivenApplications_ShouldMap() {
        // Arrange
        Application application = new Application();
        application.setId(1L);
        application.setPropertyId(2L);
        application.setManagerId(3L);
        application.setStatus(Status.DENIED.name());
        application.setNote("My Note");

        // Act
        List<ApplicationDto> result = applicationMapper.toDtoList(Collections.singletonList(application));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(application.getId(), result.get(0).getId());
        assertEquals(application.getPropertyId(), result.get(0).getPropertyId());
        assertEquals(application.getManagerId(), result.get(0).getManagerId());
        assertEquals(application.getStatus(), result.get(0).getStatus().name());
        assertEquals(application.getNote(), result.get(0).getNote());
    }
}

