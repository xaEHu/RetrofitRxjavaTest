package com.xaehu.testview.bean;

public class KugouDetail {

    /**
     * fileSize : 3198974
     * album_img : http://imge.kugou.com/stdmusic/{size}/20161109/20161109171040932108.jpg
     * url : http://fs.open.kugou.com/b581ce50f62a906c2d87d1ea52e8a20f/5ce2167f/G078/M08/18/17/jg0DAFgi6G-AKqsqADDP_nSW5F4051.mp3
     * time : 1558321463
     * singerName : 李玉刚
     * songName : 刚好遇见你
     * hash : CB7EE97F4CC11C4EA7A1FA4B516A5D97
     * error :
     * imgUrl : http://singerimg.kugou.com/uploadpic/softhead/{size}/20181224/20181224183453372.jpg
     * status : 1
     * singerId : 2018
     * fileName : 李玉刚 - 刚好遇见你
     * errcode : 0
     * timeLength : 200
     */

    private int fileSize;
    private String album_img;
    private String url;
    private long time;
    private String singerName;
    private String songName;
    private String hash;
    private String error;
    private String imgUrl;
    private int status;
    private int singerId;
    private String fileName;
    private int errcode;
    private int timeLength;

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getAlbum_img() {
        return album_img;
    }

    public void setAlbum_img(String album_img) {
        this.album_img = album_img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }
}
