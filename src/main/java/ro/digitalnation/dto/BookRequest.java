package ro.digitalnation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRequest(
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 40) String isbn,
        @NotNull Long publisherId) {
}
