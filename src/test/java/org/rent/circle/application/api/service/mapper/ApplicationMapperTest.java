package org.rent.circle.application.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.rent.circle.application.api.dto.AdditionalIncomeSourceDto;
import org.rent.circle.application.api.dto.ApplicantDto;
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
import org.rent.circle.application.api.enums.Suffix;
import org.rent.circle.application.api.persistence.model.Application;

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
            .build();

        // Act
        Application result = applicationMapper.toModel(saveApplicationDto);

        // Assert
        assertNotNull(result);
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
            .residedFromMonth("January")
            .residedFromYear(2000)
            .residedToMonth("February")
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
        assertEquals(residentialHistory.getResidedFromMonth(), result.getApplicant().getResidentialHistories().get(0).getResidedFromMonth());
        assertEquals(residentialHistory.getResidedFromYear(), result.getApplicant().getResidentialHistories().get(0).getResidedFromYear());
        assertEquals(residentialHistory.getResidedToMonth(), result.getApplicant().getResidentialHistories().get(0).getResidedToMonth());
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
}

