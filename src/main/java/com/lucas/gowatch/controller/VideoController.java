package com.lucas.gowatch.controller;

import com.lucas.gowatch.controller.mapper.Translator;
import com.lucas.gowatch.controller.model.GenericResponse;
import com.lucas.gowatch.controller.model.RatingRequest;
import com.lucas.gowatch.controller.model.VideoResponse;
import com.lucas.gowatch.entity.Channel;
import com.lucas.gowatch.entity.Rating;
import com.lucas.gowatch.entity.Video;
import com.lucas.gowatch.service.StorageService;
import com.lucas.gowatch.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Component
@RestController
@RequestMapping(path = "/video")
public class VideoController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private CreateVideoUseCase createVideoUseCase;

    @Autowired
    private ConsultAllVideosUseCase consultAllVideoUseCase;

    @Autowired
    private ConsultOneVideoUseCase consultOneVideoUseCase;

    @Autowired
    private RateVideoUseCase rateVideoUseCase;

    @Autowired
    private RemoveRateVideoUseCase removeRateVideoUseCase;

    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<VideoResponse> createVideo(
            @RequestParam("video") MultipartFile videoFile,
            @RequestParam("channel_id") Long channelId,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        if(title.isEmpty()) throw new IllegalArgumentException();

        // Create channel and video entity
        Channel creator = new Channel();
        creator.setId(channelId);

        Video video = new Video();
        video.setChannel(creator);
        video.setTitle(title);
        video.setDescription(description);

        // Manage file
        String fileName = storageService.store(videoFile);
        video.setVideoFile(fileName);

        // Response request
        VideoResponse videoResponse = Translator.translate(createVideoUseCase.execute(video), VideoResponse.class);
        return new ResponseEntity<>(videoResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/stream/{video}")
    public StreamingResponseBody videoResource(@PathVariable("video") String videoName, HttpServletResponse response) throws FileNotFoundException {
        File videoFile = new File("public/videos/" + videoName);
        final InputStream videoFileStream = new FileInputStream(videoFile);
        return (os) -> readAndWrite(videoFileStream, os);
    }


    // Source of this http://shazsterblog.blogspot.com/2016/02/asynchronous-streaming-request.html
    // Used to stream bytes
    private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
        byte[] data = new byte[2048];
        int read;
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
        }
        os.flush();
    }

    @GetMapping()
    public ResponseEntity< List<VideoResponse> > consultAllVideo(){
        List<VideoResponse> videoResponseList = Translator.translate(consultAllVideoUseCase.execute(), VideoResponse.class);
        return new ResponseEntity<>(videoResponseList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VideoResponse> consultOneVideo(@PathVariable("id") Long id){
        VideoResponse videoResponse = Translator.translate(consultOneVideoUseCase.execute(id), VideoResponse.class);
        return new ResponseEntity<>(videoResponse, HttpStatus.FOUND);
    }

    @PostMapping(path = "/rating")
    public ResponseEntity<GenericResponse> rateVideo(@RequestBody RatingRequest ratingRequest){
        String response = rateVideoUseCase.execute(Translator.translate(ratingRequest, Rating.class));
        return new ResponseEntity<>(new GenericResponse(response), HttpStatus.OK);
    }

    @DeleteMapping(path = "/rating")
    public ResponseEntity<GenericResponse> removeRateVideo(@RequestBody RatingRequest ratingRequest){
        String response = removeRateVideoUseCase.execute(Translator.translate(ratingRequest, Rating.class));
        return new ResponseEntity<>(new GenericResponse(response), HttpStatus.OK);
    }

}
