package ro.digitalnation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublisherRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 40) String location,
        @NotBlank @Size(max = 40) String telephone,
        @NotBlank @Email @Size(max = 50) String email) {
}
