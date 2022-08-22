package com.example.pullpointdev.dto;

import com.example.pullpointdev.entity.Artist;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.deser.ArrayDeserializer;
import org.codehaus.jackson.map.deser.std.CollectionDeserializer;
import org.codehaus.jackson.map.deser.std.StringCollectionDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonNaming
public class CreatePullPointReq{
    String name;

    private double latitude;

    private double longitude;

    private String address;

    private String description;

    private long owner;

    private List<Long> artists;

    private Long category;

    public List<Long> subcategories;

    private String startTime;

    private String endTime;
}
