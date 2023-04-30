package ru.practicum.explorewithme.ewm.model.compilation;

import lombok.*;
import ru.practicum.explorewithme.ewm.model.event.Event;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "compilations")
public class Compilation {
    @Column(name = "compilation_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pinned")
    private Boolean pinned;

    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
}
