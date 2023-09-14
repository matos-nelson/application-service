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
import org.rent.circle.application.api.dto.ApplicationDto;
import org.rent.circle.application.api.dto.CoApplicantDto;
import org.rent.circle.application.api.dto.EmergencyContactDto;
import org.rent.circle.application.api.dto.EmployerDto;
import org.rent.circle.application.api.dto.IdentificationDto;
import org.rent.circle.application.api.dto.OccupantDto;
import org.rent.circle.application.api.dto.PersonalReferenceDto;
import org.rent.circle.application.api.dto.PetDto;
import org.rent.circle.application.api.dto.PrimaryApplicantDto;
import org.rent.circle.application.api.dto.ResidentialHistoryDto;
import org.rent.circle.application.api.dto.SaveApplicationDto;
import org.rent.circle.application.api.dto.VehicleDto;
import org.rent.circle.application.api.enums.Status;
import org.rent.circle.application.api.enums.Suffix;
import org.rent.circle.application.api.persistence.model.AdditionalIncomeSource;
import org.rent.circle.application.api.persistence.model.Application;
import org.rent.circle.application.api.persistence.model.CoApplicant;
import org.rent.circle.application.api.persistence.model.CoSigner;
import org.rent.circle.application.api.persistence.model.EmergencyContact;
import org.rent.circle.application.api.persistence.model.Employer;
import org.rent.circle.application.api.persistence.model.Identification;
import org.rent.circle.application.api.persistence.model.Occupant;
import org.rent.circle.application.api.persistence.model.PersonalReference;
import org.rent.circle.application.api.persistence.model.Pet;
import org.rent.circle.application.api.persistence.model.PrimaryApplicant;
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
    public void toModel_WhenGivenASaveApplicationWithPrimaryApplicant_ShouldMap() {
        // Arrange
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
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
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertEquals(primaryApplicantDto.getFirstName(), result.getPrimaryApplicant().getFirstName());
        assertEquals(primaryApplicantDto.getLastName(), result.getPrimaryApplicant().getLastName());
        assertEquals(primaryApplicantDto.getMiddleName(), result.getPrimaryApplicant().getMiddleName());
        assertEquals(primaryApplicantDto.getSuffix().value, result.getPrimaryApplicant().getSuffix());
        assertEquals(primaryApplicantDto.getEmail(), result.getPrimaryApplicant().getEmail());
        assertEquals(primaryApplicantDto.getPhone(), result.getPrimaryApplicant().getPhone());
        assertEquals(primaryApplicantDto.isRecentlyEvicted(), result.getPrimaryApplicant().isRecentlyEvicted());
        assertEquals(primaryApplicantDto.getEvictionExplanation(), result.getPrimaryApplicant().getEvictionExplanation());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .residentialHistories(Collections.singletonList(residentialHistory))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getResidentialHistories());
        assertEquals(residentialHistory.getAddressId(), result.getPrimaryApplicant().getResidentialHistories().get(0).getAddressId());
        assertEquals(residentialHistory.isCurrentResidence(), result.getPrimaryApplicant().getResidentialHistories().get(0).isCurrentResidence());
        assertEquals(residentialHistory.getResidedFromMonth().name(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedFromMonth());
        assertEquals(residentialHistory.getResidedFromYear(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedFromYear());
        assertEquals(residentialHistory.getResidedToMonth().name(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedToMonth());
        assertEquals(residentialHistory.getResidedToYear(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedToYear());
        assertEquals(residentialHistory.getMonthlyRent(), result.getPrimaryApplicant().getResidentialHistories().get(0).getMonthlyRent());
        assertEquals(residentialHistory.getLandlordName(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordName());
        assertEquals(residentialHistory.getLandlordPhone(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordPhone());
        assertEquals(residentialHistory.getLandlordEmail(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordEmail());
        assertEquals(residentialHistory.getLeaveReason(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLeaveReason());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .personalReferences(Collections.singletonList(personalReference))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getPersonalReferences());
        assertEquals(personalReference.getName(), result.getPrimaryApplicant().getPersonalReferences().get(0).getName());
        assertEquals(personalReference.getRelationship(), result.getPrimaryApplicant().getPersonalReferences().get(0).getRelationship());
        assertEquals(personalReference.getEmail(), result.getPrimaryApplicant().getPersonalReferences().get(0).getEmail());
        assertEquals(personalReference.getPhone(), result.getPrimaryApplicant().getPersonalReferences().get(0).getPhone());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .coApplicants(Collections.singletonList(coApplicant))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getCoApplicants());
        assertEquals(coApplicant.getFirstName(), result.getPrimaryApplicant().getCoApplicants().get(0).getFirstName());
        assertEquals(coApplicant.getLastName(), result.getPrimaryApplicant().getCoApplicants().get(0).getLastName());
        assertEquals(coApplicant.getEmail(), result.getPrimaryApplicant().getCoApplicants().get(0).getEmail());
        assertEquals(coApplicant.getPhone(), result.getPrimaryApplicant().getCoApplicants().get(0).getPhone());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithOccupant_ShouldMap() {
        // Arrange
        OccupantDto occupant = OccupantDto.builder()
            .firstName("Co")
            .lastName("Applicant")
            .dateOfBirth(LocalDate.of(2000, 8, 20))
            .build();
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .occupants(Collections.singletonList(occupant))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getOccupants());
        assertEquals(occupant.getFirstName(), result.getPrimaryApplicant().getOccupants().get(0).getFirstName());
        assertEquals(occupant.getLastName(), result.getPrimaryApplicant().getOccupants().get(0).getLastName());
        assertEquals(occupant.getDateOfBirth(), result.getPrimaryApplicant().getOccupants().get(0).getDateOfBirth());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .pets(Collections.singletonList(pet))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getPets());
        assertEquals(pet.getName(), result.getPrimaryApplicant().getPets().get(0).getName());
        assertEquals(pet.getBreed(), result.getPrimaryApplicant().getPets().get(0).getBreed());
        assertEquals(pet.getWeight(), result.getPrimaryApplicant().getPets().get(0).getWeight());
        assertEquals(pet.getAge(), result.getPrimaryApplicant().getPets().get(0).getAge());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .identification(identification)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getPrimaryApplicant().getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getPrimaryApplicant().getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getPrimaryApplicant().getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getPrimaryApplicant().getIdentification().getIssuedLocation());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .emergencyContact(emergencyContact)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getEmergencyContact());
        assertEquals(emergencyContact.getName(), result.getPrimaryApplicant().getEmergencyContact().getName());
        assertEquals(emergencyContact.getRelationship(), result.getPrimaryApplicant().getEmergencyContact().getRelationship());
        assertEquals(emergencyContact.getPhone(), result.getPrimaryApplicant().getEmergencyContact().getPhone());
        assertEquals(emergencyContact.getEmail(), result.getPrimaryApplicant().getEmergencyContact().getEmail());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .vehicles(Collections.singletonList(vehicle))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getVehicles());
        assertEquals(vehicle.getMake(), result.getPrimaryApplicant().getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getPrimaryApplicant().getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getPrimaryApplicant().getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getPrimaryApplicant().getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getPrimaryApplicant().getVehicles().get(0).getLicenceNumber());
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
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .employer(employer)
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getEmployer());
        assertEquals(employer.getAddressId(), result.getPrimaryApplicant().getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getPrimaryApplicant().getEmployer().getName());
        assertEquals(employer.getPhone(), result.getPrimaryApplicant().getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getPrimaryApplicant().getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getPrimaryApplicant().getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getPrimaryApplicant().getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getPrimaryApplicant().getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getPrimaryApplicant().getEmployer().getSupervisorEmail());
    }

    @Test
    public void toModel_WhenGivenAnApplicationWithAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSourceDto additionalIncome = AdditionalIncomeSourceDto.builder()
            .name("Additional Income")
            .monthlyIncome(BigDecimal.valueOf(567.32))
            .build();
        PrimaryApplicantDto primaryApplicantDto = PrimaryApplicantDto.builder()
            .additionalIncomeSources(Collections.singletonList(additionalIncome))
            .build();
        SaveApplicationDto saveApplicationDto = SaveApplicationDto.builder()
            .primaryApplicant(primaryApplicantDto)
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getAdditionalIncomeSources());
        assertEquals(additionalIncome.getName(), result.getPrimaryApplicant().getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncome.getMonthlyIncome(), result.getPrimaryApplicant().getAdditionalIncomeSources().get(0).getMonthlyIncome());
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
    public void toDto_WhenGivenAnApplicationWithPrimaryApplicant_ShouldMap() {
        // Arrange
        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setFirstName("first");
        primaryApplicant.setLastName("last");
        primaryApplicant.setMiddleName("middle");
        primaryApplicant.setSuffix("JR");
        primaryApplicant.setEmail("applicant@email.com");
        primaryApplicant.setPhone("1234567890");
        primaryApplicant.setRecentlyEvicted(true);
        primaryApplicant.setEvictionExplanation("Moved out of state");

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertEquals(primaryApplicant.getFirstName(), result.getPrimaryApplicant().getFirstName());
        assertEquals(primaryApplicant.getLastName(), result.getPrimaryApplicant().getLastName());
        assertEquals(primaryApplicant.getMiddleName(), result.getPrimaryApplicant().getMiddleName());
        assertEquals(primaryApplicant.getSuffix(), result.getPrimaryApplicant().getSuffix().toString());
        assertEquals(primaryApplicant.getEmail(), result.getPrimaryApplicant().getEmail());
        assertEquals(primaryApplicant.getPhone(), result.getPrimaryApplicant().getPhone());
        assertEquals(primaryApplicant.isRecentlyEvicted(), result.getPrimaryApplicant().isRecentlyEvicted());
        assertEquals(primaryApplicant.getEvictionExplanation(), result.getPrimaryApplicant().getEvictionExplanation());
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

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setResidentialHistories(Collections.singletonList(residentialHistory));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getResidentialHistories());
        assertEquals(residentialHistory.getAddressId(), result.getPrimaryApplicant().getResidentialHistories().get(0).getAddressId());
        assertEquals(residentialHistory.isCurrentResidence(), result.getPrimaryApplicant().getResidentialHistories().get(0).isCurrentResidence());
        assertEquals(residentialHistory.getResidedFromMonth(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedFromMonth().name());
        assertEquals(residentialHistory.getResidedFromYear(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedFromYear());
        assertEquals(residentialHistory.getResidedToMonth(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedToMonth().name());
        assertEquals(residentialHistory.getResidedToYear(), result.getPrimaryApplicant().getResidentialHistories().get(0).getResidedToYear());
        assertEquals(residentialHistory.getMonthlyRent(), result.getPrimaryApplicant().getResidentialHistories().get(0).getMonthlyRent());
        assertEquals(residentialHistory.getLandlordName(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordName());
        assertEquals(residentialHistory.getLandlordPhone(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordPhone());
        assertEquals(residentialHistory.getLandlordEmail(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLandlordEmail());
        assertEquals(residentialHistory.getLeaveReason(), result.getPrimaryApplicant().getResidentialHistories().get(0).getLeaveReason());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithPersonalReference_ShouldMap() {
        // Arrange
        PersonalReference personalReference = new PersonalReference();
        personalReference.setName("Personal Reference");
        personalReference.setRelationship("System");
        personalReference.setEmail("test@email.com");
        personalReference.setPhone("1234567890");

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setPersonalReferences(Collections.singletonList(personalReference));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getPersonalReferences());
        assertEquals(personalReference.getName(), result.getPrimaryApplicant().getPersonalReferences().get(0).getName());
        assertEquals(personalReference.getRelationship(), result.getPrimaryApplicant().getPersonalReferences().get(0).getRelationship());
        assertEquals(personalReference.getEmail(), result.getPrimaryApplicant().getPersonalReferences().get(0).getEmail());
        assertEquals(personalReference.getPhone(), result.getPrimaryApplicant().getPersonalReferences().get(0).getPhone());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCoApplicant_ShouldMap() {
        // Arrange
        CoApplicant coApplicant = new CoApplicant();
        coApplicant.setFirstName("Co");
        coApplicant.setLastName("Applicant");
        coApplicant.setEmail("test@email.com");
        coApplicant.setPhone("1234567890");

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setCoApplicants(Collections.singletonList(coApplicant));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getCoApplicants());
        assertEquals(coApplicant.getFirstName(), result.getPrimaryApplicant().getCoApplicants().get(0).getFirstName());
        assertEquals(coApplicant.getLastName(), result.getPrimaryApplicant().getCoApplicants().get(0).getLastName());
        assertEquals(coApplicant.getEmail(), result.getPrimaryApplicant().getCoApplicants().get(0).getEmail());
        assertEquals(coApplicant.getPhone(), result.getPrimaryApplicant().getCoApplicants().get(0).getPhone());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithOccupant_ShouldMap() {
        // Arrange
        Occupant occupant = new Occupant();
        occupant.setFirstName("First");
        occupant.setLastName("Last");
        occupant.setDateOfBirth(LocalDate.of(2000, 8, 20));

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setOccupants(Collections.singletonList(occupant));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getOccupants());
        assertEquals(occupant.getFirstName(), result.getPrimaryApplicant().getOccupants().get(0).getFirstName());
        assertEquals(occupant.getLastName(), result.getPrimaryApplicant().getOccupants().get(0).getLastName());
        assertEquals(occupant.getDateOfBirth(), result.getPrimaryApplicant().getOccupants().get(0).getDateOfBirth());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithPet_ShouldMap() {
        // Arrange
        Pet pet = new Pet();
        pet.setName("Pet");
        pet.setBreed("Dog");
        pet.setWeight(10);
        pet.setAge((byte) 3);

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setPets(Collections.singletonList(pet));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getPets());
        assertEquals(pet.getName(), result.getPrimaryApplicant().getPets().get(0).getName());
        assertEquals(pet.getBreed(), result.getPrimaryApplicant().getPets().get(0).getBreed());
        assertEquals(pet.getWeight(), result.getPrimaryApplicant().getPets().get(0).getWeight());
        assertEquals(pet.getAge(), result.getPrimaryApplicant().getPets().get(0).getAge());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithIdentification_ShouldMap() {
        // Arrange
        Identification identification = new Identification();
        identification.setDateOfBirth(LocalDate.of(2020, 3, 12));
        identification.setSsn("SSN");
        identification.setGovernmentIssuedId("Driver Licence");
        identification.setIssuedLocation("State");

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setIdentification(identification);

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getPrimaryApplicant().getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getPrimaryApplicant().getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getPrimaryApplicant().getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getPrimaryApplicant().getIdentification().getIssuedLocation());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithEmergencyContact_ShouldMap() {
        // Arrange
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setName("Emergency Contact");
        emergencyContact.setRelationship("Friend");
        emergencyContact.setPhone("1234567890");
        emergencyContact.setEmail("contact@email.com");

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setEmergencyContact(emergencyContact);

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getEmergencyContact());
        assertEquals(emergencyContact.getName(), result.getPrimaryApplicant().getEmergencyContact().getName());
        assertEquals(emergencyContact.getRelationship(), result.getPrimaryApplicant().getEmergencyContact().getRelationship());
        assertEquals(emergencyContact.getPhone(), result.getPrimaryApplicant().getEmergencyContact().getPhone());
        assertEquals(emergencyContact.getEmail(), result.getPrimaryApplicant().getEmergencyContact().getEmail());
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

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setVehicles(Collections.singletonList(vehicle));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getVehicles());
        assertEquals(vehicle.getMake(), result.getPrimaryApplicant().getVehicles().get(0).getMake());
        assertEquals(vehicle.getModel(), result.getPrimaryApplicant().getVehicles().get(0).getModel());
        assertEquals(vehicle.getYear(), result.getPrimaryApplicant().getVehicles().get(0).getYear());
        assertEquals(vehicle.getColor(), result.getPrimaryApplicant().getVehicles().get(0).getColor());
        assertEquals(vehicle.getLicenceNumber(), result.getPrimaryApplicant().getVehicles().get(0).getLicenceNumber());
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

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setEmployer(employer);

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getEmployer());
        assertEquals(employer.getAddressId(), result.getPrimaryApplicant().getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getPrimaryApplicant().getEmployer().getName());
        assertEquals(employer.getPhone(), result.getPrimaryApplicant().getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getPrimaryApplicant().getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getPrimaryApplicant().getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getPrimaryApplicant().getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getPrimaryApplicant().getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getPrimaryApplicant().getEmployer().getSupervisorEmail());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSource additionalIncomeSource = new AdditionalIncomeSource();
        additionalIncomeSource.setName("Additional Income");
        additionalIncomeSource.setMonthlyIncome(BigDecimal.valueOf(567.32));

        PrimaryApplicant primaryApplicant = new PrimaryApplicant();
        primaryApplicant.setAdditionalIncomeSources(Collections.singletonList(additionalIncomeSource));

        Application application = new Application();
        application.setPrimaryApplicant(primaryApplicant);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getPrimaryApplicant());
        assertNotNull(result.getPrimaryApplicant().getAdditionalIncomeSources());
        assertEquals(additionalIncomeSource.getName(), result.getPrimaryApplicant().getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncomeSource.getMonthlyIncome(), result.getPrimaryApplicant().getAdditionalIncomeSources().get(0).getMonthlyIncome());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCoSigner_ShouldMap() {
        // Arrange
        CoSigner coSigner = new CoSigner();
        coSigner.setAddressId(1L);
        coSigner.setFirstName("first");
        coSigner.setLastName("last");
        coSigner.setMiddleName("middle");
        coSigner.setSuffix("JR");
        coSigner.setEmail("applicant@email.com");
        coSigner.setPhone("1234567890");

        Application application = new Application();
        application.setCoSigner(coSigner);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCoSigner());
        assertEquals(coSigner.getFirstName(), result.getCoSigner().getFirstName());
        assertEquals(coSigner.getLastName(), result.getCoSigner().getLastName());
        assertEquals(coSigner.getMiddleName(), result.getCoSigner().getMiddleName());
        assertEquals(coSigner.getSuffix(), result.getCoSigner().getSuffix().toString());
        assertEquals(coSigner.getEmail(), result.getCoSigner().getEmail());
        assertEquals(coSigner.getPhone(), result.getCoSigner().getPhone());
        assertEquals(coSigner.getAddressId(), result.getCoSigner().getAddressId());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCoSignerAndEmployer_ShouldMap() {
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

        CoSigner coSigner = new CoSigner();
        coSigner.setEmployer(employer);

        Application application = new Application();
        application.setCoSigner(coSigner);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCoSigner());
        assertNotNull(result.getCoSigner().getEmployer());
        assertEquals(employer.getAddressId(), result.getCoSigner().getEmployer().getAddressId());
        assertEquals(employer.getName(), result.getCoSigner().getEmployer().getName());
        assertEquals(employer.getPhone(), result.getCoSigner().getEmployer().getPhone());
        assertEquals(employer.getMonthlySalary(), result.getCoSigner().getEmployer().getMonthlySalary());
        assertEquals(employer.getPositionHeld(), result.getCoSigner().getEmployer().getPositionHeld());
        assertEquals(employer.getYearsWorked(), result.getCoSigner().getEmployer().getYearsWorked());
        assertEquals(employer.getSupervisorName(), result.getCoSigner().getEmployer().getSupervisorName());
        assertEquals(employer.getSupervisorEmail(), result.getCoSigner().getEmployer().getSupervisorEmail());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCoSignerAndAdditionalIncomeSource_ShouldMap() {
        // Arrange
        AdditionalIncomeSource additionalIncomeSource = new AdditionalIncomeSource();
        additionalIncomeSource.setName("Additional Income");
        additionalIncomeSource.setMonthlyIncome(BigDecimal.valueOf(567.32));

        CoSigner coSigner = new CoSigner();
        coSigner.setAdditionalIncomeSources(Collections.singletonList(additionalIncomeSource));

        Application application = new Application();
        application.setCoSigner(coSigner);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCoSigner());
        assertNotNull(result.getCoSigner().getAdditionalIncomeSources());
        assertEquals(additionalIncomeSource.getName(), result.getCoSigner().getAdditionalIncomeSources().get(0).getName());
        assertEquals(additionalIncomeSource.getMonthlyIncome(), result.getCoSigner().getAdditionalIncomeSources().get(0).getMonthlyIncome());
    }

    @Test
    public void toDto_WhenGivenAnApplicationWithCosignerAndIdentification_ShouldMap() {
        // Arrange
        Identification identification = new Identification();
        identification.setDateOfBirth(LocalDate.of(2020, 3, 12));
        identification.setSsn("SSN");
        identification.setGovernmentIssuedId("Driver Licence");
        identification.setIssuedLocation("State");

        CoSigner coSigner = new CoSigner();
        coSigner.setIdentification(identification);

        Application application = new Application();
        application.setCoSigner(coSigner);

        // Act
        ApplicationDto result = applicationMapper.toDto(application);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCoSigner());
        assertNotNull(result.getCoSigner().getIdentification());
        assertEquals(identification.getDateOfBirth(), result.getCoSigner().getIdentification().getDateOfBirth());
        assertEquals(identification.getSsn(), result.getCoSigner().getIdentification().getSsn());
        assertEquals(identification.getGovernmentIssuedId(), result.getCoSigner().getIdentification().getGovernmentIssuedId());
        assertEquals(identification.getIssuedLocation(), result.getCoSigner().getIdentification().getIssuedLocation());
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

