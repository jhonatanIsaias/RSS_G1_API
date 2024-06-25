package br.edu.ifs.rss_g1.notices_g1.service.Exceptions;




import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StandardError implements Serializable {
    private Long timestamp;
    private Integer status;
    private String message;
    private String path;
    private List<String> listErrors = new ArrayList<>();

    public StandardError(Long timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

}
