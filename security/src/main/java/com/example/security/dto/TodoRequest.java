package com.example.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

  @NotBlank(message = "Il titolo è obbligatorio")
  private String titolo;

  private String descrizione;
}