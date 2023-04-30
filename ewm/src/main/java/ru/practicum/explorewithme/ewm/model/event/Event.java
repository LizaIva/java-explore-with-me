package ru.practicum.explorewithme.ewm.model.event;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.practicum.explorewithme.ewm.model.category.Category;
import ru.practicum.explorewithme.ewm.model.compilation.Compilation;
import ru.practicum.explorewithme.ewm.model.request.Request;
import ru.practicum.explorewithme.ewm.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "events")
public class Event {

    @Column(name = "event_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "annotation", nullable = false)
    private String annotation;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created", nullable = false)
    private LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "confirmed_requests", nullable = false)
    private Integer confirmedRequests;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User initiator;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "views", nullable = false)
    private Integer views;

    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;

    @Column(name = "published_on", nullable = false)
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "location_lat")
    private Double locationLat;

    @Column(name = "location_lon")
    private Double locationLon;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "compilation_id")
    )
    private List<Compilation> compilations;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Request> requests;
}
