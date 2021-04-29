# download-merge-ts
Download and merge HTTP TS stream video.

This project uses [FFmpeg](https://www.ffmpeg.org/) and [ffmpeg-cli-wrapper](https://github.com/bramp/ffmpeg-cli-wrapper).

A compiled version of this project can be found [here](https://github.com/martialonso/download-merge-ts/releases/tag/v1.0-SNAPSHOT).

## Usage
1. Download and run the jar file
2. Set the `URL` with the `{num}` parameter. For example: 
   If you want to download and merge example.com/video/stream1.ts, example.com/video/stream2.ts, example.com/video/stream3.ts, ...
   Set the URL to example.com/video/stream{num}.ts
3. Set the `start` and `end` number and the `step` quantity
4. Set the output file and click on `Download and merge`
  
