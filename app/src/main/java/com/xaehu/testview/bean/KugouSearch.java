package com.xaehu.testview.bean;

import java.util.List;

public class KugouSearch {


    /**
     * status : 1
     * error :
     * data : {"info":[{"filesize":4112080,"bitrate":128,"price":200,"songname_original":"尽头","singername":"赵方婧","filename":"赵方婧 - 尽头","price_320":200,"songname":"尽头","hash":"73f211b375593a4332bb5e4a28602c61","mvhash":"237f68e950df010c12426a2d50dc3b3e","duration":256,"album_name":"尽头"}],"total":442}
     * errcode : 0
     */

    private int status;
    private String error;
    private DataBean data;
    private int errcode;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public static class DataBean {
        /**
         * info : [{"filesize":4112080,"bitrate":128,"price":200,"songname_original":"尽头","singername":"赵方婧","filename":"赵方婧 - 尽头","songname":"尽头","hash":"73f211b375593a4332bb5e4a28602c61","mvhash":"237f68e950df010c12426a2d50dc3b3e","duration":256,"album_name":"尽头"}]
         * total : 442
         */

        private int total;
        private List<InfoBean> info;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * filesize : 4112080
             * price : 200
             * singername : 赵方婧
             * filename : 赵方婧 - 尽头
             * songname : 尽头
             * hash : 73f211b375593a4332bb5e4a28602c61
             * mvhash : 237f68e950df010c12426a2d50dc3b3e
             * duration : 256
             * album_name : 尽头
             */

            private int filesize;
            private int price;
            private String singername;
            private String filename;
            private String songname;
            private String hash;
            private String mvhash;
            private int duration;
            private String album_name;

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getMvhash() {
                return mvhash;
            }

            public void setMvhash(String mvhash) {
                this.mvhash = mvhash;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getAlbum_name() {
                return album_name;
            }

            public void setAlbum_name(String album_name) {
                this.album_name = album_name;
            }

            @Override
            public String toString() {
                return getFilename();
            }
        }
    }

    @Override
    public String toString() {
        return "KugouSearch{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", data=" + data +
                ", errcode=" + errcode +
                '}';
    }
}
