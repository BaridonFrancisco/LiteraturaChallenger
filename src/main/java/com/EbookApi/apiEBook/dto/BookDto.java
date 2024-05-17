package com.EbookApi.apiEBook.dto;

public record BookDto(
        String title,
        Long countdownload,
        String language,
        Boolean copyright,
        String genero,
        AuthorDTO author
) {
}
