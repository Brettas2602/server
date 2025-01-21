package br.ifgoiano.Projeto.Final.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import br.ifgoiano.Projeto.Final.mapper.DozerMapper;
import br.ifgoiano.Projeto.Final.model.Music;
import br.ifgoiano.Projeto.Final.services.MusicService;
import br.ifgoiano.Projeto.Final.vo.FileVO;
import br.ifgoiano.Projeto.Final.vo.MusicUploadVO;
import br.ifgoiano.Projeto.Final.vo.MusicVO;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api/musics")
public class MusicController {

    private static final String UPLOAD_DIR = "public/uploads";

    @Autowired
    private MusicService musicService;

    @PostConstruct
    public void init() throws IOException {
        Path uploadsDir = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }
    }

    @GetMapping
    public List<MusicVO> findAll() {
        List<Music> musics = musicService.findAll();
        return DozerMapper.parseListObjects(musics, MusicVO.class);
    }
    
    @GetMapping("/{id}")
    public MusicVO findById(@PathVariable Long id) {
        Music music = musicService.findById(id);
        return DozerMapper.parseObject(music, MusicVO.class);
    }

    @GetMapping("/curtidas")
    public List<MusicVO> findByCurtida(){
        List<Music> musics = musicService.findByCurtida(true);
        return DozerMapper.parseListObjects(musics, MusicVO.class);
    }
    
    @RequestMapping("/search")
    public List<MusicVO> findAllByNome(@RequestParam String nome) {
        List<Music> musics = musicService.findAllByNome(nome);
        return DozerMapper.parseListObjects(musics, MusicVO.class);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws IOException {
        Music music = musicService.findById(id);
        if (music == null) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get(UPLOAD_DIR, music.getFileName());
        Resource resource = new UrlResource(filePath.toUri());

        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "aplication/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header("Content-Disposition", "attachment; fileName=\"" + music.getFileName() + "\"")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IOException {
        Music music = musicService.findById(id);
        if (music != null) {
            Path filePath = Paths.get(UPLOAD_DIR, music.getFileName());
            Files.deleteIfExists(filePath);
        }
        musicService.delete(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MusicVO put(@RequestBody MusicVO music) {
        Music musicEntity = DozerMapper.parseObject(music, Music.class);
        return DozerMapper.parseObject(musicService.put(musicEntity), MusicVO.class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MusicVO create(@RequestBody MusicUploadVO music) {
        System.out.println(music);
        try {
            FileVO file = music.getFile();
            // Decode base64 string to byte array 
            String base64Data = file.uri().substring(file.uri().indexOf(",") + 1);

            byte[] fileBytes = Base64.getDecoder().decode(base64Data);

            // Generate a new fileName with UUID
            String newFileName = UUID.randomUUID().toString();

            // Add file extension if provided in the original fileName
            if (file.name() != null && file.name().contains(".")) {
                String fileExtension = file.name().substring(file.name().lastIndexOf("."));
                newFileName += fileExtension;
            }else {
                // Default to .mp3 if no extension
                newFileName += ".mp3";
            }

            // Save file
            Path filePath = Paths.get(UPLOAD_DIR, newFileName);
            Files.write(filePath, fileBytes);

            // Create and save music entity
            Music musicEntity = new Music();

            musicEntity.setNome(music.getName());
            musicEntity.setArtista(music.getArtista());
            musicEntity.setCurtida(music.getCurtida());
            musicEntity.setFileName(newFileName);
            musicEntity.setCreatedAt(new Date());

            MusicVO savedMusic = DozerMapper.parseObject(musicService.create(musicEntity), MusicVO.class);

            return savedMusic;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing upload: " + e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> handleMissingParts(MissingServletRequestPartException e) {
        return ResponseEntity.badRequest().body("Missing required part: " + e.getRequestPartName());
    }
}
