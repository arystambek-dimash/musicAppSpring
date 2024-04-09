package com.musicapp.service;

import com.musicapp.dto.MusicDTO;
import com.musicapp.entity.Artist;
import com.musicapp.entity.Genre;
import com.musicapp.entity.Music;
import com.musicapp.repository.ArtistRepository;
import com.musicapp.repository.GenreRepository;
import com.musicapp.repository.MusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MusicService {
    private final MusicRepository musicRepository;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;

    public Music create(MusicDTO musicDTO) {
        Genre genre = genreRepository.findByGenre(musicDTO.getGenre())
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        Artist artist = artistRepository.findByArtist(musicDTO.getArtist())
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));
                Music music = Music.builder()
                .title(musicDTO.getTitle())
                .artist(artist)
                .cover(musicDTO.getCover())
                .music(musicDTO.getMusic())
                .genre(genre)
                .build();
        musicRepository.save(music);
        return music;
    }
}
