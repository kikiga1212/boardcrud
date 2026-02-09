package com.example.boardcrud.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardcrudDTO {
    private Integer id;
    private String subject;
    private String content;
}
