package ru.practicum.explorewithme.ewm.model.event;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "locations")
public class Location {
    @Column(name = "location_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location_lat", nullable = false)
    private Long locationLat;

    @Column(name = "location_lon", nullable = false)
    private Long locationLon;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Event> events;
}
